import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import type { ComponentShape, FunctionShape } from '../src/component-shape.ts'
import { mainFunctionConfig, type ComponentModel } from '../src/model.ts'
import { coverageTestFor } from '../src/test-generator.ts'
import { component, part, shapeOf } from './model-fixture.ts'

/**
 * What the coverage tests read from the shape, on cases no committed model has: the snapshot test
 * cannot tell these rules from a simpler one that happens to agree on all 66 components.
 */

const coverageOf = (model: ComponentModel, shape: ComponentShape = shapeOf(model)) => coverageTestFor(model.name, shape, mainFunctionConfig(model))
const linesOf = (kotlin: string, pattern: RegExp) => kotlin.split('\n').filter(line => pattern.test(line)).map(line => line.trim())

describe('a main function whose own class is not the component class', () => {
  const stat = component('stat', 'Stat', 'stat', {}, { class: 'stats', role: 'status' })
  const kotlin = coverageOf({ ...stat, functions: [stat.functions[0], part('daisyStatTitle', 'stat-title')] })

  test("expects the component class, which the main body writes, and its sibling's own", () => {
    assert.deepEqual(linesOf(kotlin, /defaults", "/), [
      'assertRendered(html, Expected("Stat defaults", "stat", closes = "</div></div>"))',
      'assertRendered(html, Expected("StatClasses defaults", "stats"))',
      'assertRendered(html, Expected("StatTitle defaults", "stat-title", closes = "</div></div>"))',
      'assertRendered(html, Expected("StatTitleClasses defaults", "stat-title"))',
    ])
  })

  test('expects the role on the main function only, the one that sets it', () => {
    assert.deepEqual(linesOf(kotlin, /sets role/), [
      'assertTrue(html.contains("role=\\""), "Stat sets role")',
      'assertTrue(html.contains("role=\\""), "Stat sets role")',
    ])
  })
})

describe("a main function's extras", () => {
  test('are expected in body order, those before the classes first', () => {
    const model = component('wrap', 'Wrap', 'wrap', {}, {
      parameters: [
        { kind: 'extra', name: 'later', type: 'String', default: '"x"', apply: 'this.later = later' },
        { kind: 'extra', name: 'early', type: 'String', default: '"x"', apply: 'this.early = early', position: 'before_classes' },
      ],
    })

    // Both set unconditionally, so only their position orders them, in the defaults and in all flags.
    assert.deepEqual(linesOf(coverageOf(model), /sets /), [
      'assertTrue(html.contains("early=\\""), "Wrap sets early")',
      'assertTrue(html.contains("later=\\""), "Wrap sets later")',
      'assertTrue(html.contains("early=\\""), "Wrap sets early")',
      'assertTrue(html.contains("later=\\""), "Wrap sets later")',
    ])
  })
})

describe('a scope member', () => {
  test('is called without content where only another parameter is required', () => {
    const join = component('join', 'Join', 'join')
    const member = { name: 'daisyPopover', parameters: [{ name: 'id', type: 'HtmlId', default: null, doc: null }, { name: 'content', type: '(DIV.() -> Unit)?', default: 'null', doc: null }] } as unknown as FunctionShape
    const shape = { ...shapeOf(join), scope: { name: 'JoinScope', markerClass: 'join-item', members: [member], imports: [] } } as unknown as ComponentShape

    assert.deepEqual(linesOf(coverageOf(join, shape), /^\s+daisyPopover\(/), ['daisyPopover()'])
  })
})
