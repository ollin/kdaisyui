import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { classesJoinedWith, joinScopeMembers } from '../src/join-scope.ts'
import type { ComponentShape, FunctionShape } from '../src/component-shape.ts'

/** A component shape reduced to what the scope reads: its class, and the functions it has. */
function shape(prefix: string, functions: readonly Partial<FunctionShape>[]): ComponentShape {
  return {
    componentName: prefix,
    componentDir: prefix,
    prefix,
    enums: [],
    functions: functions.map(fn => ({ kind: 'main', name: `daisy${prefix}`, ...fn })),
  } as ComponentShape
}

describe('classesJoinedWith', () => {
  test('collects a class that shares an element with join-item', () => {
    const classes = classesJoinedWith('<div class="$$join"><button class="$$btn $$join-item">A</button></div>')

    assert.deepEqual([...classes], ['btn'])
  })

  test('does not collect join-item itself', () => {
    const classes = classesJoinedWith('<button class="$$btn $$join-item">A</button>')

    assert.equal(classes.has('join-item'), false)
  })

  test('does not collect a class on a sibling element', () => {
    // The rule is about ONE element. A join whose children are a button and a select does not
    // make `select` a companion of `btn`, and a rule reading the whole example block would say
    // it did — the defect `documented-classes.ts` exists to prevent.
    const classes = classesJoinedWith(
      '<div class="$$join"><button class="$$btn $$join-item">A</button><select class="$$select"></select></div>',
    )

    assert.deepEqual([...classes], ['btn'])
  })

  test('does not collect a class a Tailwind variant applies conditionally', () => {
    // `lg:$$btn` is the button class at one breakpoint and not at others, while the generated
    // `daisyButton` always emits it. A member built from that element would mark an element
    // DaisyUI does not document as a join item below `lg`.
    const classes = classesJoinedWith('<button class="lg:$$btn $$join-item">A</button>')

    assert.deepEqual([...classes], [])
  })

  test('ignores an element that does not carry join-item at all', () => {
    const classes = classesJoinedWith('<button class="$$btn">A</button>')

    assert.deepEqual([...classes], [])
  })

  test('collects every companion when one element carries two component classes', () => {
    // Measured shape, not invented: DaisyUI documents
    // `<input class="$$btn $$theme-controller $$join-item">`, which is why the derived set has
    // seven members and only 72 sightings.
    const classes = classesJoinedWith('<input class="$$btn $$theme-controller $$join-item" />')

    assert.deepEqual([...classes].sort(), ['btn', 'theme-controller'])
  })
})

describe('joinScopeMembers', () => {
  test('takes the main function of every component whose class is a companion', () => {
    const members = joinScopeMembers(
      [shape('btn', [{ name: 'daisyButton' }]), shape('card', [{ name: 'daisyCard' }])],
      new Set(['btn', 'card']),
    )

    assert.deepEqual(members.map(member => member.name), ['daisyButton', 'daisyCard'])
  })

  test('leaves out a component whose class never shares an element with join-item', () => {
    const members = joinScopeMembers(
      [shape('btn', [{ name: 'daisyButton' }]), shape('alert', [{ name: 'daisyAlert' }])],
      new Set(['btn']),
    )

    assert.deepEqual(members.map(member => member.name), ['daisyButton'])
  })

  test('leaves out a part, which wears its own class and not the component class', () => {
    // `join-item` is documented beside `card`, never beside `card-body`. A scope member for a
    // part would mark an element DaisyUI documents inside the join item rather than as one.
    const members = joinScopeMembers(
      [shape('card', [{ name: 'daisyCard' }, { kind: 'part', name: 'daisyCardBody' }])],
      new Set(['card']),
    )

    assert.deepEqual(members.map(member => member.name), ['daisyCard'])
  })

  test('orders members by name, so a rename is the only thing that moves the output', () => {
    const members = joinScopeMembers(
      [shape('select', [{ name: 'daisySelect' }]), shape('btn', [{ name: 'daisyButton' }])],
      new Set(['btn', 'select']),
    )

    assert.deepEqual(members.map(member => member.name), ['daisyButton', 'daisySelect'])
  })

  test('ignores a companion class no generated component owns', () => {
    // DaisyUI documents `join-item` beside classes this library does not generate a component
    // for. Those stay on `extraClasses`; they cannot become members, because there is no
    // function to overload — the same reason the six inherited exceptions cannot.
    const members = joinScopeMembers([shape('btn', [{ name: 'daisyButton' }])], new Set(['btn', 'mask']))

    assert.deepEqual(members.map(member => member.name), ['daisyButton'])
  })
})
