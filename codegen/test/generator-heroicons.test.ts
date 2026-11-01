import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { generateKotlinFile } from '../src/generator-heroicons.ts'
import type { IconPaths, PascalName } from '../src/parser/svg-heroicons.ts'

/**
 * What no pinned Heroicons icon does, so the snapshot tests cannot show it: an icon that ships no
 * 16px and no 20px solid variant.
 */
describe('an icon with only its 24px variants', () => {
  const paths: IconPaths = { outline24: '<path d="M1" />', solid16: null, solid20: null, solid24: '<path d="M2" />' }
  const kotlin = generateKotlinFile('Probe' as PascalName, paths)

  test('draws the 24px solid paths at every size', () => {
    assert.match(kotlin, /^ {8}HeroIconVariant\.Solid -> heroIconProbeSolid24$/m)
  })

  test('gives the solid variant the 24px viewBox at every size', () => {
    assert.match(kotlin, /^ {8}HeroIconVariant\.Solid -> "0 0 24 24"$/m)
  })
})
