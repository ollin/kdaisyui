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
  /** What DaisyUI's documentation shows, or null when it shows nothing to check against. */
  readonly documented: string | null
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
}

/** The config key for one observation. */
export function exceptionKeyOf(observation: ElementObservation): string {
  if (observation.isComponentClass) return observation.componentDir
  return `${observation.componentDir}/${observation.cssClass}`
}

function disagrees(observation: ElementObservation): boolean {
  return observation.documented !== null && observation.documented !== observation.chosen
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
      `DaisyUI documents it on <${observation.documented!.toLowerCase()}>. Declare it on the ` +
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

  for (const observation of observations) {
    const key = exceptionKeyOf(observation)
    const exception = exceptions[key]

    if (observation.documented === null) {
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

  return { findings, excused }
}

/** The message the generator dies with. One line per finding, so none of them is buried. */
export function describeCrossCheckFailure(result: CrossCheckResult): string {
  const lines = result.findings.map(finding => `  ✗ ${finding.message}`)
  return [`Element cross-check failed (${result.findings.length}):`, ...lines].join('\n')
}
