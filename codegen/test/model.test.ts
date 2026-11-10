import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { entryName, mainFunctionConfig, shapeFromModel, withDeclaredScopes, type ComponentModel, type Descriptions } from '../src/model.ts'
import { component, enumParameter, mainFunction, part, toggle } from './model-fixture.ts'

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

    assert.deepEqual(kbd.parameters.map(parameter => parameter.name), ['text', 'id', 'size', 'extraClasses', 'block'])
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

  test('opens the join scope on the component that declares it, with the declared items as members', () => {
    const item: ComponentModel = { ...KBD, functions: [{ ...KBD.functions[0], joinItem: true }] }
    const join: ComponentModel = { component: 'join', name: 'Join', class: 'join', enums: [], joinScope: true, functions: [{ ...KBD.functions[0], name: 'daisyJoin', element: 'DIV', class: 'join', text: false, parameters: [] }] }
    const declared = [item, join].map(model => ({ model, shape: shapeFromModel(model, DESCRIPTIONS) }))

    const [, joined] = withDeclaredScopes(declared)

    assert.deepEqual([joined.scope?.members.map(fn => fn.name), joined.functions[0].blockScope], [['daisyKbd'], 'JoinScope'])
  })

  test("imports what the join items' main functions need, and nothing their parts need", () => {
    const extra = (name: string, imports: string[]) => ({ kind: 'extra' as const, name, type: 'X', default: 'null', apply: '', imports })
    const item: ComponentModel = { ...KBD, functions: [
      { ...KBD.functions[0], joinItem: true, parameters: [extra('type', ['kotlinx.html.ButtonType'])] },
      { ...KBD.functions[0], kind: 'part', name: 'daisyKbdPart', class: 'kbd-part', parameters: [extra('method', ['kotlinx.html.FormMethod'])] },
    ] }
    const join: ComponentModel = { component: 'join', name: 'Join', class: 'join', enums: [], joinScope: true, functions: [{ ...KBD.functions[0], name: 'daisyJoin', element: 'DIV', class: 'join', text: false, parameters: [] }] }

    const [, joined] = withDeclaredScopes([item, join].map(model => ({ model, shape: shapeFromModel(model, DESCRIPTIONS) })))

    assert.deepEqual(joined.scope?.imports, ['kotlinx.html.ButtonType'])
  })

  test("carries an extra's sample onto the shape only when it declares one", () => {
    const extra = (name: string, sample?: string) => ({ kind: 'extra' as const, name, type: 'Foo?', default: 'null', apply: '', ...(sample ? { sample } : {}) })
    const parameters = shapeFromModel({ ...KBD, enums: [], functions: [{ ...KBD.functions[0], parameters: [extra('a', 'Foo.A'), extra('b')] }] }, DESCRIPTIONS).functions[0].parameters
    assert.deepEqual(parameters.filter(parameter => parameter.name === 'a' || parameter.name === 'b').map(parameter => 'sample' in parameter && parameter.sample), ['Foo.A', false])
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

  test('keeps the classes an extra parameter writes out of the body config', () => {
    const disabled = { name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) { this.disabled = true; addClassNames("btn-disabled") }' }
    const button: ComponentModel = { ...KBD, enums: [], functions: [{ ...KBD.functions[0], parameters: [{ kind: 'extra', ...disabled, writes: ['btn-disabled'] }] }] }

    assert.deepEqual(mainFunctionConfig(button).extras, [disabled])
  })

  test('a void element takes the same optional block, on its own element', () => {
    const image: ComponentModel = { ...KBD, enums: [], functions: [{ ...KBD.functions[0], element: 'IMG', parameters: [] }] }

    assert.deepEqual(shapeFromModel(image, DESCRIPTIONS).functions[0].parameters.at(-1), { name: 'block', type: '(IMG.() -> Unit)', default: '{}', doc: "Writes the element's attributes, and its children where it can hold any" })
  })

  test('calls the three kotlinx.html builders whose names differ from their tag', () => {
    const builderOf = (element: string) => shapeFromModel({ ...KBD, enums: [], functions: [{ ...KBD.functions[0], element, parameters: [] }] }, DESCRIPTIONS).functions[0].tagBuilder

    assert.deepEqual(['FIELDSET', 'TEXTAREA', 'DIV'].map(builderOf), ['fieldSet', 'textArea', 'div'])
  })

  test("names an enum's entries after the class of the part that owns it", () => {
    const steps: ComponentModel = { ...KBD, class: 'steps', enums: [{ name: 'StepColor', label: 'Color variants', classes: ['step-primary'] }], functions: [
      { ...KBD.functions[0], class: 'steps', parameters: [] },
      { ...KBD.functions[0], kind: 'part', name: 'daisyStep', class: 'step', parameters: [{ kind: 'enum', name: 'color', enum: 'StepColor', doc: '' }] },
    ] }

    assert.equal(shapeFromModel(steps, DESCRIPTIONS).enums[0].entries[0].name, 'Primary')
  })

  test('hands the body the main function even where a part is declared before it', () => {
    const model = { ...component('steps', 'Steps', 'steps', {}, { text: true }), functions: [part('daisyStep', 'step'), mainFunction('daisySteps', 'steps', { text: true })] }

    assert.equal(mainFunctionConfig(model).hasTextParam, true)
  })
})

describe('entryName', () => {
  const owned = { ...component('step', 'Steps', 'steps'), functions: [
    mainFunction('daisySteps', 'steps', { parameters: [toggle('vertical', 'steps-vertical')] }),
    part('daisyStep', 'step', { parameters: [enumParameter('color', 'StepColor', 'Color')] }),
  ] }

  test("strips the class of the function that owns the enum, not of the first function with parameters", () => {
    assert.equal(entryName(owned, 'StepColor', 'step-primary'), 'Primary')
  })

  test("falls back to the component's class where no function owns the enum, or the owner's does not fit", () => {
    assert.equal(entryName(owned, 'Unowned', 'steps-wide'), 'Wide')
    assert.equal(entryName(owned, 'StepColor', 'steps-neutral'), 'Neutral')
  })

  test("falls back to the component's class where the owning function has no class of its own", () => {
    const classless = { ...owned, functions: [owned.functions[0], { ...owned.functions[1], kind: 'classes' as const, class: null }] }

    assert.equal(entryName(classless, 'StepColor', 'steps-neutral'), 'Neutral')
  })

  test('keeps a class that fits neither', () => {
    assert.equal(entryName(owned, 'StepColor', 'join-item'), 'JoinItem')
  })
})

