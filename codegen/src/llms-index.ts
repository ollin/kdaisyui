/**
 * `llms.txt`: the index https://llmstxt.org/ specifies. An H1, a blockquote, a few lines of
 * prose, then H2 sections that are lists of links an agent fetches on demand. The detail lives
 * behind the links: the reference pages, the prose documentation and `llms-full.txt`.
 *
 * The component list is generated from the same shapes the reference pages are rendered from, so
 * a new component is linked without anyone editing the template. The rest of the file is
 * `codegen/llms-template.md`.
 */
import type { ComponentShape } from './component-shape.ts'
import { byDisplayName, docFileNameFor, summaryLine, type DocSummary } from './generator-docs.ts'

/** Where GitHub serves the committed Markdown as plain text; `blob` URLs serve HTML. */
export const RAW_BASE = 'https://raw.githubusercontent.com/ollin/kdaisyui/main/'

/** Where the template takes the component links. */
const PLACEHOLDER = '{{COMPONENTS}}'

/** Written by the generator rather than the template, so no template edit can drop it. */
const HEADER = [
  '<!--',
  'GENERATED — DO NOT EDIT',
  'Source: codegen/llms-template.md and the generated component API',
  'Regenerate: just generate',
  '-->',
  '',
].join('\n')

export function renderLlmsIndex(
  template: string,
  shapes: readonly ComponentShape[],
  docSummaries: Readonly<Record<string, DocSummary>>,
): string {
  if (!template.includes(PLACEHOLDER)) throw new Error(`llms template has no ${PLACEHOLDER} line`)
  const links = [...shapes].sort(byDisplayName).map(shape => componentLink(shape, docSummaries[shape.componentDir]))
  return HEADER + template.replace(PLACEHOLDER, links.join('\n'))
}

/** What the reference parsers read as a link item: `- [name](url)`, optionally `: note`. */
const LINK_ITEM = /^- \[[^\]]+\]\((?<url>[^)]+)\)(: .*)?$/

/** A linked repository path, with the line that links it; `null` for a link to another host. */
function repositoryLink(line: string): string | null {
  const url = LINK_ITEM.exec(line)?.groups?.url
  return url !== undefined && url.startsWith(RAW_BASE) ? url.slice(RAW_BASE.length) : null
}

/** Documentation pages the index does not link. A page nobody can find is a page nobody reads. */
export function coverageFindings(index: string, pages: readonly string[]): string[] {
  const linked = new Set(index.split('\n').map(repositoryLink))
  return pages.filter(page => !linked.has(page)).map(page => `${page} is not linked from llms.txt`)
}

/** Repository links whose target does not exist. Another host's reachability is not ours to know. */
export function linkFindings(index: string, exists: (repositoryPath: string) => boolean): string[] {
  return index
    .split('\n')
    .map((line, at) => ({ path: repositoryLink(line), at }))
    .filter(({ path }) => path !== null && !exists(path))
    .map(({ path, at }) => `line ${at + 1}: links ${path}, which does not exist in the repository`)
}

/**
 * Where the rendered index breaks the shape https://llmstxt.org/ specifies. The reference
 * parsers would not object, since they only collect the link items they find; a paragraph or
 * sub-heading after the first H2 would be silently dropped, which is why the gate is here.
 */
export function shapeFindings(index: string): string[] {
  const lines = index.split('\n').map((line, at) => ({ line, at }))
  const firstH2 = lines.findIndex(({ line }) => line.startsWith('## '))
  const preamble = firstH2 === -1 ? lines : lines.slice(0, firstH2)
  const lists = firstH2 === -1 ? [] : lines.slice(firstH2)
  const h1Count = preamble.filter(({ line }) => line.startsWith('# ')).length
  return [
    ...preamble
      .filter(({ line }) => line.startsWith('#') && !line.startsWith('# '))
      .map(({ line, at }) => `line ${at + 1}: before the first H2 the only heading is the H1, got "${line}"`),
    ...(h1Count === 1 ? [] : [`the index needs exactly one H1, found ${h1Count}`]),
    ...lists
      .filter(({ line }) => line !== '' && !line.startsWith('## ') && !LINK_ITEM.test(line))
      .map(({ line, at }) => `line ${at + 1}: after the first H2 only H2 headings and link items may appear, got "${line}"`),
  ]
}

function componentLink(shape: ComponentShape, docSummary: DocSummary | undefined): string {
  return `- [${shape.componentName}](${RAW_BASE}docs/reference/${docFileNameFor(shape.componentDir)}.md): ${summaryLine(shape, docSummary)}`
}
