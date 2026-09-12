import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { parseTestCases, buildClassMappings } from '../src/test-generator.ts'

// Characterization tests: they pin what `parseTestCases` does TODAY, so section 2 can
// restructure it and know immediately whether anything moved. They were green on the
// first run and no assertion was chosen to describe desirable behaviour — several of
// them below record behaviour that is arguably wrong.
//
// That this file imports the module at all is the other half of task 1.1's proof: before
// the entry-point guard, loading it regenerated all 66 components.

const doc = (...lines) => lines.join('\n')

describe('parseTestCases', () => {
  test('pairs a heading with the html block that follows it', () => {
    const cases = parseTestCases(doc('### ~Primary button', '```html', '<button>hi</button>', '```'))

    assert.deepEqual(cases, [{ name: 'Primary button', html: '<button>hi</button>' }])
  })

  test('keeps every line of a multi-line block, without the fences', () => {
    const cases = parseTestCases(doc('### ~Card', '```html', '<div>', '  <p>x</p>', '</div>', '```'))

    assert.equal(cases[0].html, '<div>\n  <p>x</p>\n</div>')
  })

  test('trims the heading but not the html', () => {
    const cases = parseTestCases(doc('### ~   Spaced   ', '```html', '  <b>x</b>  ', '```'))

    assert.equal(cases[0].name, 'Spaced')
    assert.equal(cases[0].html, '  <b>x</b>  ')
  })

  test('collects several cases in document order', () => {
    const cases = parseTestCases(
      doc('### ~One', '```html', '<i>1</i>', '```', '### ~Two', '```html', '<i>2</i>', '```'),
    )

    assert.deepEqual(cases.map((c) => c.name), ['One', 'Two'])
    assert.deepEqual(cases.map((c) => c.html), ['<i>1</i>', '<i>2</i>'])
  })

  test('returns nothing for a document with no headings', () => {
    assert.deepEqual(parseTestCases(doc('# Title', 'prose', '```html', '<i>x</i>', '```')), [])
  })

  test('ignores prose between the heading and the block', () => {
    const cases = parseTestCases(doc('### ~Note', 'some prose', '', '```html', '<i>x</i>', '```'))

    assert.deepEqual(cases, [{ name: 'Note', html: '<i>x</i>' }])
  })

  // --- Behaviour that is recorded, not endorsed ---------------------------------------
  // Each of these drops input silently. They are pinned so a refactoring cannot change
  // them by accident; whether they SHOULD change is a separate question from this change.

  test('DROPS a case whose block is not html — the language must match exactly', () => {
    const cases = parseTestCases(doc('### ~Kotlin sample', '```kotlin', 'val x = 1', '```'))

    assert.deepEqual(cases, [])
  })

  test('DROPS a heading with no code block at all', () => {
    assert.deepEqual(parseTestCases(doc('### ~Lonely', '### ~Also lonely')), [])
  })

  test('accepts an unterminated final block, unlike every other case', () => {
    const cases = parseTestCases(doc('### ~Truncated', '```html', '<i>x</i>'))

    assert.deepEqual(cases, [{ name: 'Truncated', html: '<i>x</i>' }])
  })

  test('a heading inside a fenced block is content, not a new case', () => {
    const cases = parseTestCases(
      doc('### ~First', '```html', '<i>1</i>', '### ~Second', '```html', '<i>2</i>', '```'),
    )

    // Markdown semantics: everything between fences is literal, headings included. So the
    // second ```html is the CLOSING fence of the first block, and the document contains
    // exactly one test case whose body happens to include a line that looks like a heading.
    //
    // The input is malformed either way — someone forgot a closing fence. What changed is
    // which wrong answer you get: this one is visibly wrong, where the previous behaviour
    // emitted a SECOND case with an empty body, which downstream turns into a generated
    // test asserting nothing at all.
    assert.deepEqual(cases, [{ name: 'First', html: '<i>1</i>\n### ~Second' }])
  })
})

// Characterization tests for the target of task 2.3 (cc 11, nesting depth 4). Same rules as
// above: they record what the function does, not what it ought to do.

const frontmatter = (classnames) => ({ classnames })

describe('buildClassMappings', () => {
  test('takes the component class from the first component entry', () => {
    const { componentClass } = buildClassMappings(
      frontmatter({ component: [{ class: 'btn' }, { class: 'ignored' }] }),
    )

    assert.equal(componentClass, 'btn')
  })

  test('allows the component class and every category class', () => {
    const { allowedClasses } = buildClassMappings(
      frontmatter({
        component: [{ class: 'btn' }],
        placement: [{ class: 'btn-top' }],
        modifier: [{ class: 'btn-outline' }],
        direction: [{ class: 'btn-end' }],
        behavior: [{ class: 'btn-active' }],
        style: [{ class: 'btn-ghost' }],
      }),
    )

    assert.deepEqual(
      [...allowedClasses].sort(),
      ['btn', 'btn-active', 'btn-end', 'btn-ghost', 'btn-outline', 'btn-top'],
    )
  })

  test('maps each class to a camelCase param with the component prefix stripped', () => {
    const { classToParam } = buildClassMappings(
      frontmatter({ component: [{ class: 'btn' }], modifier: [{ class: 'btn-no-animation' }] }),
    )

    assert.deepEqual(classToParam, { 'btn-no-animation': 'noAnimation' })
  })

  test('builds the reverse map from param back to class', () => {
    const { paramToGeneratedClass } = buildClassMappings(
      frontmatter({ component: [{ class: 'btn' }], modifier: [{ class: 'btn-outline' }] }),
    )

    assert.deepEqual(paramToGeneratedClass, { outline: 'btn-outline' })
  })

  test('leaves a class alone when it does not carry the component prefix', () => {
    const { classToParam } = buildClassMappings(
      frontmatter({ component: [{ class: 'btn' }], style: [{ class: 'glass' }] }),
    )

    assert.deepEqual(classToParam, { glass: 'glass' })
  })

  test('strips only the FIRST occurrence of the prefix', () => {
    // `String.replace` with a string argument replaces once. Pinned because a later
    // switch to a regex would silently change this.
    const { classToParam } = buildClassMappings(
      frontmatter({ component: [{ class: 'btn' }], modifier: [{ class: 'btn-btn-x' }] }),
    )

    assert.deepEqual(classToParam, { 'btn-btn-x': 'btnX' })
  })

  test('tolerates a document with no classnames at all', () => {
    const result = buildClassMappings({})

    assert.equal(result.componentClass, undefined)
    assert.deepEqual([...result.allowedClasses], [])
    assert.deepEqual(result.classToParam, {})
  })

  test('skips a category that is not an array, and an item with no class', () => {
    const { allowedClasses } = buildClassMappings(
      frontmatter({
        component: [{ class: 'btn' }],
        modifier: 'not-an-array',
        style: [{ notAClass: 'x' }, { class: 'btn-ghost' }],
      }),
    )

    assert.deepEqual([...allowedClasses].sort(), ['btn', 'btn-ghost'])
  })

})
