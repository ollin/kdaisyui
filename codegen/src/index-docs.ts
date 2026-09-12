import fs from 'fs'
import path from 'path'
import {
  getAllComponentDirs,
  readComponentFrontmatter,
  type ComponentName,
} from './parser/frontmatter.ts'
import { parseLlmsTxt, getElementForComponent } from './parser/llms-txt.ts'
import { classifyFromFrontmatter } from './classifier.ts'
import { buildComponentShape, type ComponentShape } from './component-shape.ts'
import {
  docFileNameFor,
  generateComponentPage,
  generateIndexPage,
  type DocSummary,
} from './generator-docs.ts'

// Committed prose tree, not build output — the same argument as `lib/generated/`: a reader of a
// fresh clone gets the reference documentation without running anything.
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../docs/reference')
const CONFIG_PATH = path.resolve(import.meta.dirname, '../codegen-config.json')

function parseArg(flag: string, fallback: string): string {
  for (const arg of process.argv) {
    if (arg.startsWith(`${flag}=`)) return arg.slice(flag.length + 1)
  }
  return fallback
}

const OUTPUT_DIR = parseArg('--output-dir', DEFAULT_OUTPUT_DIR)

function loadConfig() {
  if (!fs.existsSync(CONFIG_PATH)) return {}
  return JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf8'))
}

/**
 * The shape of a component that gets a page, or null for one that does not.
 *
 * The two skip conditions are exactly `index-new.ts`'s, and must stay so: a page has to exist for
 * precisely the components that have a generated Kotlin file, or the index links at nothing.
 */
function shapeFor(componentName: string, config, elementRules): ComponentShape | null {
  if (config.skip?.includes(componentName)) return null

  const frontmatter = readComponentFrontmatter(componentName as ComponentName)
  if (!frontmatter?.classnames?.component?.length) return null

  const classified = classifyFromFrontmatter(frontmatter, componentName as ComponentName)
  const element = config.componentElements?.[componentName] ?? getElementForComponent(elementRules, componentName)
  return buildComponentShape(classified, { componentDir: componentName, element }, config)
}

interface Written {
  readonly shapes: ComponentShape[]
  /** File names written this run, so the stale ones can be told apart. */
  readonly fileNames: Set<string>
  readonly skipped: number
  /** Components that fell back to DaisyUI's own description. */
  readonly withoutSummary: string[]
}

function writeComponentPages(config, docSummaries: Readonly<Record<string, DocSummary>>): Written {
  const elementRules = parseLlmsTxt()
  const componentDirs = getAllComponentDirs()

  const documented = componentDirs
    .map(componentDir => ({ componentDir, shape: shapeFor(componentDir, config, elementRules) }))
    .filter((entry): entry is { componentDir: string; shape: ComponentShape } => entry.shape !== null)

  const fileNames = new Set<string>()
  const withoutSummary: string[] = []

  for (const { componentDir, shape } of documented) {
    const summary = docSummaries[componentDir]
    if (!summary) withoutSummary.push(componentDir)

    const fileName = `${docFileNameFor(componentDir)}.md`
    fs.writeFileSync(path.join(OUTPUT_DIR, fileName), generateComponentPage(shape, summary))
    fileNames.add(fileName)
    console.log(`  ✓ ${fileName} (${shape.functions.length} functions)`)
  }

  return {
    shapes: documented.map(entry => entry.shape),
    fileNames,
    skipped: componentDirs.length - documented.length,
    withoutSummary,
  }
}

/**
 * Deletes pages for components that no longer exist.
 *
 * Without this, a component DaisyUI removes leaves its page behind documenting a function the
 * library no longer has — and the drift job could not catch it, because regeneration would never
 * touch the file.
 */
function removeStalePages(kept: ReadonlySet<string>): string[] {
  const removed = fs
    .readdirSync(OUTPUT_DIR)
    .filter(file => file.endsWith('.md') && file !== 'index.md' && !kept.has(file))
  for (const file of removed) fs.rmSync(path.join(OUTPUT_DIR, file))
  return removed
}

/**
 * A missing summary is a warning, not a failure. A DaisyUI release that adds a component must not
 * break regeneration; the page is generated from DaisyUI's own description instead, and it shows
 * up both here and in the regeneration diff, which is where someone notices it.
 */
function reportMissingSummaries(components: readonly string[]): void {
  if (components.length === 0) return
  console.log(
    `\n⚠ No docSummaries entry for: ${components.join(', ')}` +
      `\n  Each fell back to the first sentence of its DaisyUI description.` +
      `\n  Add a summary in codegen/codegen-config.json → docSummaries.`,
  )
}

function main() {
  console.log('Generating kdaisyui component reference from DaisyUI source...\n')

  const config = loadConfig()
  const docSummaries: Record<string, DocSummary> = config.docSummaries ?? {}

  fs.mkdirSync(OUTPUT_DIR, { recursive: true })
  const written = writeComponentPages(config, docSummaries)
  fs.writeFileSync(path.join(OUTPUT_DIR, 'index.md'), generateIndexPage(written.shapes, docSummaries))
  const removed = removeStalePages(written.fileNames)

  console.log(`\nGenerated ${written.shapes.length} pages plus index.md, skipped ${written.skipped}`)
  console.log(`Output: ${OUTPUT_DIR}`)
  if (removed.length > 0) console.log(`Removed ${removed.length} stale page(s): ${removed.join(', ')}`)
  reportMissingSummaries(written.withoutSummary)
}

main()
