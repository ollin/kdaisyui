/**
 * What a component's generated functions emit where, paired with what DaisyUI documents.
 *
 * One observation per (function, class): the function's own class, and every boolean or enum
 * entry the function declares. Read off the shape rather than the Kotlin, because the shape is
 * what both emitters are generated from — a class the shape puts on the main function is on the
 * main function in the Kotlin, the tests and the reference page alike.
 */
import type { ComponentShape, FunctionShape } from './component-shape.ts'
import { DocumentedElements } from './element-cross-check.ts'
import type { ElementObservation } from './element-cross-check.ts'

function classesEmittedBy(fn: FunctionShape, shape: ComponentShape): string[] {
  const own = fn.cssClass === null ? [] : [fn.cssClass]
  const booleans = fn.parameters.flatMap((parameter) => (parameter.cssClass === undefined ? [] : [parameter.cssClass]))
  // Enums are declared on the main function, and only there — the shape carries them at the
  // component level, so they are attributed here rather than read off the parameters.
  const enums = fn.kind === 'main' ? shape.enums.flatMap((enumShape) => enumShape.entries.map((entry) => entry.cssClass)) : []
  return [...own, ...booleans, ...enums]
}

/**
 * Where DaisyUI documents each of a component's classes, the component's own among them.
 *
 * One lookup, not two. It used to carry the component class separately because that reading
 * was different — the FIRST documented example, where every other class got the commonest.
 * Both are now the set of every element the class is shown on, so a second field would be the
 * same answer fetched a second way, which is how two answers start disagreeing.
 */
export type DocumentedClasses = ReadonlyMap<string, DocumentedElements>

export function observeElements(shape: ComponentShape, documented: DocumentedClasses): ElementObservation[] {
  return shape.functions.flatMap((fn) => observeFunction(fn, shape, documented))
}

function observeFunction(fn: FunctionShape, shape: ComponentShape, documented: DocumentedClasses): ElementObservation[] {
  const observations = classesEmittedBy(fn, shape).map((cssClass) => ({
    componentDir: shape.componentDir,
    cssClass,
    isComponentClass: fn.kind === 'main' && cssClass === fn.cssClass,
    chosen: fn.element,
    documented: documented.get(cssClass) ?? DocumentedElements.none(),
  }))
  const own = observations.find((observation) => observation.cssClass === fn.cssClass)
  return observations.filter((observation) => !repeatsTheFunctionsOwnVerdict(observation, own))
}

/**
 * A class documented on the same element as the function's own class says nothing the own
 * class does not already say: `badge-primary` on the badge's `<div>` agrees or disagrees
 * exactly as `badge` does. Seventeen copies of one finding would bury the one that matters,
 * which is a class documented on a DIFFERENT element from the function's own — `menu-active`
 * on an `<a>` while `menu` is on the `<ul>`.
 */
function repeatsTheFunctionsOwnVerdict(observation: ElementObservation, own: ElementObservation | undefined): boolean {
  if (own === undefined || observation === own) return false
  return observation.documented.sameAs(own.documented.orElse(own.chosen))
}
