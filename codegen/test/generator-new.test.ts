import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { generateKotlinFile } from '../src/generator-new.ts'
import { allBooleans, buildComponentShape } from '../src/component-shape.ts'
import { withJoinScope } from '../src/join-scope.ts'
import type { FunctionShape } from '../src/component-shape.ts'
import type { ComponentSource } from '../src/component-shape.ts'
import type { GroupClassification } from '../src/class-groups.ts'
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

/**
 * The emitter takes a finished shape; these cases describe a component, so they build one.
 *
 * The shape is built OUTSIDE the emitter because the join scope is attached to it after the
 * per-component build — a shape rebuilt inside the emitter could not carry it.
 */
function kotlinFor(
  component: ClassifiedComponent,
  source: ComponentSource,
  config = {},
  groups: GroupClassification = allBooleans(component),
): string {
  return generateKotlinFile(buildComponentShape(component, source, config, groups), config)
}

describe('the // Source: attribution', () => {
  test('cites the DaisyUI directory it was read from', () => {
    // Lower-casing the PascalCase name gave `components/fileinput/`, which does not exist.
    // Ten of 66 components are multi-word and every one of them cited nothing.
    const kotlin = kotlinFor(classified(), { componentDir: 'file-input', element: 'INPUT' }, {})

    assert.match(kotlin, /^\/\/ Source: daisyui\/packages\/docs\/src\/routes\/\(routes\)\/components\/file-input\/\+page\.md$/m)
    assert.ok(!kotlin.includes('components/fileinput/'))
  })

  test('leaves a single-word citation alone', () => {
    const kotlin = kotlinFor(
      classified({ componentName: 'Card', componentClass: 'Card', prefix: 'card' }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )

    assert.match(kotlin, /components\/card\/\+page\.md/)
  })
})

describe('the class values a body applies', () => {
  /** Indicator's placements: documented on the <span> its item renders, not on the container. */
  const indicator = () =>
    kotlinFor(
      classified({ componentName: 'Indicator', componentClass: 'Indicator', prefix: 'indicator', placements: ['top', 'bottom'], parts: ['indicator-item'] }),
      {
        componentDir: 'indicator',
        element: 'DIV',
        documentedElements: new Map([
          ['indicator', new Map([['DIV', 1]])],
          ['indicator-item', new Map([['SPAN', 1]])],
          ['indicator-top', new Map([['SPAN', 1]])],
          ['indicator-bottom', new Map([['SPAN', 1]])],
        ]),
      },
      { subComponentElements: { 'indicator-item': 'span' } },
      { enums: [{ enumName: 'IndicatorPlacement', parameterName: 'placement', category: 'placements', members: ['top', 'bottom'] }], booleans: [] },
    )

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
    const kotlin = kotlinFor(
      classified({ componentName: 'Fieldset', componentClass: 'Fieldset', prefix: 'fieldset' }),
      { componentDir: 'fieldset', element: 'FIELDSET' },
      {},
    )

    assert.match(kotlin, /Renders `<fieldset class="fieldset \.\.\.">`\./)
    assert.ok(!kotlin.includes('<fieldSet class'))
  })

  test('still CALLS the builder, which is a different name', () => {
    const kotlin = kotlinFor(
      classified({ componentName: 'Textarea', componentClass: 'Textarea', prefix: 'textarea' }),
      { componentDir: 'textarea', element: 'TEXTAREA' },
      {},
    )

    assert.match(kotlin, /Renders `<textarea class="textarea \.\.\.">`\./)
    assert.match(kotlin, /^ {4}textArea \{$/m)
    assert.match(kotlin, /attrs: \(TEXTAREA\.\(\) -> Unit\)\? = null,/)
  })

  test('leaves a part comment on the element too', () => {
    const kotlin = kotlinFor(
      classified({ componentName: 'Fieldset', componentClass: 'Fieldset', prefix: 'fieldset', parts: ['fieldset-legend'] }),
      { componentDir: 'fieldset', element: 'FIELDSET' },
      {},
    )

    assert.ok(!kotlin.includes('<fieldSet'))
  })
})

describe('the join scope', () => {
  /** A button reduced to the two parameters that show how a member mirrors and delegates. */
  const button = (): FunctionShape =>
    buildComponentShape(
      classified({ componentName: 'Button', componentClass: 'Button', prefix: 'btn' }),
      { componentDir: 'button', element: 'BUTTON' },
      {},
      allBooleans(classified({ componentName: 'Button', componentClass: 'Button', prefix: 'btn' })),
    ).functions[0]

  const joined = () =>
    generateKotlinFile(
      withJoinScope(
        buildComponentShape(
          classified({ componentName: 'Join', componentClass: 'Join', prefix: 'join' }),
          { componentDir: 'join', element: 'DIV' },
          {},
          allBooleans(classified({ componentName: 'Join', componentClass: 'Join', prefix: 'join' })),
        ),
        [button()],
      ),
      {},
    )

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
    assert.match(kotlinFor(classified({ componentName: 'Card', componentClass: 'Card', prefix: 'card' }), { componentDir: 'card', element: 'DIV' }), /^ {4}div \{$/m)
  })
})
