import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { documentedElementIn, documentedElementsIn, fencedHtmlBlocks } from '../src/parser/documented-element.ts'

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

describe('documentedElementIn', () => {
  test('names the element carrying the marked component class', () => {
    // The nine components whose skill file has no Syntax block; these three stand for them.
    assert.equal(documentedElementIn('<label class="$$swap"><div class="$$swap-on"></div></label>', 'swap'), 'LABEL')
    assert.equal(documentedElementIn('<ul class="$$menu"><li></li></ul>', 'menu'), 'UL')
    assert.equal(documentedElementIn('<dialog class="$$modal"></dialog>', 'modal'), 'DIALOG')
  })

  test('matches the class as a whole token, not a substring', () => {
    // `$$swap-on` must not answer for `swap`, or every part would shadow its component.
    assert.equal(documentedElementIn('<div class="$$swap-on"></div><label class="$$swap"></label>', 'swap'), 'LABEL')
  })

  test('requires the marker, so a live preview cannot answer', () => {
    assert.equal(documentedElementIn('<label class="swap"></label>', 'swap'), null)
  })

  test('takes the first documented example when several are shown', () => {
    assert.equal(documentedElementIn('<details class="$$dropdown"></details>\n<div class="$$dropdown"></div>', 'dropdown'), 'DETAILS')
  })

  test('returns null when the class is documented nowhere', () => {
    assert.equal(documentedElementIn('<div class="$$card"></div>', 'swap'), null)
  })

  test('reads a custom element, which kotlinx.html has no tag class for', () => {
    // `calendar` is documented as Cally's custom element. Reported faithfully so the caller can
    // refuse it, rather than truncated at the hyphen as an earlier regex did.
    assert.equal(documentedElementIn('<calendar-date class="$$cally"></calendar-date>', 'cally'), 'CALENDAR-DATE')
  })

  describe('the constructs a regex gets wrong', () => {
    test('a > inside a quoted attribute value', () => {
      assert.equal(documentedElementIn('<div alt="a > b" class="$$x"></div>', 'x'), 'DIV')
    })

    test('a comment containing a tag', () => {
      assert.equal(documentedElementIn('<!-- <span class="$$x"> --><b class="$$x"></b>', 'x'), 'B')
    })

    test('script content containing markup', () => {
      assert.equal(documentedElementIn(`<script>var s = '<i class="$$x">'</script><em class="$$x"></em>`, 'x'), 'EM')
    })
  })
})

describe('documentedElementsIn', () => {
  const menu = `
    <ul class="$$menu $$menu-horizontal">
      <li><a class="$$menu-active">Item</a></li>
      <li class="lg:$$menu-disabled"><a>Other</a></li>
    </ul>`

  test('names the element carrying every marked class, one entry per class', () => {
    const elements = documentedElementsIn(menu)

    assert.equal(elements.get('menu'), 'UL')
    assert.equal(elements.get('menu-horizontal'), 'UL')
    assert.equal(elements.get('menu-active'), 'A')
  })

  test('skips a class that appears only behind a Tailwind variant', () => {
    // `lg:$$menu-disabled` wears the class at one breakpoint; a function always emits it, so
    // that element is not evidence of where the class belongs.
    assert.equal(documentedElementsIn(menu).get('menu-disabled'), undefined)
  })

  test('leaves out a class shown on several elements, because the docs do not say', () => {
    // `badge` is on a <span> once and a <div> seventeen times. Neither is "the" element.
    const html = `<span class="$$badge"></span><div class="$$badge $$badge-xs"></div>`

    const elements = documentedElementsIn(html)

    assert.equal(elements.has('badge'), false)
    assert.equal(elements.get('badge-xs'), 'DIV')
  })

  test('ignores classes without the marker', () => {
    assert.equal(documentedElementsIn(`<div class="flex $$card"></div>`).has('flex'), false)
  })
})
