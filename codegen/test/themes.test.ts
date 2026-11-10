import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { renderDaisyThemeEnum, themeNames } from '../src/themes.ts'

describe('renderDaisyThemeEnum', () => {
  it('declares one entry per theme, named by its capitalised name and carrying the name', () => {
    assert.match(renderDaisyThemeEnum(['abyss', 'nord']), /enum class DaisyTheme\(val value: String\) \{\n    Abyss\("abyss"\),\n    Nord\("nord"\),\n\}\n$/)
  })
})

describe('themeNames', () => {
  it('returns the stems of the CSS files, sorted, and ignores every other file', () => {
    assert.deepEqual(themeNames(['nord.css', 'abyss.css', 'README.md', 'light.css']), ['abyss', 'light', 'nord'])
  })

  it('refuses a theme its entry rule cannot spell, naming the file', () => {
    assert.throws(() => themeNames(['nord.css', 'foo-bar.css']), /^Error: DaisyTheme cannot name foo-bar\.css: /)
  })
})
