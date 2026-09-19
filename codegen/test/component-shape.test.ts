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

/** `documentedElements` as the parser hands it over: each element a class is shown on, once. */
const sets = (byClass: Record<string, string[]>) =>
  new Map(Object.entries(byClass).map(([cls, elements]) => [cls, new Map(elements.map(element => [element, 1]))]))

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

  test('declares a boolean on the part whose element DaisyUI documents the class on', () => {
    // `dock-active` is shown on the <button> that `daisyDockItem` renders, never on the
    // container's <div>. The parameter belongs to the item.
    const shape = buildComponentShape(
      classified({ componentName: 'Dock', prefix: 'dock', modifiers: ['active'], parts: ['dock-item'] }),
      {
        componentDir: 'dock',
        element: 'DIV',
        documentedElements: sets({ dock: ['DIV'], 'dock-active': ['BUTTON'], 'dock-item': ['BUTTON'] }),
      },
      { subComponentElements: { 'dock-item': 'button' } },
    )
    const [main, item] = shape.functions

    assert.ok(!names(main.parameters).includes('active'))
    assert.ok(names(item.parameters).includes('active'))
    assert.equal(item.parameters.find((p) => p.name === 'active')?.cssClass, 'dock-active')
  })

  test('keeps a boolean shown on the element DaisyUI shows the component on', () => {
    // `dropdown-close` is shown on the <div>-shaped dropdown. `daisyDropdown` renders <details>
    // and `daisyDropdownContent` renders a <div> — the same tag name, a different element.
    // The class is on the dropdown, so it stays on the dropdown's function.
    const shape = buildComponentShape(
      classified({ componentName: 'Dropdown', prefix: 'dropdown', modifiers: ['close'], parts: ['dropdown-content'] }),
      {
        componentDir: 'dropdown',
        element: 'DETAILS',
        documentedElements: sets({ dropdown: ['DIV', 'DETAILS'], 'dropdown-close': ['DIV'], 'dropdown-content': ['UL'] }),
      },
      {},
    )
    const [main, content] = shape.functions

    assert.ok(names(main.parameters).includes('close'))
    assert.ok(!names(content.parameters).includes('close'))
  })

  test('moves an enum whole to the part whose element DaisyUI shows every member on', () => {
    // `indicator-top` and `indicator-bottom` are shown on the <span> that `daisyIndicatorItem`
    // renders, never on the container's <div>. A choice is one parameter, so it travels whole
    // or not at all — a member alone would be half a choice on each function.
    const shape = buildComponentShape(
      classified({ componentName: 'Indicator', prefix: 'indicator', placements: ['top', 'bottom'], parts: ['indicator-item'] }),
      {
        componentDir: 'indicator',
        element: 'DIV',
        documentedElements: sets({
          indicator: ['DIV'],
          'indicator-item': ['SPAN'],
          'indicator-top': ['SPAN'],
          'indicator-bottom': ['SPAN'],
        }),
      },
      { subComponentElements: { 'indicator-item': 'span' } },
      { enums: [{ enumName: 'IndicatorPlacement', parameterName: 'placement', category: 'placements', members: ['top', 'bottom'] }], booleans: [] },
    )
    const [main, item] = shape.functions

    assert.ok(!names(main.parameters).includes('placement'))
    assert.ok(names(item.parameters).includes('placement'))
  })

  test('never moves an enum member; the enum stays whole on the main function', () => {
    const shape = buildComponentShape(
      classified({ componentName: 'Tab', prefix: 'tabs', placements: ['top', 'bottom'], parts: ['tab'] }),
      {
        componentDir: 'tab',
        element: 'DIV',
        documentedElements: sets({ tabs: ['DIV'], 'tabs-bottom': ['A'], tab: ['A'] }),
      },
      { subComponentElements: { tab: 'a' } },
      { enums: [{ enumName: 'TabPlacement', parameterName: 'placement', category: 'placements', members: ['top', 'bottom'] }], booleans: [] },
    )
    const [main, tab] = shape.functions

    assert.ok(names(main.parameters).includes('placement'))
    assert.ok(!names(tab.parameters).includes('bottom'))
  })

  test('declares a boolean on every part that renders its documented element', () => {
    // `timeline-box` is shown on `timeline-start` and `timeline-end`, both <div>s.
    const shape = buildComponentShape(
      classified({ componentName: 'Timeline', prefix: 'timeline', modifiers: ['box'], parts: ['timeline-start', 'timeline-end'] }),
      {
        componentDir: 'timeline',
        element: 'UL',
        documentedElements: sets({ timeline: ['UL'], 'timeline-box': ['DIV'] }),
      },
      {},
    )
    const [main, start, end] = shape.functions

    assert.ok(!names(main.parameters).includes('box'))
    assert.ok(names(start.parameters).includes('box'))
    assert.ok(names(end.parameters).includes('box'))
  })

  test('keeps a boolean on the main function when no part renders the documented element', () => {
    // Moving it to a part on a DIFFERENT wrong element would fix nothing; the cross-check
    // keeps reporting it until the part's element is right.
    const shape = buildComponentShape(
      classified({ componentName: 'Dock', prefix: 'dock', modifiers: ['active'], parts: ['dock-item'] }),
      {
        componentDir: 'dock',
        element: 'DIV',
        documentedElements: sets({ 'dock-active': ['BUTTON'] }),
      },
      {},
    )
    const [main, item] = shape.functions

    assert.ok(names(main.parameters).includes('active'))
    assert.ok(!names(item.parameters).includes('active'))
  })

  test('a boolean carries the class it emits; nothing else does', () => {
    // What the element cross-check will read: which class a parameter puts on the element.
    const shape = buildComponentShape(classified({ modifiers: ['side'] }), { componentDir: 'card', element: 'DIV' }, {})
    const [id, side, extraClasses] = shape.functions[0].parameters

    assert.equal(side.cssClass, 'card-side')
    assert.equal(id.cssClass, undefined)
    assert.equal(extraClasses.cssClass, undefined)
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

  test('a part renders the element DaisyUI shows it on, over the name heuristic', () => {
    // `hero-overlay` matched `overlay` → LABEL; DaisyUI shows a <div>. `footer-title` matched
    // `title` → H2; DaisyUI shows an <h6>.
    const shape = buildComponentShape(
      classified({ componentName: 'Footer', prefix: 'footer', parts: ['footer-overlay', 'footer-title'] }),
      {
        componentDir: 'footer',
        element: 'FOOTER',
        documentedElements: sets({ 'footer-overlay': ['DIV'], 'footer-title': ['H6'] }),
      },
      {},
    )

    assert.deepEqual(shape.functions.slice(1).map(f => f.element), ['DIV', 'H6'])
  })

  test('a part on an element only its parent can open takes that parent as receiver', () => {
    // kotlinx.html opens <legend> only inside FIELDSET. DaisyUI shows the parent, so the
    // function is `FIELDSET.daisyFieldsetLegend`, and `daisyFieldset { daisyFieldsetLegend {} }`
    // is the only way to write it — which is also the only way DaisyUI documents it.
    const shape = buildComponentShape(
      classified({ componentName: 'Fieldset', prefix: 'fieldset', parts: ['fieldset-legend'] }),
      {
        componentDir: 'fieldset',
        element: 'FIELDSET',
        documentedElements: sets({ 'fieldset-legend': ['LEGEND'] }),
        documentedParents: new Map([['fieldset-legend', 'FIELDSET']]),
      },
      {},
    )

    assert.equal(shape.functions[1].element, 'LEGEND')
    assert.equal(shape.functions[1].receiver, 'FIELDSET')
  })

  test('a part on such an element keeps the heuristic when its parent is not documented', () => {
    // Shown under two different parents, or at the top of an example: no receiver can be
    // derived, so the documented element would not compile. The heuristic stays and so does
    // the cross-check's exception.
    const shape = buildComponentShape(
      classified({ componentName: 'Fieldset', prefix: 'fieldset', parts: ['fieldset-legend'] }),
      { componentDir: 'fieldset', element: 'FIELDSET', documentedElements: sets({ 'fieldset-legend': ['LEGEND'] }) },
      {},
    )

    assert.equal(shape.functions[1].element, 'DIV')
    assert.equal(shape.functions[1].receiver, 'FlowContent')
  })

  test('a part shown on several elements renders the usual one', () => {
    // `indicator-item` is a <span> 26 times and a <div> once; one stray example does not
    // outvote the rest.
    const shape = buildComponentShape(
      classified({ componentName: 'Indicator', prefix: 'indicator', parts: ['indicator-item'] }),
      { componentDir: 'indicator', element: 'DIV', documentedElements: new Map([['indicator-item', new Map([['SPAN', 26], ['DIV', 1]])]]) },
      {},
    )

    assert.equal(shape.functions[1].element, 'SPAN')
  })

  test('a part shown equally often on two elements falls back to the heuristic', () => {
    const shape = buildComponentShape(
      classified({ parts: ['card-title'] }),
      { componentDir: 'card', element: 'DIV', documentedElements: sets({ 'card-title': ['H2', 'DIV'] }) },
      {},
    )

    assert.equal(shape.functions[1].element, 'H2')
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
    assert.deepEqual(custom.modifierClasses, [])
  })

  test('a class a custom part always writes is not also a boolean on the main function', () => {
    // Otherwise `skeleton-text` is askable twice: once as `daisySkeletonText`, which is
    // DaisyUI's markup, and once as `daisySkeleton(text = true)`, which is a <div> wearing a
    // class DaisyUI only ever shows on a <span>. Derived from the part's own declaration —
    // nothing to configure, and nothing that can disagree with it.
    const shape = buildComponentShape(
      classified({ componentName: 'Skeleton', prefix: 'skeleton', modifiers: ['text'] }),
      { componentDir: 'skeleton', element: 'DIV' },
      {
        customParts: {
          skeleton: [{ name: 'Text', element: 'SPAN', cssClass: 'skeleton', modifierClasses: ['skeleton-text'] }],
        },
      },
    )

    assert.ok(!names(shape.functions[0].parameters).includes('text'))
  })

  test('a custom part carries the modifier classes its construction always writes', () => {
    // DaisyUI shows `skeleton-text` on exactly one markup: `<span class="skeleton
    // skeleton-text">`. Two classes, so the function's own class cannot say it alone.
    const shape = buildComponentShape(
      classified({ componentName: 'Skeleton', prefix: 'skeleton' }),
      { componentDir: 'skeleton', element: 'DIV' },
      {
        customParts: {
          skeleton: [{ name: 'Text', element: 'SPAN', cssClass: 'skeleton', modifierClasses: ['skeleton-text'] }],
        },
      },
    )

    const custom = shape.functions[1]
    assert.equal(custom.name, 'daisySkeletonText')
    assert.equal(custom.cssClass, 'skeleton')
    assert.deepEqual(custom.modifierClasses, ['skeleton-text'])
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
