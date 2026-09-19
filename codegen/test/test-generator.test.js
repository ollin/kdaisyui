import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { parseTestCases, buildClassMappings, parseScopeBuilder, parseScopeMembers } from '../src/test-generator.ts'

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

  test('allows a class that does not carry the component prefix', () => {
    const { allowedClasses } = buildClassMappings(
      frontmatter({ component: [{ class: 'btn' }], style: [{ class: 'glass' }] }),
    )

    assert.deepEqual([...allowedClasses].sort(), ['btn', 'glass'])
  })

  test('tolerates a document with no classnames at all', () => {
    const result = buildClassMappings({})

    assert.equal(result.componentClass, undefined)
    assert.deepEqual([...result.allowedClasses], [])
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

describe('the builder a scoped function opens', () => {
  // A component whose `content` runs in a generated scope does not call a kotlinx.html
  // builder — it constructs the scope and visits it. The builder parser saw no builder and
  // returned null, and the `closes` assertion silently vanished from the generated coverage
  // test. A generated assertion that disappears is exactly the failure the mutation gate
  // exists to catch, and here it disappeared from the generator itself.
  const scopeBody = '        JoinScope(emptyMap(), consumer).visit {\n            addClassNames("join")'
  const file = [
    'class JoinScope internal constructor(',
    '    initialAttributes: Map<String, String>,',
    '    consumer: TagConsumer<*>,',
    ') : DIV(initialAttributes, consumer) {',
  ].join('\n')

  test('resolves to the element the scope extends', () => {
    assert.equal(parseScopeBuilder(scopeBody, file), 'div')
  })

  test('is absent for an ordinary body, which parseEmittedBuilder already answers', () => {
    assert.equal(parseScopeBuilder('        div {', file), null)
  })

  test('is absent when the file declares no such scope', () => {
    assert.equal(parseScopeBuilder(scopeBody, 'class Something else'), null)
  })
})

describe('the scope members a coverage test must reach', () => {
  // Every member is a generated function with its own parameter defaults, so the aggregated
  // 100% line gate counts them. Hand-listing them in a test would be a second copy of a
  // DERIVED list — the thing deriving the set was meant to avoid — so the coverage tests are
  // generated from the same file the members are.
  const file = [
    'fun FlowContent.daisyJoin(',
    '    content: (JoinScope.() -> Unit),',
    ') {',
    '    JoinScope(emptyMap(), consumer).visit {',
    '    }',
    '}',
    '',
    'class JoinScope internal constructor(',
    '    initialAttributes: Map<String, String>,',
    '    consumer: TagConsumer<*>,',
    ') : DIV(initialAttributes, consumer) {',
    '',
    '    fun daisyButton(',
    '        text: String? = null,',
    '        content: (BUTTON.() -> Unit)? = null,',
    '    ) {',
    '    }',
    '',
    '    fun daisyCard(',
    '        id: HtmlId? = null,',
    '        content: (DIV.() -> Unit),',
    '    ) {',
    '    }',
    '}',
  ].join('\n')

  test('finds every member', () => {
    assert.deepEqual(parseScopeMembers(file).map((m) => m.name), ['daisyButton', 'daisyCard'])
  })

  test('knows which member must be given a content lambda', () => {
    const [button, card] = parseScopeMembers(file)

    assert.equal(button.requiresContent, false)
    assert.equal(card.requiresContent, true)
  })

  test('names the function whose lambda the members live in', () => {
    assert.equal(parseScopeMembers(file)[0].opener, 'daisyJoin')
  })

  test('finds nothing in a file with no scope', () => {
    assert.deepEqual(parseScopeMembers('fun FlowContent.daisyCard(\n) {\n    div {\n    }\n}'), [])
  })
})
