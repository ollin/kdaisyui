import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { classifyGroups, ExclusivityError, GroupNamingError } from '../src/class-groups.ts'
import type { EnumNames } from '../src/class-groups.ts'
import { ClassPair, Measurement } from '../src/measurement.ts'
import type { ExclusivityJson } from '../src/measurement.ts'
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

/** A measurement calling every pair of `members` exclusive — the shape of a single choice. */
function oneChoice(members: readonly string[]): { exclusive: string[] } {
  return { exclusive: ClassPair.allOf(members).map(String) }
}

function measured(json: ExclusivityJson): Measurement {
  return Measurement.fromJson(json)
}

const NO_NAMES: EnumNames = {}
const NOT_MEASURED = Measurement.fromJson({})

describe('classifyGroups', () => {
  it('turns a measured single choice into one enum once it is named', () => {
    const members = ['spinner', 'dots', 'bars']

    const result = classifyGroups(
      component({ componentName: 'Loading', styles: members }),
      'loading',
      { loading: { styles: 'Animation' } },
      measured({ loading: { styles: oneChoice(members) } }),
    )

    assert.deepEqual(result.enums, [
      { enumName: 'LoadingAnimation', category: 'styles', members },
    ])
    assert.deepEqual(result.booleans, [])
  })

  it('leaves a group boolean when one pair composes, whatever its category', () => {
    // `styles` used to become an enum by virtue of being `styles`. The browser says
    // `tab-box tab-border` reaches CSS neither reaches alone, so an enum would make that
    // combination inexpressible — the expensive direction.
    const result = classifyGroups(
      component({ componentName: 'Tab', styles: ['box', 'border', 'lift'] }),
      'tab',
      NO_NAMES,
      measured({ tab: { styles: { compose: ['box|border', 'box|lift', 'border|lift'] } } }),
    )

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['box', 'border', 'lift'])
  })

  it('promotes a modifiers group the measurement calls exclusive', () => {
    // The mirror of the case above: `modifiers` used to be boolean by virtue of being
    // `modifiers`, and `card-side` is inert against `card-image-full`.
    const result = classifyGroups(
      component({ componentName: 'Card', modifiers: ['side', 'image-full'] }),
      'card',
      { card: { modifiers: 'Layout' } },
      measured({ card: { modifiers: { exclusive: ['side|image-full'] } } }),
    )

    assert.deepEqual(result.enums.map((group) => group.enumName), ['CardLayout'])
    assert.deepEqual(result.booleans, [])
  })

  it('treats "same" as not established, because inventing exclusivity is the costly error', () => {
    const result = classifyGroups(
      component({ componentName: 'Stack', modifiers: ['top', 'bottom'] }),
      'stack',
      NO_NAMES,
      measured({ stack: { modifiers: { same: ['top|bottom'] } } }),
    )

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['top', 'bottom'])
  })

  it('treats an unmeasured group as not established', () => {
    const result = classifyGroups(
      component({ styles: ['alpha', 'beta'] }),
      'button',
      NO_NAMES,
      NOT_MEASURED,
    )

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['alpha', 'beta'])
  })

  it('keeps a lone member boolean', () => {
    // One class answers no question on its own — `divider-start` alone is a flag.
    const result = classifyGroups(
      component({ placements: ['start'] }),
      'divider',
      NO_NAMES,
      NOT_MEASURED,
    )

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['start'])
  })

  it('fails the run on an unnamed group whose every pair is exclusive', () => {
    // The point of the whole module: a measured choice nobody named would otherwise let a
    // caller set two contradictory answers at once.
    const members = ['circle', 'square', 'heart']

    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Mask', styles: members }),
          'mask',
          NO_NAMES,
          measured({ mask: { styles: oneChoice(members) } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /mask\.styles/)
        assert.match(error.message, /circle, square, heart/)
        assert.match(error.message, /enumNames\.mask\.styles/)
        return true
      },
    )
  })

  it('rejects a configured enum the measurement refutes', () => {
    // Config asserting a choice the browser denies is the asymmetric error, so it stops the
    // run rather than shipping an enum that hides a reachable combination.
    assert.throws(
      () =>
        classifyGroups(
          component({ styles: ['outline', 'ghost'] }),
          'button',
          { button: { styles: 'Emphasis' } },
          measured({ button: { styles: { compose: ['outline|ghost'] } } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof ExclusivityError)
        assert.match(error.message, /outline\|ghost is compose/)
        assert.match(error.message, /impossible to express/)
        return true
      },
    )
  })

  it('rejects a split whose axes are exclusive across as well as within', () => {
    // Every cross pair exclusive means one clique, and two enums would let a caller answer
    // the same question twice.
    const members = ['top', 'bottom', 'start', 'end']
    const byAxis = [
      { name: 'Vertical', members: ['top', 'bottom'] },
      { name: 'Horizontal', members: ['start', 'end'] },
    ]

    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Modal', placements: members }),
          'modal',
          { modal: { placements: byAxis } },
          measured({ modal: { placements: oneChoice(members) } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof ExclusivityError)
        assert.match(error.message, /Vertical and Horizontal/)
        assert.match(error.message, /single choice/)
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
          NOT_MEASURED,
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
          NOT_MEASURED,
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
      measured({
        button: {
          styles: { exclusive: ['outline|dash'] },
          modifiers: { exclusive: ['wide|block'] },
          behaviors: { compose: ['active|disabled'] },
        },
      }),
    )

    assert.deepEqual(result.enums.map((group) => group.enumName), ['ButtonEmphasis', 'ButtonLayout'])
    assert.deepEqual(result.booleans, ['active', 'disabled'])
  })
})
