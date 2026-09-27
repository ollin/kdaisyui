import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { generateLlmsTxt } from '../src/generator-llms.ts'
import type { ComponentShape, ParameterShape } from '../src/component-shape.ts'

const SIZE: ParameterShape = { name: 'size', type: 'ClassValues<KbdSize>?', default: 'null', doc: null, enumName: 'KbdSize' }
const CONTENT: ParameterShape = { name: 'content', type: '(KBD.() -> Unit)', default: null, doc: null }

/** A component reduced to what llms.txt renders: name, directory, enums and functions. */
const KBD = {
  componentName: 'Kbd',
  componentDir: 'kbd',
  prefix: 'kbd',
  enums: [{ name: 'KbdSize', entries: [{ name: 'Sm', cssClass: 'kbd-sm' }, { name: 'Lg', cssClass: 'kbd-lg' }] }],
  functions: [{ kind: 'main', name: 'daisyKbd', receiver: 'FlowContent', htmlTag: 'kbd', desc: '', parameters: [SIZE, CONTENT] }],
} as unknown as ComponentShape

describe('generateLlmsTxt', () => {
  test('replaces the placeholder with each component signature', () => {
    const llms = generateLlmsTxt('before\n{{COMPONENTS}}\nafter\n', [KBD], {})

    assert.match(llms, /^fun FlowContent\.daisyKbd\(size: ClassValues<KbdSize>\? = null, content: \(KBD\.\(\) -> Unit\)\)$/m)
    assert.doesNotMatch(llms, /\{\{COMPONENTS\}\}/)
  })

  test('lists each enum a component declares with the constants a caller can name', () => {
    const llms = generateLlmsTxt('{{COMPONENTS}}', [KBD], {})

    assert.match(llms, /^enum class KbdSize \{ Sm, Lg \}$/m)
  })

  test('heads each component with its summary and element, then fences its API as Kotlin', () => {
    const llms = generateLlmsTxt('{{COMPONENTS}}', [KBD], { kbd: { summary: 'Keyboard key display' } })

    assert.match(llms, /^### Kbd\n\nKeyboard key display — renders `<kbd>`\.\n\n```kotlin\n(.+\n)+```$/m)
  })

  test('refuses a template that has lost its placeholder', () => {
    assert.throws(() => generateLlmsTxt('no placeholder here\n', [KBD], {}), /\{\{COMPONENTS\}\}/)
  })
})
