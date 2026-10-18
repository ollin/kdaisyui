import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { mainFunctionConfig, modelFromShape, shapeFromModel, withDeclaredScopes, type ComponentModel, type Descriptions } from '../src/model.ts'
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
    const config = { extras: [{ name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) this.disabled = true' }], role: null, inputType: null } as unknown as ComponentConfig

    assert.deepEqual(modelFromShape(shapeFromModel(full, DESCRIPTIONS), config), full)
  })

  test('opens the join scope on the component that declares it, with the declared items as members', () => {
    const item: ComponentModel = { ...KBD, functions: [{ ...KBD.functions[0], joinItem: true }] }
    const join: ComponentModel = { component: 'join', name: 'Join', class: 'join', enums: [], joinScope: true, functions: [{ ...KBD.functions[0], name: 'daisyJoin', element: 'DIV', class: 'join', text: false, parameters: [] }] }
    const declared = [item, join].map(model => ({ model, shape: shapeFromModel(model, DESCRIPTIONS) }))

    const [, joined] = withDeclaredScopes(declared)

    assert.deepEqual([joined.scope?.members.map(fn => fn.name), joined.functions[0].contentScope], [['daisyKbd'], 'JoinScope'])
  })

  test('hands the main function body its extras, text, role and input type', () => {
    const disabled = { kind: 'extra', name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) this.disabled = true' } as const
    const checkbox: ComponentModel = { ...KBD, enums: [], functions: [{ ...KBD.functions[0], element: 'INPUT', parameters: [disabled], role: 'switch', inputType: 'checkBox' }] }

    assert.deepEqual(mainFunctionConfig(checkbox), {
      extras: [{ name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) this.disabled = true' }],
      hasTextParam: true,
      role: 'switch',
      inputType: 'checkBox',
    })
  })

  test('records the classes an extra parameter writes, and keeps them out of the body config', () => {
    const disabled = { name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) { this.disabled = true; addClassNames("btn-disabled") }' }
    const shape = shapeFromModel({ ...KBD, enums: [], functions: [{ ...KBD.functions[0], parameters: [{ kind: 'extra', ...disabled }] }] }, DESCRIPTIONS)
    const config = { extras: [disabled], role: null, inputType: null } as unknown as ComponentConfig

    const model = modelFromShape(shape, config)

    assert.deepEqual([model.functions[0].parameters[0], mainFunctionConfig(model).extras], [{ kind: 'extra', ...disabled, writes: ['btn-disabled'] }, [disabled]])
  })
})
