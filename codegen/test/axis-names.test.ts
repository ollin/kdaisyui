import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { deriveAxisNames } from '../src/axis-names.ts'
import { MeasuredPairs } from '../src/measurement.ts'
import { PropertyTable } from '../src/parser/property-table.ts'

const TABLE = PropertyTable.parse(
  [
    '| Dropdown | `--anchor-v`  | vertical position of the anchor    |',
    '|          | `--anchor-h`  | horizontal position of the anchor  |',
    '| Tooltip  | `--tt-trans`  | transform offset of the tooltip    |',
    '| Odd      | `--both`      | vertical and horizontal thing      |',
    '| Indicator| `--indicator-s` | start position of the indicator  |',
    '|          | `--indicator-x` | horizontal position of the indicator |',
  ].join('\n'),
)

function measuredDeclaring(declares: Record<string, string[]>): MeasuredPairs {
  return MeasuredPairs.fromJson({ declares })
}

describe('deriveAxisNames', () => {
  it('names each axis by the direction its custom property is described with', () => {
    const measured = measuredDeclaring({
      start: ['--anchor-h', 'translate'], end: ['--anchor-h', 'translate'],
      top: ['--anchor-v', 'top'], bottom: ['--anchor-v', 'top'],
    })

    const names = deriveAxisNames([['start', 'end'], ['top', 'bottom']], 'placements', measured, TABLE)

    assert.deepEqual(names, ['HorizontalPlacement', 'VerticalPlacement'])
  })

  it('ignores a documented property with no direction beside one that has it', () => {
    // indicator-start sets --indicator-s ("start position") and --indicator-x ("horizontal
    // position"). The first says nothing about the axis; the second names it.
    const measured = measuredDeclaring({ start: ['--indicator-s', '--indicator-x'], top: ['--anchor-v'] })

    assert.deepEqual(deriveAxisNames([['start'], ['top']], 'placements', measured, TABLE), ['HorizontalPlacement', 'VerticalPlacement'])
  })

  it('yields nothing when the only documented properties have no direction', () => {
    const measured = measuredDeclaring({ start: ['--tt-trans'], top: ['--anchor-v'] })

    assert.equal(deriveAxisNames([['start'], ['top']], 'placements', measured, TABLE), undefined)
  })

  it('yields nothing when an axis declares no custom property at all', () => {
    // badge: outline/dash and soft/ghost set colours and borders, no variable of their own.
    const measured = measuredDeclaring({ outline: ['border-color'], soft: ['background-color'] })

    assert.equal(deriveAxisNames([['outline'], ['soft']], 'styles', measured, TABLE), undefined)
  })

  it('yields nothing when a property is not in the table', () => {
    const measured = measuredDeclaring({ start: ['--unknown-x'], top: ['--anchor-v'] })

    assert.equal(deriveAxisNames([['start'], ['top']], 'placements', measured, TABLE), undefined)
  })

  it('yields nothing when two axes would get the same word', () => {
    const measured = measuredDeclaring({ start: ['--anchor-v'], top: ['--anchor-v'] })

    assert.equal(deriveAxisNames([['start'], ['top']], 'placements', measured, TABLE), undefined)
  })

  it('yields nothing when a description uses both words', () => {
    const measured = measuredDeclaring({ start: ['--both'], top: ['--anchor-v'] })

    assert.equal(deriveAxisNames([['start'], ['top']], 'placements', measured, TABLE), undefined)
  })

  it('yields nothing when the members of one axis disagree', () => {
    const measured = measuredDeclaring({ start: ['--anchor-h'], end: ['--anchor-v'], top: ['--anchor-v'] })

    assert.equal(deriveAxisNames([['start', 'end'], ['top']], 'placements', measured, TABLE), undefined)
  })
})
