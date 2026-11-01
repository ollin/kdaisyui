import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { mutationScope } from '../src/mutation-scope.ts'

/** The `git diff -U0 --relative` output for one file, with the given hunk headers. */
const diffOf = (file: string, ...hunks: string[]) =>
  [`diff --git a/${file} b/${file}`, 'index 1111111..2222222 100644', `--- a/${file}`, `+++ b/${file}`, ...hunks.flatMap(hunk => [hunk, '+changed'])].join('\n')

describe('mutationScope', () => {
  test('gives one line range per hunk that adds lines', () => {
    const diff = diffOf('src/model.ts', '@@ -10,0 +11,3 @@ export function shape()', '@@ -40,2 +44,2 @@')

    assert.deepEqual(mutationScope(diff), ['src/model.ts:11-13', 'src/model.ts:44-45'])
  })

  test('reads a hunk without a count as one line', () => {
    assert.deepEqual(mutationScope(diffOf('src/model.ts', '@@ -7 +7 @@')), ['src/model.ts:7-7'])
  })
})
