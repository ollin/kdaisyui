import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { inject, pageFor, stripSentinel } from '../src/exclusivity-probe.ts'

/**
 * One test per defect the probe has actually had.
 *
 * Each of these produced a wrong verdict that nothing caught, and each was found by reading
 * what the browser computed rather than by reading the probe. A regression here is a wrong
 * public API, so they are pinned by name.
 */
describe('the probe defects', () => {
  it('strips the $$ sentinel DaisyUI marks the component class with', () => {
    // Left in, the element matches no rule at all and every pair renders identically — which
    // reads as "indistinguishable" for the whole component.
    assert.equal(stripSentinel('<button class="$$btn $$btn-lg">Go</button>'), '<button class="btn btn-lg">Go</button>')
  })

  it('strips the group\'s own members before injecting, so the example cannot compete', () => {
    // DaisyUI's mask example is `class="mask mask-squircle"`. Without this, every
    // single-member case rendered as a squircle and the run measured stylesheet order.
    const result = inject(
      '<img class="mask mask-squircle" />',
      'mask',
      ['squircle', 'square', 'heart'],
      ['square'],
      [],
    )

    assert.equal(result, '<img class="mask mask-square" />')
  })

  it('keeps classes that are not members of the group under test', () => {
    const result = inject(
      '<div class="alert alert-vertical shadow">x</div>',
      'alert',
      ['outline', 'soft'],
      ['outline'],
      [],
    )

    assert.equal(result, '<div class="alert alert-vertical shadow alert-outline">x</div>')
  })

  it('adds the constant colour context, without which colour-mixing classes look alike', () => {
    // `alert-outline` and `alert-soft` both mix `--alert-color`; unlit, both computed to the
    // same transparent black.
    const result = inject('<div class="alert">x</div>', 'alert', ['outline', 'soft'], ['soft'], [
      'alert-info',
    ])

    assert.equal(result, '<div class="alert alert-info alert-soft">x</div>')
  })

  it('renders a baseline with the group stripped and nothing injected', () => {
    // `tooltip-top` restates `.tooltip`'s own declarations, so alone it equals every other
    // member alone and the pair rule called it exclusive with all six. Only a case with
    // nothing injected can tell "changed nothing" from "exclusive with everything".
    const result = inject('<div class="tooltip tooltip-top">x</div>', 'tooltip', ['top', 'bottom'], [], [])

    assert.equal(result, '<div class="tooltip">x</div>')
  })

  it('inlines the stylesheet, because a linked file:// sheet hides its rules from the page', () => {
    // Chromium treats a stylesheet linked from a file:// page as cross-origin, and `cssRules`
    // throws SecurityError — which is what `declares` reads. Found on the first measurement
    // run after `declares` was added.
    const page = pageFor([], '.tooltip{--tt-off:1px}', '5.7.17')

    assert.match(page, /<style>\.tooltip\{--tt-off:1px\}<\/style>/)
    assert.doesNotMatch(page, /<link/)
  })

  it('injects both members of a pair onto the same element', () => {
    const result = inject('<div class="alert">x</div>', 'alert', ['outline', 'soft'], ['outline', 'soft'], [])

    assert.equal(result, '<div class="alert alert-outline alert-soft">x</div>')
  })

  it('touches only the FIRST element carrying the component class', () => {
    // A carousel example holds many `carousel-item` children; injecting into each would
    // measure something nobody writes.
    const result = inject(
      '<div class="carousel"><div class="carousel">a</div></div>',
      'carousel',
      ['start', 'end'],
      ['start'],
      [],
    )

    assert.equal(result, '<div class="carousel carousel-start"><div class="carousel">a</div></div>')
  })

  it('leaves an element whose class merely CONTAINS the prefix alone', () => {
    // `carousel-item` is not `carousel`; a substring match would inject into the wrong node.
    const result = inject('<div class="carousel-item">a</div>', 'carousel', ['start'], ['start'], [])

    assert.equal(result, '<div class="carousel-item">a</div>')
  })
})
