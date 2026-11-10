import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { themeNames } from '../src/themes.ts'

describe('themeNames', () => {
  it('returns the stems of the CSS files, sorted, and ignores every other file', () => {
    assert.deepEqual(themeNames(['nord.css', 'abyss.css', 'README.md', 'light.css']), ['abyss', 'light', 'nord'])
  })
})
