import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { categoryWord, classifyGroups, ExclusivityError, GroupNamingError } from '../src/class-groups.ts'
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
  it('turns a measured single choice into one enum named after its category', () => {
    // Nobody names it: the word is DaisyUI's, so `loading.styles` is `LoadingStyle` and a reader
    // holding DaisyUI's docs can find the group. `parameterName` is the same word in camelCase.
    const members = ['spinner', 'dots', 'bars']

    const result = classifyGroups(
      component({ componentName: 'Loading', styles: members }),
      'loading',
      NO_NAMES,
      measured({ loading: { styles: oneChoice(members) } }),
    )

    assert.deepEqual(result.enums, [
      { enumName: 'LoadingStyle', parameterName: 'style', category: 'styles', members },
    ])
    assert.deepEqual(result.booleans, [])
  })

  it('singularises every category word', () => {
    assert.deepEqual(
      ['styles', 'modifiers', 'behaviors', 'directions', 'placements'].map(categoryWord),
      ['Style', 'Modifier', 'Behavior', 'Direction', 'Placement'],
    )
  })

  it('refuses a configured name for a single-axis group, because the name is derived', () => {
    // `MaskShape` was the one invented name; a config that may restate a rule will drift from
    // it. The message says what the derived name is, so the fix is a deletion.
    const members = ['circle', 'square', 'heart']

    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Mask', styles: members }),
          'mask',
          { mask: { styles: 'Shape' } } as unknown as EnumNames,
          measured({ mask: { styles: oneChoice(members) } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /names a single-axis group "Shape"/)
        assert.match(error.message, /MaskStyle/)
        assert.match(error.message, /Remove the entry/)
        return true
      },
    )
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
      NO_NAMES,
      measured({ card: { modifiers: { exclusive: ['side|image-full'] } } }),
    )

    assert.deepEqual(result.enums.map((group) => group.enumName), ['CardModifier'])
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

  it('rejects a configured axis the measurement refutes', () => {
    // Config asserting a choice the browser denies is the asymmetric error, so it stops the
    // run rather than shipping an enum that hides a reachable combination.
    assert.throws(
      () =>
        classifyGroups(
          component({ styles: ['outline', 'ghost', 'link'] }),
          'button',
          { button: { styles: [{ name: 'Emphasis', members: ['outline', 'ghost'] }, { name: 'Kind', members: ['link'] }] } },
          measured({ button: { styles: { compose: ['outline|ghost', 'outline|link', 'ghost|link'] } } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof ExclusivityError)
        assert.match(error.message, /outline\|ghost is compose/)
        assert.match(error.message, /impossible to express/)
        return true
      },
    )
  })

  it('withdraws a derived enum when DaisyUI adds a member that composes', () => {
    // `CardModifier` is honest only while every modifier belongs to it. A newcomer that composes
    // makes the group no longer a choice, so the whole group falls back to booleans — the
    // measurement's answer, not an enum claiming to cover a class it cannot. That a newcomer
    // exists at all is caught earlier by `:lib:verifyExclusivity`, which fails until the
    // measurement is re-run; this is what the re-run then produces.
    const result = classifyGroups(
      component({ componentName: 'Card', modifiers: ['side', 'image-full', 'newcomer'] }),
      'card',
      NO_NAMES,
      measured({
        card: {
          modifiers: {
            exclusive: ['side|image-full'],
            compose: ['side|newcomer', 'image-full|newcomer'],
          },
        },
      }),
    )

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['side', 'image-full', 'newcomer'])
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

  it('splits a group into one axis and some declared flags', () => {
    // `dropdown.placements` as measured: `start|center|end` is a clean choice, while `left` and
    // `right` compose with everything including `top` and `bottom`. Declaring the sides as an
    // axis would make `dropdown-left dropdown-top` inexpressible.
    const result = classifyGroups(
      component({
        componentName: 'Dropdown',
        placements: ['start', 'center', 'end', 'top', 'bottom', 'left', 'right'],
      }),
      'dropdown',
      {
        dropdown: {
          placements: {
            axes: [{ name: 'AlignPlacement', members: ['start', 'center', 'end'] }],
            booleans: ['top', 'bottom', 'left', 'right'],
          },
        },
      },
      measured({
        dropdown: {
          placements: {
            exclusive: ['start|center', 'start|end', 'center|end', 'top|bottom'],
            compose: ['top|left', 'top|right', 'bottom|left', 'bottom|right', 'left|right'],
          },
        },
      }),
    )

    assert.deepEqual(result.enums.map((group) => group.enumName), ['DropdownAlignPlacement'])
    assert.deepEqual(result.booleans, ['top', 'bottom', 'left', 'right'])
  })

  it('keeps DaisyUI\'s order for declared flags, not the config\'s', () => {
    // Otherwise reordering a list in the config reorders generated parameters, and the diff
    // claims an API change that nobody made.
    const result = classifyGroups(
      component({ componentName: 'Dropdown', placements: ['start', 'top', 'bottom'] }),
      'dropdown',
      { dropdown: { placements: { axes: [], booleans: ['bottom', 'top', 'start'] } } },
      measured({ dropdown: { placements: { compose: ['start|top', 'start|bottom', 'top|bottom'] } } }),
    )

    assert.deepEqual(result.booleans, ['start', 'top', 'bottom'])
  })

  it('rejects a named group that leaves a member in neither list', () => {
    // The whole point of declaring booleans: a class DaisyUI adds later is in no list and stops
    // the run, instead of becoming a flag by omission.
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Dropdown', placements: ['start', 'center', 'end', 'newcomer'] }),
          'dropdown',
          {
            dropdown: {
              placements: {
                axes: [{ name: 'AlignPlacement', members: ['start', 'center', 'end'] }],
                booleans: [],
              },
            },
          },
          NOT_MEASURED,
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /"newcomer" unassigned/)
        assert.match(error.message, /or be listed under "booleans"/)
        return true
      },
    )
  })

  it('rejects declared flags that the measurement says are a choice', () => {
    // Declaring a choice as flags is the mirror of naming flags as a choice, and costs the
    // caller the type safety the measurement earned.
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Tab', placements: ['top', 'bottom'] }),
          'tab',
          { tab: { placements: { axes: [], booleans: ['top', 'bottom'] } } },
          measured({ tab: { placements: { exclusive: ['top|bottom'] } } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof ExclusivityError)
        assert.match(error.message, /lists top, bottom as booleans/)
        assert.match(error.message, /Give it an axis/)
        return true
      },
    )
  })

  it('accepts flags that hold an exclusive pair but are not a choice as a whole', () => {
    // `top|bottom` is exclusive while both compose with `left`. Reporting that as a missed enum
    // would fire on the one group this form exists for.
    const result = classifyGroups(
      component({ componentName: 'Dropdown', placements: ['top', 'bottom', 'left'] }),
      'dropdown',
      { dropdown: { placements: { axes: [], booleans: ['top', 'bottom', 'left'] } } },
      measured({
        dropdown: {
          placements: { exclusive: ['top|bottom'], compose: ['top|left', 'bottom|left'] },
        },
      }),
    )

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['top', 'bottom', 'left'])
  })

  it('classifies every category of one component in a single pass', () => {
    const result = classifyGroups(
      component({
        styles: ['outline', 'dash'],
        modifiers: ['wide', 'block'],
        behaviors: ['active', 'disabled'],
      }),
      'button',
      NO_NAMES,
      measured({
        button: {
          styles: { exclusive: ['outline|dash'] },
          modifiers: { exclusive: ['wide|block'] },
          behaviors: { compose: ['active|disabled'] },
        },
      }),
    )

    assert.deepEqual(result.enums.map((group) => group.enumName), ['ButtonStyle', 'ButtonModifier'])
    assert.deepEqual(result.booleans, ['active', 'disabled'])
  })
})
