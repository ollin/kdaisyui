/**
 * Which generated function expresses an element DaisyUI documents, judged by its classes.
 *
 * Read by the generation gate and by the test generator, so the two cannot disagree about what
 * the API can produce. The element's tag is not compared, by decision.
 */

import type { ComponentModel, ModelFunction } from './model.ts'
import { offeredBy } from './model-completeness.ts'
import { JOIN_ITEM_CLASS } from './join-scope.ts'

/** A function and the component model that declares it. */
export interface ModelledFunction {
  readonly model: ComponentModel
  readonly fn: ModelFunction
}

/**
 * The first function whose own class the element carries and that offers every other class,
 * searching `models` in the order given: the page's own model first, then the others.
 */
export function expressingFunction(models: readonly ComponentModel[], counted: readonly string[]): ModelledFunction | null {
  const candidates = models.flatMap(model => model.functions.map(fn => ({ model, fn })))
  return candidates.find(({ model, fn }) => fn.class !== null && counted.includes(fn.class) && counted.every(cls => offers(model, fn, cls))) ?? null
}

function offers(model: ComponentModel, fn: ModelFunction, cls: string): boolean {
  return offeredBy(model, fn).includes(cls) || (cls === JOIN_ITEM_CLASS && fn.kind === 'main' && fn.joinItem === true)
}
