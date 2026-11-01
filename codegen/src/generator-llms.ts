/**
 * `llms-full.txt`: the whole generated API in one file, for an AI tool that fetches one URL.
 *
 * Generated whole from `codegen/llms-full-template.md` and the same component shapes that
 * `docs/reference/` and `lib/api/components.api` are rendered from. Until 0.7.0 it was
 * hand-maintained as `llms.txt`, and it still documented the 0.1.x API a release after 0.6.0 had
 * replaced it: `daisyBadge(dash, ghost, outline, soft: Boolean)` where the library had two enums,
 * a `DIV` receiver where `daisyDropdownContent` takes a `UL`. The README pointed library users and
 * their AI tools at exactly that file. Since `llms-txt-index` the name `llms.txt` belongs to the
 * index (`llms-index.ts`), which links here.
 */
import type { ComponentShape } from './component-shape.ts'
import { summaryLine, type DocSummary } from './generator-docs.ts'
import { componentSignatures } from './component-api-dump.ts'

/** Where the template takes the component API. */
const PLACEHOLDER = '{{COMPONENTS}}'

/** Written by the generator rather than the template, so no template edit can drop it. */
const HEADER = [
  '<!--',
  'GENERATED — DO NOT EDIT',
  'Source: codegen/llms-full-template.md and the generated component API',
  'Regenerate: just generate',
  '-->',
  '',
].join('\n')

export function generateLlmsFull(
  template: string,
  shapes: readonly ComponentShape[],
  docSummaries: Readonly<Record<string, DocSummary>>,
): string {
  if (!template.includes(PLACEHOLDER)) throw new Error(`llms-full template has no ${PLACEHOLDER} line`)
  const sections = shapes.map(shape => componentSection(shape, docSummaries[shape.componentDir]))
  return HEADER + template.replace(PLACEHOLDER, sections.join('\n'))
}

function componentSection(shape: ComponentShape, docSummary: DocSummary | undefined): string {
  const enums = shape.enums.map(e => `enum class ${e.name} { ${e.entries.map(entry => entry.name).join(', ')} }`)
  const lead = `${summaryLine(shape, docSummary)} — renders \`<${shape.functions[0].htmlTag}>\`.`
  return [`### ${shape.componentName}`, '', lead, '', '```kotlin', ...enums, ...componentSignatures(shape), '```', ''].join('\n')
}
