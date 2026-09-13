/**
 * Writes the Kotlin-level API baseline, or checks the committed one against it.
 *
 * Deliberately a separate entry point from `index-new.ts`, and deliberately NOT part of
 * `just generate`. A baseline rewritten by the same command that regenerates the code would
 * follow every change in silence — which is the failure this whole change is about.
 *
 * `--check` is what CI and `check` run; the bare form is what a maintainer runs on purpose
 * after reading the diff.
 */

import fs from 'fs'
import path from 'path'
import { readComponentSet } from './component-set.ts'
import { generateComponentApiDump } from './component-api-dump.ts'

const DEFAULT_BASELINE = path.resolve(import.meta.dirname, '../../lib/api/components.api')
const CONFIG_PATH = path.resolve(import.meta.dirname, '../codegen-config.json')

function parseArg(flag: string, fallback: string): string {
  for (const arg of process.argv) {
    if (arg.startsWith(`${flag}=`)) return arg.slice(flag.length + 1)
  }
  return fallback
}

const BASELINE = parseArg('--baseline', DEFAULT_BASELINE)
const CHECK_ONLY = process.argv.includes('--check')

function loadConfig() {
  return fs.existsSync(CONFIG_PATH) ? JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf8')) : {}
}

/** The first differing line, which is more use than "they differ". */
function firstDifference(expected: string, actual: string): string {
  const expectedLines = expected.split('\n')
  const actualLines = actual.split('\n')
  const length = Math.max(expectedLines.length, actualLines.length)

  for (let i = 0; i < length; i++) {
    if (expectedLines[i] === actualLines[i]) continue
    return [
      `  first difference at line ${i + 1}:`,
      `    committed: ${expectedLines[i] ?? '(end of file)'}`,
      `    generated: ${actualLines[i] ?? '(end of file)'}`,
    ].join('\n')
  }
  return '  (files differ only in trailing content)'
}

function main() {
  // The same component set the Kotlin and Markdown generators use, from one implementation —
  // a baseline covering a different set would report changes nobody made.
  const dump = generateComponentApiDump(readComponentSet(loadConfig()).generated.map(c => c.shape))

  if (!CHECK_ONLY) {
    fs.mkdirSync(path.dirname(BASELINE), { recursive: true })
    fs.writeFileSync(BASELINE, dump)
    console.log(`Wrote ${BASELINE}`)
    console.log('Read the diff before committing it — that is the whole point of this file.')
    return
  }

  if (!fs.existsSync(BASELINE)) {
    console.error(`Missing ${BASELINE}. Run :lib:updateComponentApi to create it.`)
    process.exitCode = 1
    return
  }

  const committed = fs.readFileSync(BASELINE, 'utf8')
  if (committed === dump) {
    console.log('Component API matches the committed baseline.')
    return
  }

  console.error(
    [
      'The generated components\' Kotlin API no longer matches lib/api/components.api.',
      '',
      firstDifference(committed, dump),
      '',
      'This file carries what lib/api/lib.api cannot: lambda receiver types, parameter names',
      'and default values. A change here can be source-breaking while the JVM baseline shows',
      'nothing — which is why it is checked separately.',
      '',
      'If the change is intended: run :lib:updateComponentApi, READ the diff, and commit it.',
      'A breaking change also needs a "How to migrate" entry in README.md.',
    ].join('\n'),
  )
  process.exitCode = 1
}

main()
