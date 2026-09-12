import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { docFileNameFor, generateComponentPage } from '../src/generator-docs.ts'
import { buildComponentShape } from '../src/component-shape.ts'
import type { ClassifiedComponent } from '../src/classifier.ts'

/**
 * The reference page emitter.
 *
 * Written against the four shapes the change's section 1 picked out as the ones most likely to
 * break a template, and the `card` expectation is the one task 3.1 names: a component with
 * several parts and an enum. Fixtures are hand-built, so these run without the submodule.
 */

function classified(overrides: Partial<ClassifiedComponent> = {}): ClassifiedComponent {
  return {
    componentName: 'Card',
    componentClass: 'Card',
    desc: 'Cards are used to group and display content in a way that is easily readable.',
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

const CARD = classified({
  sizes: ['xs', 'sm', 'md', 'lg', 'xl'],
  styles: ['border', 'dash'],
  modifiers: ['image-full', 'side'],
  parts: ['card-title', 'card-body', 'card-actions'],
  descs: {
    border: 'Adds border to <card>',
    dash: 'dash style',
    'image-full': 'The image in <figure> element will be the background',
    side: 'The image in <figure> will be on to the side',
  },
})

const page = (component: ClassifiedComponent, dir: string, element: string, summary?, config = {}) =>
  generateComponentPage(buildComponentShape(component, { componentDir: dir, element }, config), summary)

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
    size: CardSize? = null,
    border: Boolean = false,  // Adds border to <card>
    dash: Boolean = false,  // dash style
    imageFull: Boolean = false,  // The image in <figure> element will be the background
    side: Boolean = false,  // The image in <figure> will be on to the side
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
\`\`\`

\`\`\`kotlin
fun FlowContent.daisyCardTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (H2.() -> Unit)? = null,
    content: (H2.() -> Unit)? = null,
)
\`\`\`

\`\`\`kotlin
fun FlowContent.daisyCardBody(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
\`\`\`

\`\`\`kotlin
fun FlowContent.daisyCardActions(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
\`\`\`
`

    assert.equal(page(CARD, 'card', 'DIV', { summary: 'Content containers with body and title' }), expected)
  })

  test('documents the element a part actually renders, not a guess', () => {
    // The defect that motivated the change: `megamenu.md` said DIV where the code says SPAN.
    const result = page(
      classified({ componentName: 'Megamenu', prefix: 'megamenu', parts: ['megamenu-active'] }),
      'megamenu',
      'DIV',
      { summary: 'Horizontal menu with popover navigation blocks' },
      { subComponentElements: { 'megamenu-active': 'SPAN' } },
    )

    assert.match(result, /fun FlowContent\.daisyMegamenuActive\(\n {4}id: HtmlId\? = null,\n {4}extraClasses: String\? = null,\n {4}attrs: \(SPAN\.\(\) -> Unit\)\? = null,\n {4}content: \(SPAN\.\(\) -> Unit\),\n\)/)
  })

  test('names a static attribute in the renders clause', () => {
    const result = page(
      classified({ componentName: 'Megamenu', prefix: 'megamenu' }),
      'megamenu',
      'DIV',
      { summary: 'Horizontal menu with popover navigation blocks' },
      { componentAttributes: { megamenu: { popover: '' } } },
    )

    assert.match(result, /Renders `<div class="megamenu \.\.\." popover>`\./)
  })

  test('documents a custom part, which a hand-written page can simply forget', () => {
    // `drawer.md` omitted `daisyDrawerButton`; `megamenu.md` omitted `daisyMegamenuPanel`.
    const result = page(
      classified({ componentName: 'Drawer', prefix: 'drawer' }),
      'drawer',
      'DIV',
      { summary: 'Sidebar layout with toggle' },
      { customParts: { drawer: [{ name: 'Button', element: 'DIV', cssClass: 'drawer-button' }] } },
    )

    assert.match(result, /fun FlowContent\.daisyDrawerButton\(/)
  })

  test('a component with no parts emits exactly one block', () => {
    const result = page(
      classified({ componentName: 'Badge', prefix: 'badge', colors: ['primary', 'secondary'], sizes: ['xs'] }),
      'badge',
      'SPAN',
      { summary: 'Labels, counts, and status tags' },
    )

    assert.equal(result.match(/```kotlin/g)?.length, 1)
  })

  test('gives each enum its own comment line', () => {
    const result = page(
      classified({ componentName: 'Badge', prefix: 'badge', colors: ['primary', 'secondary'], sizes: ['xs', 'sm'] }),
      'badge',
      'SPAN',
      { summary: 'Labels' },
    )

    assert.match(result, /\/\/ BadgeVariant: Primary \| Secondary\n\/\/ BadgeSize: Xs \| Sm\n/)
  })

  test('prefers the longer description where one is committed', () => {
    const result = page(CARD, 'card', 'DIV', {
      summary: 'Short one',
      description: 'The longer paragraph, for the three pages that say more than their table row',
    })

    assert.match(result, /^The longer paragraph, .* row\. Renders/m)
    assert.ok(!result.includes('Short one'))
  })

  test('falls back to the first sentence of the DaisyUI description when none is committed', () => {
    const result = page(CARD, 'card', 'DIV', undefined)

    assert.match(result, /^Cards are used to group and display content in a way that is easily readable\. Renders/m)
  })

  test('emits only the renders clause when there is no description at all', () => {
    const result = page(classified({ desc: '' }), 'card', 'DIV', undefined)

    assert.match(result, /^Renders `<div class="card \.\.\.">`\.$/m)
  })

  test('does not comment the escape-hatch parameters, only the DaisyUI booleans', () => {
    const result = page(CARD, 'card', 'DIV', { summary: 'x' })

    assert.ok(!result.includes('// Type-safe HTML id'))
    assert.ok(!result.includes('// Additional CSS classes'))
    assert.ok(result.includes('// dash style'))
  })
})
