import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { themeNames } from '../src/themes.ts'

describe('themeNames', () => {
  it('returns the stems of the CSS files, sorted, and ignores every other file', () => {
    assert.deepEqual(themeNames(['nord.css', 'abyss.css', 'README.md', 'light.css']), ['abyss', 'light', 'nord'])
  })

  it('refuses a theme its entry rule cannot spell, naming the file', () => {
    assert.throws(() => themeNames(['nord.css', 'foo-bar.css']), /^Error: DaisyTheme cannot name foo-bar\.css: /)
  })
})
