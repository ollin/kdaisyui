import fs from 'fs'
import path from 'path'
import { everyDeclaredClass, getAllComponentDirs, getClassesByCategory, readComponentPage, type ComponentName } from './parser/frontmatter.ts'
import { fencedHtmlBlocks } from './parser/documented-element.ts'
import { gapFindings, gapsIn, pageFirst, tagFindings } from './documented-elements.ts'
import { generateKotlinFile } from './generator-new.ts'
import { readComponentSet, type SkipReason } from './component-set.ts'
import { classFindings, directoryFindings, namingFindings, offeredClasses } from './model-completeness.ts'
import { mainFunctionConfig, modelFileNames, readModel, type ComponentModel } from './model.ts'

// Committed generated root — a sibling of lib/src/, never inside it.
// Gradle passes --output-dir explicitly; this default is for a bare `node` run.
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../lib/generated/main/kotlin/io/github/ollin/kdaisyui/components')

// Shipped inside the published jar. A consumer's Tailwind cannot find these class names
// any other way: they are assembled at runtime from enum values, so they appear in no
// file the scanner reads, and the jar carries no Kotlin sources.
const DEFAULT_CLASS_LIST = path.resolve(import.meta.dirname, '../../lib/generated/main/resources/kdaisyui-classes.txt')

function parseArg(flag: string, fallback: string): string {
  for (const arg of process.argv) {
    if (arg.startsWith(`${flag}=`)) return arg.slice(flag.length + 1)
  }
  return fallback
}

const OUTPUT_DIR = parseArg('--output-dir', DEFAULT_OUTPUT_DIR)
const CLASS_LIST_FILE = parseArg('--class-list', DEFAULT_CLASS_LIST)

/** Every class name a component can put on the page, whatever category DaisyUI filed it under. */
function collectClasses(classnames) {
  return Object.keys(classnames ?? {}).flatMap(category => getClassesByCategory(classnames, category))
}

/** The page's documented elements no function expresses and its model does not declare, and stale declarations. */
function documentedGapFindings(page: Pick<ComponentModel, 'component' | 'notExpressed'>, models: readonly ComponentModel[], componentClasses: ReadonlySet<string>): string[] {
  const html = fencedHtmlBlocks(readComponentPage(page.component as ComponentName) ?? '')
  return [...gapFindings(page, gapsIn(html, pageFirst(models, page.component), componentClasses)), ...tagFindings(page.component, html)]
}

function writeClassList(classes) {
  const sorted = [...new Set(classes)].sort()
  const header = [
    '# Every DaisyUI class kdaisyui can emit. Generated - do not edit.',
    '#',
    '# Point your Tailwind @source at this file. Scanning your own sources is not enough:',
    '# a class like btn-primary is assembled at runtime from ButtonColor.Primary, so it',
    '# appears nowhere in your code, and the published jar carries no Kotlin sources.',
    '',
  ]
  fs.mkdirSync(path.dirname(CLASS_LIST_FILE), { recursive: true })
  fs.writeFileSync(CLASS_LIST_FILE, `${header.join('\n')}${sorted.join('\n')}\n`)
  return sorted.length
}

/**
 * Fails the run when a component's model and the classes DaisyUI declares for it disagree.
 * Runs after generation like the checks above, so the output a wrong model produced is on disk.
 */
function reportIncompleteModels(findings: readonly string[]): void {
  if (findings.length === 0) return
  console.error(`\nThe model disagrees with DaisyUI's classes, its documented elements or the naming rule:\n${findings.map(finding => `  ${finding}`).join('\n')}`)
  process.exitCode = 1
}

/** What each skip reason reads as on the console, so the run says why a component is absent. */
const SKIP_MESSAGES: Readonly<Record<SkipReason, string>> = {
  'configured-skip': 'Skipped (alias)',
  'no-frontmatter': 'No frontmatter found',
  'no-component-class': 'No component class defined',
}

function main() {
  console.log('Generating kdaisyui components from DaisyUI source...\n')

  const componentDirs = getAllComponentDirs()

  console.log(`Found ${componentDirs.length} components in DaisyUI docs\n`)
  const unmatched = directoryFindings(componentDirs, modelFileNames())
  if (unmatched.length > 0) {
    console.error(`Every DaisyUI directory needs a model file in codegen/model/:\n${unmatched.map(finding => `  ${finding}`).join('\n')}`)
    process.exit(1)
  }

  // Which components exist and what each one's API is — read ONCE, here, by the module that
  // owns that question. This run used to decide it a second time with its own copy of the
  // classification loop, and a second copy is how the reference pages and the Kotlin come to
  // describe different sets. It is also what the join scope needs: its members are other
  // components' functions, so a loop that knows one component at a time cannot build it.
  const componentSet = readComponentSet()
  const byDirectory = new Map(componentSet.generated.map(component => [component.componentDir, component]))
  const skipReasons = new Map(componentSet.skipped.map(component => [component.componentDir, component.reason]))

  const allClasses = []
  const modelFindings: string[] = []
  const models = componentSet.generated.map(component => component.model)
  const componentClasses = everyDeclaredClass(componentDirs)

  // DaisyUI's own directory order, so the log reads down the same list a reader sees on disk
  // and a skipped component keeps its place among the generated ones.
  for (const componentName of componentDirs) {
    const skipReason = skipReasons.get(componentName)
    if (skipReason !== undefined) {
      console.log(`  ${skipReason === 'configured-skip' ? '⊘' : '⚠'} ${componentName}: ${SKIP_MESSAGES[skipReason]}`)
      // Skipped or not, the page's examples are DaisyUI's, and the gate covers them.
      modelFindings.push(...documentedGapFindings(readModel(componentName), models, componentClasses))
      continue
    }

    const component = byDirectory.get(componentName)
    if (component === undefined) continue
    const { classified, shape, frontmatter } = component

    const kotlin = generateKotlinFile(shape, mainFunctionConfig(component.model))
    const outFile = path.join(OUTPUT_DIR, `${classified.componentName}.kt`)

    fs.mkdirSync(OUTPUT_DIR, { recursive: true })
    fs.writeFileSync(outFile, kotlin)
    // Only generated components contribute: a skipped one emits nothing, so listing its
    // classes would put CSS in a consumer's bundle that this library can never produce.
    allClasses.push(...offeredClasses(component.model))
    modelFindings.push(...classFindings(component.model, collectClasses(frontmatter.classnames)), ...namingFindings(component.model), ...documentedGapFindings(component.model, models, componentClasses))
    console.log(`  ✓ ${classified.componentName}.kt (${shape.functions[0].element})`)
  }

  const classCount = writeClassList(allClasses)

  console.log(`\nGenerated ${componentSet.generated.length} components, skipped ${componentSet.skipped.length}`)
  console.log(`Output: ${OUTPUT_DIR}`)
  console.log(`Class list: ${CLASS_LIST_FILE} (${classCount} classes)`)

  reportIncompleteModels(modelFindings)
}

main()
