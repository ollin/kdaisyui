import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { ClassPair, GroupKey, Measurement, MeasuredPairs } from '../src/measurement.ts'

describe('ClassPair', () => {
  it('is unordered, because the file records one order and callers ask in either', () => {
    assert.ok(new ClassPair('top', 'bottom').equals(new ClassPair('bottom', 'top')))
  })

  it('is not equal to a pair sharing only one member', () => {
    assert.ok(!new ClassPair('top', 'bottom').equals(new ClassPair('top', 'start')))
  })

  it('round-trips through the string form the JSON file stores', () => {
    assert.equal(String(ClassPair.parse('half-1|half-2')), 'half-1|half-2')
  })

  it('enumerates every pair of a group, each combination once', () => {
    const pairs = ClassPair.allOf(['a', 'b', 'c']).map(String)

    assert.deepEqual(pairs, ['a|b', 'a|c', 'b|c'])
  })

  it('has no pairs for a lone member, which answers no question on its own', () => {
    assert.deepEqual(ClassPair.allOf(['only']), [])
  })

  it('spots a member the group no longer has', () => {
    const known = new Set(['top', 'bottom'])

    assert.ok(new ClassPair('top', 'diagonal').mentionsAnythingOutside(known))
    assert.ok(!new ClassPair('top', 'bottom').mentionsAnythingOutside(known))
  })
})

describe('GroupKey', () => {
  it('round-trips through the dotted form', () => {
    const key = GroupKey.parse('file-input.modifiers')

    assert.equal(key.component, 'file-input')
    assert.equal(key.category, 'modifiers')
    assert.equal(String(key), 'file-input.modifiers')
  })

  it('splits on the FIRST dot, since a component directory may contain none', () => {
    // Guarding the direction that would break silently: a category is never dotted, a
    // directory could become so, and splitting on the last dot would then swap the two.
    assert.equal(GroupKey.parse('mockup-browser.styles').component, 'mockup-browser')
  })
})

describe('MeasuredPairs', () => {
  const pairs = MeasuredPairs.fromJson({
    exclusive: ['top|bottom'],
    compose: ['top|start'],
    same: ['start|end'],
  })

  it('answers with the verdict a pair was filed under', () => {
    assert.equal(pairs.verdictFor(new ClassPair('top', 'bottom')), 'exclusive')
    assert.equal(pairs.verdictFor(new ClassPair('top', 'start')), 'compose')
    assert.equal(pairs.verdictFor(new ClassPair('start', 'end')), 'same')
  })

  it('answers for a pair asked the other way round', () => {
    assert.equal(pairs.verdictFor(new ClassPair('bottom', 'top')), 'exclusive')
  })

  it('says nothing about a pair nobody measured', () => {
    assert.equal(pairs.verdictFor(new ClassPair('top', 'nowhere')), undefined)
  })

  it('calls a group a single choice only when every pair is exclusive', () => {
    assert.ok(MeasuredPairs.fromJson({ exclusive: ['a|b', 'a|c', 'b|c'] }).isSingleChoice())
    assert.ok(!MeasuredPairs.fromJson({ exclusive: ['a|b'], same: ['a|c'] }).isSingleChoice())
  })

  it('does not call an EMPTY group a single choice', () => {
    // The trap in the rule: "every pair is exclusive" is vacuously true of no pairs, which
    // would turn an unmeasured group into an enum — the error whose cost is asymmetric.
    assert.ok(!MeasuredPairs.none().isSingleChoice())
  })

  it('describes each non-exclusive pair, naming its verdict or its absence', () => {
    const described = pairs.describeNonExclusive(['top', 'start', 'nowhere'])

    assert.deepEqual(described, ['top|start is compose', 'top|nowhere is unmeasured', 'start|nowhere is unmeasured'])
  })

  it('describes nothing when every pair is exclusive', () => {
    assert.deepEqual(pairs.describeNonExclusive(['top', 'bottom']), [])
  })

  it('reports whether it covers every pair of a member list', () => {
    assert.ok(pairs.coversEveryPairOf(['top', 'bottom']))
    assert.ok(!pairs.coversEveryPairOf(['top', 'bottom', 'nowhere']))
  })
})

describe('Measurement', () => {
  const measurement = Measurement.fromJson({
    indicator: {
      placements: { exclusive: ['top|bottom'], compose: ['top|start'] },
    },
    loading: { styles: { exclusive: ['spinner|dots'] } },
  })

  it('finds a group by key', () => {
    assert.equal(measurement.forGroup(GroupKey.parse('loading.styles')).count(), 1)
  })

  it('returns an EMPTY group for one nobody measured, rather than nothing', () => {
    // Callers then never branch on absence: unmeasured and composing both mean "not
    // established", and the asymmetric-cost rule treats them alike.
    assert.equal(measurement.forGroup(GroupKey.parse('button.styles')).count(), 0)
  })

  it('distinguishes "measured as empty" from "never measured"', () => {
    assert.ok(measurement.wasMeasured(GroupKey.parse('loading.styles')))
    assert.ok(!measurement.wasMeasured(GroupKey.parse('button.styles')))
  })

  it('lists only the groups whose every pair is exclusive', () => {
    assert.deepEqual(measurement.singleChoices().map(String), ['loading.styles'])
  })

  it('counts its groups and its pairs', () => {
    assert.equal(measurement.groupCount(), 2)
    assert.equal(measurement.pairCount(), 3)
  })
})
