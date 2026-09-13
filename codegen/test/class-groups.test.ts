import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { classifyGroups, GroupNamingError } from '../src/class-groups.ts'
import type { EnumNames } from '../src/class-groups.ts'
import type { ClassifiedComponent } from '../src/classifier.ts'

function component(overrides: Partial<ClassifiedComponent>): ClassifiedComponent {
  return {
    componentName: 'Button',
    componentClass: 'Btn',
    desc: '',
    prefix: 'btn',
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

const NO_NAMES: EnumNames = {}

describe('classifyGroups', () => {
  it('turns a named styles group into one enum', () => {
    const result = classifyGroups(
      component({ styles: ['outline', 'dash', 'soft'] }),
      'button',
      { button: { styles: 'Emphasis' } },
    )

    assert.deepEqual(result.enums, [
      { enumName: 'ButtonEmphasis', category: 'styles', members: ['outline', 'dash', 'soft'] },
    ])
    assert.deepEqual(result.booleans, [])
  })

  it('leaves modifiers boolean without an explicit entry', () => {
    // The default runs the other way here: six of the seven groups that failed the name test
    // were `modifiers`, DaisyUI's catch-all.
    const result = classifyGroups(
      component({ modifiers: ['hover', 'open', 'close'] }),
      'dropdown',
      NO_NAMES,
    )

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['hover', 'open', 'close'])
  })

  it('promotes a modifiers group that was given a name', () => {
    const result = classifyGroups(
      component({ modifiers: ['wide', 'block', 'square', 'circle'] }),
      'button',
      { button: { modifiers: 'Layout' } },
    )

    assert.deepEqual(result.enums.map((e) => e.enumName), ['ButtonLayout'])
    assert.deepEqual(result.booleans, [])
  })

  it('splits a two-axis placement group into one enum per axis', () => {
    const result = classifyGroups(
      component({
        componentName: 'Indicator',
        placements: ['top', 'middle', 'bottom', 'start', 'center', 'end'],
      }),
      'indicator',
      {
        indicator: {
          placements: [
            { name: 'Vertical', members: ['top', 'middle', 'bottom'] },
            { name: 'Horizontal', members: ['start', 'center', 'end'] },
          ],
        },
      },
    )

    assert.deepEqual(
      result.enums.map((e) => [e.enumName, e.members]),
      [
        ['IndicatorVertical', ['top', 'middle', 'bottom']],
        ['IndicatorHorizontal', ['start', 'center', 'end']],
      ],
    )
  })

  it('keeps a lone member boolean even in an enum-by-default category', () => {
    // One class answers no question on its own — `divider-start` alone is a flag.
    const result = classifyGroups(component({ placements: ['start'] }), 'divider', NO_NAMES)

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['start'])
  })

  it('fails the run on an unnamed multi-member group in an enum-by-default category', () => {
    // The point of the whole module: a group nobody named is a group that would otherwise let
    // a caller set two contradictory answers at once.
    assert.throws(
      () => classifyGroups(component({ styles: ['box', 'border', 'lift'] }), 'tab', NO_NAMES),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /tab\.styles/)
        assert.match(error.message, /box, border, lift/)
        assert.match(error.message, /enumNames\.tab\.styles/)
        return true
      },
    )
  })

  it('rejects a split that leaves a member unassigned', () => {
    // A new DaisyUI class landing in a split group must stop the build, not quietly become a
    // boolean nobody chose.
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Toast', placements: ['top', 'bottom', 'start'] }),
          'toast',
          { toast: { placements: [{ name: 'Vertical', members: ['top', 'bottom'] }] } },
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /"start" unassigned/)
        return true
      },
    )
  })

  it('rejects a split naming a class DaisyUI does not list', () => {
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Toast', placements: ['top'] }),
          'toast',
          { toast: { placements: [{ name: 'Vertical', members: ['top', 'centre'] }] } },
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /"centre"/)
        return true
      },
    )
  })

  it('classifies every category of one component in a single pass', () => {
    const result = classifyGroups(
      component({
        styles: ['outline', 'dash'],
        modifiers: ['wide', 'block'],
        behaviors: ['active', 'disabled'],
      }),
      'button',
      { button: { styles: 'Emphasis', modifiers: 'Layout' } },
    )

    assert.deepEqual(result.enums.map((e) => e.enumName), ['ButtonEmphasis', 'ButtonLayout'])
    assert.deepEqual(result.booleans, ['active', 'disabled'])
  })
})
