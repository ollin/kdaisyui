import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { GroupKey, Measurement, loadMeasurement } from '../src/measurement.ts'
import { findMismatches } from '../src/verify-exclusivity.ts'
import type { LiveGroup } from '../src/verify-exclusivity.ts'

function named(keys: readonly GroupKey[]): string[] {
  return keys.map(String).sort()
}

function liveGroup(key: string, members: readonly string[]): LiveGroup {
  return { key: GroupKey.parse(key), members }
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

/**
 * The guard that fires on a DaisyUI bump.
 *
 * `live` is injected here rather than read from the submodule, because this job deliberately
 * has none. The real pairing of the two sides runs in `generated-sources-drift`.
 */
describe('findMismatches', () => {
  const measured = Measurement.fromJson({
    alert: { directions: { exclusive: ['vertical|horizontal'] } },
  })

  it('says nothing when the measurement matches what DaisyUI documents', () => {
    const live = [liveGroup('alert.directions', ['vertical', 'horizontal'])]

    assert.deepEqual(findMismatches(measured, live), [])
  })

  it('accepts a pair recorded the other way round', () => {
    const live = [liveGroup('alert.directions', ['horizontal', 'vertical'])]

    assert.deepEqual(findMismatches(measured, live), [])
  })

  it('reports a group DaisyUI documents and nobody measured', () => {
    const live = [liveGroup('button.styles', ['outline', 'soft'])]
    const [first] = findMismatches(measured, live)

    assert.equal(String(first.group), 'button.styles')
    assert.match(first.message, /is not measured/)
  })

  it('reports a class DaisyUI added to a measured group', () => {
    const live = [liveGroup('alert.directions', ['vertical', 'horizontal', 'diagonal'])]
    const [first] = findMismatches(measured, live)

    assert.match(first.message, /no verdict for vertical\|diagonal, horizontal\|diagonal/)
  })

  it('reports a class DaisyUI removed from a measured group', () => {
    const live = [liveGroup('alert.directions', ['vertical'])]
    const messages = findMismatches(measured, live).map((mismatch) => mismatch.message)

    assert.ok(messages.some((message) => /still records vertical\|horizontal/.test(message)))
  })

  it('reports an inert member DaisyUI no longer ships', () => {
    const withInert = Measurement.fromJson({
      tooltip: { placements: { exclusive: ['top|bottom'], inert: ['top'] } },
    })
    const live = [liveGroup('tooltip.placements', ['bottom', 'left'])]
    const messages = findMismatches(withInert, live).map((mismatch) => mismatch.message)

    assert.ok(messages.some((message) => /still records top\|bottom, top/.test(message)))
  })

  it('reports a measured group DaisyUI no longer documents', () => {
    const [first] = findMismatches(measured, [])

    assert.equal(String(first.group), 'alert.directions')
    assert.match(first.message, /no longer groups it/)
  })
})
