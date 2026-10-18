/**
 * Which generated function expresses an element DaisyUI documents, judged by its classes.
 *
 * Read by the generation gate and by the test generator, so the two cannot disagree about what
 * the API can produce. The element's tag is not compared, by decision.
 */

import type { ComponentModel, ModelFunction } from './model.ts'
import { offeredBy } from './model-completeness.ts'
import { JOIN_ITEM_CLASS } from './join-scope.ts'
import { markedElements } from './parser/documented-classes.ts'

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

/**
 * What a gap is declared by: the own classes of the functions involved when two components share
 * the element, whichever modifiers ride along; otherwise every counted class. Sorted.
 */
export function gapKey(models: readonly ComponentModel[], counted: readonly string[]): string {
  const own = new Set(models.flatMap(model => model.functions.map(fn => fn.class)))
  const owned = counted.filter(cls => own.has(cls))
  return [...(owned.length >= 2 ? owned : counted)].sort().join(' ')
}

/** A documented element no function expresses: its tag and the key its gap is declared by. */
export interface DocumentedGap {
  readonly tag: string
  readonly key: string
}

/**
 * Every element of a page's examples that no function expresses by its counted classes: the
 * unprefixed ones some frontmatter declares. `rounded-box`, a DaisyUI utility, is no component's.
 */
export function gapsIn(html: string, models: readonly ComponentModel[], componentClasses: ReadonlySet<string>): DocumentedGap[] {
  return markedElements(html)
    .map(element => ({ tag: element.tag, counted: element.classes.filter(cls => !cls.isPrefixed && componentClasses.has(cls.className)).map(cls => cls.className) }))
    .filter(({ counted }) => counted.length > 0 && expressingFunction(models, counted) === null)
    .map(({ tag, counted }) => ({ tag, key: gapKey(models, counted) }))
}
