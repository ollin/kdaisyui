/**
 * Writes `HeroIconsGeneratedTest.kt` from the pinned Heroicons submodule. The test file itself is
 * `heroIconsTestFile` in `test-generator-heroicons.ts`; this entry point only reads and writes,
 * which is why it lives in an `index-*.ts` the mutation gate leaves out.
 */
import fs from 'fs'
import path from 'path'
import { pathToFileURL } from 'node:url'
import { heroiconDirectories, parseIconFiles } from './parser/svg-heroicons.ts'
import { heroIconsTestFile } from './test-generator-heroicons.ts'

// `lib/generated/test/`, NOT `lib/src/test/`. This file's output is generated, and the
// hand-written tree is off-limits to it — `npm run generate:heroicon-tests` passes no
// --output-dir, so the default is what that command uses, and it is the command this
// generator's own output header tells you to run.
const DEFAULT_OUTPUT_DIR = path.resolve(
  import.meta.dirname,
  '../../lib/generated/test/kotlin/io/github/ollin/kdaisyui/icons',
)

function parseOutputDir(): string {
  for (const arg of process.argv) {
    if (arg.startsWith('--output-dir=')) {
      return arg.slice('--output-dir='.length)
    }
  }
  return DEFAULT_OUTPUT_DIR
}

const OUTPUT_DIR = parseOutputDir()

function main() {
  // Throws, naming `git submodule update --init heroicons`, when the submodule is absent.
  const icons = parseIconFiles(heroiconDirectories())
  fs.mkdirSync(OUTPUT_DIR, { recursive: true })
  const outFile = path.join(OUTPUT_DIR, 'HeroIconsGeneratedTest.kt')
  fs.writeFileSync(outFile, heroIconsTestFile(icons))
  console.log(`Generated render coverage tests for ${icons.size} heroicons`)
  console.log(`Output: ${outFile}`)
}

// Run only when invoked directly: the unit tests import this module for its default output
// directory, and without the guard that import would regenerate 324 icon tests.
if (process.argv[1] && import.meta.url === pathToFileURL(process.argv[1]).href) {
  main()
}

export { DEFAULT_OUTPUT_DIR }
