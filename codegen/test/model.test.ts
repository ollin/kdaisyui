import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { modelFromShape, shapeFromModel, type ComponentModel, type Descriptions } from '../src/model.ts'
import type { ComponentConfig } from '../src/component-shape.ts'

/** The smallest real component: one function, one enum, inline text. */
const KBD: ComponentModel = {
  component: 'kbd',
  name: 'Kbd',
  class: 'kbd',
  enums: [{ name: 'KbdSize', label: 'Size variants', classes: ['kbd-sm', 'kbd-lg'] }],
  functions: [{
    kind: 'main',
    name: 'daisyKbd',
    receiver: 'FlowContent',
    element: 'KBD',
    class: 'kbd',
    text: true,
    parameters: [{ kind: 'enum', name: 'size', enum: 'KbdSize', doc: 'Size variant' }],
  }],
}

const DESCRIPTIONS: Descriptions = { desc: 'Keyboard key.', classes: { sm: 'Small size' } }

describe('shapeFromModel', () => {
  test('frames the declared parameters with text, id and the escape hatches', () => {
    const [kbd] = shapeFromModel(KBD, DESCRIPTIONS).functions

    assert.deepEqual(kbd.parameters.map(parameter => parameter.name), ['text', 'id', 'size', 'extraClasses', 'attrs', 'content'])
  })

  test('types an enum parameter as the ClassValues of its enum', () => {
    const size = shapeFromModel(KBD, DESCRIPTIONS).functions[0].parameters[2]

    assert.deepEqual(size, { name: 'size', type: 'ClassValues<KbdSize>?', default: 'null', doc: 'Size variant', enumName: 'KbdSize' })
  })

  test('derives the builder and the prose tag from the declared element', () => {
    const [kbd] = shapeFromModel(KBD, DESCRIPTIONS).functions

    assert.equal(kbd.tagBuilder, 'kbd')
    assert.equal(kbd.htmlTag, 'kbd')
  })

  test('names each enum entry after its class and describes it from the frontmatter', () => {
    const [size] = shapeFromModel(KBD, DESCRIPTIONS).enums

    assert.deepEqual(size.entries, [
      { name: 'Sm', cssClass: 'kbd-sm', desc: 'Small size' },
      { name: 'Lg', cssClass: 'kbd-lg', desc: null },
    ])
  })

  test('makes a toggle a Boolean named as declared, documented from its stripped class', () => {
    const button: ComponentModel = { ...KBD, class: 'btn', enums: [], functions: [{ ...KBD.functions[0], parameters: [{ kind: 'toggle', name: 'active', class: 'btn-active' }] }] }

    const active = shapeFromModel(button, { desc: '', classes: { active: 'looks active' } }).functions[0].parameters[2]

    assert.deepEqual(active, { name: 'active', type: 'Boolean', default: 'false', doc: 'looks active', cssClass: 'btn-active' })
  })

  test('makes an extra parameter what it declares, with no description', () => {
    const disabled = { kind: 'extra', name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) this.disabled = true' } as const
    const input: ComponentModel = { ...KBD, enums: [], functions: [{ ...KBD.functions[0], parameters: [disabled] }] }

    const parameter = shapeFromModel(input, DESCRIPTIONS).functions[0].parameters[2]

    assert.deepEqual(parameter, { name: 'disabled', type: 'Boolean', default: 'false', doc: null })
  })

  test('writes the classes and attributes a construction always carries', () => {
    const popover: ComponentModel = { ...KBD, enums: [], functions: [{ ...KBD.functions[0], kind: 'custom', parameters: [], always: ['kbd-x'], attributes: { popover: '' } }] }

    const [fn] = shapeFromModel(popover, DESCRIPTIONS).functions

    assert.deepEqual([fn.modifierClasses, fn.staticAttributes], [['kbd-x'], [['popover', '']]])
  })

  test('describes the main function by the component, a part by its own class, a construction by nothing', () => {
    const parts: ComponentModel = { ...KBD, enums: [], functions: [
      { ...KBD.functions[0], parameters: [] },
      { ...KBD.functions[0], kind: 'part', class: 'kbd-part', parameters: [] },
      { ...KBD.functions[0], kind: 'custom', parameters: [] },
    ] }

    const descs = shapeFromModel(parts, { desc: 'Keyboard key.', classes: { 'kbd-part': 'A part.' } }).functions.map(fn => fn.desc)

    assert.deepEqual(descs, ['Keyboard key.', 'A part.', ''])
  })

  test('writes back the model a shape was read from', () => {
    const disabled = { kind: 'extra', name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) this.disabled = true' } as const
    const full: ComponentModel = { ...KBD, functions: [
      { ...KBD.functions[0], parameters: [...KBD.functions[0].parameters, { kind: 'toggle', name: 'wide', class: 'kbd-wide' }, disabled] },
      { kind: 'custom', name: 'daisyKbdPopover', receiver: 'FlowContent', element: 'DIV', class: null, text: false, parameters: [], attributes: { popover: '' } },
    ] }
    const config = { extras: [{ name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) this.disabled = true' }] } as unknown as ComponentConfig

    assert.deepEqual(modelFromShape(shapeFromModel(full, DESCRIPTIONS), config), full)
  })
})
