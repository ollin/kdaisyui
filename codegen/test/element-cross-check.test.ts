import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import {
  crossCheckElements,
  describeCrossCheckFailure,
  type ElementObservation,
} from '../src/element-cross-check.ts'

const agreeing: ElementObservation = { componentDir: 'card', chosen: 'DIV', documented: 'DIV' }
const otp: ElementObservation = { componentDir: 'otp', chosen: 'DIV', documented: 'LABEL' }
const undocumented: ElementObservation = { componentDir: 'ghost', chosen: 'DIV', documented: null }

const exception = (issue: number) => ({ reason: 'classification needs sorting out first', issue })

describe('crossCheckElements', () => {
  test('says nothing when every component agrees', () => {
    const result = crossCheckElements([agreeing], {})

    assert.deepEqual(result.findings, [])
    assert.deepEqual(result.excused, [])
  })

  test('fails on a disagreement with no exception, naming both elements', () => {
    const result = crossCheckElements([otp], {})

    assert.equal(result.findings.length, 1)
    assert.match(result.findings[0].message, /"otp" is generated as <div> but DaisyUI documents <label>/)
  })

  test('excuses a disagreement that has an exception', () => {
    const result = crossCheckElements([otp], { otp: exception(342) })

    assert.deepEqual(result.findings, [])
    assert.deepEqual(result.excused, ['otp'])
  })

  test('fails when DaisyUI documents no element at all', () => {
    // The precise case the measurement cannot rule out forever: a new component whose page
    // carries no $$-marked example leaves the cross-check with nothing to check.
    const result = crossCheckElements([undocumented], {})

    assert.equal(result.findings.length, 1)
    assert.match(result.findings[0].message, /documents no element for "ghost"/)
  })

  test('an exception does not excuse a component that cannot be checked', () => {
    const result = crossCheckElements([undocumented], { ghost: exception(999) })

    assert.equal(result.findings.length, 1)
    assert.match(result.findings[0].message, /documents no element for "ghost"/)
  })

  test('fails on an exception that is no longer needed', () => {
    // Without this the list grows into the hand-maintained thing it replaced.
    const result = crossCheckElements([agreeing], { card: exception(342) })

    assert.equal(result.findings.length, 1)
    assert.match(result.findings[0].message, /no longer needed/)
    assert.match(result.findings[0].message, /#342/)
  })

  test('reports every finding, not just the first', () => {
    const result = crossCheckElements([otp, undocumented, agreeing], { card: exception(1) })

    assert.deepEqual(result.findings.map(f => f.componentDir), ['otp', 'ghost', 'card'])
  })
})

describe('describeCrossCheckFailure', () => {
  test('counts the findings and gives each its own line', () => {
    const result = crossCheckElements([otp, undocumented], {})

    const message = describeCrossCheckFailure(result)

    assert.match(message, /^Element cross-check failed \(2\):/)
    assert.equal(message.split('\n').length, 3)
  })
})
