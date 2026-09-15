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

/**
 * @param documented DaisyUI's element per class, from `documentedElementsFor`
 */
export function observeElements(
  shape: ComponentShape,
  documented: ReadonlyMap<string, string>,
): ElementObservation[] {
  return shape.functions.flatMap((fn) =>
    classesEmittedBy(fn, shape).map((cssClass) => ({
      componentDir: shape.componentDir,
      cssClass,
      isComponentClass: fn.kind === 'main' && cssClass === fn.cssClass,
      chosen: fn.element,
      documented: documented.get(cssClass) ?? null,
    })),
  )
}
