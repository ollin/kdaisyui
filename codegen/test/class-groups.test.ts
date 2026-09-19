import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { categoryWord, classifyGroups, GroupNamingError } from '../src/class-groups.ts'
import type { EnumNames } from '../src/class-groups.ts'
import { ClassPair, Measurement } from '../src/measurement.ts'
import type { Evidence, ExclusivityJson } from '../src/measurement.ts'
import type { ClassifiedComponent } from '../src/classifier.ts'
import { PropertyTable } from '../src/parser/property-table.ts'

/** A table that names nothing, so every multi-axis group needs `enumNames`. */
const NO_TABLE = PropertyTable.parse('')

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

/** Two cliques that compose across — the shape of two axes. */
function twoAxes(first: readonly string[], second: readonly string[]) {
  return {
    exclusive: [...ClassPair.allOf(first), ...ClassPair.allOf(second)].map(String),
    compose: first.flatMap((left) => second.map((right) => `${left}|${right}`)),
  }
}

/** The measurement alone, with a table that names nothing. */
function measured(json: ExclusivityJson, table: PropertyTable = NO_TABLE): Evidence {
  return { measurement: Measurement.fromJson(json), table }
}

const NO_NAMES: EnumNames = {}
const NOT_MEASURED: Evidence = measured({})

/** DaisyUI's rows for the indicator, verbatim. */
const INDICATOR_TABLE = PropertyTable.parse(
  [
    '| Indicator | `--indicator-y` | vertical position of the indicator   |',
    '|           | `--indicator-x` | horizontal position of the indicator |',
  ].join('\n'),
)

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
          { mask: { styles: ['Shape'] } },
          measured({ mask: { styles: oneChoice(members) } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /DaisyUI already names this single choice — MaskStyle/)
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

  it('treats an unmeasured group as not established', () => {
    const result = classifyGroups(component({ styles: ['alpha', 'beta'] }), 'button', NO_NAMES, NOT_MEASURED)

    assert.deepEqual(result.enums, [])
    assert.deepEqual(result.booleans, ['alpha', 'beta'])
  })

  const horizontal = ['start', 'center', 'end']
  const vertical = ['top', 'middle', 'bottom']
  const indicatorMeasured = {
    ...twoAxes(horizontal, vertical),
    declares: {
      start: ['--indicator-x'], center: ['--indicator-x'], end: ['--indicator-x'],
      top: ['--indicator-y'], middle: ['--indicator-y'], bottom: ['--indicator-y'],
    },
  }

  it('names two derived axes from DaisyUI\'s property table, with no configuration', () => {
    // Each axis's members set one custom property, and DaisyUI's table describes it with a
    // direction word. DaisyUI lists the horizontal classes first, so that axis comes first.
    const result = classifyGroups(
      component({ componentName: 'Indicator', placements: [...horizontal, ...vertical] }),
      'indicator',
      NO_NAMES,
      measured({ indicator: { placements: indicatorMeasured } }, INDICATOR_TABLE),
    )

    assert.deepEqual(result.enums, [
      { enumName: 'IndicatorHorizontalPlacement', parameterName: 'horizontalPlacement', category: 'placements', members: horizontal },
      { enumName: 'IndicatorVerticalPlacement', parameterName: 'verticalPlacement', category: 'placements', members: vertical },
    ])
    assert.deepEqual(result.booleans, [])
  })

  it('refuses a configured name for axes the table already names', () => {
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Indicator', placements: [...horizontal, ...vertical] }),
          'indicator',
          { indicator: { placements: ['HorizontalPlacement', 'VerticalPlacement'] } },
          measured({ indicator: { placements: indicatorMeasured } }, INDICATOR_TABLE),
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /DaisyUI already names these axes/)
        assert.match(error.message, /IndicatorHorizontalPlacement, IndicatorVerticalPlacement/)
        return true
      },
    )
  })

  it('names two derived axes from the configured list when the table cannot', () => {
    // tooltip: its custom properties are "transform offset" and "inset position" — no
    // direction, because the alignment is relative to the side. Then, and only then, the
    // configured names apply, in derivation order.
    const result = classifyGroups(
      component({ componentName: 'Tooltip', placements: [...horizontal, ...vertical] }),
      'tooltip',
      { tooltip: { placements: ['AlignPlacement', 'SidePlacement'] } },
      measured({ tooltip: { placements: twoAxes(horizontal, vertical) } }),
    )

    assert.deepEqual(result.enums.map((group) => group.enumName), ['TooltipAlignPlacement', 'TooltipSidePlacement'])
  })

  it('gives a clique beside composing flags its enum, and keeps the flags', () => {
    // dropdown.placements as measured: `start|center|end` and `top|bottom` are cliques, `left`
    // and `right` compose with everything. The same shape names alert, avatar and badge.
    const result = classifyGroups(
      component({ componentName: 'Dropdown', placements: ['start', 'center', 'end', 'top', 'bottom', 'left', 'right'] }),
      'dropdown',
      { dropdown: { placements: ['AlignPlacement', 'VerticalPlacement'] } },
      measured({
        dropdown: {
          placements: {
            exclusive: ['start|center', 'start|end', 'center|end', 'top|bottom'],
            compose: [
              'start|top', 'start|bottom', 'start|left', 'start|right',
              'center|top', 'center|bottom', 'center|left', 'center|right',
              'end|top', 'end|bottom', 'end|left', 'end|right',
              'top|left', 'top|right', 'bottom|left', 'bottom|right', 'left|right',
            ],
          },
        },
      }),
    )

    assert.deepEqual(result.enums.map((group) => group.enumName), ['DropdownAlignPlacement', 'DropdownVerticalPlacement'])
    assert.deepEqual(result.booleans, ['left', 'right'])
  })

  it('fails on a multi-axis group nobody named, saying what the axes are', () => {
    // Only a human can say what each axis is called — DaisyUI's word for both is `placements`.
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Toast', placements: ['start', 'end', 'top', 'bottom'] }),
          'toast',
          NO_NAMES,
          measured({ toast: { placements: twoAxes(['start', 'end'], ['top', 'bottom']) } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /toast\.placements derives 2 axes — \{start, end\} and \{top, bottom\}/)
        assert.match(error.message, /Add enumNames\.toast\.placements with 2 names/)
        return true
      },
    )
  })

  it('fails when the configured names do not match the number of derived axes', () => {
    // A DaisyUI release that merges or splits an axis must stop the build, not silently pair
    // names with the wrong cliques.
    assert.throws(
      () =>
        classifyGroups(
          component({ componentName: 'Toast', placements: ['start', 'end', 'top', 'bottom'] }),
          'toast',
          { toast: { placements: ['Horizontal'] } },
          measured({ toast: { placements: twoAxes(['start', 'end'], ['top', 'bottom']) } }),
        ),
      (error: unknown) => {
        assert.ok(error instanceof GroupNamingError)
        assert.match(error.message, /with 2 names in that order; it has 1/)
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
