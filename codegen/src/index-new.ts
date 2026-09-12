import fs from 'fs'
import path from 'path'
import { getAllComponentDirs, readComponentFrontmatter, getClassesByCategory } from './parser/frontmatter.ts'
import { parseLlmsTxt, getElementForComponent } from './parser/llms-txt.ts'
import { classifyFromFrontmatter } from './classifier.ts'
import { generateKotlinFile } from './generator-new.ts'
import { documentedElementFor } from './parser/documented-element.ts'
import {
  crossCheckElements,
  describeCrossCheckFailure,
  type ElementObservation,
} from './element-cross-check.ts'
import {
  ConsumedKeyCollector,
  describeUnreadEntries,
  findUnreadEntries,
  type ConsumedKeys,
} from './config-consumption.ts'

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

function writeClassList(classes) {
  const sorted = [...new Set(classes)].sort()
  const header = [
    '# Every DaisyUI class kdaisyui can emit. Generated - do not edit.',
    '#',
    '# Point your Tailwind @source at this file. Scanning your own sources is not enough:',
    '# a class like btn-primary is assembled at runtime from ButtonVariant.Primary, so it',
    '# appears nowhere in your code, and the published jar carries no Kotlin sources.',
    '',
  ]
  fs.mkdirSync(path.dirname(CLASS_LIST_FILE), { recursive: true })
  fs.writeFileSync(CLASS_LIST_FILE, `${header.join('\n')}${sorted.join('\n')}\n`)
  return sorted.length
}
const CONFIG_PATH = path.resolve(import.meta.dirname, '../codegen-config.json')

function loadConfig() {
  if (!fs.existsSync(CONFIG_PATH)) {
    return { extras: {}, textParams: [], roles: {}, inputTypes: {} }
  }
  return JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf8'))
}

/** What the element heuristic chose for one component, beside what DaisyUI documents. */
function observeElement(componentName, element, frontmatter): ElementObservation {
  return {
    componentDir: componentName,
    chosen: (element ?? 'DIV').toUpperCase(),
    documented: documentedElementFor(componentName, frontmatter.classnames.component[0].class),
  }
}

/**
 * Runs after the files are written, deliberately: a failure here means the chosen element is
 * wrong, and seeing the output it produced is what makes that diagnosable. The exit code still
 * fails the Gradle task, so nothing ships on it.
 */
function reportCrossCheck(
  observations: readonly ElementObservation[],
  exceptions: Readonly<Record<string, { reason: string; issue: number }>>,
): void {
  const crossCheck = crossCheckElements(observations, exceptions)
  for (const componentDir of crossCheck.excused) {
    console.log(`  ⚠ ${componentDir}: element disagrees with DaisyUI, excused — see #${exceptions[componentDir].issue}`)
  }
  if (crossCheck.findings.length === 0) return
  console.error(`\n${describeCrossCheckFailure(crossCheck)}`)
  process.exitCode = 1
}

/**
 * Fails the run on any component-keyed config entry nothing read.
 *
 * Runs after generation for the same reason the cross-check does: the output a wrong config
 * produced is what makes it diagnosable. The exit code still fails the Gradle task.
 */
function reportUnreadConfig(config, consumed: ConsumedKeys): void {
  const unread = findUnreadEntries(config, consumed)
  if (unread.length === 0) return
  console.error(`\n${describeUnreadEntries(unread)}`)
  process.exitCode = 1
}

function main() {
  console.log('Generating kdaisyui components from DaisyUI source...\n')
  
  const config = loadConfig()
  const elementRules = parseLlmsTxt()
  const componentDirs = getAllComponentDirs()
  
  console.log(`Found ${componentDirs.length} components in DaisyUI docs\n`)
  
  let generated = 0
  let skipped = 0
  const allClasses = []
  const observations: ElementObservation[] = []
  // Every identifier a config lookup could legitimately have matched this run. A section key
  // that matches none of these was never read, and an unread key is indistinguishable from an
  // absent one at run time — which is how two dead `noContent` entries survived.
  const consumed = new ConsumedKeyCollector()

  for (const componentName of componentDirs) {
    // Recorded before the skip checks: `skip` itself is a config section, and an entry naming
    // a component that no longer exists must still be caught.
    consumed.directory(componentName)

    if (config.skip?.includes(componentName)) {
      console.log(`  ⊘ ${componentName}: Skipped (alias)`)
      skipped++
      continue
    }
    
    const frontmatter = readComponentFrontmatter(componentName)
    if (!frontmatter) {
      console.log(`  ⚠ ${componentName}: No frontmatter found`)
      skipped++
      continue
    }
    
    if (!frontmatter.classnames?.component?.length) {
      console.log(`  ⚠ ${componentName}: No component class defined`)
      skipped++
      continue
    }
    
    const classified = classifyFromFrontmatter(frontmatter, componentName)
    consumed.component(classified.componentName, classified.parts)
    // The element heuristic takes the first variant in DaisyUI's Syntax block. When that
    // variant only works with attributes this generator cannot emit, the result compiles
    // but does not function — see componentElements in codegen-config.json.
    const element = config.componentElements?.[componentName]
      ?? getElementForComponent(elementRules, componentName)

    // What the heuristic chose, beside what DaisyUI documents. Judged after the loop so the
    // whole set is reportable at once — dying on the first would hide the rest.
    observations.push(observeElement(componentName, element, frontmatter))

    const kotlin = generateKotlinFile(classified, { componentDir: componentName, element }, config)
    const outFile = path.join(OUTPUT_DIR, `${classified.componentName}.kt`)
    
    fs.mkdirSync(OUTPUT_DIR, { recursive: true })
    fs.writeFileSync(outFile, kotlin)
    // Only generated components contribute: a skipped one emits nothing, so listing its
    // classes would put CSS in a consumer's bundle that this library can never produce.
    allClasses.push(...collectClasses(frontmatter.classnames))
    console.log(`  ✓ ${classified.componentName}.kt (${element})`)
    generated++
  }
  
  const classCount = writeClassList(allClasses)
  
  console.log(`\nGenerated ${generated} components, skipped ${skipped}`)
  console.log(`Output: ${OUTPUT_DIR}`)
  console.log(`Class list: ${CLASS_LIST_FILE} (${classCount} classes)`)

  reportCrossCheck(observations, config.elementCrossCheckExceptions ?? {})
  reportUnreadConfig(config, consumed.keys())
}

main()
