import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { expressingFunction, gapKey, gapsIn } from '../src/documented-elements.ts'
import { component, part, toggle } from './model-fixture.ts'

const CARD = component('card', 'Card', 'card')
const CARD_WITH_BODY = { ...CARD, functions: [...CARD.functions, part('daisyCardBody', 'card-body')] }
const BUTTON = component('button', 'Button', 'btn', {}, { parameters: [toggle('active', 'btn-active')], joinItem: true })

describe('expressingFunction', () => {
  it("finds the part whose own class the element carries", () => {
    assert.equal(expressingFunction([CARD_WITH_BODY], ['card-body'])?.fn.name, 'daisyCardBody')
  })

  it("finds another component's function, with its modifiers and the join item", () => {
    assert.equal(expressingFunction([CARD_WITH_BODY, BUTTON], ['btn', 'btn-active', 'join-item'])?.fn.name, 'daisyButton')
  })

  it('finds nothing when the function lacks one of the classes', () => {
    assert.equal(expressingFunction([CARD_WITH_BODY, BUTTON], ['btn', 'btn-ghost']), null)
  })
})

describe('gapKey', () => {
  it("keys two components on one element by their own classes, whatever modifiers ride along", () => {
    assert.equal(gapKey([CARD_WITH_BODY, BUTTON], ['card-body', 'btn-active', 'btn']), 'btn card-body')
  })

  it('keys a gap within one component by every class', () => {
    assert.equal(gapKey([BUTTON], ['btn-ghost', 'btn']), 'btn btn-ghost')
  })
})

describe('gapsIn', () => {
  it('names each element no function expresses, by its tag and key, ignoring classes no component declares', () => {
    const html = '<div class="$$card-body $$rounded-box"><button class="$$btn $$btn-ghost">x</button><li class="$$list-row lg:$$card-body">y</li></div>'

    assert.deepEqual(gapsIn(html, [CARD_WITH_BODY, BUTTON], new Set(['card-body', 'btn', 'btn-ghost', 'list-row'])), [{ tag: 'button', key: 'btn btn-ghost' }, { tag: 'li', key: 'list-row' }])
  })
})
