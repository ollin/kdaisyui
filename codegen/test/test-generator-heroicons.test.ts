import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { DEFAULT_OUTPUT_DIR } from '../src/index-heroicon-tests.ts'
import { heroIconsTestFile } from '../src/test-generator-heroicons.ts'
import type { PascalName } from '../src/parser/svg-heroicons.ts'

describe('test-generator-heroicons default output', () => {
  test('writes into the generated tree, never the hand-written one', () => {
    // Gradle always passes --output-dir, which is what hid this. The default is still
    // reachable: `npm run generate:heroicon-tests` passes no arguments, and that is the
    // command the generated file's own header tells you to run.
    assert.ok(
      DEFAULT_OUTPUT_DIR.includes('/lib/generated/test/'),
      `default output dir must be under lib/generated/test/, was: ${DEFAULT_OUTPUT_DIR}`,
    )
    assert.ok(
      !DEFAULT_OUTPUT_DIR.includes('/lib/src/'),
      `default output dir must never point into the hand-written tree, was: ${DEFAULT_OUTPUT_DIR}`,
    )
  })
})

describe('an icon with no smaller solid variants', () => {
  test('expects the 24px viewBox at the medium size', () => {
    const icons = new Map([['Probe' as PascalName, { outline24: '<path />', solid16: null, solid20: null, solid24: '<path />' }]])

    assert.ok(heroIconsTestFile(icons).includes('html.contains("""viewBox="0 0 24 24" class="heroicon-md heroicon-solid" width="20" height="20">""")'))
  })
})
