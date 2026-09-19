/**
 * Comparing the element a generated function renders against the one DaisyUI documents for
 * each class the function emits.
 *
 * The generator picks a component's element with a heuristic, and every class in the component's
 * groups lands on the main function. Both can be wrong in a way nothing else here notices: the
 * element a function renders changes no CSS class, so neither the generated tests nor the drift
 * job can see it. `dropdown` once rendered a variant that could not open; `otp` rendered a
 * `<div>` where DaisyUI shows a `<label>`; `menu-active` was a parameter of `daisyMenu` while
 * DaisyUI puts it on an `<li>` two levels down, where the container's function cannot reach.
 *
 * So the check is per CLASS, and the component class is one class among them: for every class
 * a function emits, the element that function renders must be the element DaisyUI documents
 * the class on. Three things fail generation, each because the alternative is a silent wrong
 * answer:
 *
 *   1. a disagreement with no recorded exception
 *   2. a component whose own class DaisyUI documents no element for, so nothing can be checked
 *   3. an exception that is no longer needed
 *
 * The third is what stops the exception list becoming the hand-maintained list this project has
 * deleted twice. An exception must name a reason and a tracking issue, and it expires on its own
 * the moment the defect it covers is fixed.
 */

/**
 * Every element DaisyUI's documentation shows one class on.
 *
 * A collection that answers questions rather than an array callers compute over. Three of them
 * did: one asked `includes`, one a length, one compared two arrays position by position — three
 * spellings of "what does DaisyUI say here", each able to drift from the others.
 *
 * Several elements rather than one because DaisyUI genuinely uses several: `badge` is a `<div>`
 * 47 times and a `<span>` 7, and which it picks is the surrounding context — a `<span>` inside
 * an `<h2>`, where a `<div>` is invalid HTML. Naming one of those THE element makes six wrong.
 */
export class DocumentedElements {
  private readonly elements: readonly string[]

  private constructor(elements: readonly string[]) {
    this.elements = elements
  }

  static of(elements: Iterable<string>): DocumentedElements {
    return new DocumentedElements([...elements])
  }

  /** DaisyUI shows the class in no fenced example, so there is nothing to disagree with. */
  static none(): DocumentedElements {
    return new DocumentedElements([])
  }

  showsNothing(): boolean {
    return this.elements.length === 0
  }

  shows(element: string): boolean {
    return this.elements.includes(element)
  }

  sameAs(other: DocumentedElements): boolean {
    return this.elements.length === other.elements.length && this.elements.every(element => other.shows(element))
  }

  /** These elements, or the one given when DaisyUI shows none. */
  orElse(element: string): DocumentedElements {
    return this.showsNothing() ? DocumentedElements.of([element]) : this
  }

  /** `<div>`, or `<div> or <span>` — how a failure message names what DaisyUI shows. */
  asTagList(): string {
    const tags = this.elements.map(element => `<${element.toLowerCase()}>`)
    return tags.length < 2 ? tags.join('') : `${tags.slice(0, -1).join(', ')} or ${tags.at(-1)}`
  }
}

/** One class a generated function emits, beside where DaisyUI documents it. */
export interface ElementObservation {
  readonly componentDir: string
  /**
   * The class in question, e.g. `menu-active` — or the component's own class, which is not
   * always the directory name (`cally` under `calendar`), so that case is said, not inferred.
   */
  readonly cssClass: string
  readonly isComponentClass: boolean
  /** The element the generator renders the class on, e.g. `DIV`. */
  readonly chosen: string
  readonly documented: DocumentedElements
}

/** A disagreement someone has looked at, decided to keep, and filed. */
export interface CrossCheckException {
  readonly reason: string
  /** The issue tracking the defect. An exception pointing at nothing is the thing to avoid. */
  readonly issue: number
}

/**
 * Exceptions keyed by `<componentDir>/<cssClass>`; the component's own class is keyed by the
 * directory alone, as it always was.
 */
export type CrossCheckExceptions = Readonly<Record<string, CrossCheckException>>

export interface CrossCheckFinding {
  readonly componentDir: string
  readonly message: string
}

export interface CrossCheckResult {
  readonly findings: readonly CrossCheckFinding[]
  /** Disagreements covered by an exception — reported, not failed. Keys as in the config. */
  readonly excused: readonly string[]
  /** Every exception key looked up, so the config-consumption guard can tell a typo from a key. */
  readonly consulted: readonly string[]
}

/** The config key for one observation. */
export function exceptionKeyOf(observation: ElementObservation): string {
  if (observation.isComponentClass) return observation.componentDir
  return `${observation.componentDir}/${observation.cssClass}`
}

function disagrees(observation: ElementObservation): boolean {
  return !observation.documented.showsNothing() && !observation.documented.shows(observation.chosen)
}

function undocumentedFinding(observation: ElementObservation): CrossCheckFinding {
  return {
    componentDir: observation.componentDir,
    message:
      `DaisyUI documents no element for "${observation.cssClass}": no fenced html example ` +
      `carries it unprefixed, so the chosen <${observation.chosen.toLowerCase()}> cannot be ` +
      `checked against anything.`,
  }
}

function disagreementFinding(observation: ElementObservation): CrossCheckFinding {
  return {
    componentDir: observation.componentDir,
    message:
      `"${observation.cssClass}" is emitted on <${observation.chosen.toLowerCase()}> but ` +
      `DaisyUI documents it on ${observation.documented.asTagList()}. Declare it on the ` +
      `function that renders that element, or record an exception under ` +
      `elementCrossCheckExceptions["${exceptionKeyOf(observation)}"] with a reason and an issue.`,
  }
}

function staleExceptionFinding(key: string, exception: CrossCheckException): CrossCheckFinding {
  return {
    componentDir: key.split('/')[0],
    message:
      `The element exception "${key}" is no longer needed — it now agrees with DaisyUI. ` +
      `Delete it and close issue #${exception.issue} if it is done. (Reason given: ` +
      `${exception.reason})`,
  }
}

/**
 * Every reason this run must fail, and the disagreements that were excused.
 *
 * Returns findings rather than throwing, so the caller decides when to stop and the whole set is
 * reportable at once — failing on the first of three would hide the other two.
 */
export function crossCheckElements(
  observations: readonly ElementObservation[],
  exceptions: CrossCheckExceptions,
): CrossCheckResult {
  const findings: CrossCheckFinding[] = []
  const excused: string[] = []
  const consulted: string[] = []

  for (const observation of observations) {
    const key = exceptionKeyOf(observation)
    const exception = exceptions[key]
    consulted.push(key)

    if (observation.documented.showsNothing()) {
      // Only the component's own class must be documented: without it nothing about the
      // component can be checked. A modifier DaisyUI lists but never shows (`btn-md`,
      // `modal-top`) is unchecked, not wrong.
      if (observation.isComponentClass) findings.push(undocumentedFinding(observation))
      continue
    }
    if (!disagrees(observation)) {
      if (exception) findings.push(staleExceptionFinding(key, exception))
      continue
    }
    if (exception) excused.push(key)
    else findings.push(disagreementFinding(observation))
  }

  return { findings, excused, consulted }
}

/** The message the generator dies with. One line per finding, so none of them is buried. */
export function describeCrossCheckFailure(result: CrossCheckResult): string {
  const lines = result.findings.map(finding => `  ✗ ${finding.message}`)
  return [`Element cross-check failed (${result.findings.length}):`, ...lines].join('\n')
}
