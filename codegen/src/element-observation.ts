/**
 * What a component's generated functions emit where, paired with what DaisyUI documents.
 *
 * One observation per (function, class): the function's own class, and every boolean or enum
 * entry the function declares. Read off the shape rather than the Kotlin, because the shape is
 * what both emitters are generated from — a class the shape puts on the main function is on the
 * main function in the Kotlin, the tests and the reference page alike.
 */
import type { ComponentShape, FunctionShape } from './component-shape.ts'
import type { ElementObservation } from './element-cross-check.ts'

function classesEmittedBy(fn: FunctionShape, shape: ComponentShape): string[] {
  const own = fn.cssClass === null ? [] : [fn.cssClass]
  const booleans = fn.parameters.flatMap((parameter) => (parameter.cssClass === undefined ? [] : [parameter.cssClass]))
  // Enums are declared on the main function, and only there — the shape carries them at the
  // component level, so they are attributed here rather than read off the parameters.
  const enums = fn.kind === 'main' ? shape.enums.flatMap((enumShape) => enumShape.entries.map((entry) => entry.cssClass)) : []
  return [...own, ...booleans, ...enums]
}

/** Where DaisyUI documents the component's classes — two readings, two contracts. */
export interface DocumentedElements {
  /**
   * The component's own class: the FIRST documented example, as the component-level check has
   * read it since 0.4.0. `btn` is shown on a `<button>` first and on an `<a>` later; the first
   * is the one the generator is held to.
   */
  readonly componentClass: string | null
  /**
   * Every other class, where DaisyUI shows it on exactly one element. A modifier shown on two
   * is absent — unchecked — because "the first" would be a coin toss for a class whose whole
   * point is which element wears it.
   */
  readonly byClass: ReadonlyMap<string, string>
}

export function observeElements(shape: ComponentShape, documented: DocumentedElements): ElementObservation[] {
  return shape.functions.flatMap((fn) => observeFunction(fn, shape, documented))
}

function observeFunction(fn: FunctionShape, shape: ComponentShape, documented: DocumentedElements): ElementObservation[] {
  const observations = classesEmittedBy(fn, shape).map((cssClass) => {
    const isComponentClass = fn.kind === 'main' && cssClass === fn.cssClass
    return {
      componentDir: shape.componentDir,
      cssClass,
      isComponentClass,
      chosen: fn.element,
      documented: isComponentClass ? documented.componentClass : documented.byClass.get(cssClass) ?? null,
    }
  })
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
  const ownVerdict = own.documented ?? own.chosen
  return observation.documented === ownVerdict
}
