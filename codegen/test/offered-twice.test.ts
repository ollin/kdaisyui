import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { classesOfferedTwice } from '../src/offered-twice.ts'
import type { ComponentShape, EnumShape, ExtraParameter, ParameterShape } from '../src/component-shape.ts'

const PLACEMENT: EnumShape = {
  name: 'DropdownHorizontalPlacement',
  prefix: 'dropdown',
  categoryLabel: 'HorizontalPlacement variants',
  documented: true,
  entries: [
    { name: 'Start', cssClass: 'dropdown-start', desc: null },
    { name: 'End', cssClass: 'dropdown-end', desc: null },
  ],
}

const PLACEMENT_PARAMETER: ParameterShape = {
  name: 'horizontalPlacement',
  type: 'ClassValues<DropdownHorizontalPlacement>?',
  default: 'null',
  doc: null,
  enumName: 'DropdownHorizontalPlacement',
}

/** A dropdown reduced to what the guard reads: its enums and its main function's parameters. */
function dropdown(parameters: readonly ParameterShape[]): ComponentShape {
  return {
    componentName: 'Dropdown',
    componentDir: 'dropdown',
    prefix: 'dropdown',
    enums: [PLACEMENT],
    functions: [{ kind: 'main', name: 'daisyDropdown', parameters }],
  } as unknown as ComponentShape
}

function extra(name: string, cssClass: string): ExtraParameter {
  return { name, type: 'Boolean', default: 'false', apply: `if (${name}) addClassNames("${cssClass}")` }
}

describe('classesOfferedTwice', () => {
  test('reports an extras boolean writing a class its function offers as an enum constant', () => {
    const offered = classesOfferedTwice(dropdown([PLACEMENT_PARAMETER]), [extra('end', 'dropdown-end')])

    assert.deepEqual(offered, [
      { functionName: 'daisyDropdown', booleanName: 'end', enumName: 'DropdownHorizontalPlacement', cssClass: 'dropdown-end' },
    ])
  })
})
