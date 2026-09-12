/**
 * The Kotlin-level API surface of the generated components, as a committed baseline.
 *
 * `lib/api/lib.api` is a dump of JVM descriptors, and descriptors carry none of the three
 * things that make a Kotlin signature source-compatible:
 *
 *   - lambda RECEIVER types — `(DIV.() -> Unit)` and `(LABEL.() -> Unit)` both erase to
 *     `Lkotlin/jvm/functions/Function1;`
 *   - parameter NAMES — part of the API in Kotlin, because callers use named arguments
 *   - DEFAULT VALUES — only a `$default` synthetic survives, saying that some exist
 *
 * Measured on this change: `daisyOtp` changed from `DIV` to `LABEL`, which breaks every
 * caller's lambda body, and `lib/api/lib.api` showed no diff at all. Worse, the hand-written
 * `example-app` consumer still compiled, because its lambda used only `span { }` — available
 * on both. Nothing in the repository noticed.
 *
 * So this baseline answers the question the JVM one cannot. The two are kept: removing a
 * parameter IS visible there, since the arity changes.
 *
 * It is written only by an explicit task, never by `just generate`. A baseline rewritten by the
 * same command that regenerates the code would follow every change in silence, which is exactly
 * the failure being fixed.
 */

import type { ComponentShape, FunctionShape, ParameterShape } from './component-shape.ts'

/** One parameter, in the form a Kotlin caller sees it. */
function renderParameter(parameter: ParameterShape): string {
  const defaulted = parameter.default === null ? '' : ` = ${parameter.default}`
  return `${parameter.name}: ${parameter.type}${defaulted}`
}

/**
 * One function, on one line.
 *
 * One line per function so a diff names the function that changed and nothing else. A
 * multi-line rendering would report a changed parameter as a hunk in the middle of a block,
 * which is what makes `lib/api/lib.api` hard to read even where it is correct.
 */
function renderFunction(shape: FunctionShape): string {
  const parameters = shape.parameters.map(renderParameter).join(', ')
  return `fun ${shape.receiver}.${shape.name}(${parameters})`
}

/** The enum entries a caller can name, which are as much API as the functions are. */
function renderEnums(shape: ComponentShape): string[] {
  return shape.enums.map(e => `enum ${e.name}: ${e.entries.map(entry => entry.name).join(', ')}`)
}

/**
 * One component's block: its element, then its enums, then its functions.
 *
 * The element is included even though no signature names it directly, because it is the fact
 * that decides every lambda receiver — and stating it makes a receiver change readable as
 * "the element moved" rather than as four unexplained type edits.
 */
function renderComponent(shape: ComponentShape): string[] {
  const main = shape.functions[0]
  return [
    `# ${shape.componentName} <${main.htmlTag}>`,
    ...renderEnums(shape),
    ...shape.functions.map(renderFunction),
    '',
  ]
}

/**
 * The whole baseline.
 *
 * Sorted by component name so the file order cannot depend on directory iteration order, which
 * differs between filesystems. A baseline that is unstable across machines is a baseline that
 * fails for everyone but its author.
 */
export function generateComponentApiDump(shapes: readonly ComponentShape[]): string {
  const header = [
    '# GENERATED — DO NOT EDIT',
    '#',
    '# The Kotlin-level API of the generated components: lambda receiver types, parameter',
    '# names, order and defaults. lib/api/lib.api cannot carry any of those — JVM descriptors',
    '# have no representation for them — so a changed lambda receiver produces no diff there.',
    '#',
    '# Update with: ./gradlew :lib:updateComponentApi',
    '',
  ]

  const sorted = [...shapes].sort((a, b) => {
    const left = a.componentName.toLowerCase()
    const right = b.componentName.toLowerCase()
    if (left < right) return -1
    return left > right ? 1 : 0
  })

  return [...header, ...sorted.flatMap(renderComponent)].join('\n')
}
