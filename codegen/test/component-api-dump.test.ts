import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { generateComponentApiDump } from '../src/component-api-dump.ts'
import { buildComponentShape } from '../src/component-shape.ts'
import type { ClassifiedComponent } from '../src/classifier.ts'

/**
 * The Kotlin-level API baseline.
 *
 * The first test is the whole reason this file exists: the change `lib/api/lib.api` cannot see.
 */

function classified(overrides: Partial<ClassifiedComponent> = {}): ClassifiedComponent {
  return {
    componentName: 'Card',
    componentClass: 'Card',
    desc: '',
    prefix: 'card',
    colors: [],
    styles: [],
    sizes: [],
    modifiers: [],
    behaviors: [],
    parts: [],
    directions: [],
    placements: [],
    defaultSize: null,
    descs: {},
    ...overrides,
  } as ClassifiedComponent
}

const shape = (component: ClassifiedComponent, dir: string, element: string, config = {}) =>
  buildComponentShape(component, { componentDir: dir, element }, config)

describe('what the JVM baseline cannot see', () => {
  test('a changed lambda receiver changes the dump', () => {
    // `daisyOtp` moved from DIV to LABEL in this change. `lib/api/lib.api` showed no diff,
    // because both erase to Function1, and the hand-written example app still compiled.
    const otp = classified({ componentName: 'Otp', componentClass: 'Otp', prefix: 'otp' })

    const asDiv = generateComponentApiDump([shape(otp, 'otp', 'DIV')])
    const asLabel = generateComponentApiDump([shape(otp, 'otp', 'LABEL')])

    assert.notEqual(asDiv, asLabel)
    assert.match(asDiv, /attrs: \(DIV\.\(\) -> Unit\)\? = null/)
    assert.match(asLabel, /attrs: \(LABEL\.\(\) -> Unit\)\? = null/)
  })

  test('parameter names are in the dump, because named arguments make them API', () => {
    const dump = generateComponentApiDump([
      shape(classified({ sizes: ['xs'], modifiers: ['side'] }), 'card', 'DIV'),
    ])

    assert.match(dump, /size: CardSize\? = null/)
    assert.match(dump, /side: Boolean = false/)
  })

  test('default values are in the dump', () => {
    const required = generateComponentApiDump([shape(classified(), 'card', 'DIV')])
    const optional = generateComponentApiDump([shape(classified(), 'card', 'DIV', { textParams: ['card'] })])

    assert.match(required, /content: \(DIV\.\(\) -> Unit\)\)$/m)
    assert.match(optional, /content: \(DIV\.\(\) -> Unit\)\? = null\)$/m)
  })

  test('a removed parameter changes the dump too, so it does not only cover the blind spot', () => {
    const withContent = generateComponentApiDump([shape(classified(), 'card', 'DIV')])
    const voidElement = generateComponentApiDump([shape(classified(), 'card', 'IMG')])

    assert.match(withContent, /content:/)
    assert.ok(!voidElement.includes('content:'))
  })
})

describe('generateComponentApiDump', () => {
  test('names the element, so a receiver change reads as the element moving', () => {
    const dump = generateComponentApiDump([shape(classified({ componentName: 'Fieldset', prefix: 'fieldset' }), 'fieldset', 'FIELDSET')])

    // The HTML element, not the kotlinx.html builder.
    assert.match(dump, /^# Fieldset <fieldset>$/m)
  })

  test('lists enum entries, which a caller can name', () => {
    const dump = generateComponentApiDump([
      shape(classified({ colors: ['primary', 'secondary'], sizes: ['xs'] }), 'card', 'DIV'),
    ])

    assert.match(dump, /^enum CardVariant: Primary, Secondary$/m)
    assert.match(dump, /^enum CardSize: Xs$/m)
  })

  test('gives each function one line, so a diff names the function that changed', () => {
    const dump = generateComponentApiDump([
      shape(classified({ parts: ['card-title', 'card-body'] }), 'card', 'DIV'),
    ])

    const functions = dump.split('\n').filter(line => line.startsWith('fun '))
    assert.equal(functions.length, 3)
    assert.match(functions[0], /^fun FlowContent\.daisyCard\(id: HtmlId\? = null, /)
  })

  test('sorts by component name, so filesystem order cannot change the file', () => {
    const cards = shape(classified(), 'card', 'DIV')
    const alert = shape(classified({ componentName: 'Alert', componentClass: 'Alert', prefix: 'alert' }), 'alert', 'DIV')

    assert.equal(generateComponentApiDump([cards, alert]), generateComponentApiDump([alert, cards]))
  })

  test('is byte-stable for the same input', () => {
    const shapes = [shape(classified(), 'card', 'DIV')]

    assert.equal(generateComponentApiDump(shapes), generateComponentApiDump(shapes))
  })

  test('carries the do-not-edit header and names the update task', () => {
    const dump = generateComponentApiDump([shape(classified(), 'card', 'DIV')])

    assert.match(dump, /^# GENERATED — DO NOT EDIT$/m)
    assert.match(dump, /:lib:updateComponentApi/)
  })
})
