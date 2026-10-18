import fs from 'fs'
import path from 'path'
import { readComponentSet, type GeneratedComponent } from './component-set.ts'
import type { ComponentShape } from './component-shape.ts'
import {
  docFileNameFor,
  generateComponentPage,
  generateIndexPage,
  type DocSummary,
} from './generator-docs.ts'
import { generateLlmsTxt } from './generator-llms.ts'

// Committed prose tree, not build output — the same argument as `lib/generated/`: a reader of a
// fresh clone gets the reference documentation without running anything.
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../docs/reference')

function parseArg(flag: string, fallback: string): string {
  for (const arg of process.argv) {
    if (arg.startsWith(`${flag}=`)) return arg.slice(flag.length + 1)
  }
  return fallback
}

const OUTPUT_DIR = parseArg('--output-dir', DEFAULT_OUTPUT_DIR)
const LLMS_TXT = parseArg('--llms-txt', path.resolve(import.meta.dirname, '../../llms.txt'))
const LLMS_TEMPLATE = path.resolve(import.meta.dirname, '../llms-template.md')

interface Written {
  readonly shapes: ComponentShape[]
  /** File names written this run, so the stale ones can be told apart. */
  readonly fileNames: Set<string>
  readonly skipped: number
  /** Components that fell back to DaisyUI's own description. */
  readonly withoutSummary: string[]
}

/** Each component's hand-written summary, from its model file, by DaisyUI directory. */
function summariesOf(components: readonly GeneratedComponent[]): Record<string, DocSummary> {
  return Object.fromEntries(components.flatMap(({ componentDir, model }) => (model.summary === undefined ? [] : [[componentDir, model.summary]])))
}

function writeComponentPages(
  documented: readonly GeneratedComponent[],
  skipped: number,
  docSummaries: Readonly<Record<string, DocSummary>>,
): Written {
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
    skipped,
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
    `\n⚠ No summary for: ${components.join(', ')}` +
      `\n  Each fell back to the first sentence of its DaisyUI description.` +
      `\n  Add one in codegen/model/<directory>.json → summary.`,
  )
}

function main() {
  console.log('Generating kdaisyui component reference from DaisyUI source...\n')

  // One shared definition of which components get generated — a page must exist for precisely
  // the components that have a generated Kotlin file, or the index links at nothing.
  const { generated, skipped } = readComponentSet()
  const docSummaries = summariesOf(generated)

  fs.mkdirSync(OUTPUT_DIR, { recursive: true })
  const written = writeComponentPages(generated, skipped.length, docSummaries)
  fs.writeFileSync(path.join(OUTPUT_DIR, 'index.md'), generateIndexPage(written.shapes, docSummaries))
  const removed = removeStalePages(written.fileNames)
  // Same shapes, same summaries, same run: llms.txt cannot describe a different API than the pages.
  fs.writeFileSync(LLMS_TXT, generateLlmsTxt(fs.readFileSync(LLMS_TEMPLATE, 'utf8'), written.shapes, docSummaries))
  console.log(`llms.txt: ${LLMS_TXT}`)

  console.log(`\nGenerated ${written.shapes.length} pages plus index.md, skipped ${written.skipped}`)
  console.log(`Output: ${OUTPUT_DIR}`)
  if (removed.length > 0) console.log(`Removed ${removed.length} stale page(s): ${removed.join(', ')}`)
  reportMissingSummaries(written.withoutSummary)
}

main()
