/**
 * Entry point: write the exclusivity probe page into a directory that already holds
 * `daisyui.css` from the webjar.
 *
 * Driven by `:e2e-tests:measureExclusivity`, which unpacks the webjar, runs this, opens the
 * page with the Playwright the project already has, and writes `codegen/exclusivity.json`.
 * The logic lives in `exclusivity-probe.ts` so it can be unit-tested without this file's
 * side effects.
 */
import { copyFileSync, writeFileSync } from 'node:fs'
import path from 'node:path'
import { buildProbePage } from './exclusivity-probe.ts'

const IN_PAGE_SCRIPT = path.resolve(import.meta.dirname, 'exclusivity-verdicts.js')

function argument(name: string): string {
  const found = process.argv.find((a) => a.startsWith(`--${name}=`))
  if (!found) throw new Error(`Missing required argument --${name}=…`)
  return found.slice(name.length + 3)
}

const outputDir = argument('output-dir')
const page = buildProbePage()

writeFileSync(path.join(outputDir, 'probe.html'), page.html)
copyFileSync(IN_PAGE_SCRIPT, path.join(outputDir, 'exclusivity-verdicts.js'))

console.log(`${page.cases.length} cases across ${page.groups} groups`)
if (page.unmeasurable.length > 0) {
  // Not a failure here: `verify-exclusivity.ts` is what decides whether an unmeasurable group
  // may stay unmeasured. This line only says which ones the run could not reach.
  console.log(`no documented example, cannot measure: ${page.unmeasurable.join(', ')}`)
}
