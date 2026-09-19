import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { documentedElementSetsIn, documentedElementTalliesIn, documentedParentsIn, fencedHtmlBlocks, usualElementOf } from '../src/parser/documented-element.ts'

/**
 * Reading the element DaisyUI documents.
 *
 * Fixtures are inline, so these run in `codegen-tests`, which has no submodule — the real pages
 * are exercised end to end by `generated-sources-drift` instead.
 *
 * The three constructs in the second block are the reason this module uses a parser. Each was
 * measured against the regex it replaced, and the regex answered all three wrongly.
 */

describe('fencedHtmlBlocks', () => {
  test('takes every html block and leaves the prose behind', () => {
    const markdown = [
      '### ~Swap text',
      '<label class="swap">live preview</label>',
      '```html',
      '<label class="$$swap">first</label>',
      '```',
      'some prose',
      '```html',
      '<label class="$$swap">second</label>',
      '```',
    ].join('\n')

    const html = fencedHtmlBlocks(markdown)

    assert.ok(html.includes('first'))
    assert.ok(html.includes('second'))
    assert.ok(!html.includes('some prose'))
    // The un-fenced live preview carries the class WITHOUT the marker, so it cannot be mistaken
    // for the documented example — which is why the marker is what this reads.
    assert.ok(!html.includes('live preview'))
  })

  test('yields nothing for a page with no html block', () => {
    assert.equal(fencedHtmlBlocks('# Title\n\nprose only\n'), '')
  })

  test('ignores a fenced block in another language', () => {
    assert.equal(fencedHtmlBlocks('```kotlin\ndaisySwap()\n```\n'), '')
  })
})



describe('documentedElementSetsIn', () => {
  const menu = `
    <ul class="$$menu $$menu-horizontal">
      <li><a class="$$menu-active">Item</a></li>
      <li class="lg:$$menu-disabled"><a>Other</a></li>
    </ul>`

  test('names every element carrying each marked class', () => {
    const elements = documentedElementSetsIn(menu)

    assert.deepEqual([...elements.get('menu') ?? []], ['UL'])
    assert.deepEqual([...elements.get('menu-active') ?? []], ['A'])
  })

  test('keeps ALL of them where DaisyUI shows a class on several', () => {
    // What separates this from the reading it replaced: that one named the commonest and said
    // nothing on a tie, so `badge` was a <div> and its seven documented <span>s were a defect.
    const html = `<span class="$$badge"></span><div class="$$badge $$badge-xs"></div><div class="$$badge"></div>`

    const elements = documentedElementSetsIn(html)

    assert.deepEqual([...elements.get('badge') ?? []].sort(), ['DIV', 'SPAN'])
    assert.deepEqual([...elements.get('badge-xs') ?? []], ['DIV'])
  })

  test('skips a class that appears only behind a Tailwind variant', () => {
    // `lg:$$menu-disabled` wears the class at one breakpoint; a function always emits it, so
    // that element is not evidence of where the class belongs.
    assert.equal(documentedElementSetsIn(menu).has('menu-disabled'), false)
  })

  test('ignores classes without the marker', () => {
    assert.equal(documentedElementSetsIn(`<div class="flex $$card"></div>`).has('flex'), false)
  })
})

describe('documentedParentsIn', () => {
  test('names the parent element of each marked class', () => {
    const html = `<fieldset class="$$fieldset"><legend class="$$fieldset-legend">T</legend></fieldset>`

    assert.equal(documentedParentsIn(html).get('fieldset-legend'), 'FIELDSET')
  })

  test('has no parent for a class at the top of an example', () => {
    assert.equal(documentedParentsIn(`<fieldset class="$$fieldset"></fieldset>`).has('fieldset'), false)
  })

  test('leaves out a class whose parent differs between examples', () => {
    const html = `<ul><li class="$$step">a</li></ul><ol><li class="$$step">b</li></ol>`

    assert.equal(documentedParentsIn(html).has('step'), false)
  })
})

describe('usualElementOf', () => {
  const html = `<span class="$$item"></span><span class="$$item"></span><div class="$$item"></div><p class="$$tie"></p><b class="$$tie"></b>`
  const tallies = documentedElementTalliesIn(html)

  test('names the element a class is shown on more often than any other', () => {
    assert.equal(usualElementOf(tallies.get('item')), 'SPAN')
  })

  test('names nothing on a tie, so a coin toss cannot become an element', () => {
    assert.equal(usualElementOf(tallies.get('tie')), undefined)
  })

  test('names nothing for a class shown nowhere', () => {
    assert.equal(usualElementOf(tallies.get('absent')), undefined)
  })
})
