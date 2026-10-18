import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { documentedCall, expressingFunction, gapFindings, gapKey, gapsIn } from '../src/documented-elements.ts'
import { markedElements } from '../src/parser/documented-classes.ts'
import { component, enumParameter, part, toggle } from './model-fixture.ts'

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

describe('gapFindings', () => {
  const gaps = [{ tag: 'li', key: 'list-row' }, { tag: 'li', key: 'list-row' }, { tag: 'button', key: 'btn btn-ghost' }]

  it('names each undeclared gap once, and each declaration no gap needs', () => {
    const list = { ...CARD, component: 'list', notExpressed: { 'list-row': 'no function yet', 'list-col-grow': 'gone' } }

    assert.deepEqual(gapFindings(list, gaps), [
      'list: <button> btn btn-ghost is documented, and no function expresses it',
      'list: list-col-grow is declared not expressed, and no element on the page needs it',
    ])
  })
})

describe('documentedCall', () => {
  const sized = { ...BUTTON, enums: [{ name: 'ButtonSize', label: 'Size variants', classes: ['btn-lg', 'btn-sm'] }], functions: [{ ...BUTTON.functions[0], parameters: [...BUTTON.functions[0].parameters, enumParameter('size', 'ButtonSize', '')] }] }
  const classes = new Set(['btn', 'btn-active', 'btn-lg', 'btn-sm', 'join-item', 'list-row'])
  const callFor = (html: string) => documentedCall([sized], markedElements(html)[0], classes)

  it('passes each class through its typed parameter, and a prefixed one through extraClasses', () => {
    assert.deepEqual(callFor('<button class="$$btn $$btn-active md:$$btn-lg">x</button>'), { fn: sized.functions[0], args: 'active = true, extraClasses = "md:btn-lg"', expectedClasses: 'btn btn-active md:btn-lg' })
  })

  it('neither passes nor expects join-item, and names an enum entry', () => {
    assert.deepEqual(callFor('<button class="$$btn $$btn-sm $$join-item">x</button>')?.args, 'size = ButtonSize.Sm')
    assert.equal(callFor('<button class="$$btn $$join-item">x</button>')?.expectedClasses, 'btn')
  })

  it('makes no call for a gap', () => {
    assert.equal(callFor('<li class="$$list-row">x</li>'), null)
  })
})
