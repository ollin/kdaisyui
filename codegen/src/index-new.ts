import fs from 'fs'
import path from 'path'
import { getAllComponentDirs, getClassesByCategory } from './parser/frontmatter.ts'
import { generateKotlinFile } from './generator-new.ts'
import { documentedElementSetsFor } from './parser/documented-element.ts'
import { readComponentSet, type SkipReason } from './component-set.ts'
import { observeElements } from './element-observation.ts'
import {
  crossCheckElements,
  describeCrossCheckFailure,
  DocumentedElements,
  type CrossCheckExceptions,
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

/**
 * Runs after the files are written, deliberately: a failure here means the chosen element is
 * wrong, and seeing the output it produced is what makes that diagnosable. The exit code still
 * fails the Gradle task, so nothing ships on it.
 */
function reportCrossCheck(
  observations: readonly ElementObservation[],
  exceptions: CrossCheckExceptions,
  consumed: ConsumedKeyCollector,
): void {
  const crossCheck = crossCheckElements(observations, exceptions)
  consumed.exceptionKeys(crossCheck.consulted)
  for (const key of crossCheck.excused) {
    console.log(`  ⚠ ${key}: element disagrees with DaisyUI, excused — see #${exceptions[key].issue}`)
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

/** What each skip reason reads as on the console, so the run says why a component is absent. */
const SKIP_MESSAGES: Readonly<Record<SkipReason, string>> = {
  'configured-skip': 'Skipped (alias)',
  'no-frontmatter': 'No frontmatter found',
  'no-component-class': 'No component class defined',
}

function main() {
  console.log('Generating kdaisyui components from DaisyUI source...\n')

  const config = loadConfig()
  const componentDirs = getAllComponentDirs()

  console.log(`Found ${componentDirs.length} components in DaisyUI docs\n`)

  // Which components exist and what each one's API is — read ONCE, here, by the module that
  // owns that question. This run used to decide it a second time with its own copy of the
  // classification loop, and a second copy is how the reference pages and the Kotlin come to
  // describe different sets. It is also what the join scope needs: its members are other
  // components' functions, so a loop that knows one component at a time cannot build it.
  const componentSet = readComponentSet(config)
  const byDirectory = new Map(componentSet.generated.map(component => [component.componentDir, component]))
  const skipReasons = new Map(componentSet.skipped.map(component => [component.componentDir, component.reason]))

  const allClasses = []
  const observations: ElementObservation[] = []
  // Every identifier a config lookup could legitimately have matched this run. A section key
  // that matches none of these was never read, and an unread key is indistinguishable from an
  // absent one at run time — which is how two dead `noContent` entries survived.
  const consumed = new ConsumedKeyCollector()

  // DaisyUI's own directory order, so the log reads down the same list a reader sees on disk
  // and a skipped component keeps its place among the generated ones.
  for (const componentName of componentDirs) {
    // Recorded before the skip check: `skip` itself is a config section, and an entry naming
    // a component that no longer exists must still be caught.
    consumed.directory(componentName)

    const skipReason = skipReasons.get(componentName)
    if (skipReason !== undefined) {
      console.log(`  ${skipReason === 'configured-skip' ? '⊘' : '⚠'} ${componentName}: ${SKIP_MESSAGES[skipReason]}`)
      continue
    }

    const component = byDirectory.get(componentName)
    if (component === undefined) continue
    const { classified, shape, source, groups, frontmatter } = component
    consumed.component(classified.componentName, classified.parts)

    // Every class each function emits, beside EVERY element DaisyUI documents it on. Judged
    // after the loop so the whole set is reportable at once — dying on the first would hide
    // the rest.
    //
    // The sets, not the tallies' usual element: which of several DaisyUI picks is the
    // surrounding context, and the generator renders one function for all of them. Holding it
    // to the commonest would call six of `badge`'s seven documented `<span>`s a defect.
    const documentedSets = documentedElementSetsFor(componentName)
    observations.push(...observeElements(
      shape,
      new Map([...documentedSets].map(([cls, elements]) => [cls, DocumentedElements.of(elements)])),
    ))

    const kotlin = generateKotlinFile(classified, source, config, groups)
    const outFile = path.join(OUTPUT_DIR, `${classified.componentName}.kt`)

    fs.mkdirSync(OUTPUT_DIR, { recursive: true })
    fs.writeFileSync(outFile, kotlin)
    // Only generated components contribute: a skipped one emits nothing, so listing its
    // classes would put CSS in a consumer's bundle that this library can never produce.
    allClasses.push(...collectClasses(frontmatter.classnames))
    console.log(`  ✓ ${classified.componentName}.kt (${shape.functions[0].element})`)
  }

  const classCount = writeClassList(allClasses)

  console.log(`\nGenerated ${componentSet.generated.length} components, skipped ${componentSet.skipped.length}`)
  console.log(`Output: ${OUTPUT_DIR}`)
  console.log(`Class list: ${CLASS_LIST_FILE} (${classCount} classes)`)

  reportCrossCheck(observations, config.elementCrossCheckExceptions ?? {}, consumed)
  reportUnreadConfig(config, consumed.keys())
}

main()
