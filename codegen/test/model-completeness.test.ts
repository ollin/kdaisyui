import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { classFindings } from '../src/model-completeness.ts'
import type { ComponentModel } from '../src/model.ts'

const MASK: ComponentModel = {
  component: 'mask',
  name: 'Mask',
  class: 'mask',
  enums: [{ name: 'MaskStyle', label: 'Style variants', classes: ['mask-circle', 'mask-square'] }],
  functions: [{ kind: 'main', name: 'daisyMask', receiver: 'FlowContent', element: 'IMG', class: 'mask', text: false, parameters: [{ kind: 'enum', name: 'style', enum: 'MaskStyle', doc: '' }] }],
  notOffered: { 'mask-half': 'not yet' },
}

describe('classFindings', () => {
  it('accepts a model that places or declines every declared class, and nothing else', () => {
    assert.deepEqual(classFindings(MASK, ['mask', 'mask-circle', 'mask-square', 'mask-half']), [])
  })

  it('names a class DaisyUI declares that the model places nowhere', () => {
    assert.deepEqual(classFindings(MASK, ['mask', 'mask-circle', 'mask-square', 'mask-half', 'mask-star']), [
      'mask: DaisyUI declares mask-star, and the model places it nowhere',
    ])
  })

  it('names a class the model places that DaisyUI no longer declares', () => {
    assert.deepEqual(classFindings(MASK, ['mask', 'mask-circle', 'mask-half']), [
      'mask: the model places mask-square, which DaisyUI does not declare',
    ])
  })
})
