/**
 * What a component's generated functions emit where, paired with what DaisyUI documents.
 *
 * One observation per (function, class): the function's own class, and every boolean or enum
 * entry the function declares. Read off the shape rather than the Kotlin, because the shape is
 * what both emitters are generated from — a class the shape puts on the main function is on the
 * main function in the Kotlin, the tests and the reference page alike.
 */
import type { ComponentShape, FunctionShape, ParameterShape } from './component-shape.ts'
import { DocumentedElements } from './element-cross-check.ts'
import type { ElementObservation } from './element-cross-check.ts'

/**
 * Every class one generated function puts on its element: its own, its booleans', and every
 * member of every enum it declares.
 *
 * All three read off the SIGNATURE. Enums used to be taken from the component instead, on the
 * rule that they sit on the main function — true until they began moving to the part whose
 * element wears them, and then silently wrong: indicator's placements were reported against
 * the container's `<div>` while `daisyIndicatorItem` declared them.
 */
function classesEmittedBy(fn: FunctionShape, shape: ComponentShape): string[] {
  const own = fn.cssClass === null ? [] : [fn.cssClass]
  const declared = (parameter: ParameterShape): readonly string[] =>
    parameter.cssClass !== undefined
      ? [parameter.cssClass]
      : entriesOf(shape, parameter.enumName).map((entry) => entry.cssClass)
  return [...own, ...fn.parameters.flatMap(declared)]
}

function entriesOf(shape: ComponentShape, enumName: string | undefined) {
  return shape.enums.find((enumShape) => enumShape.name === enumName)?.entries ?? []
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
