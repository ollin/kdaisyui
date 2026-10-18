import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { joinScopeMembers, withJoinScope } from '../src/join-scope.ts'
import type { ComponentShape, FunctionShape, ParameterShape } from '../src/component-shape.ts'
import { component, shapeOf } from './model-fixture.ts'

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

/** The join component as the model declares it, before the scope is attached. */
function joinShape(): ComponentShape {
  return shapeOf(component('join', 'Join', 'join'))
}

const parameterNamed = (shape: ComponentShape, name: string): ParameterShape | undefined =>
  shape.functions[0].parameters.find(parameter => parameter.name === name)

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

describe('withJoinScope', () => {
  const member = (name: string): FunctionShape =>
    ({ kind: 'main', name, element: 'BUTTON', parameters: [] }) as unknown as FunctionShape

  test("the content lambda's receiver becomes the scope", () => {
    const joined = withJoinScope(joinShape(), [member('daisyButton')])

    assert.equal(parameterNamed(joined, 'content')?.type, '(JoinScope.() -> Unit)')
  })

  test('the element keeps its own escape hatch, because the scope IS that element', () => {
    // JoinScope extends DIV, so `attrs` still reaches a DIV and every kotlinx.html builder
    // still works inside the lambda. Only the RECEIVER of `content` changes.
    const joined = withJoinScope(joinShape(), [member('daisyButton')])

    assert.equal(parameterNamed(joined, 'attrs')?.type, '(DIV.() -> Unit)?')
  })

  test('the main function says which scope its body must open', () => {
    const joined = withJoinScope(joinShape(), [member('daisyButton')])

    assert.equal(joined.functions[0].contentScope, 'JoinScope')
  })

  test('the scope carries its members and the class each of them adds', () => {
    const joined = withJoinScope(joinShape(), [member('daisyButton'), member('daisySelect')])

    assert.equal(joined.scope?.name, 'JoinScope')
    assert.equal(joined.scope?.markerClass, 'join-item')
    assert.deepEqual(joined.scope?.members.map(fn => fn.name), ['daisyButton', 'daisySelect'])
  })

  test('nothing else about the component moves', () => {
    const before = joinShape()
    const after = withJoinScope(before, [member('daisyButton')])

    assert.equal(after.functions.length, before.functions.length)
    assert.deepEqual(after.enums, before.enums)
    assert.deepEqual(
      after.functions[0].parameters.map(parameter => parameter.name),
      before.functions[0].parameters.map(parameter => parameter.name),
    )
  })
})
