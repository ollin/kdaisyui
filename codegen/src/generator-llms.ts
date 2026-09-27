/**
 * `llms.txt`: the API reference an AI tool reads when a project depends on kdaisyui.
 *
 * Generated whole from `codegen/llms-template.md` and the same component shapes that
 * `docs/reference/` and `lib/api/components.api` are rendered from. Until 0.7.0 it was
 * hand-maintained, and it still documented the 0.1.x API a release after 0.6.0 had replaced it:
 * `daisyBadge(dash, ghost, outline, soft: Boolean)` where the library had two enums, a `DIV`
 * receiver where `daisyDropdownContent` takes a `UL`. The README pointed library users and their
 * AI tools at exactly that file.
 */
import type { ComponentShape } from './component-shape.ts'
import { summaryLine, type DocSummary } from './generator-docs.ts'
import { renderFunction } from './component-api-dump.ts'

/** Where the template takes the component API. */
const PLACEHOLDER = '{{COMPONENTS}}'

export function generateLlmsTxt(
  template: string,
  shapes: readonly ComponentShape[],
  docSummaries: Readonly<Record<string, DocSummary>>,
): string {
  if (!template.includes(PLACEHOLDER)) throw new Error(`llms template has no ${PLACEHOLDER} line`)
  const sections = shapes.map(shape => componentSection(shape, docSummaries[shape.componentDir]))
  return template.replace(PLACEHOLDER, sections.join('\n'))
}

function componentSection(shape: ComponentShape, docSummary: DocSummary | undefined): string {
  const enums = shape.enums.map(e => `enum class ${e.name} { ${e.entries.map(entry => entry.name).join(', ')} }`)
  const lead = `${summaryLine(shape, docSummary)} — renders \`<${shape.functions[0].htmlTag}>\`.`
  return [`### ${shape.componentName}`, '', lead, '', '```kotlin', ...enums, ...shape.functions.map(renderFunction), '```', ''].join('\n')
}
