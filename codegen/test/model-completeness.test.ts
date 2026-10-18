import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { classFindings, directoryFindings, namingFindings } from '../src/model-completeness.ts'
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

  it('names a function that offers one class twice', () => {
    const twice: ComponentModel = { ...MASK, functions: [{ ...MASK.functions[0], parameters: [...MASK.functions[0].parameters, { kind: 'toggle', name: 'circle', class: 'mask-circle' }] }] }

    assert.deepEqual(classFindings(twice, ['mask', 'mask-circle', 'mask-square', 'mask-half']), ['mask: daisyMask offers mask-circle twice'])
  })
})

describe('directoryFindings', () => {
  it('names a directory without a model file and a model file without a directory', () => {
    assert.deepEqual(directoryFindings(['aura', 'button'], ['button', 'calendar']), [
      'aura: DaisyUI documents it, and it has no model file',
      'calendar: a model file for a directory DaisyUI does not have',
    ])
  })
})

describe('namingFindings', () => {
  it('names a part called otherwise than its class, and accepts a declared stem', () => {
    const stats: ComponentModel = { ...MASK, component: 'stat', functions: [
      { ...MASK.functions[0], name: 'daisyStatistics', class: 'stats', stem: 'Statistics' },
      { ...MASK.functions[0], kind: 'part', name: 'daisyStatStat', class: 'stat', parameters: [] },
    ] }

    assert.deepEqual(namingFindings(stats), ['stat: daisyStatStat should be called daisyStat'])
  })
})
