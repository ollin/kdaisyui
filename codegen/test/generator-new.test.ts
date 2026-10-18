import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { generateKotlinFile } from '../src/generator-new.ts'
import { mainFunctionConfig, withDeclaredScopes, type ComponentModel } from '../src/model.ts'
import { component, enumParameter, part, shapeOf, toggle } from './model-fixture.ts'

/**
 * What the generated Kotlin SAYS about itself.
 *
 * Both facts here were wrong in shipped output, and neither could be caught by anything else:
 * an attribution and a doc comment change no CSS class, so the generated tests and the drift
 * job are blind to them.
 */

const kotlinFor = (model: ComponentModel): string => generateKotlinFile(shapeOf(model), mainFunctionConfig(model))

const withParts = (model: ComponentModel, ...parts: ComponentModel['functions']): ComponentModel => ({ ...model, functions: [...model.functions, ...parts] })

describe('the // Source: attribution', () => {
  test('cites the DaisyUI directory it was read from', () => {
    // Lower-casing the PascalCase name gave `components/fileinput/`, which does not exist.
    // Ten of 66 components are multi-word and every one of them cited nothing.
    const kotlin = kotlinFor(component('file-input', 'FileInput', 'file-input', {}, { element: 'INPUT' }))

    assert.match(kotlin, /^\/\/ Source: daisyui\/packages\/docs\/src\/routes\/\(routes\)\/components\/file-input\/\+page\.md$/m)
    assert.ok(!kotlin.includes('components/fileinput/'))
  })

  test('leaves a single-word citation alone', () => {
    const kotlin = kotlinFor(component('card', 'Card', 'card'))

    assert.match(kotlin, /components\/card\/\+page\.md/)
  })
})

describe('the class values a body applies', () => {
  /** Indicator's placements: documented on the <span> its item renders, not on the container. */
  const indicator = () =>
    kotlinFor(withParts(
      component('indicator', 'Indicator', 'indicator', { enums: [{ name: 'IndicatorPlacement', label: 'Placement variants', classes: ['indicator-top', 'indicator-bottom'] }] }),
      part('daisyIndicatorItem', 'indicator-item', { element: 'SPAN', parameters: [enumParameter('placement', 'IndicatorPlacement', 'Placement variant')] }),
    ))

  test('applies the enum in the function whose signature declares it', () => {
    // The signature moved and the body did not, so `daisyIndicator` read a parameter it no
    // longer had. Nothing in the codegen saw it; the generated Kotlin failed to compile.
    const [container, item] = indicator().split('fun FlowContent.daisyIndicatorItem')

    assert.ok(!container.includes('addClassNames(placement)'))
    assert.ok(item.includes('addClassNames(placement)'))
  })
})

describe('the doc comment', () => {
  test('names the HTML element, not the kotlinx.html builder', () => {
    // `<fieldSet>` and `<textArea>` are not HTML elements. The reference pages already got
    // this right; only the Kotlin emitter conflated the builder with the element.
    const kotlin = kotlinFor(component('fieldset', 'Fieldset', 'fieldset', {}, { element: 'FIELDSET' }))

    assert.match(kotlin, /Renders `<fieldset class="fieldset \.\.\.">`\./)
    assert.ok(!kotlin.includes('<fieldSet class'))
  })

  test('still CALLS the builder, which is a different name', () => {
    const kotlin = kotlinFor(component('textarea', 'Textarea', 'textarea', {}, { element: 'TEXTAREA' }))

    assert.match(kotlin, /Renders `<textarea class="textarea \.\.\.">`\./)
    assert.match(kotlin, /^ {4}textArea \{$/m)
    assert.match(kotlin, /attrs: \(TEXTAREA\.\(\) -> Unit\)\? = null,/)
  })

  test('leaves a part comment on the element too', () => {
    const kotlin = kotlinFor(withParts(
      component('fieldset', 'Fieldset', 'fieldset', {}, { element: 'FIELDSET' }),
      part('daisyFieldsetLegend', 'fieldset-legend'),
    ))

    assert.ok(!kotlin.includes('<fieldSet'))
  })
})

describe('the join scope', () => {
  /** A button with no parameters of its own, which is enough to show how a member mirrors and delegates. */
  const button = component('button', 'Button', 'btn', {}, { element: 'BUTTON', joinItem: true })
  const join = component('join', 'Join', 'join', { joinScope: true })

  const joined = () => {
    const [, shape] = withDeclaredScopes([button, join].map(model => ({ model, shape: shapeOf(model) })))
    return generateKotlinFile(shape, mainFunctionConfig(join))
  }

  test('the main function opens the scope instead of a plain div', () => {
    // The scope has to BE the element, not wrap it: the class it marks its children with is
    // read by CSS variables that `.join` writes onto its direct children.
    assert.match(joined(), /^ {4}JoinScope\(emptyMap\(\), consumer\)\.visit \{$/m)
  })

  test('the scope extends the element the component renders', () => {
    assert.match(joined(), /^class JoinScope internal constructor\($/m)
    assert.match(joined(), /\) : DIV\(initialAttributes, consumer\) \{$/m)
  })

  test('the tag builder is not imported, because nothing calls it any more', () => {
    // Positive control beside it: the element class IS still needed, for `attrs`.
    assert.match(joined(), /^import kotlinx\.html\.DIV$/m)
    assert.ok(!joined().includes('import kotlinx.html.div\n'))
  })

  test('a member delegates to the top-level function with the marker class added', () => {
    assert.match(joined(), /^ {8}flow\.daisyButton\($/m)
    assert.match(joined(), /^ {12}extraClasses = joinItem\(extraClasses\),$/m)
  })

  test('a member mirrors the top-level parameter list', () => {
    const scope = joined().split('class JoinScope')[1]

    assert.match(scope, /^ {4}fun daisyButton\($/m)
    assert.match(scope, /^ {8}attrs: \(BUTTON\.\(\) -> Unit\)\? = null,$/m)
  })

  test('the scope is the only way to write the marker class', () => {
    // No parameter anywhere emits it. `join-item` outside a join strips the element's own
    // corners, so a writable parameter would let a caller do that silently.
    assert.ok(!joined().includes('addClassNames("join-item")'))
  })

  test('a component with no scope opens its tag exactly as before', () => {
    assert.match(kotlinFor(component('card', 'Card', 'card')), /^ {4}div \{$/m)
  })
})

describe('the class sibling', () => {
  const button = component('button', 'Button', 'btn', { enums: [{ name: 'ButtonColor', label: 'Color variants', classes: ['btn-primary'] }] }, {
    parameters: [enumParameter('color', 'ButtonColor', 'Color variant'), toggle('active', 'btn-active'), { kind: 'extra', name: 'disabled', type: 'Boolean', default: 'false', apply: 'if (disabled) { this.disabled = true; addClassNames("btn-disabled") }', writes: ['btn-disabled'] }],
  })

  test("adds the function's classes from the same typed parameters, an extra's class as a toggle", () => {
    const sibling = kotlinFor(button).split('fun Tag.daisyButtonClasses')[1]

    assert.equal(sibling, `(
    color: ClassValues<ButtonColor>? = null,
    active: Boolean = false,
    disabled: Boolean = false,
) {
    addClassNames("btn")
    addClassNames(color)
    if (active) addClassNames("btn-active")
    if (disabled) addClassNames("btn-disabled")
}
`)
  })

  test('takes no parameters where its function offers no class', () => {
    assert.ok(kotlinFor(withParts(button, part('daisyButtonIcon', 'btn-icon'))).includes('fun Tag.daisyButtonIconClasses() {\n    addClassNames("btn-icon")\n}'))
  })
})
