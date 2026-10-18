import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { docFileNameFor, generateComponentPage, generateIndexPage } from '../src/generator-docs.ts'
import { withDeclaredScopes, type ComponentModel, type Descriptions } from '../src/model.ts'
import { component, enumParameter, part, shapeOf, toggle } from './model-fixture.ts'

/**
 * The reference page emitter.
 *
 * Written against the four shapes the change's section 1 picked out as the ones most likely to
 * break a template, and the `card` expectation is the one task 3.1 names: a component with
 * several parts and an enum. Fixtures are hand-built models, so these run without the submodule.
 */

const CARD_DESCRIPTIONS: Descriptions = {
  desc: 'Cards are used to group and display content in a way that is easily readable.',
  classes: {
    border: 'Adds border to <card>',
    dash: 'dash style',
    'image-full': 'The image in <figure> element will be the background',
    side: 'The image in <figure> will be on to the side',
  },
}

const CARD: ComponentModel = {
  ...component('card', 'Card', 'card'),
  enums: [{ name: 'CardSize', label: 'Size variants', classes: ['card-xs', 'card-sm', 'card-md', 'card-lg', 'card-xl'] }],
  functions: [
    { ...component('card', 'Card', 'card').functions[0], parameters: [
      enumParameter('size', 'CardSize', 'Size variant'),
      toggle('border', 'card-border'),
      toggle('dash', 'card-dash'),
      toggle('imageFull', 'card-image-full'),
      toggle('side', 'card-side'),
    ] },
    part('daisyCardTitle', 'card-title', { element: 'H2', text: true }),
    part('daisyCardBody', 'card-body'),
    part('daisyCardActions', 'card-actions'),
  ],
}

const page = (model: ComponentModel, summary?, descriptions: Descriptions = CARD_DESCRIPTIONS) =>
  generateComponentPage(shapeOf(model, descriptions), summary)

const withParts = (model: ComponentModel, ...parts: ComponentModel['functions']): ComponentModel => ({ ...model, functions: [...model.functions, ...parts] })

const BADGE = (sizes: string[]): ComponentModel => ({
  ...component('badge', 'Badge', 'badge', {}, { element: 'SPAN', parameters: [enumParameter('variant', 'BadgeVariant', 'Color variant'), enumParameter('size', 'BadgeSize', 'Size variant')] }),
  enums: [
    { name: 'BadgeVariant', label: 'Color variants', classes: ['badge-primary', 'badge-secondary'] },
    { name: 'BadgeSize', label: 'Size variants', classes: sizes.map(size => `badge-${size}`) },
  ],
})

describe('docFileNameFor', () => {
  test('camel-cases a kebab directory name, which is how index.md links the file', () => {
    assert.equal(docFileNameFor('file-input'), 'fileInput')
    assert.equal(docFileNameFor('card'), 'card')
    assert.equal(docFileNameFor('mockup-browser'), 'mockupBrowser')
  })
})

