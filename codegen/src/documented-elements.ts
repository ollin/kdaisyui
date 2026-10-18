/**
 * Which generated function expresses an element DaisyUI documents, judged by its classes.
 *
 * Read by the generation gate and by the test generator, so the two cannot disagree about what
 * the API can produce. The element's tag is not compared, by decision.
 */

import { entryName, type ComponentModel, type ModelFunction } from './model.ts'
import { offeredBy, writtenBy } from './model-completeness.ts'
import { JOIN_ITEM_CLASS } from './join-scope.ts'
import { markedElements, type MarkedElement } from './parser/documented-classes.ts'

/** A function and the component model that declares it. */
export interface ModelledFunction {
  readonly model: ComponentModel
  readonly fn: ModelFunction
}

/** The models in the order a page's elements are matched against them: the page's own first. */
export function pageFirst(models: readonly ComponentModel[], page: string): ComponentModel[] {
  return [...models.filter(model => model.component === page), ...models.filter(model => model.component !== page)]
}

/** One function's share of an element's classes. */
export interface ClaimedGroup extends ModelledFunction {
  readonly classes: readonly string[]
}

/** Who claims which of an element's classes, and what nobody claims. */
export interface Claims {
  readonly groups: readonly ClaimedGroup[]
  readonly unclaimed: readonly string[]
}

/**
 * `design.md` D4, searching `models` in the order given, the page's own first: for each own class
 * the element carries, the function with that own class offering the most of the element's
 * classes claims what it offers; what remains is claimed whole by a function with no own class,
 * or by nobody.
 */
export function claimingGroups(models: readonly ComponentModel[], counted: readonly string[]): Claims {
  const candidates = models.flatMap(model => model.functions.map(fn => ({ model, fn })))
  const owned = counted.flatMap(own => ownerOf(candidates, own, counted)).map(owner => ({ ...owner, classes: counted.filter(cls => offers(owner.model, owner.fn, cls)) }))
  const rest = counted.filter(cls => !owned.some(group => group.classes.includes(cls)))
  const free = rest.length === 0 ? undefined : candidates.find(({ model, fn }) => fn.class === null && rest.every(cls => offers(model, fn, cls)))
  return free === undefined ? { groups: owned, unclaimed: rest } : { groups: [...owned, { ...free, classes: rest }], unclaimed: [] }
}

/** Of the functions whose own class is `own`, the one offering the most of the element's classes. */
function ownerOf(candidates: readonly ModelledFunction[], own: string, counted: readonly string[]): ModelledFunction[] {
  const share = ({ model, fn }: ModelledFunction) => counted.filter(cls => offers(model, fn, cls)).length
  const owners = candidates.filter(({ fn }) => fn.class === own)
  return owners.length === 0 ? [] : [owners.reduce((best, next) => (share(next) > share(best) ? next : best))]
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
    .map(element => ({ tag: element.tag, counted: countedClasses(element, componentClasses) }))
    .filter(({ counted }) => counted.length > 0 && claimingGroups(models, counted).unclaimed.length > 0)
    .map(({ tag, counted }) => ({ tag, key: gapKey(models, counted) }))
}

/** What the gate reports for one page: gaps nothing declares, and declarations no gap needs. */
export function gapFindings(model: Pick<ComponentModel, 'component' | 'notExpressed'>, gaps: readonly DocumentedGap[]): string[] {
  const declared = model.notExpressed ?? {}
  const keys = new Set(gaps.map(gap => gap.key))
  return [...new Set([
    ...gaps.filter(gap => !(gap.key in declared)).map(gap => `${model.component}: <${gap.tag}> ${gap.key} is documented, and no function expresses it`),
    ...Object.keys(declared).filter(key => !keys.has(key)).map(key => `${model.component}: ${key} is declared not expressed, and no element on the page needs it`),
  ])]
}

/** The call a generated test makes for a documented element, and the classes it must render. */
export interface DocumentedCall {
  readonly fn: ModelFunction
  readonly args: string
  readonly expectedClasses: string
}

/**
 * The call reproducing an expressible element, or null for a gap. Prefixed component classes go
 * through `extraClasses` and are expected as written; `join-item` is neither passed nor expected,
 * because only a join's content adds it.
 */
export function documentedCall(models: readonly ComponentModel[], element: MarkedElement, componentClasses: ReadonlySet<string>): DocumentedCall | null {
  const counted = countedClasses(element, componentClasses)
  const { groups, unclaimed } = claimingGroups(models, counted)
  if (groups.length === 0 || unclaimed.length > 0) return null
  const [primary, ...others] = [...groups].sort((left, right) => rank(models, left) - rank(models, right))
  if (primary.fn.kind === 'classes' && others.length > 0) throw new Error(`${counted.join(' ')}: several class-only groups and no function rendering the element`)
  const variants = element.classes.filter(cls => cls.isPrefixed && componentClasses.has(cls.className)).map(cls => cls.rendered)
  const siblings = others.map(group => `${group.fn.name}Classes(${groupArguments(group).join(', ')})`)
  const args = [
    ...groupArguments(primary),
    ...(variants.length > 0 ? [`extraClasses = "${variants.join(' ')}"`] : []),
    ...(siblings.length > 0 ? [`attrs = { ${siblings.join('; ')} }`] : []),
  ]
  const expected = [...counted.filter(cls => cls !== JOIN_ITEM_CLASS), ...variants]
  return { fn: primary.fn, args: args.join(', '), expectedClasses: [...new Set(expected)].sort().join(' ') }
}

/** A rendering function's group before any class-only one, then the page's own model first. */
function rank(models: readonly ComponentModel[], group: ClaimedGroup): number {
  return (group.fn.kind === 'classes' ? models.length : 0) + models.indexOf(group.model)
}

/** The named arguments that make a group's function write the group's classes. */
function groupArguments({ model, fn, classes }: ClaimedGroup): string[] {
  return classes.filter(cls => cls !== fn.class && cls !== JOIN_ITEM_CLASS && !(fn.always ?? []).includes(cls)).map(cls => argumentFor(model, fn, cls))
}

/** The named argument that makes `fn` write `cls`; the classifier has already found that one does. */
function argumentFor(model: ComponentModel, fn: ModelFunction, cls: string): string {
  const parameter = fn.parameters.find(candidate => writtenBy(model, candidate).includes(cls))
  if (parameter === undefined) throw new Error(`${fn.name} offers no parameter writing ${cls}`)
  return `${parameter.name} = ${parameter.kind === 'enum' ? `${parameter.enum}.${entryName(model, parameter.enum, cls)}` : 'true'}`
}

/** The classes the gate judges an element by: unprefixed, and declared by some component. */
function countedClasses(element: MarkedElement, componentClasses: ReadonlySet<string>): string[] {
  return element.classes.filter(cls => !cls.isPrefixed && componentClasses.has(cls.className)).map(cls => cls.className)
}
