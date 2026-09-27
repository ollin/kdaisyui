/**
 * A DaisyUI class a generated function offers through two parameters at once.
 *
 * Once a measured class group becomes an enum, each of its classes must be reachable ONLY as an
 * enum constant. A boolean for the same class makes `daisyDropdown(end = true,
 * horizontalPlacement = Start)` compile and emit both classes — exactly the combination the
 * measurement said the enum exists to rule out. v0.6.0 shipped five of them on `daisyDropdown`,
 * from hand-written `extras` entries that predated the enum.
 */
import type { ComponentShape, ExtraParameter } from './component-shape.ts'

/** One class offered twice: by which function, as which boolean, and inside which enum. */
export interface OfferedTwice {
  readonly functionName: string
  readonly booleanName: string
  readonly enumName: string
  readonly cssClass: string
}

export function classesOfferedTwice(
  shape: ComponentShape,
  extras: readonly ExtraParameter[],
): OfferedTwice[] {
  const main = shape.functions.find(fn => fn.kind === 'main')
  const enumOf = new Map(shape.enums.flatMap(e => e.entries.map(entry => [entry.cssClass as string, e.name])))
  return extras.flatMap(extra => classesWrittenBy(extra)
    .filter(cls => enumOf.has(cls))
    .map(cls => ({ functionName: main.name, booleanName: extra.name, enumName: enumOf.get(cls), cssClass: cls })))
}

/** The class literals an `extras` fragment passes to `addClassNames`. */
function classesWrittenBy(extra: ExtraParameter): string[] {
  return [...extra.apply.matchAll(/addClassNames\("([^"]+)"\)/g)].map(match => match[1])
}
