import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { DEFAULT_OUTPUT_DIR } from '../src/test-generator-heroicons.ts'

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
