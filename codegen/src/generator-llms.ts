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
import type { DocSummary } from './generator-docs.ts'

export function generateLlmsTxt(
  template: string,
  _shapes: readonly ComponentShape[],
  _docSummaries: Readonly<Record<string, DocSummary>>,
): string {
  return template
}
