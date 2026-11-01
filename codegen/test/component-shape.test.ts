import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { hasBuilder, hasSibling, isVoidElement, siblingParameters, type CssClass, type ParameterShape, type SiblingShape, type TagClass } from '../src/component-shape.ts'

/**
 * The rules of the shape that the committed models exercise only in part: the models use three of
 * the fourteen void elements, one library element, and no extra that is not already a Boolean.
 */

const VOID = ['AREA', 'BASE', 'BR', 'COL', 'EMBED', 'HR', 'IMG', 'INPUT', 'LINK', 'META', 'PARAM', 'SOURCE', 'TRACK', 'WBR']

function sibling(overrides: Partial<SiblingShape>): SiblingShape {
  return { name: 'daisyX', cssClass: null, modifierClasses: [], parameters: [], ...overrides }
}

describe('isVoidElement', () => {
  it("is true for each of the specification's fourteen void elements", () => {
    assert.deepEqual(VOID.filter(element => !isVoidElement(element as TagClass)), [])
  })

  it('is false for an element that takes content', () => {
    assert.equal(isVoidElement('DIV' as TagClass), false)
  })
})

describe('hasBuilder', () => {
  it("knows the library's own elements beside kotlinx.html's", () => {
    assert.equal(hasBuilder('calendar-date'), true)
    assert.equal(hasBuilder('div'), true)
  })

  it('refuses a tag neither builds', () => {
    assert.equal(hasBuilder('blink'), false)
  })
})

describe('siblingParameters', () => {
  it('offers an extra that writes a class as a plain toggle, whatever its own type', () => {
    const extra: ParameterShape = { name: 'loading', type: 'String?', default: 'null', doc: null, writes: ['btn-loading' as CssClass] }

    assert.deepEqual(siblingParameters(sibling({ parameters: [extra] })), [{ ...extra, type: 'Boolean', default: 'false' }])
  })
})

describe('hasSibling', () => {
  it('is true for a function that writes only the classes it always carries', () => {
    assert.equal(hasSibling(sibling({ modifierClasses: ['menu-sm' as CssClass] })), true)
  })

  it('is false for a function that writes no class at all', () => {
    assert.equal(hasSibling(sibling({})), false)
  })
})
