import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { generateKotlinFile } from '../src/generator-new.ts'
import type { ClassifiedComponent } from '../src/classifier.ts'

/**
 * What the generated Kotlin SAYS about itself.
 *
 * Both facts here were wrong in shipped output, and neither could be caught by anything else:
 * an attribution and a doc comment change no CSS class, so the generated tests and the drift
 * job are blind to them.
 */

function classified(overrides: Partial<ClassifiedComponent> = {}): ClassifiedComponent {
  return {
    componentName: 'FileInput',
    componentClass: 'FileInput',
    desc: '',
    prefix: 'file-input',
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

describe('the // Source: attribution', () => {
  test('cites the DaisyUI directory it was read from', () => {
    // Lower-casing the PascalCase name gave `components/fileinput/`, which does not exist.
    // Ten of 66 components are multi-word and every one of them cited nothing.
    const kotlin = generateKotlinFile(classified(), { componentDir: 'file-input', element: 'INPUT' }, {})

    assert.match(kotlin, /^\/\/ Source: daisyui\/packages\/docs\/src\/routes\/\(routes\)\/components\/file-input\/\+page\.md$/m)
    assert.ok(!kotlin.includes('components/fileinput/'))
  })

  test('leaves a single-word citation alone', () => {
    const kotlin = generateKotlinFile(
      classified({ componentName: 'Card', componentClass: 'Card', prefix: 'card' }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )

    assert.match(kotlin, /components\/card\/\+page\.md/)
  })
})

describe('the doc comment', () => {
  test('names the HTML element, not the kotlinx.html builder', () => {
    // `<fieldSet>` and `<textArea>` are not HTML elements. The reference pages already got
    // this right; only the Kotlin emitter conflated the builder with the element.
    const kotlin = generateKotlinFile(
      classified({ componentName: 'Fieldset', componentClass: 'Fieldset', prefix: 'fieldset' }),
      { componentDir: 'fieldset', element: 'FIELDSET' },
      {},
    )

    assert.match(kotlin, /Renders `<fieldset class="fieldset \.\.\.">`\./)
    assert.ok(!kotlin.includes('<fieldSet class'))
  })

  test('still CALLS the builder, which is a different name', () => {
    const kotlin = generateKotlinFile(
      classified({ componentName: 'Textarea', componentClass: 'Textarea', prefix: 'textarea' }),
      { componentDir: 'textarea', element: 'TEXTAREA' },
      {},
    )

    assert.match(kotlin, /Renders `<textarea class="textarea \.\.\.">`\./)
    assert.match(kotlin, /^ {4}textArea \{$/m)
    assert.match(kotlin, /attrs: \(TEXTAREA\.\(\) -> Unit\)\? = null,/)
  })

  test('leaves a part comment on the element too', () => {
    const kotlin = generateKotlinFile(
      classified({ componentName: 'Fieldset', componentClass: 'Fieldset', prefix: 'fieldset', parts: ['fieldset-legend'] }),
      { componentDir: 'fieldset', element: 'FIELDSET' },
      {},
    )

    assert.ok(!kotlin.includes('<fieldSet'))
  })
})
