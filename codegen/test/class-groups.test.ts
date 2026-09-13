import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import {
  classifyGroups,
  loadExclusivity,
  ExclusivityError,
  GroupNamingError,
} from '../src/class-groups.ts'
import type { EnumNames, Exclusivity } from '../src/class-groups.ts'
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

/** Every pair of `members`, all called exclusive — the shape of a measured single choice. */
function allExclusive(members: readonly string[]): { exclusive: string[] } {
  const pairs: string[] = []
  for (let i = 0; i < members.length; i++)
    for (let j = i + 1; j < members.length; j++) pairs.push(`${members[i]}|${members[j]}`)
  return { exclusive: pairs }
}

const NO_NAMES: EnumNames = {}
const NOT_MEASURED: Exclusivity = {}

describe('classifyGroups', () => {
  it('turns a measured single choice into one enum once it is named', () => {
    const result = classifyGroups(
      component({ componentName: 'Loading', styles: ['spinner', 'dots', 'bars'] }),
      'loading',
      { loading: { styles: 'Animation' } },
      { loading: { styles: allExclusive(['spinner', 'dots', 'bars']) } },
    )

    assert.deepEqual(result.enums, [
      { enumName: 'LoadingAnimation', category: 'styles', members: ['spinner', 'dots', 'bars'] },
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
      { tab: { styles: { compose: ['box|border', 'box|lift', 'border|lift'] } } },
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
      { card: { modifiers: { exclusive: ['side|image-full'] } } },
    )

    assert.deepEqual(result.enums.map((e) => e.enumName), ['CardLayout'])
    assert.deepEqual(result.booleans, [])
  })

  it('treats "same" as not established, because inventing exclusivity is the costly error', () => {
    const result = classifyGroups(
      component({ componentName: 'Stack', modifiers: ['top', 'bottom'] }),
      'stack',
      NO_NAMES,
      { stack: { modifiers: { same: ['top|bottom'] } } },
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
      {
        indicator: {
          placements: {
            exclusive: [
              'top|middle',
              'top|bottom',
              'middle|bottom',
              'start|center',
              'start|end',
              'center|end',
            ],
            compose: [
              'start|top',
              'start|middle',
              'start|bottom',
              'center|top',
              'center|middle',
              'center|bottom',
              'end|top',
              'end|middle',
              'end|bottom',
            ],
          },
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
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Mask', styles: ['circle', 'square', 'heart'] }),
          'mask',
          NO_NAMES,
          { mask: { styles: allExclusive(['circle', 'square', 'heart']) } },
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
          { button: { styles: { compose: ['outline|ghost'] } } },
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
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Modal', placements: ['top', 'bottom', 'start', 'end'] }),
          'modal',
          {
            modal: {
              placements: [
                { name: 'Vertical', members: ['top', 'bottom'] },
                { name: 'Horizontal', members: ['start', 'end'] },
              ],
            },
          },
          { modal: { placements: allExclusive(['top', 'bottom', 'start', 'end']) } },
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
      {
        button: {
          styles: { exclusive: ['outline|dash'] },
          modifiers: { exclusive: ['wide|block'] },
          behaviors: { compose: ['active|disabled'] },
        },
      },
    )

    assert.deepEqual(result.enums.map((e) => e.enumName), ['ButtonEmphasis', 'ButtonLayout'])
    assert.deepEqual(result.booleans, ['active', 'disabled'])
  })
})

describe('the committed measurement', () => {
  const exclusivity = loadExclusivity()

  /** Every group whose pairs are ALL exclusive — the ones that may become an enum as they are. */
  function singleChoices(): string[] {
    const found: string[] = []
    for (const [dir, categories] of Object.entries(exclusivity)) {
      for (const [category, measured] of Object.entries(categories)) {
        const total =
          (measured.exclusive?.length ?? 0) +
          (measured.compose?.length ?? 0) +
          (measured.same?.length ?? 0)
        if (total > 0 && total === (measured.exclusive?.length ?? 0)) found.push(`${dir}.${category}`)
      }
    }
    return found.sort()
  }

  it('pins which groups the browser called a single choice', () => {
    // Task 2.3. These fifteen are what block 3 may turn into an enum without splitting first;
    // a DaisyUI bump that moves one of them should fail here and be read, not re-dumped.
    assert.deepEqual(singleChoices(), [
      'alert.directions',
      'card.modifiers',
      'card.styles',
      'carousel.modifiers',
      'divider.directions',
      'join.directions',
      'list.modifiers',
      'loading.styles',
      'mask.modifiers',
      'mask.styles',
      'menu.directions',
      'pagination.directions',
      'stat.directions',
      'steps.directions',
      'tab.placements',
    ])
  })

  it('pins the four groups that need an axis split', () => {
    // Neither one clique nor all-boolean: each holds two cliques that overlap or compose.
    // `enumNames` declares the axes and `classifyGroups` checks them against these pairs.
    for (const group of [
      'indicator.placements',
      'toast.placements',
      'dropdown.placements',
      'tooltip.placements',
    ]) {
      const [dir, category] = group.split('.')
      const measured = exclusivity[dir][category]
      assert.ok((measured.exclusive?.length ?? 0) > 0, `${group} has exclusive pairs`)
      assert.ok((measured.compose?.length ?? 0) > 0, `${group} has composing pairs`)
    }
  })

  it('covers 44 groups and 310 pairs', () => {
    let groups = 0
    let pairs = 0
    for (const categories of Object.values(exclusivity)) {
      for (const measured of Object.values(categories)) {
        groups++
        pairs +=
          (measured.exclusive?.length ?? 0) +
          (measured.compose?.length ?? 0) +
          (measured.same?.length ?? 0)
      }
    }
    assert.equal(groups, 44)
    assert.equal(pairs, 310)
  })
})
