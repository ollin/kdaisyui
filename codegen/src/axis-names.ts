/**
 * The name of each axis of a multi-axis group, derived where DaisyUI documents it.
 *
 * An axis's members set a custom property — `indicator-top` sets `--indicator-y`,
 * `indicator-start` sets `--indicator-x` — and DaisyUI's property table describes that
 * variable with a direction word: "vertical position of the indicator". When every custom
 * property an axis's members declare is described with the same one of {vertical, horizontal},
 * and the group's other axes with the other, the axis is `<Word>Placement` and nobody had to
 * choose it.
 *
 * Where that yields nothing — the tooltip's `--tt-trans` is "transform offset", with no
 * direction, because its alignment is relative to the side — the names are configured in
 * `enumNames`, and only there. A configured name for a group this module CAN name fails the
 * build: the config must not carry a rule twice.
 */
import type { Axis } from './axis-derivation.ts'
import type { MeasuredPairs } from './measurement.ts'
import type { PropertyTable } from './parser/property-table.ts'
import { categoryWord } from './class-groups.ts'
import type { GroupCategory } from './class-groups.ts'

const DIRECTION_WORDS: readonly string[] = ['vertical', 'horizontal']

function capitalised(word: string): string {
  return word.charAt(0).toUpperCase() + word.slice(1)
}

/** The direction word a description uses, if it uses exactly one. */
function directionOf(description: string): string | undefined {
  const words = description.split(' ')
  const found = DIRECTION_WORDS.filter((direction) => words.includes(direction))
  return found.length === 1 ? found[0] : undefined
}

/** Custom properties (`--…`) the axis's members declare, without duplicates. */
function customPropertiesOf(axis: Axis, measured: MeasuredPairs): string[] {
  const all = axis.flatMap((member) => measured.declaredProperties(member))
  return [...new Set(all.filter((property) => property.startsWith('--')))]
}

/**
 * The one direction word DaisyUI's table gives the axis's custom properties, or nothing.
 *
 * Every property must be in the table — an undocumented one is "DaisyUI does not say". Among
 * the documented ones, those described with a direction must all agree, and there must be at
 * least one; a property the table describes without a direction (`--indicator-s`, "start
 * position of the indicator") is neither evidence nor counter-evidence. A name that guesses is
 * worse than a name that is configured, so any doubt yields nothing.
 */
function directionOfAxis(axis: Axis, measured: MeasuredPairs, table: PropertyTable): string | undefined {
  const descriptions = customPropertiesOf(axis, measured).map((property) => table.describe(property))
  if (descriptions.length === 0 || descriptions.includes(undefined)) return undefined

  const directions = new Set(
    descriptions.map((description) => directionOf(description as string)).filter((direction) => direction !== undefined),
  )
  return directions.size === 1 ? [...directions][0] : undefined
}

/**
 * One name per axis when DaisyUI's table names every axis with a distinct direction; nothing
 * otherwise, so the caller falls back to configuration.
 */
export function deriveAxisNames(
  axes: readonly Axis[],
  category: GroupCategory,
  measured: MeasuredPairs,
  table: PropertyTable,
): readonly string[] | undefined {
  const directions = axes.map((axis) => directionOfAxis(axis, measured, table))
  const defined = directions.filter((direction) => direction !== undefined)
  if (defined.length !== axes.length) return undefined
  if (new Set(defined).size !== axes.length) return undefined
  return defined.map((direction) => `${capitalised(direction as string)}${categoryWord(category)}`)
}
