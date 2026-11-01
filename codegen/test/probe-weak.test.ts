import { test } from 'node:test'
import assert from 'node:assert/strict'

import { doubled } from '../src/probe-weak.ts'

test('runs doubled', () => {
  assert.equal(typeof doubled(3), 'number')
})
