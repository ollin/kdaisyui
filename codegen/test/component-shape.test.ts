import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { buildComponentShape } from '../src/component-shape.ts'
import type { ClassifiedComponent } from '../src/classifier.ts'

/**
 * The shape both emitters read.
 *
 * These are not characterization tests of the Kotlin output — `generated-sources-drift` already
 * pins that, byte for byte, and did so through the extraction this file arrived with. They pin
 * the CONTRACT the Markdown emitter is about to depend on, so a change to parameter order or to
 * the part-element heuristic fails here rather than silently rewriting 66 documentation pages.
 *
 * Fixtures are hand-built rather than read from the submodule, like `frontmatter.test.ts`: a
 * test that needs `git submodule update` is a test that does not run.
 */

function classified(overrides: Partial<ClassifiedComponent> = {}): ClassifiedComponent {
  return {
    componentName: 'Card',
    componentClass: 'Card',
    desc: 'Cards group content.',
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

const names = (parameters: readonly { name: string }[]) => parameters.map(p => p.name)

describe('buildComponentShape', () => {
  test('names the main function and its receiver', () => {
    const shape = buildComponentShape(classified(), { componentDir: 'card', element: 'DIV' }, {})

    assert.equal(shape.functions.length, 1)
    assert.equal(shape.functions[0].kind, 'main')
    assert.equal(shape.functions[0].name, 'daisyCard')
    assert.equal(shape.functions[0].receiver, 'FlowContent')
  })

  test('upper-cases the element so config may be written in either case', () => {
    const shape = buildComponentShape(classified(), { componentDir: 'card', element: 'details' }, {})

    assert.equal(shape.functions[0].element, 'DETAILS')
    assert.equal(shape.functions[0].tagBuilder, 'details')
  })

  test('falls back to DIV when no element was resolved', () => {
    const shape = buildComponentShape(classified(), { componentDir: 'card', element: undefined }, {})

    assert.equal(shape.functions[0].element, 'DIV')
  })

  test('translates the three kotlinx.html builders that differ from their tag class', () => {
    for (const [element, builder] of [['FIELDSET', 'fieldSet'], ['INPUT', 'input'], ['TEXTAREA', 'textArea']]) {
      const shape = buildComponentShape(classified(), { componentDir: 'x', element }, {})
      assert.equal(shape.functions[0].tagBuilder, builder)
    }
  })

  test('orders main parameters: text, id, variant, size, booleans, extras, escape hatches', () => {
    const shape = buildComponentShape(
      classified({
        componentName: 'Button',
        prefix: 'btn',
        colors: ['primary'],
        sizes: ['sm'],
        styles: ['outline'],
        modifiers: ['wide'],
      }),
      { componentDir: 'button', element: 'BUTTON' },
      {
        textParams: ['button'],
        extras: { button: [{ name: 'disabled', type: 'Boolean', default: 'false', apply: 'x' }] },
      },
    )

    assert.deepEqual(names(shape.functions[0].parameters), [
      'text', 'id', 'variant', 'size', 'outline', 'wide', 'disabled', 'extraClasses', 'attrs', 'content',
    ])
  })

  test('sorts the booleans, whatever category they came from', () => {
    const shape = buildComponentShape(
      classified({ styles: ['soft'], modifiers: ['dash'], behaviors: ['active'], placements: ['top'] }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )

    assert.deepEqual(names(shape.functions[0].parameters).slice(1, 5), ['active', 'dash', 'soft', 'top'])
  })

  test('drops a boolean an extras entry already covers', () => {
    const shape = buildComponentShape(
      classified({ styles: ['outline'] }),
      { componentDir: 'card', element: 'DIV' },
      { extras: { card: [{ name: 'outline', type: 'Boolean', default: 'false', apply: 'x' }] } },
    )

    const outlines = shape.functions[0].parameters.filter(p => p.name === 'outline')
    assert.equal(outlines.length, 1)
    // The extras entry won, so it carries no description even though a class of the same name did.
    assert.equal(outlines[0].doc, null)
  })

  test('adds an additionalBooleans entry that no class category declared', () => {
    const shape = buildComponentShape(
      classified(),
      { componentDir: 'card', element: 'DIV' },
      { additionalBooleans: { card: ['active'] } },
    )

    assert.ok(names(shape.functions[0].parameters).includes('active'))
  })

  test('escapes a boolean whose camelCase name is a Kotlin keyword', () => {
    const shape = buildComponentShape(
      classified({ modifiers: ['object'] }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )

    assert.ok(names(shape.functions[0].parameters).includes('_object'))
  })

  test('carries a class description onto its boolean parameter', () => {
    const shape = buildComponentShape(
      classified({ modifiers: ['dash'], descs: { dash: 'dash style' } }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )

    const dash = shape.functions[0].parameters.find(p => p.name === 'dash')
    assert.equal(dash?.doc, 'dash style')
  })

  test('content is required, and becomes optional only when text competes with it', () => {
    const required = buildComponentShape(classified(), { componentDir: 'card', element: 'DIV' }, {})
    const optional = buildComponentShape(classified(), { componentDir: 'card', element: 'DIV' }, { textParams: ['card'] })

    assert.equal(required.functions[0].parameters.at(-1)?.default, null)
    assert.equal(required.functions[0].parameters.at(-1)?.type, '(DIV.() -> Unit)')
    assert.equal(optional.functions[0].parameters.at(-1)?.default, 'null')
    assert.equal(optional.functions[0].parameters.at(-1)?.type, '(DIV.() -> Unit)?')
  })

  test('a void element takes no content parameter, whatever the config says', () => {
    // The three defects this replaced: `file-input` and `theme-controller` were listed under a
    // key the lookup never used, and `mask` — an <img> — was never listed at all.
    for (const element of ['INPUT', 'IMG', 'BR', 'HR']) {
      const shape = buildComponentShape(classified(), { componentDir: 'x', element }, {})
      assert.deepEqual(
        names(shape.functions[0].parameters),
        ['id', 'extraClasses', 'attrs'],
        `<${element.toLowerCase()}> must not take content`,
      )
    }
  })

  test('an element that may have children keeps its content parameter', () => {
    for (const element of ['DIV', 'LABEL', 'SELECT', 'TEXTAREA']) {
      const shape = buildComponentShape(classified(), { componentDir: 'x', element }, {})
      assert.ok(
        names(shape.functions[0].parameters).includes('content'),
        `<${element.toLowerCase()}> must take content`,
      )
    }
  })

  test('a void element suppresses content even where a text shortcut exists', () => {
    const shape = buildComponentShape(classified(), { componentDir: 'card', element: 'INPUT' }, { textParams: ['card'] })

    assert.deepEqual(names(shape.functions[0].parameters), ['text', 'id', 'extraClasses', 'attrs'])
  })

  test('reports componentAttributes as the main function static attributes', () => {
    const shape = buildComponentShape(
      classified({ componentName: 'Megamenu', prefix: 'megamenu' }),
      { componentDir: 'megamenu', element: 'DIV' },
      { componentAttributes: { megamenu: { popover: '' } } },
    )

    assert.deepEqual(shape.functions[0].staticAttributes, [['popover', '']])
  })

  test('emits one part function per part class, prefix stripped from the name', () => {
    const shape = buildComponentShape(
      classified({ parts: ['card-title', 'card-body'] }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )

    assert.deepEqual(shape.functions.map(f => f.name), ['daisyCard', 'daisyCardTitle', 'daisyCardBody'])
    assert.deepEqual(shape.functions.map(f => f.kind), ['main', 'part', 'part'])
  })

  test('infers a part element from its name, and lets subComponentElements override', () => {
    const inferred = buildComponentShape(
      classified({ parts: ['card-title', 'drawer-overlay', 'card-body'] }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )
    assert.deepEqual(inferred.functions.slice(1).map(f => f.element), ['H2', 'LABEL', 'DIV'])

    const overridden = buildComponentShape(
      classified({ parts: ['megamenu-active'] }),
      { componentDir: 'megamenu', element: 'DIV' },
      { subComponentElements: { 'megamenu-active': 'SPAN' } },
    )
    assert.equal(overridden.functions[1].element, 'SPAN')
  })

  test('a part whose name contains "title" takes a text shortcut', () => {
    const shape = buildComponentShape(
      classified({ parts: ['card-title', 'card-body'] }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )

    assert.deepEqual(names(shape.functions[1].parameters), ['text', 'id', 'extraClasses', 'attrs', 'content'])
    assert.deepEqual(names(shape.functions[2].parameters), ['id', 'extraClasses', 'attrs', 'content'])
  })

  test('a part description never resolves, because descs is keyed without the prefix', () => {
    // Pinned as a known defect, not as desirable behaviour: `parts` holds `card-title` while
    // `descs` holds `title`, so no part has ever carried its DaisyUI description. Fixing it
    // changes the generated output of every component with parts, so it needs its own commit.
    const shape = buildComponentShape(
      classified({ parts: ['card-title'], descs: { title: 'Title of the card' } }),
      { componentDir: 'card', element: 'DIV' },
      {},
    )

    assert.equal(shape.functions[1].desc, '')
  })

  test('a custom part may declare its own receiver, element and static attributes', () => {
    const shape = buildComponentShape(
      classified({ componentName: 'Modal', prefix: 'modal' }),
      { componentDir: 'modal', element: 'DIALOG' },
      {
        customParts: {
          modal: [{ name: 'Popover', element: 'DIV', cssClass: 'modal', staticAttributes: { popover: '' } }],
        },
      },
    )

    const custom = shape.functions[1]
    assert.equal(custom.kind, 'custom')
    assert.equal(custom.name, 'daisyModalPopover')
    assert.equal(custom.receiver, 'FlowContent')
    assert.equal(custom.element, 'DIV')
    assert.deepEqual(custom.staticAttributes, [['popover', '']])
  })

  test('a custom part with no cssClass is a structural wrapper', () => {
    const shape = buildComponentShape(
      classified(),
      { componentDir: 'card', element: 'DIV' },
      { customParts: { card: [{ name: 'Wrapper', element: 'DIV' }] } },
    )

    assert.equal(shape.functions[1].cssClass, null)
  })

  test('builds one enum per populated category, named after the component', () => {
    const shape = buildComponentShape(
      classified({
        componentName: 'Badge',
        prefix: 'badge',
        colors: ['primary'],
        sizes: ['xs'],
        descs: { xs: 'Extra small size' },
      }),
      { componentDir: 'badge', element: 'SPAN' },
      {},
    )

    assert.deepEqual(shape.enums.map(e => e.name), ['BadgeVariant', 'BadgeSize'])
    assert.deepEqual(shape.enums.map(e => e.categoryLabel), ['Color variants', 'Size variants'])
    assert.deepEqual(shape.enums[1].entries, [{ name: 'Xs', cssClass: 'badge-xs', desc: 'Extra small size' }])
    // The class-level doc comment keys on the COMPONENT having any descriptions, not this enum's.
    assert.equal(shape.enums[0].documented, true)
  })

  test('omits enums for empty categories', () => {
    const shape = buildComponentShape(classified(), { componentDir: 'card', element: 'DIV' }, {})

    assert.deepEqual(shape.enums, [])
  })

  test('passes the DaisyUI directory through rather than lower-casing the Pascal name', () => {
    const shape = buildComponentShape(
      classified({ componentName: 'FileInput', prefix: 'file-input' }),
      { componentDir: 'file-input', element: 'INPUT' },
      {},
    )

    assert.equal(shape.componentDir, 'file-input')
  })
})