describe('generateComponentPage', () => {
  test('emits the card page', () => {
    const expected = `<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/card/+page.md
Regenerate: just generate
-->

# Card

[DaisyUI documentation →](https://daisyui.com/components/card/)

Content containers with body and title. Renders \`<div class="card ...">\`.

\`\`\`kotlin
// CardSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyCard(
    id: HtmlId? = null,
    size: ClassValues<CardSize>? = null,
    border: Boolean = false,  // Adds border to <card>
    dash: Boolean = false,  // dash style
    imageFull: Boolean = false,  // The image in <figure> element will be the background
    side: Boolean = false,  // The image in <figure> will be on to the side
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCardClasses(size: ClassValues<CardSize>? = null, border: Boolean = false, dash: Boolean = false, imageFull: Boolean = false, side: Boolean = false)
\`\`\`

\`\`\`kotlin
fun FlowContent.daisyCardTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCardTitleClasses()
\`\`\`

\`\`\`kotlin
fun FlowContent.daisyCardBody(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCardBodyClasses()
\`\`\`

\`\`\`kotlin
fun FlowContent.daisyCardActions(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyCardActionsClasses()
\`\`\`
`

    assert.equal(page(CARD, { summary: 'Content containers with body and title' }), expected)
  })

  test('documents the element a part actually renders, not a guess', () => {
    // The defect that motivated the change: `megamenu.md` said DIV where the code says SPAN.
    const result = page(
      withParts(component('megamenu', 'Megamenu', 'megamenu'), part('daisyMegamenuActive', 'megamenu-active', { element: 'SPAN' })),
      { summary: 'Horizontal menu with popover navigation blocks' },
    )

    assert.match(result, /fun FlowContent\.daisyMegamenuActive\(\n {4}id: HtmlId\? = null,\n {4}extraClasses: String\? = null,\n {4}attrs: \(SPAN\.\(\) -> Unit\)\? = null,\n {4}content: \(SPAN\.\(\) -> Unit\),\n\)/)
  })

  test('names a static attribute in the renders clause', () => {
    const result = page(
      component('megamenu', 'Megamenu', 'megamenu', {}, { attributes: { popover: '' } }),
      { summary: 'Horizontal menu with popover navigation blocks' },
    )

    assert.match(result, /Renders `<div class="megamenu \.\.\." popover>`\./)
  })

  test('documents a custom part, which a hand-written page can simply forget', () => {
    // `drawer.md` omitted `daisyDrawerButton`; `megamenu.md` omitted `daisyMegamenuPanel`.
    const result = page(
      withParts(component('drawer', 'Drawer', 'drawer'), { ...part('daisyDrawerButton', 'drawer-button'), kind: 'custom' }),
      { summary: 'Sidebar layout with toggle' },
    )

    assert.match(result, /fun FlowContent\.daisyDrawerButton\(/)
  })

  test('a component with no parts emits exactly one block', () => {
    const result = page(BADGE(['xs']), { summary: 'Labels, counts, and status tags' })

    assert.equal(result.match(/```kotlin/g)?.length, 1)
  })

  test('gives each enum its own comment line', () => {
    const result = page(BADGE(['xs', 'sm']), { summary: 'Labels' })

    assert.match(result, /\/\/ BadgeVariant: Primary \| Secondary\n\/\/ BadgeSize: Xs \| Sm\n/)
  })

  test('prefers the longer description where one is committed', () => {
    const result = page(CARD, {
      summary: 'Short one',
      description: 'The longer paragraph, for the three pages that say more than their table row',
    })

    assert.match(result, /^The longer paragraph, .* row\. Renders/m)
    assert.ok(!result.includes('Short one'))
  })

  test('falls back to the first sentence of the DaisyUI description when none is committed', () => {
    const result = page(CARD, undefined)

    assert.match(result, /^Cards are used to group and display content in a way that is easily readable\. Renders/m)
  })

  test('emits only the renders clause when there is no description at all', () => {
    const result = page(component('card', 'Card', 'card'), undefined, { desc: '', classes: {} })

    assert.match(result, /^Renders `<div class="card \.\.\.">`\.$/m)
  })

  test('does not comment the escape-hatch parameters, only the DaisyUI booleans', () => {
    const result = page(CARD, { summary: 'x' })

    assert.ok(!result.includes('// Type-safe HTML id'))
    assert.ok(!result.includes('// Additional CSS classes'))
    assert.ok(result.includes('// dash style'))
  })

  test('names the HTML element, not the kotlinx.html builder', () => {
    // `<fieldSet>` is not an HTML element. The lambda receiver type still is `FIELDSET`.
    const result = page(component('fieldset', 'Fieldset', 'fieldset', {}, { element: 'FIELDSET' }), {
      summary: 'Groups related form controls',
    })

    assert.match(result, /Renders `<fieldset class="fieldset \.\.\.">`\./)
    assert.ok(!result.includes('<fieldSet class'))
    assert.match(result, /attrs: \(FIELDSET\.\(\) -> Unit\)\? = null,/)
  })
})

describe('generateIndexPage', () => {
  const shape = (name: string, dir: string, element: string) => shapeOf(component(dir, name, dir, {}, { element }))

  test('emits one row per component, with its summary and rendered element', () => {
    const result = generateIndexPage([shape('Card', 'card', 'DIV')], {
      card: { summary: 'Content containers with body and title' },
    })

    assert.match(result, /^\| Component \| Description \| Tag \|$/m)
    assert.match(result, /^\| \[Card\]\(card\.md\) \| Content containers with body and title \| `<div>` \|$/m)
  })

  test('links the camelCase file name for a multi-word component', () => {
    const result = generateIndexPage([shape('FileInput', 'file-input', 'INPUT')], {
      'file-input': { summary: 'File upload input field' },
    })

    assert.match(result, /\| \[FileInput\]\(fileInput\.md\) \|/)
  })

  test('uses the short summary even where a longer page description exists', () => {
    const result = generateIndexPage([shape('Megamenu', 'megamenu', 'DIV')], {
      megamenu: { summary: 'Horizontal menu with popover navigation blocks', description: 'A much longer paragraph' },
    })

    assert.match(result, /\| Horizontal menu with popover navigation blocks \|/)
    assert.ok(!result.includes('A much longer paragraph'))
  })

  test('sorts case-insensitively by display name, which directory order gets wrong', () => {
    const result = generateIndexPage(
      [
        shape('TextRotate', 'text-rotate', 'SPAN'),
        shape('Textarea', 'textarea', 'TEXTAREA'),
        shape('HoverGallery', 'hover-gallery', 'FIGURE'),
        shape('Hover3d', 'hover-3d', 'DIV'),
      ],
      {},
    )

    const order = [...result.matchAll(/^\| \[(\w+)\]/gm)].map(m => m[1])
    assert.deepEqual(order, ['Hover3d', 'HoverGallery', 'Textarea', 'TextRotate'])
  })

  test('names the HTML element in the tag column', () => {
    const result = generateIndexPage([shape('Textarea', 'textarea', 'TEXTAREA')], {})

    assert.match(result, /\| `<textarea>` \|$/m)
  })

  test('carries the do-not-edit attribution', () => {
    const result = generateIndexPage([shape('Card', 'card', 'DIV')], {})

    assert.match(result, /^<!--\nGENERATED — DO NOT EDIT\n/)
  })
})

describe('a component whose content runs in a scope', () => {
  const page = () => {
    const button = component('button', 'Button', 'btn', {}, { element: 'BUTTON', joinItem: true })
    const join = component('join', 'Join', 'join', { joinScope: true })
    const [, joined] = withDeclaredScopes([button, join].map(model => ({ model, shape: shapeOf(model) })))
    return generateComponentPage(joined, undefined)
  }

  test('shows the call that is marked', () => {
    assert.match(page(), /^ {4}daisyButton\(\) {2,}\/\/ marked `join-item`$/m)
  })

  test('shows the nested call that is NOT marked, which is the surprising one', () => {
    assert.match(page(), /^ {8}daisyButton\(\) {2,}\/\/ NOT marked/m)
  })

  test('shows how to mark a nested call deliberately', () => {
    assert.match(page(), /this@daisyJoin\.daisyButton\(\)/)
  })

  test('names every member, so the page says which calls this applies to', () => {
    assert.match(page(), /`daisyButton`/)
  })

  test('says why there is no parameter for it, naming both classes', () => {
    assert.match(page(), /`\.join` writes four corner-radius/)
    assert.match(page(), /`\.join-item` reads them/)
    assert.match(page(), /outside a\n`\.join` the class strips the element's own corners/)
  })

  test('interpolates every placeholder', () => {
    // A single-quoted line among template literals shipped a literal `${shape.prefix}` onto
    // the page. Nothing else would have caught it: the surrounding assertions matched the
    // prose on either side of it.
    assert.ok(!page().includes('${'))
  })

  test('a component without a scope grows no such section', () => {
    const plain = generateComponentPage(shapeOf(component('card', 'Card', 'card'), CARD_DESCRIPTIONS), undefined)

    assert.ok(!plain.includes('join-item'))
    assert.ok(!plain.includes('## Join items'))
  })
})

describe('the class sibling on a reference page', () => {
  test('follows its function in the same block', () => {
    const page = generateComponentPage(shapeOf(component('kbd', 'Kbd', 'kbd')), undefined)

    assert.ok(page.includes(")\n\n// The same classes on an element you write yourself, or beside another component's:\nfun Tag.daisyKbdClasses()\n```"))
  })
})
