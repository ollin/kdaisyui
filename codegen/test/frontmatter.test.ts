import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { everyDeclaredClass, getBaseClass, getClassesByCategory, getEntriesByCategory, parseFrontmatter, readComponentFrontmatter, type ComponentName } from '../src/parser/frontmatter.ts'

/**
 * Characterization tests for the hand-rolled YAML reader.
 *
 * `parseYamlFrontmatter` is cc 32 with nesting depth 5 and eight bumps, and it had no tests
 * at all. These pin what it does TODAY so the refactoring in 2.6 can prove it changed
 * nothing — the same order `test-and-clean-codegen` used on `parseTestCases`.
 *
 * Several record behaviour that is arguably wrong. They are marked, and none of them is an
 * invitation to change it inside a refactoring.
 *
 * The indentation in these fixtures is not decorative: the parser dispatches on
 * `line.search(/\S/)` being exactly 0, 2 or 4, so every level shift selects a different
 * branch. The shapes here are the ones DaisyUI actually ships.
 */

const doc = (...lines: string[]) => ['---', ...lines, '---'].join('\n')

describe('parseFrontmatter', () => {
  test('returns null when the document has no frontmatter block', () => {
    assert.equal(parseFrontmatter('# Just a heading\n'), null)
  })

  test('reads top-level scalars', () => {
    const fm = parseFrontmatter(doc('title: Button', 'layout: components'))

    assert.equal(fm?.title, 'Button')
    assert.equal(fm?.layout, 'components')
  })

  test('strips single and double quotes from a scalar', () => {
    const fm = parseFrontmatter(doc("title: 'Button'", 'desc: "Takes actions"'))

    assert.equal(fm?.title, 'Button')
    assert.equal(fm?.desc, 'Takes actions')
  })

  test('converts booleans and numbers away from strings', () => {
    const fm = parseFrontmatter(doc('showComponentPageTabs: true', 'hidden: false', 'order: 3'))

    assert.equal(fm?.showComponentPageTabs, true)
    assert.equal(fm?.hidden, false)
    assert.equal(fm?.order, 3)
  })

  test('reads a top-level list', () => {
    const fm = parseFrontmatter(doc('tags:', '  - one', '  - two'))

    assert.deepEqual(fm?.tags, ['one', 'two'])
  })

  test('reads the shape DaisyUI actually ships: category, item, continuation', () => {
    const fm = parseFrontmatter(
      doc(
        'classnames:',
        '  component:',
        "  - class: 'btn'",
        '    desc: Button',
        '  color:',
        '  - class: btn-primary',
        '    desc: primary color',
      ),
    )

    assert.deepEqual(fm?.classnames, {
      component: [{ class: 'btn', desc: 'Button' }],
      color: [{ class: 'btn-primary', desc: 'primary color' }],
    })
  })

  test('a bare list item with no colon becomes a class entry', () => {
    const fm = parseFrontmatter(doc('classnames:', '  part:', '  - btn-group'))

    assert.deepEqual(fm?.classnames?.part, [{ class: 'btn-group' }])
  })

  test('accepts items indented one level deeper, via the duplicated branch', () => {
    // The parser handles `- ` at BOTH indent 2 and indent 4, with the two blocks written
    // out twice. This test is what lets 2.6 collapse them and prove nothing moved.
    const fm = parseFrontmatter(doc('classnames:', '  size:', '    - class: btn-sm'))

    assert.deepEqual(fm?.classnames?.size, [{ class: 'btn-sm' }])
  })

  test('carries the default flag through as a boolean', () => {
    const fm = parseFrontmatter(
      doc('classnames:', '  size:', '  - class: btn-md', '    default: true'),
    )

    assert.deepEqual(fm?.classnames?.size, [{ class: 'btn-md', default: true }])
  })

  // --- Recorded, not endorsed -----------------------------------------------------------

  test('DROPS a line indented deeper than four spaces', () => {
    // Dispatch is on exactly 0, 2 or 4. Six spaces matches no branch and the line is lost
    // in silence — no error, no warning, just absent data.
    const fm = parseFrontmatter(
      doc('classnames:', '  component:', '  - class: btn', '      desc: lost'),
    )

    assert.deepEqual(fm?.classnames?.component, [{ class: 'btn' }])
  })

  test('DROPS a category item that appears before any category', () => {
    const fm = parseFrontmatter(doc('classnames:', '  - class: orphan'))

    assert.deepEqual(fm?.classnames, {})
  })

  test('a top-level key with no value becomes an empty list, not an empty string', () => {
    const fm = parseFrontmatter(doc('tags:'))

    assert.deepEqual(fm?.tags, [])
  })

  test('a top-level line with no colon is skipped', () => {
    const fm = parseFrontmatter(doc('title: Button', 'nonsense', 'layout: components'))

    assert.equal(fm?.title, 'Button')
    assert.equal(fm?.layout, 'components')
  })
})

