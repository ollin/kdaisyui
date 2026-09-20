import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { parseComponentSkills, parseLlmsTxtContent, getElementForComponent } from '../src/parser/llms-txt.ts'
import type { ComponentName } from '../src/parser/frontmatter.ts'

const named = (name: string) => name as ComponentName

/**
 * The element heuristic reads DaisyUI's own `skills/daisyui/components/*.md`.
 *
 * Which component a file describes is decided by its FILENAME, never by the `### ` heading
 * inside it. DaisyUI retitled 67 of those 68 headings in 5.7.42 — `### card` became
 * `### Card`, `### file-input` became `### File input` — and four were reworded outright:
 * `mockup-browser.md` is headed "Browser mockup". A heading is a human title, so no
 * normalisation of it recovers the directory name, and the directory name is what every
 * caller looks components up by.
 */
describe('parseComponentSkills', () => {
  it('keys a component by its filename, not by its heading', () => {
    const rules = parseComponentSkills(new Map([
      [named('file-input'), '### File input\n\n#### Syntax\n```html\n<input type="file" class="file-input" />\n```\n'],
    ]))

    assert.equal(getElementForComponent(rules, named('file-input')), 'input')
  })

  it('resolves a component DaisyUI retitled, not merely recased', () => {
    // `mockup-browser.md` is headed "Browser mockup": the words are in the other order, so
    // lowercasing and hyphenating the heading yields `browser-mockup` and finds nothing.
    const rules = parseComponentSkills(new Map([
      [named('mockup-browser'), '### Browser mockup\n\n#### Syntax\n```html\n<div class="mockup-browser">x</div>\n```\n'],
    ]))

    // Asserting the element alone would prove nothing here: `div` is also what a MISSING rule
    // returns, so the test would pass against a parser that found nothing at all.
    assert.equal(rules.has(named('mockup-browser')), true)
    assert.equal(rules.has(named('browser-mockup')), false)
    assert.equal(getElementForComponent(rules, named('mockup-browser')), 'div')
  })

  it('matches the component class against the filename, which is what the class is named after', () => {
    // The base-class check is the second place the name was taken from the heading. With
    // "File input" it compares against a class `file-input` and never matches, so the element
    // falls back to `div` even when the syntax block is found.
    const rules = parseComponentSkills(new Map([
      [named('file-input'), '### File input\n\n#### Syntax\n```html\n<div class="wrapper">w</div>\n<input class="file-input" />\n```\n'],
    ]))

    assert.equal(getElementForComponent(rules, named('file-input')), 'input')
  })

  it('still falls back to div when the syntax block names no element of the component', () => {
    const rules = parseComponentSkills(new Map([
      [named('card'), '### Card\n\n#### Syntax\n```html\n<p>nothing of ours here</p>\n```\n'],
    ]))

    assert.equal(getElementForComponent(rules, named('card')), 'div')
  })

  it('reads elements out of the Rules prose as well', () => {
    const rules = parseComponentSkills(new Map([
      [named('link'), '### Link\n\n#### Rules\n- Use it on an <a> element\n'],
    ]))

    assert.equal(getElementForComponent(rules, named('link')), 'a')
  })

  it('stops collecting at the next heading', () => {
    const rules = parseComponentSkills(new Map([
      [named('badge'), '### Badge\n\n#### Syntax\n```html\n<span class="badge">b</span>\n```\n\n#### Class names\n<table class="not-ours">\n'],
    ]))

    assert.equal(getElementForComponent(rules, named('badge')), 'span')
  })
})

describe('parseLlmsTxtContent, the legacy single-blob source', () => {
  it('still keys by heading, because a blob has no filenames', () => {
    // `packages/docs/static/llms.txt` vanished in DaisyUI 5.5.23. The reader still prefers it
    // when present, so an older pin keeps working — and there the heading is the only name
    // available.
    const rules = parseLlmsTxtContent('### card\n\n#### Syntax\n```html\n<div class="card">c</div>\n```\n')

    assert.equal(getElementForComponent(rules, named('card')), 'div')
  })
})
