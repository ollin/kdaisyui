/**
 * The test generator's entry point: reads the arguments, the DaisyUI pages and the generated
 * component sources, and writes the documented-element and coverage tests. Everything it writes
 * is computed by pure functions in `test-generator.ts`; reading and writing live here, in an
 * `index-*.ts` the mutation gate leaves out.
 *
 *   node src/index-test-generator.ts <component|all> [--output-dir=…] [--components-dir=…]
 */
import fs from 'fs'
import path from 'path'
import { pathToFileURL } from 'node:url'
import { getAllComponentDirs, parseFrontmatter, readComponentPage, type ComponentName } from './parser/frontmatter.ts'
import { isSkipped, readModel, type ComponentModel } from './model.ts'
import { documentedContext, documentedTestFor, toClassName, writeCoverageTest, type DocumentedContext } from './test-generator.ts'
import { readComponentSet } from './component-set.ts'

// Committed generated root — a sibling of lib/src/, never inside it.
// Gradle passes --output-dir explicitly; this default is for a bare `node` run.
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../lib/generated/test/kotlin/io/github/ollin/kdaisyui/components')

function parseArg(flag, fallback) {
  const prefix = `--${flag}=`
  for (const arg of process.argv) {
    if (arg.startsWith(prefix)) {
      return arg.slice(prefix.length)
    }
  }
  return fallback
}

const OUTPUT_DIR = parseArg('output-dir', DEFAULT_OUTPUT_DIR)


// Where the generated component sources are READ BACK from, to drive every branch
// of every function. Gradle passes --components-dir so lib/build.gradle.kts stays
// the single source of that path; the default only serves a bare `node` run.
//
// This used to be a second hardcoded copy of the path, and when the real one moved
// it silently produced no coverage tests at all — see generateAllCoverage below.
const GENERATED_MAIN_DIR = parseArg(
  'components-dir',
  path.resolve(import.meta.dirname, '../../lib/generated/main/kotlin/io/github/ollin/kdaisyui/components'),
)

function generateForSkippedPage(componentName, context: DocumentedContext): number {
  const { kotlin, testCount } = documentedTestFor(componentName, readComponentPage(componentName as ComponentName) ?? '', null, context)!
  fs.writeFileSync(path.join(OUTPUT_DIR, `${toClassName(componentName)}Test.kt`), kotlin)
  return testCount
}

function generateForComponent(componentName, componentModel: ComponentModel, context: DocumentedContext) {
  const page = readComponentPage(componentName as ComponentName)
  if (page === null) return { success: false, error: 'File not found' }
  if (!parseFrontmatter(page)) return { success: false, error: 'No frontmatter' }

  const result = documentedTestFor(componentName, page, componentModel, context)
  if (result === null) return { success: false, error: 'No test cases' }
  fs.writeFileSync(path.join(OUTPUT_DIR, `${toClassName(componentName)}Test.kt`), result.kotlin)
  return { success: true, testCount: result.testCount }
}

function generateAllCoverage() {
  // FAIL, never skip. This used to warn and return, so a wrong path produced zero
  // coverage tests while the build stayed green and the other generators kept the
  // test count looking plausible. That cost 8% line and 9% branch coverage without
  // turning anything red. A missing input is a broken build, not a warning.
  if (!fs.existsSync(GENERATED_MAIN_DIR)) {
    throw new Error(
      `Generated component sources not found at ${GENERATED_MAIN_DIR}. ` +
      `Coverage tests are read back from them, so this cannot be skipped. ` +
      `Pass --components-dir=<path>, or check generatedMainDir in lib/build.gradle.kts.`
    )
  }
  const files = fs.readdirSync(GENERATED_MAIN_DIR).filter((f) => f.endsWith('.kt')).sort()
  if (files.length === 0) {
    throw new Error(`No .kt files in ${GENERATED_MAIN_DIR}; generateComponents must run first.`)
  }
  const shapes = new Map(readComponentSet().generated.map(component => [component.classified.componentName as string, component.shape]))
  let generated = 0
  for (const file of files) {
    const result = writeCoverageTest(GENERATED_MAIN_DIR, OUTPUT_DIR, file, shapes.get(path.basename(file, '.kt'))!)
    if (result.success) generated++
    else console.log(`  ⊘ coverage ${file}: ${result.error}`)
  }
  console.log(`Generated coverage tests for ${generated} component files`)
}

/**
 * Generate one component and print its progress line.
 * @returns whether it produced tests — the caller only needs the tally.
 */
function generateAndReport(componentName, context: DocumentedContext) {
  const model = readModel(componentName)
  if (isSkipped(model)) {
    console.log(`  ⊘ ${componentName}: Skipped (alias), ${generateForSkippedPage(componentName, context)} documented-element tests`)
    return false
  }
  const result = generateForComponent(componentName, model, context)
  console.log(
    result.success
      ? `  ✓ ${componentName}: ${result.testCount} tests`
      : `  ⊘ ${componentName}: ${result.error}`,
  )
  return result.success
}

function generateAllComponents() {
  console.log('Generating tests for all components...\n')

  let generated = 0
  let skipped = 0
  const context = documentedContext()
  for (const componentName of getAllComponentDirs()) {
    if (generateAndReport(componentName, context)) generated++
    else skipped++
  }

  console.log(`\nGenerated tests for ${generated} components, skipped ${skipped}`)
  generateAllCoverage()
}

/** Single-component mode. Unlike the bulk mode, a failure here is fatal: it was asked for. */
function generateSingleComponent(componentName) {
  const model = readModel(componentName)
  if (isSkipped(model)) {
    console.error(`Error: ${componentName} is skipped (alias)`)
    process.exit(1)
  }
  const result = generateForComponent(componentName, model, documentedContext())
  if (!result.success) {
    console.error(`Error: ${result.error}`)
    process.exit(1)
  }
  console.log(`Generated ${result.testCount} tests for ${componentName}`)
}

function printUsageAndExit() {
  console.log('Usage: node index-test-generator.ts <component-name|all>')
  console.log('Examples:')
  console.log('  node index-test-generator.ts dropdown')
  console.log('  node index-test-generator.ts all')
  process.exit(1)
}

function main() {
  const [mode] = process.argv.slice(2)

  if (mode === 'all') return generateAllComponents()
  if (mode) return generateSingleComponent(mode)
  printUsageAndExit()
}

main()