// --- Edges no pinned DaisyUI page reaches, which the snapshot tests therefore cannot see ---------

describe('parseFrontmatter, at the edges', () => {
  test('reads only a block at the very start of the document', () => {
    assert.equal(parseFrontmatter('Intro\n---\ntitle: Button\n---'), null)
  })

  test('unquotes only a value quoted at both ends', () => {
    const fm = parseFrontmatter(doc("a: 'open", "b: close'", 'c: "open', 'd: close"', "e: 'x\"", 'f: "x\''))

    assert.deepEqual([fm?.a, fm?.b, fm?.c, fm?.d, fm?.e, fm?.f], ["'open", "close'", '"open', 'close"', "'x\"", '"x\''])
  })

  test('reads a number only where the whole value is one, and keeps an empty value a string', () => {
    const fm = parseFrontmatter(doc('classnames:', '  size:', '  - class: btn-xs', '    order: 12', '    note: 12px', '    empty: '))

    assert.deepEqual(fm?.classnames?.size, [{ class: 'btn-xs', order: 12, note: '12px', empty: '' }])
  })

  test('trims a key and a list item, and skips blank lines', () => {
    const fm = parseFrontmatter(doc('title  : Button', '', 'tags:', '  -   one  ', '', '  - two'))

    assert.equal(fm?.title, 'Button')
    assert.deepEqual(fm?.tags, ['one', 'two'])
  })
})

describe('the classnames getters', () => {
  const classnames = parseFrontmatter(doc('classnames:', '  component:', '  - class: btn', '  color:', '  - class: btn-primary'))?.classnames

  test('read the component class and a category', () => {
    assert.equal(getBaseClass(classnames), 'btn')
    assert.deepEqual(getClassesByCategory(classnames, 'color'), ['btn-primary'])
  })

  test('give an empty description where an entry has none', () => {
    assert.deepEqual(getEntriesByCategory(classnames, 'color'), [{ class: 'btn-primary', desc: '' }])
  })

  test('give nothing for a category or a component class a page does not declare', () => {
    assert.equal(getBaseClass(undefined), null)
    assert.equal(getBaseClass({ color: [] } as never), null)
    assert.deepEqual(getClassesByCategory(classnames, 'size'), [])
    assert.deepEqual(getEntriesByCategory(undefined, 'size'), [])
  })
})

describe('parseFrontmatter, on lines it ignores', () => {
  test('ignores a category line without a colon, and an item line without one under an open item', () => {
    const fm = parseFrontmatter(doc('classnames:', '  nonsense', '  color:', '  - class: btn-primary', '    nonsense'))

    assert.deepEqual(fm?.classnames, { color: [{ class: 'btn-primary' }] })
  })

  test('ignores an indented line under a scalar, a non-item under a list, and continuation with no open item', () => {
    const fm = parseFrontmatter(doc('title: Button', '  - stray', 'tags:', '  nonsense', '    desc: orphan', '  - one'))

    assert.equal(fm?.title, 'Button')
    assert.deepEqual(fm?.tags, ['one'])
  })
})

describe('the classnames readers, on what a page may lack', () => {
  test('give an empty class for an item that names none', () => {
    const classnames = parseFrontmatter(doc('classnames:', '  color:', '  - desc: no class'))?.classnames

    assert.deepEqual(getEntriesByCategory(classnames, 'color'), [{ class: '', desc: 'no class' }])
    assert.deepEqual(getClassesByCategory(undefined, 'color'), [])
  })

  test('read nothing for a component DaisyUI does not have', () => {
    const missing = 'no-such-component' as ComponentName

    assert.equal(readComponentFrontmatter(missing), null)
    assert.deepEqual([...everyDeclaredClass([missing])], [])
  })
})
