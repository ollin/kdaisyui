import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { parseTestCases } from '../src/test-generator.js'

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

  test('a heading inside an unclosed block desynchronises the fence state', () => {
    const cases = parseTestCases(
      doc('### ~First', '```html', '<i>1</i>', '### ~Second', '```html', '<i>2</i>', '```'),
    )

    // The heading branch runs BEFORE the in-code-block branch, so `### ~Second` starts a
    // new case even though the first fence is still open — and `inCodeBlock` is never
    // reset. The next ```html is therefore read as the CLOSING fence of the first block,
    // which flushes "Second" with an empty body, and `<i>2</i>` then falls outside any
    // block and is lost.
    //
    // Recorded, not endorsed: this is a latent defect, and pinning it means a refactoring
    // has to change it deliberately rather than by accident.
    assert.deepEqual(cases, [
      { name: 'First', html: '<i>1</i>' },
      { name: 'Second', html: '' },
    ])
  })
})
