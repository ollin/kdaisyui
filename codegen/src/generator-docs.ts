/**
 * How MARKDOWN states a component's generated API.
 *
 * The sibling of `generator-new.ts`: same `ComponentShape`, different language. That sharing is
 * the whole point — `docs/reference/` used to restate these signatures by hand, and restating
 * them is how `megamenu.md` came to document a `DIV` lambda receiver for a function that takes a
 * `SPAN`, and shipped that way in v0.4.0.
 *
 * One fact a page carries is NOT in the shape: its one-line editorial description. All 66 are
 * hand-written — none matches DaisyUI's own `desc` — so they live in `codegen-config.json` under
 * `docSummaries` and arrive here as an input like any other. See the change's design.md for why
 * DaisyUI's text cannot replace them.
 */

import {
  staticAttributeDoc,
  type ComponentShape,
  type FunctionShape,
  type ParameterShape,
} from './component-shape.ts'

/**
 * A boolean that carries a description carries a DAISYUI CLASS description, and that is worth
 * showing. Every other `doc` on the shape is a fixed sentence about an escape hatch — `attrs`
 * gives direct tag access, `extraClasses` appends classes — which a reference page has no reason
 * to repeat on all 66 of them. `megamenu.md` already comments exactly the booleans, by hand.
 */
function inlineComment(parameter: ParameterShape): string {
  return parameter.doc && parameter.type === 'Boolean' ? `  // ${parameter.doc}` : ''
}

/** The editorial text for one component, from `codegen-config.json`. */
export interface DocSummary {
  /** One line, also used as the component's row in the index table. Written without a period. */
  readonly summary: string
  /** A fuller opening paragraph, for the three components whose page says more than the table. */
  readonly description?: string
}

/**
 * `file-input` → `fileInput`.
 *
 * What `docs/reference/` has always named its files, and camelCase rather than the directory's
 * kebab-case because `index.md` links them that way.
 */
export function docFileNameFor(componentDir: string): string {
  const [first, ...rest] = componentDir.split('-')
  return first + rest.map(part => part.charAt(0).toUpperCase() + part.slice(1)).join('')
}

/**
 * Markdown has no comment syntax, so the do-not-edit attribution is an HTML comment: invisible
 * to a reader of the rendered page, unmissable to anyone who opens the file to edit it — which
 * is the only audience it is for.
 */
function header(shape: ComponentShape): string {
  return [
    '<!--',
    'GENERATED — DO NOT EDIT',
    `Source: daisyui/packages/docs/src/routes/(routes)/components/${shape.componentDir}/+page.md`,
    'Regenerate: just generate',
    '-->',
  ].join('\n')
}

/**
 * The opening paragraph: the editorial line, then what the component renders.
 *
 * Falls back to the first sentence of DaisyUI's own description when no summary is committed, so
 * a component a DaisyUI release has just added documents itself immediately — badly, and visibly
 * so in the regeneration diff, rather than not at all or not building.
 */
function description(shape: ComponentShape, docSummary: DocSummary | undefined): string {
  const main = shape.functions[0]
  const editorial = docSummary?.description ?? docSummary?.summary ?? firstSentence(main.desc)
  const clause = rendersClause(main)
  return editorial ? `${editorial}. ${clause}` : clause
}

function firstSentence(text: string): string {
  const end = text.indexOf('. ')
  return (end === -1 ? text : text.slice(0, end)).replace(/\.$/, '')
}

/** Word for word what the Kotlin doc comment says, so the two cannot describe different HTML. */
function rendersClause(shape: FunctionShape): string {
  const attrs = staticAttributeDoc(shape.staticAttributes)
  return shape.cssClass === null
    ? `Structural wrapper. Renders \`<${shape.tagBuilder}${attrs}>\`.`
    : `Renders \`<${shape.tagBuilder} class="${shape.cssClass} ..."${attrs}>\`.`
}

/**
 * The enum reminder above the main signature, one line per enum.
 *
 * The hand-written pages put both of a component's enums on one line. One line each instead,
 * because a component with eight colour variants and five sizes overflowed it, and because
 * adding an enum should not reflow the line above.
 */
function enumComments(shape: ComponentShape): string[] {
  return shape.enums.map(e => `// ${e.name}: ${e.entries.map(entry => entry.name).join(' | ')}`)
}

/**
 * One fenced block per function, which is what 65 of the 66 hand-written pages do. `megamenu.md`
 * is the exception and becomes consistent with the rest.
 */
function signatureBlock(shape: FunctionShape, leadingComments: readonly string[]): string {
  const parameters = shape.parameters.map(parameter => {
    const defaulted = parameter.default === null ? '' : ` = ${parameter.default}`
    return `    ${parameter.name}: ${parameter.type}${defaulted},${inlineComment(parameter)}`
  })

  return [
    '```kotlin',
    ...leadingComments,
    `fun ${shape.receiver}.${shape.name}(`,
    ...parameters,
    ')',
    '```',
  ].join('\n')
}

/** One component's reference page. */
export function generateComponentPage(
  shape: ComponentShape,
  docSummary: DocSummary | undefined,
): string {
  const [main, ...rest] = shape.functions

  const blocks = [
    signatureBlock(main, enumComments(shape)),
    ...rest.map(fn => signatureBlock(fn, [])),
  ]

  return [
    header(shape),
    '',
    `# ${shape.componentName}`,
    '',
    `[DaisyUI documentation →](https://daisyui.com/components/${shape.componentDir}/)`,
    '',
    description(shape, docSummary),
    '',
    blocks.join('\n\n'),
    '',
  ].join('\n')
}
