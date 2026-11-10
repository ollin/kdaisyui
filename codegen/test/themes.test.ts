import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { renderDaisyThemeEnum, renderThemeMap, themeNames } from '../src/themes.ts'

describe('renderDaisyThemeEnum', () => {
  it('declares one entry per theme, named by its capitalised name and carrying the name', () => {
    assert.match(renderDaisyThemeEnum(['abyss', 'nord']), /enum class DaisyTheme\(val value: String\) \{\n    Abyss\("abyss"\),\n    Nord\("nord"\),\n\}\n$/)
  })

  it('documents the enum, saying a stylesheet carries only the themes its entry names', () => {
    assert.match(renderDaisyThemeEnum([]), /\n\/\*\*\n \* DaisyUI's built-in themes; [^]* carries only the\n \* themes its entry names, [^]*\*\/\nenum class/)
  })

  it('opens with the generated header, naming its source, and the themes package', () => {
    const header = renderDaisyThemeEnum([]).split('\n').slice(0, 7).join('\n')
    assert.equal(header, '// GENERATED — DO NOT EDIT\n// Source: daisyui/packages/daisyui/src/themes/*.css\n// Regenerate: cd codegen && npm run generate\n\npackage io.github.ollin.kdaisyui.themes\n\n/**')
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

describe('renderThemeMap', () => {
  it('writes each theme as its entry and its value, tab-separated, under a header', () => {
    assert.match(renderThemeMap(['abyss', 'nord']), /^# Per theme: [^\n]*\n# Gradle plugin\. Generated - do not edit\.\nAbyss\tabyss\nNord\tnord\n$/)
  })
})
