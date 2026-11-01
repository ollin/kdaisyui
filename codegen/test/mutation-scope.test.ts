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

  test('skips a hunk that only deletes, since a deleted line has nothing to mutate', () => {
    assert.deepEqual(mutationScope(diffOf('src/model.ts', '@@ -12,4 +11,0 @@')), [])
  })

  test('skips what the gate does not mutate: tests, entry points, files outside src/', () => {
    const diff = [
      diffOf('test/model.test.ts', '@@ -1,0 +2,1 @@'),
      diffOf('src/index-docs.ts', '@@ -1,0 +2,1 @@'),
      diffOf('stryker.config.json', '@@ -1,0 +2,1 @@'),
      diffOf('src/parser/frontmatter.ts', '@@ -1,0 +2,1 @@'),
    ].join('\n')

    assert.deepEqual(mutationScope(diff), ['src/parser/frontmatter.ts:2-2'])
  })

  test('reads a count of more than one digit', () => {
    assert.deepEqual(mutationScope(diffOf('src/model.ts', '@@ -1,0 +5,12 @@')), ['src/model.ts:5-16'])
  })

  test('takes no hunk header or file header from inside an added line', () => {
    const file = [
      'diff --git a/src/model.ts b/src/model.ts',
      '--- a/src/model.ts',
      '+++ b/src/model.ts',
      '@@ -1,0 +2,1 @@',
      "+const marker = '@@ -1 +90 @@ and diff --git a/x b/x'",
      '@@ -9,0 +10,1 @@',
      '+const next = 1',
    ].join('\n')

    assert.deepEqual(mutationScope(file), ['src/model.ts:2-2', 'src/model.ts:10-10'])
  })

  test('mutates only TypeScript directly under src/, whatever a path merely contains', () => {
    const diff = [diffOf('test/src/helper.ts', '@@ -1,0 +2,1 @@'), diffOf('src/model.ts.orig', '@@ -1,0 +2,1 @@'), diffOf('src/parser/index-like.ts', '@@ -1,0 +2,1 @@')].join('\n')

    assert.deepEqual(mutationScope(diff), ['src/parser/index-like.ts:2-2'])
  })

  test('skips a deleted file, whose new side is /dev/null', () => {
    const deleted = ['diff --git a/src/gone.ts b/src/gone.ts', 'deleted file mode 100644', '--- a/src/gone.ts', '+++ /dev/null', '@@ -1,3 +0,0 @@'].join('\n')

    assert.deepEqual(mutationScope(deleted), [])
  })

  test('gives nothing for an empty diff', () => {
    assert.deepEqual(mutationScope(''), [])
  })

  test('reads a hunk without a count as one line', () => {
    assert.deepEqual(mutationScope(diffOf('src/model.ts', '@@ -7 +7 @@')), ['src/model.ts:7-7'])
  })
})
