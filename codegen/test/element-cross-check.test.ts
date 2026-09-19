import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import {
  crossCheckElements,
  describeCrossCheckFailure,
  type ElementObservation,
} from '../src/element-cross-check.ts'

const agreeing: ElementObservation = { componentDir: 'card', cssClass: 'card', isComponentClass: true, chosen: 'DIV', documented: ['DIV'] }
const otp: ElementObservation = { componentDir: 'otp', cssClass: 'otp', isComponentClass: true, chosen: 'DIV', documented: ['LABEL'] }
const undocumented: ElementObservation = { componentDir: 'ghost', cssClass: 'ghost', isComponentClass: true, chosen: 'DIV', documented: [] }
/** A modifier on the container's function while DaisyUI puts it on a child. */
const menuActive: ElementObservation = { componentDir: 'menu', cssClass: 'menu-active', isComponentClass: false, chosen: 'UL', documented: ['LI'] }

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
    assert.match(result.findings[0].message, /"otp" is emitted on <div> but DaisyUI documents it on <label>/)
  })

  test('fails on a class emitted by the wrong function, naming the config key to excuse it', () => {
    // The defect this change exists for: `menu-active` declared on `daisyMenu`, whose <ul>
    // never wears it — the class does nothing there and nothing noticed.
    const result = crossCheckElements([menuActive], {})

    assert.equal(result.findings.length, 1)
    assert.match(result.findings[0].message, /"menu-active" is emitted on <ul> but DaisyUI documents it on <li>/)
    assert.match(result.findings[0].message, /elementCrossCheckExceptions\["menu\/menu-active"\]/)
  })

  test('excuses a class disagreement keyed component/class, and reports it under that key', () => {
    const result = crossCheckElements([menuActive], { 'menu/menu-active': exception(350) })

    assert.deepEqual(result.findings, [])
    assert.deepEqual(result.excused, ['menu/menu-active'])
  })

  test('keys the component class by directory, even when the class is named differently', () => {
    // `calendar`'s class is `cally`, `tab`'s container class is `tabs`: the existing
    // exceptions are keyed by directory and must go on matching.
    const cally: ElementObservation = { componentDir: 'calendar', cssClass: 'cally', isComponentClass: true, chosen: 'DIV', documented: ['CALENDAR-DATE'] }

    const result = crossCheckElements([cally], { calendar: exception(343) })

    assert.deepEqual(result.excused, ['calendar'])
  })

  test('a component-level exception does not cover a class of that component', () => {
    const result = crossCheckElements([menuActive], { menu: exception(350) })

    assert.equal(result.findings.length, 1)
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

  test('leaves a modifier DaisyUI lists but never shows unchecked', () => {
    // `btn-md` is in the frontmatter and in no example. Its function's element is checked
    // through the component class; the modifier itself has nothing to be wrong against.
    const btnMd: ElementObservation = { componentDir: 'button', cssClass: 'btn-md', isComponentClass: false, chosen: 'BUTTON', documented: [] }

    assert.deepEqual(crossCheckElements([btnMd], {}).findings, [])
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

  test('names the component of a stale class-level exception', () => {
    const agreeingClass: ElementObservation = { ...menuActive, chosen: 'LI' }
    const result = crossCheckElements([agreeingClass], { 'menu/menu-active': exception(350) })

    assert.equal(result.findings[0].componentDir, 'menu')
    assert.match(result.findings[0].message, /"menu\/menu-active" is no longer needed/)
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
