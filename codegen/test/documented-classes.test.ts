import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import {
  DocumentedClass,
  classTokensOf,
  documentedElementClasses,
} from '../src/parser/documented-classes.ts'

// Every fixture here is a literal HTML string. Nothing reads the DaisyUI checkout, because the
// `codegen-tests` CI job has no submodules by design — see AGENTS.md.

describe('DocumentedClass.parse', () => {
  test('reads an unprefixed token as a class with no variant', () => {
    const parsed = DocumentedClass.parse('$$menu-vertical')

    assert.equal(parsed?.variant, null)
    assert.equal(parsed?.className, 'menu-vertical')
    assert.equal(parsed?.isPrefixed, false)
    assert.equal(parsed?.rendered, 'menu-vertical')
  })

  test('keeps the Tailwind variant a prefixed token is applied at', () => {
    const parsed = DocumentedClass.parse('lg:$$menu-horizontal')

    assert.equal(parsed?.variant, 'lg')
    assert.equal(parsed?.className, 'menu-horizontal')
    assert.equal(parsed?.isPrefixed, true)
    assert.equal(parsed?.rendered, 'lg:menu-horizontal')
  })

  test('keeps a variant that itself contains a hyphen', () => {
    assert.equal(DocumentedClass.parse('max-sm:$$megamenu-vertical')?.variant, 'max-sm')
  })

  test('returns null for a Tailwind utility, which carries no marker', () => {
    assert.equal(DocumentedClass.parse('rounded-box'), null)
  })

  test('returns null for a marker with nothing after it', () => {
    assert.equal(DocumentedClass.parse('$$'), null)
  })

  // Refusing beats guessing: nothing in DaisyUI's docs writes this, and inventing a reading
  // for it is how an extractor starts asserting something nobody documented.
  test('REFUSES a marker that is not preceded by a colon', () => {
    assert.equal(DocumentedClass.parse('lg$$menu-horizontal'), null)
  })
})

describe('classTokensOf', () => {
  test('splits on any run of whitespace and drops the empties', () => {
    assert.deepEqual(classTokensOf({ attribs: { class: '  a\n  b   c ' } }), ['a', 'b', 'c'])
  })

  test('treats an element with no class attribute as carrying none', () => {
    assert.deepEqual(classTokensOf({ attribs: {} }), [])
    assert.deepEqual(classTokensOf({}), [])
  })
})

describe('documentedElementClasses', () => {
  test('reads the classes of the element that carries the component class', () => {
    const [classes] = documentedElementClasses('<ul class="$$menu $$menu-vertical">x</ul>', 'menu')

    assert.deepEqual(classes.map(c => c.rendered), ['menu', 'menu-vertical'])
  })

  // Failure mode 1: the old extractor unioned every `$$` token in the block, so the variant
  // vanished and the two directions looked like one element wearing both at once.
  test('keeps a variant instead of collapsing it into the unprefixed class', () => {
    const [classes] = documentedElementClasses(
      '<ul class="$$menu $$menu-vertical lg:$$menu-horizontal bg-base-200">x</ul>',
      'menu',
    )

    assert.deepEqual(classes.map(c => c.rendered), ['menu', 'menu-vertical', 'lg:menu-horizontal'])
  })

  // Failure mode 2: three sibling tooltips became ONE call carrying top, start and end.
  test('reports sibling elements separately rather than unioning them', () => {
    const elements = documentedElementClasses(
      '<div class="$$tooltip $$tooltip-top $$tooltip-start"></div>' +
        '<div class="$$tooltip $$tooltip-top"></div>' +
        '<div class="$$tooltip $$tooltip-top $$tooltip-end"></div>',
      'tooltip',
    )

    assert.deepEqual(
      elements.map(classes => classes.map(c => c.rendered)),
      [
        ['tooltip', 'tooltip-top', 'tooltip-start'],
        ['tooltip', 'tooltip-top'],
        ['tooltip', 'tooltip-top', 'tooltip-end'],
      ],
    )
  })

  // Failure mode 3: a CHILD's placement was hoisted onto the parent, so `daisyIndicator` was
  // called with every placement the example put on its `indicator-item`.
  test('does not hoist a child element class onto the parent', () => {
    const [classes] = documentedElementClasses(
      '<div class="$$indicator"><span class="$$indicator-item $$indicator-start"></span></div>',
      'indicator',
    )

    assert.deepEqual(classes.map(c => c.rendered), ['indicator'])
  })

  test('finds the component element however deeply the example nests it', () => {
    const elements = documentedElementClasses(
      '<div class="wrapper"><section><button class="$$btn $$btn-wide"></button></section></div>',
      'btn',
    )

    assert.deepEqual(elements.map(classes => classes.map(c => c.rendered)), [['btn', 'btn-wide']])
  })

  // `daisyTooltip()` always emits `tooltip`, so an element that wears the component class only
  // at one breakpoint cannot be reproduced by a call and must not become a test.
  test('SKIPS an element that carries the component class only at a variant', () => {
    assert.deepEqual(documentedElementClasses('<div class="lg:$$tooltip"></div>', 'tooltip'), [])
  })

  test('matches the component class as a whole token, not as a prefix of a longer one', () => {
    assert.deepEqual(documentedElementClasses('<div class="$$menu-vertical"></div>', 'menu'), [])
  })

  test('returns nothing when the example documents no such element', () => {
    assert.deepEqual(documentedElementClasses('<p>prose only</p>', 'menu'), [])
  })
})
