import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { documentedElementIn, fencedHtmlBlocks } from '../src/parser/documented-element.ts'

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
