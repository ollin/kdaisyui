/**
 * Comparing the element the generator chose against the one DaisyUI documents.
 *
 * The generator picks an element with a heuristic, and a heuristic can be wrong in a way nothing
 * else here notices: the element a component renders changes no CSS class, so neither the
 * generated tests nor the drift job can see a wrong one. `dropdown` once rendered a variant that
 * could not open; `otp` renders a `<div>` where DaisyUI shows a `<label>`, so a click does not
 * focus the input it wraps.
 *
 * Three things fail generation, and each exists because the alternative is a silent wrong answer:
 *
 *   1. a disagreement with no recorded exception
 *   2. a component DaisyUI documents no element for, so nothing can be checked
 *   3. an exception that is no longer needed
 *
 * The third is what stops the exception list becoming the hand-maintained list this change
 * deleted elsewhere. An exception must name a reason and a tracking issue, and it must expire on
 * its own the moment the defect it covers is fixed.
 */

/** What the generator chose for one component, beside what DaisyUI documents. */
export interface ElementObservation {
  readonly componentDir: string
  /** The element the generator will emit, e.g. `DIV`. */
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

export interface CrossCheckFinding {
  readonly componentDir: string
  readonly message: string
}

export interface CrossCheckResult {
  readonly findings: readonly CrossCheckFinding[]
  /** Disagreements covered by an exception — reported, not failed. */
  readonly excused: readonly string[]
}

function disagrees(observation: ElementObservation): boolean {
  return observation.documented !== null && observation.documented !== observation.chosen
}

function undocumentedFinding(observation: ElementObservation): CrossCheckFinding {
  return {
    componentDir: observation.componentDir,
    message:
      `DaisyUI documents no element for "${observation.componentDir}": no fenced html example ` +
      `carries its $$-marked component class, so the chosen <${observation.chosen.toLowerCase()}> ` +
      `cannot be checked against anything.`,
  }
}

function disagreementFinding(observation: ElementObservation): CrossCheckFinding {
  return {
    componentDir: observation.componentDir,
    message:
      `"${observation.componentDir}" is generated as <${observation.chosen.toLowerCase()}> but ` +
      `DaisyUI documents <${observation.documented!.toLowerCase()}>. Fix it with a ` +
      `componentElements entry, or record an exception with a reason and an issue.`,
  }
}

function staleExceptionFinding(componentDir: string, exception: CrossCheckException): CrossCheckFinding {
  return {
    componentDir,
    message:
      `The element exception for "${componentDir}" is no longer needed — it now agrees with ` +
      `DaisyUI. Delete it and close issue #${exception.issue} if it is done. (Reason given: ` +
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
  exceptions: Readonly<Record<string, CrossCheckException>>,
): CrossCheckResult {
  const findings: CrossCheckFinding[] = []
  const excused: string[] = []

  for (const observation of observations) {
    const exception = exceptions[observation.componentDir]

    if (observation.documented === null) {
      findings.push(undocumentedFinding(observation))
      continue
    }
    if (!disagrees(observation)) {
      if (exception) findings.push(staleExceptionFinding(observation.componentDir, exception))
      continue
    }
    if (exception) excused.push(observation.componentDir)
    else findings.push(disagreementFinding(observation))
  }

  return { findings, excused }
}

/** The message the generator dies with. One line per finding, so none of them is buried. */
export function describeCrossCheckFailure(result: CrossCheckResult): string {
  const lines = result.findings.map(finding => `  ✗ ${finding.message}`)
  return [`Element cross-check failed (${result.findings.length}):`, ...lines].join('\n')
}
