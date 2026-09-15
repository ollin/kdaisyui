import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { AxisDerivationError, deriveAxes } from '../src/axis-derivation.ts'
import { ClassPair, MeasuredPairs } from '../src/measurement.ts'
import type { MeasuredGroupJson } from '../src/measurement.ts'

/** A measurement calling every pair of `members` exclusive — the shape of a single choice. */
function clique(members: readonly string[]): string[] {
  return ClassPair.allOf(members).map(String)
}

/** Every pair between the two lists, as `left|right` keys. */
function across(lefts: readonly string[], rights: readonly string[]): string[] {
  return lefts.flatMap((left) => rights.map((right) => `${left}|${right}`))
}

function derive(members: readonly string[], json: MeasuredGroupJson) {
  return deriveAxes(members, MeasuredPairs.fromJson(json), 'test.group')
}

describe('deriveAxes', () => {
  it('makes a single clique one axis and leaves nothing boolean', () => {
    const members = ['spinner', 'dots', 'bars']

    const result = derive(members, { exclusive: clique(members) })

    assert.deepEqual(result, { axes: [members], booleans: [] })
  })

  it('makes two cliques that compose across two axes', () => {
    // indicator and toast: the shape that used to be declared and is now read off the graph.
    const vertical = ['top', 'middle', 'bottom']
    const horizontal = ['start', 'center', 'end']

    const result = derive([...horizontal, ...vertical], {
      exclusive: [...clique(vertical), ...clique(horizontal)],
      compose: across(vertical, horizontal),
    })

    assert.deepEqual(result.axes, [horizontal, vertical])
    assert.deepEqual(result.booleans, [])
  })

  it('keeps a clique beside members that compose with everything', () => {
    // dropdown.placements, and — same shape — alert.styles, avatar.modifiers, badge.styles.
    const members = ['start', 'center', 'end', 'top', 'bottom', 'left', 'right']

    const result = derive(members, {
      exclusive: [...clique(['start', 'center', 'end']), 'top|bottom'],
      compose: [
        ...across(['start', 'center', 'end'], ['top', 'bottom', 'left', 'right']),
        ...across(['top', 'bottom'], ['left', 'right']),
        'left|right',
      ],
    })

    assert.deepEqual(result.axes, [['start', 'center', 'end'], ['top', 'bottom']])
    assert.deepEqual(result.booleans, ['left', 'right'])
  })

  it('leaves a connected component that is not a clique boolean', () => {
    // button.styles: outline|dash exclusive, dash|soft exclusive, outline|soft compose — one
    // component, not a choice. The not-established rule, not a build failure.
    const result = derive(['outline', 'dash', 'soft'], {
      exclusive: ['outline|dash', 'dash|soft'],
      compose: ['outline|soft'],
    })

    assert.deepEqual(result, { axes: [], booleans: ['outline', 'dash', 'soft'] })
  })

  it('keeps a lone member boolean', () => {
    assert.deepEqual(derive(['start'], {}), { axes: [], booleans: ['start'] })
  })

  it('keeps a composing pair boolean, whatever its category', () => {
    const result = derive(['box', 'border'], { compose: ['box|border'] })

    assert.deepEqual(result, { axes: [], booleans: ['box', 'border'] })
  })

  it('treats same as not established', () => {
    const result = derive(['top', 'bottom'], { same: ['top|bottom'] })

    assert.deepEqual(result, { axes: [], booleans: ['top', 'bottom'] })
  })

  describe('an inert member', () => {
    it('rejoins the choice it is a default of', () => {
      // loading-spinner changes nothing because the example is already a spinner; it is still
      // one of the six.
      const members = ['spinner', 'dots', 'ring']

      const result = derive(members, { exclusive: clique(members), inert: ['spinner'] })

      assert.deepEqual(result.axes, [members])
    })

    it('turns a lone live member plus its default into a two-member axis', () => {
      // alert.directions: `horizontal` is the default and reads inert; without placing it,
      // `vertical` would be alone and AlertDirection would vanish.
      const result = derive(['vertical', 'horizontal'], {
        exclusive: ['vertical|horizontal'],
        inert: ['horizontal'],
      })

      assert.deepEqual(result.axes, [['vertical', 'horizontal']])
    })

    it('is placed by what it declares when it is exclusive with two axes', () => {
      // tooltip-top: exclusive with all six others, because it changes nothing. It declares
      // what tooltip-bottom declares and nothing the alignments declare.
      const members = ['top', 'bottom', 'left', 'right', 'start', 'center', 'end']
      const sides = ['bottom', 'left', 'right']
      const aligns = ['start', 'center', 'end']

      const result = derive(members, {
        exclusive: [...clique(sides), ...clique(aligns), ...across(['top'], [...sides, ...aligns])],
        compose: across(sides, aligns),
        inert: ['top'],
        declares: {
          top: ['inset', 'transform'],
          bottom: ['inset', 'transform'],
          left: ['inset-block', 'transform'],
          right: ['inset-block', 'transform'],
          start: ['--tt-inset', '--tt-trans'],
          center: ['--tt-inset', '--tt-trans'],
          end: ['--tt-inset', '--tt-trans'],
        },
      })

      assert.deepEqual(result.axes, [['top', 'bottom', 'left', 'right'], aligns])
    })

    it('stays boolean when it is exclusive with no axis', () => {
      // carousel-horizontal reads inert and composes with vertical: a flag, like any composing
      // class.
      const result = derive(['vertical', 'horizontal'], {
        compose: ['vertical|horizontal'],
        inert: ['horizontal'],
      })

      assert.deepEqual(result, { axes: [], booleans: ['vertical', 'horizontal'] })
    })

    it('places every inert member against the live components, not against one another', () => {
      // menu.modifiers: four classes the probe cannot see, each "exclusive" with `paged` and
      // `same` with one another. Placed one at a time, whichever came first joined `{paged}`
      // and blocked the rest — and `{paged, first}` was a clique. Placed together, all four
      // join, the component is not a clique, and everything stays boolean.
      const invisible = ['active', 'disabled', 'focus']
      const result = derive([...invisible, 'paged'], {
        exclusive: across(invisible, ['paged']),
        same: clique(invisible),
        inert: invisible,
      })

      assert.deepEqual(result, { axes: [], booleans: ['active', 'disabled', 'focus', 'paged'] })
    })

    it('leaves a group of only inert members boolean', () => {
      // stack.modifiers: the probe cannot see any of them on the container.
      const result = derive(['top', 'bottom'], { same: ['top|bottom'], inert: ['top', 'bottom'] })

      assert.deepEqual(result, { axes: [], booleans: ['top', 'bottom'] })
    })

    it('fails when what it declares cannot break a tie either', () => {
      const result = () =>
        derive(['x', 'a', 'b'], {
          exclusive: ['x|a', 'x|b'],
          compose: ['a|b'],
          inert: ['x'],
          declares: { x: ['top'], a: ['left'], b: ['right'] },
        })

      assert.throws(result, (error: unknown) => {
        assert.ok(error instanceof AxisDerivationError)
        assert.match(error.message, /"x" is inert and exclusive with 2 axes/)
        assert.match(error.message, /matches 0 of them/)
        return true
      })
    })
  })

  it('orders axes and their members as DaisyUI lists them, whatever the graph walk did', () => {
    const result = derive(['b', 'a', 'd', 'c'], {
      exclusive: ['a|b', 'c|d'],
      compose: across(['a', 'b'], ['c', 'd']),
    })

    assert.deepEqual(result.axes, [['b', 'a'], ['d', 'c']])
  })
})
