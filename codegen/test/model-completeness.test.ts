import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { classFindings, directoryFindings, extraSampleFindings, namingFindings } from '../src/model-completeness.ts'
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
      { ...MASK.functions[0], name: 'daisyStatistics', class: 'stats', stem: 'Statistics', parameters: [] },
      { ...MASK.functions[0], kind: 'part', name: 'daisyStatStat', class: 'stat', parameters: [] },
    ] }

    assert.deepEqual(namingFindings(stats), ['stat: daisyStatStat should be called daisyStat'])
  })

  it("names an enum called otherwise than its function's stem and its axis", () => {
    const mask: ComponentModel = { ...MASK, functions: [{ ...MASK.functions[0], parameters: [{ kind: 'enum', name: 'style', enum: 'MaskShape', doc: '' }] }] }

    assert.deepEqual(namingFindings(mask), ['mask: MaskShape should be called MaskStyle'])
  })

  it('leaves constructions and class-only functions to their declared names, and checks only enum parameters', () => {
    const modal: ComponentModel = { ...MASK, functions: [
      { ...MASK.functions[0], parameters: [{ kind: 'toggle', name: 'open', class: 'mask-open' }] },
      { ...MASK.functions[0], kind: 'custom', name: 'daisyMaskPopover', parameters: [] },
      { ...MASK.functions[0], kind: 'classes', name: 'daisyMaskItem', class: null, element: null, parameters: [] },
    ] }

    assert.deepEqual(namingFindings(modal), [])
  })
})

describe('extraSampleFindings', () => {
  const extra = (name: string, type: string, sample?: string) => ({ kind: 'extra' as const, name, type, default: 'null', apply: '', ...(sample ? { sample } : {}) })
  const withExtras = (...parameters: ReturnType<typeof extra>[]): ComponentModel => ({ ...MASK, functions: [{ ...MASK.functions[0], parameters }] })

  it('names a nullable extra of another type that declares no sample', () => {
    assert.deepEqual(extraSampleFindings(withExtras(extra('theme', 'Foo?'), extra('kind', 'Foo?', 'Foo.Bar'))), ['mask: daisyMask.theme needs a sample'])
  })

  it('accepts a String, a Boolean, a component enum bare or in ClassValues, and a non-nullable type', () => {
    const driven = withExtras(extra('a', 'String?'), extra('b', 'Boolean?'), extra('c', 'MaskStyle?'), extra('d', 'ClassValues<MaskStyle>?'), extra('e', 'Foo'))
    assert.deepEqual(extraSampleFindings(driven), [])
  })
})
