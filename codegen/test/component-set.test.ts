import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { readComponentSet } from '../src/component-set.ts'
import { isSkipped, modelFileNames, readModel } from '../src/model.ts'

describe('readComponentSet', () => {
  test('lists every component its model skips, with the reason the model gives', () => {
    const expected = modelFileNames().sort().flatMap(componentDir => {
      const model = readModel(componentDir)
      return isSkipped(model) ? [{ componentDir, reason: model.skip }] : []
    })
    const skipped = [...readComponentSet().skipped].sort((left, right) => (left.componentDir < right.componentDir ? -1 : 1))

    assert.ok(expected.length > 0)
    assert.deepEqual(skipped, expected)
  })
})
