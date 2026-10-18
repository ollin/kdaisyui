import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { claimingGroups, documentedCall, expressingFunction, gapFindings, gapKey, gapsIn, pageFirst } from '../src/documented-elements.ts'
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

  it("calls the first model's function and adds another component's classes through its sibling", () => {
    const call = documentedCall([CARD_WITH_BODY, sized], markedElements('<div class="$$btn $$btn-lg $$card-body">x</div>')[0], new Set([...classes, 'card-body']))

    assert.deepEqual({ fn: call?.fn.name, args: call?.args, expected: call?.expectedClasses }, { fn: 'daisyCardBody', args: 'attrs = { daisyButtonClasses(size = ButtonSize.Lg) }', expected: 'btn btn-lg card-body' })
  })

  it('makes no call for a gap', () => {
    assert.equal(callFor('<li class="$$list-row">x</li>'), null)
  })
})

describe('pageFirst', () => {
  it("puts the page's own model first and keeps the others in their order", () => {
    assert.deepEqual(pageFirst([CARD, BUTTON, CARD_WITH_BODY], 'button'), [BUTTON, CARD, CARD_WITH_BODY])
  })
})

describe('claimingGroups', () => {
  const claims = (models, counted) => {
    const { groups, unclaimed } = claimingGroups(models, counted)
    return { groups: groups.map(group => `${group.fn.name}: ${group.classes.join(' ')}`), unclaimed }
  }
  const MENU_ITEM = { ...component('menu', 'Menu', 'menu'), functions: [{ ...part('daisyMenuItem', 'unused'), class: null, parameters: [toggle('active', 'menu-active')] }] }
  const SKELETON = { ...component('skeleton', 'Skeleton', 'skeleton'), functions: [...component('skeleton', 'Skeleton', 'skeleton').functions, part('daisySkeletonText', 'skeleton', { always: ['skeleton-text'] })] }

  it("gives each own class's function its share, so two components make two groups", () => {
    assert.deepEqual(claims([CARD_WITH_BODY, BUTTON], ['card-body', 'btn', 'btn-active']), { groups: ['daisyCardBody: card-body', 'daisyButton: btn btn-active'], unclaimed: [] })
  })

  it('lets a function with no own class claim what remains whole', () => {
    assert.deepEqual(claims([MENU_ITEM], ['menu-active']), { groups: ['daisyMenuItem: menu-active'], unclaimed: [] })
  })

  it('takes, of two functions with one own class, the one offering more', () => {
    assert.deepEqual(claims([SKELETON], ['skeleton', 'skeleton-text']).groups, ['daisySkeletonText: skeleton skeleton-text'])
  })

  it('leaves unclaimed what no function offers', () => {
    assert.deepEqual(claims([BUTTON], ['btn', 'btn-ghost']).unclaimed, ['btn-ghost'])
  })
})
