import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { observeElements } from '../src/element-observation.ts'
import { buildComponentShape } from '../src/component-shape.ts'
import type { ClassifiedComponent } from '../src/classifier.ts'

function classified(overrides: Partial<ClassifiedComponent> = {}): ClassifiedComponent {
  return {
    componentName: 'Menu',
    componentClass: 'Menu',
    desc: '',
    prefix: 'menu',
    colors: [],
    styles: [],
    sizes: [],
    modifiers: [],
    behaviors: [],
    parts: [],
    directions: [],
    placements: [],
    defaultSize: null,
    descs: {},
    ...overrides,
  } as ClassifiedComponent
}

const documented = new Map([
  ['menu', 'UL'],
  ['menu-active', 'LI'],
  ['menu-title', 'LI'],
])

describe('observeElements', () => {
  test('observes the component class on the main function', () => {
    const shape = buildComponentShape(classified(), { componentDir: 'menu', element: 'UL' }, {})

    const [own] = observeElements(shape, documented)

    assert.deepEqual(own, { componentDir: 'menu', cssClass: 'menu', isComponentClass: true, chosen: 'UL', documented: 'UL' })
  })

  test('observes every boolean on the function that declares it', () => {
    // The defect: a modifier of a child declared on the container. The observation records
    // the container's element as chosen and the child's as documented, so the check can say so.
    const shape = buildComponentShape(
      classified({ modifiers: ['active'] }),
      { componentDir: 'menu', element: 'UL' },
      {},
    )

    const active = observeElements(shape, documented).find((o) => o.cssClass === 'menu-active')

    assert.deepEqual(active, { componentDir: 'menu', cssClass: 'menu-active', isComponentClass: false, chosen: 'UL', documented: 'LI' })
  })

  test('observes a part on its own function, not the main one', () => {
    const shape = buildComponentShape(
      classified({ parts: ['menu-title'] }),
      { componentDir: 'menu', element: 'UL' },
      {},
    )

    const title = observeElements(shape, documented).find((o) => o.cssClass === 'menu-title')
    const [main] = shape.functions

    assert.notEqual(title?.chosen, main.element)
    assert.equal(title?.chosen, shape.functions[1].element)
    assert.equal(title?.isComponentClass, false)
  })

  test('records null for a class DaisyUI documents nowhere', () => {
    const shape = buildComponentShape(
      classified({ modifiers: ['ghostly'] }),
      { componentDir: 'menu', element: 'UL' },
      {},
    )

    const ghostly = observeElements(shape, documented).find((o) => o.cssClass === 'menu-ghostly')

    assert.equal(ghostly?.documented, null)
  })
})
