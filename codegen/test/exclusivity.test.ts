import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { GroupKey, loadMeasurement } from '../src/measurement.ts'

function named(keys: readonly GroupKey[]): string[] {
  return keys.map(String).sort()
}

/**
 * What the committed measurement says, pinned.
 *
 * These assert on the DATA only. Whether it still matches the DaisyUI in the submodule is
 * `verify-exclusivity.ts`'s job and runs in `generated-sources-drift`, because this job is
 * deliberately free of submodules — see the comment on `codegen-tests` in `ci.yml`.
 */
describe('the committed measurement', () => {
  const measurement = loadMeasurement()

  it('pins which groups the browser called a single choice', () => {
    // These fifteen may become an enum without splitting first. A DaisyUI bump that moves one
    // of them should fail here and be READ, not re-dumped: a group leaving this list removes
    // an enum from the public API, and one joining it adds one.
    assert.deepEqual(named(measurement.singleChoices()), [
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
    // Neither one clique nor all-boolean: each holds two cliques that overlap or compose, so
    // `enumNames` has to declare the axes and `classifyGroups` checks them against these pairs.
    const ambiguous = ['indicator.placements', 'toast.placements', 'dropdown.placements', 'tooltip.placements']

    for (const key of ambiguous.map(GroupKey.parse)) {
      const measured = measurement.forGroup(key)
      assert.ok(measured.count() > 0, `${key} is measured`)
      assert.ok(!measured.isSingleChoice(), `${key} is not one choice`)
    }
  })

  it('covers 44 groups and 310 pairs', () => {
    assert.equal(measurement.groupCount(), 44)
    assert.equal(measurement.pairCount(), 310)
  })
})
