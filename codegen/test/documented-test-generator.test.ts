import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import type { ComponentModel } from '../src/model.ts'
import { documentedTestFor } from '../src/test-generator.ts'
import { component, part } from './model-fixture.ts'

/**
 * The documented-element test files, on cases no committed page has: the snapshot test cannot tell
 * these imports and branches from a simpler generator that happens to agree on all 66 pages.
 */

const page = (...examples: string[]) => examples.map((html, index) => `### ~Case ${index}\n\`\`\`html\n${html}\n\`\`\``).join('\n')
const testFileFor = (model: ComponentModel, classes: string[], ...examples: string[]) =>
  documentedTestFor(model.component, page(...examples), model, { models: [model], componentClasses: new Set(classes) })!.kotlin
const withPart = (model: ComponentModel, ...functions: ComponentModel['functions']) => ({ ...model, functions: [...model.functions, ...functions] })
const dropdown = component('dropdown', 'Dropdown', 'dropdown')

describe('the attribute test', () => {
  const kbd = component('kbd', 'Kbd', 'kbd', {}, { element: 'KBD', attributes: { 'data-a': '1', 'data-b': '2' } })

  test('asserts every attribute on its own line, and imports assertTrue without any custom part', () => {
    const kotlin = testFileFor(kbd, ['kbd'], '<kbd class="$$kbd">x</kbd>')

    assert.ok(kotlin.includes('        assertTrue(html.contains("data-a=\\"1\\""))\n        assertTrue(html.contains("data-b=\\"2\\""))\n'))
    assert.ok(kotlin.includes('\nimport kotlin.test.assertTrue\n'))
  })
})

describe('a documented element the test writes itself', () => {
  test('puts a legend inside the fieldset it needs', () => {
    const x = withPart(component('x', 'X', 'x'), part('daisyXLegend', 'x-legend'))

    assert.ok(testFileFor(x, ['x', 'x-legend'], '<legend class="$$x-legend">L</legend>').includes('createHTML(prettyPrint = false).fieldSet {'))
  })

  test('imports addClassNames for the variant classes it adds', () => {
    const kbd = component('kbd', 'Kbd', 'kbd', { enums: [{ name: 'KbdSize', label: 'Size', classes: ['kbd-lg'] }] }, { element: 'KBD' })

    assert.ok(testFileFor(kbd, ['kbd', 'kbd-lg'], '<span class="$$kbd md:$$kbd-lg">x</span>').includes('\nimport io.github.ollin.kdaisyui.core.addClassNames\n'))
  })
})

describe('htmlId', () => {
  const HTML_ID = '\nimport io.github.ollin.kdaisyui.core.htmlId\n'

  test('is imported for a documented call of an anchored popover', () => {
    const withPopover = withPart(dropdown, part('daisyDropdownContent', 'dropdown-content', { element: 'UL', anchoredPopover: true }))

    assert.ok(testFileFor(withPopover, ['dropdown', 'dropdown-content'], '<ul class="$$dropdown-content" popover>x</ul>').includes(HTML_ID))
  })

  test('is imported for a custom anchored popover no documented call names', () => {
    const withPopover = withPart(dropdown, { ...part('daisyDropdownPopover', 'dropdown-content', { element: 'UL', anchoredPopover: true }), kind: 'custom' })

    assert.ok(testFileFor(withPopover, ['dropdown', 'dropdown-content'], '<div class="$$dropdown">x</div>').includes(HTML_ID))
  })
})

describe('the documented-element tests', () => {
  test("name a test after its case alone where the main function expresses it, wherever it is declared", () => {
    const partFirst = { ...component('x', 'X', 'x'), functions: [part('daisyXItem', 'x-item'), component('x', 'X', 'x').functions[0]] }

    assert.ok(testFileFor(partFirst, ['x', 'x-item'], '<div class="$$x">x</div>').includes('    fun case_0() {'))
  })

  test('leave the helper out of a file without one', () => {
    assert.ok(!testFileFor(component('x', 'X', 'x'), ['x', 'x-unknown'], '<div class="$$x-unknown">x</div>').includes('assertDocumented'))
  })
})
