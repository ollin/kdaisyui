import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { expressingFunction } from '../src/documented-elements.ts'
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
