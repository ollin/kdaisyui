import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { generateComponentApiDump } from '../src/component-api-dump.ts'
import { component, enumParameter, part, shapeOf, toggle } from './model-fixture.ts'

/**
 * The Kotlin-level API baseline.
 *
 * The first test is the whole reason this file exists: the change `lib/api/lib.api` cannot see.
 */

const card = (overrides = {}, main = {}) => shapeOf(component('card', 'Card', 'card', overrides, main))

describe('what the JVM baseline cannot see', () => {
  test('a changed lambda receiver changes the dump', () => {
    // `daisyOtp` moved from DIV to LABEL in this change. `lib/api/lib.api` showed no diff,
    // because both erase to Function1, and the hand-written example app still compiled.
    const asDiv = generateComponentApiDump([shapeOf(component('otp', 'Otp', 'otp', {}, { element: 'DIV' }))])
    const asLabel = generateComponentApiDump([shapeOf(component('otp', 'Otp', 'otp', {}, { element: 'LABEL' }))])

    assert.notEqual(asDiv, asLabel)
    assert.match(asDiv, /attrs: \(DIV\.\(\) -> Unit\)\? = null/)
    assert.match(asLabel, /attrs: \(LABEL\.\(\) -> Unit\)\? = null/)
  })

  test('parameter names are in the dump, because named arguments make them API', () => {
    const dump = generateComponentApiDump([
      card(
        { enums: [{ name: 'CardSize', label: 'Size variants', classes: ['card-xs'] }] },
        { parameters: [enumParameter('size', 'CardSize', 'Size variant'), toggle('side', 'card-side')] },
      ),
    ])

    // `ClassValues<CardSize>?`, not `CardSize?`: the holder is what lets one parameter take a bare
    // entry, an entry at a Tailwind variant, and combinations of those. The enum name stays the
    // type argument, so the dump still records WHICH group answers this parameter.
    assert.match(dump, /size: ClassValues<CardSize>\? = null/)
    assert.match(dump, /side: Boolean = false/)
  })

  test('default values are in the dump', () => {
    const required = generateComponentApiDump([card()])
    const optional = generateComponentApiDump([card({}, { text: true })])

    assert.match(required, /content: \(DIV\.\(\) -> Unit\)\)$/m)
    assert.match(optional, /content: \(DIV\.\(\) -> Unit\)\? = null\)$/m)
  })

  test('a removed parameter changes the dump too, so it does not only cover the blind spot', () => {
    const withContent = generateComponentApiDump([card()])
    const voidElement = generateComponentApiDump([card({}, { element: 'IMG' })])

    assert.match(withContent, /content:/)
    assert.ok(!voidElement.includes('content:'))
  })
})

describe('generateComponentApiDump', () => {
  test('names the element, so a receiver change reads as the element moving', () => {
    const dump = generateComponentApiDump([shapeOf(component('fieldset', 'Fieldset', 'fieldset', {}, { element: 'FIELDSET' }))])

    // The HTML element, not the kotlinx.html builder.
    assert.match(dump, /^# Fieldset <fieldset>$/m)
  })

  test('lists enum entries, which a caller can name', () => {
    const dump = generateComponentApiDump([
      card(
        { enums: [
          { name: 'CardVariant', label: 'Color variants', classes: ['card-primary', 'card-secondary'] },
          { name: 'CardSize', label: 'Size variants', classes: ['card-xs'] },
        ] },
        { parameters: [enumParameter('variant', 'CardVariant', 'Color variant'), enumParameter('size', 'CardSize', 'Size variant')] },
      ),
    ])

    assert.match(dump, /^enum CardVariant: Primary, Secondary$/m)
    assert.match(dump, /^enum CardSize: Xs$/m)
  })

  test('gives each function one line, so a diff names the function that changed', () => {
    const parts = [part('daisyCardTitle', 'card-title', { element: 'H2' }), part('daisyCardBody', 'card-body')]
    const dump = generateComponentApiDump([shapeOf({ ...component('card', 'Card', 'card'), functions: [component('card', 'Card', 'card').functions[0], ...parts] })])

    const functions = dump.split('\n').filter(line => line.startsWith('fun ') && !line.startsWith('fun Tag.'))
    assert.equal(functions.length, 3)
    assert.match(functions[0], /^fun FlowContent\.daisyCard\(id: HtmlId\? = null, /)
  })

  test('sorts by component name, so filesystem order cannot change the file', () => {
    const cards = card()
    const alert = shapeOf(component('alert', 'Alert', 'alert'))

    assert.equal(generateComponentApiDump([cards, alert]), generateComponentApiDump([alert, cards]))
  })

  test('is byte-stable for the same input', () => {
    const shapes = [card()]

    assert.equal(generateComponentApiDump(shapes), generateComponentApiDump(shapes))
  })

  test('carries the do-not-edit header and names the update task', () => {
    const dump = generateComponentApiDump([card()])

    assert.match(dump, /^# GENERATED — DO NOT EDIT$/m)
    assert.match(dump, /:lib:updateComponentApi/)
  })
})

describe('the class siblings', () => {
  test("follow their function, with the function's class parameters only", () => {
    const button = component('button', 'Button', 'btn', { enums: [{ name: 'ButtonSize', label: 'Size variants', classes: ['btn-sm'] }] }, { parameters: [enumParameter('size', 'ButtonSize', ''), toggle('active', 'btn-active')] })
    const lines = generateComponentApiDump([shapeOf(button)]).split('\n')
    const function_ = lines.findIndex(line => line.startsWith('fun FlowContent.daisyButton('))

    assert.equal(lines[function_ + 1], 'fun Tag.daisyButtonClasses(size: ClassValues<ButtonSize>? = null, active: Boolean = false)')
  })
})

describe('a class-only function', () => {
  test('is dumped as its sibling, after the functions', () => {
    const menu = { ...component('menu', 'Menu', 'menu'), functions: [...component('menu', 'Menu', 'menu').functions, { kind: 'classes' as const, name: 'daisyMenuItem', receiver: 'Tag', element: null as unknown as string, class: null, text: false, parameters: [toggle('active', 'menu-active')] }] }
    const lines = generateComponentApiDump([shapeOf(menu)]).split('\n')

    assert.equal(lines[lines.findIndex(line => line.startsWith('fun Tag.daisyMenuClasses(')) + 1], 'fun Tag.daisyMenuItemClasses(active: Boolean = false)')
  })
})
