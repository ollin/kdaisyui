/**
 * What changed in DaisyUI's component documentation between two tags, for a human reading a bump.
 *
 * Compares DaisyUI with itself, never with the model: a deliberate divergence such as `daisyBadge`
 * rendering `<span>` does not change between releases, so it needs no exception here. Reads both
 * tags through `git show` in the submodule, so nothing is checked out and the working tree stays
 * at the pinned tag.
 */

import { execFileSync } from 'node:child_process'
import { getClassesByCategory, parseFrontmatter } from './parser/frontmatter.ts'
import { documentedElementSetsIn, fencedHtmlBlocks } from './parser/documented-element.ts'
import { documentedElementClasses } from './parser/documented-classes.ts'
import { JOIN_ITEM_CLASS } from './join-scope.ts'

/** Where the component pages live inside the DaisyUI repository. */
export const COMPONENTS_IN_REPO = 'packages/docs/src/routes/(routes)/components'

/** The component documentation of one DaisyUI release, however it is read. */
export interface ComponentDocs {
  componentDirs(): string[]
  page(componentDir: string): string | null
}

/** The component documentation of one DaisyUI tag. */
export class DocsAtTag implements ComponentDocs {
  private readonly repo: string
  private readonly tag: string

  constructor(repo: string, tag: string) {
    this.repo = repo
    this.tag = tag
  }

  private git(...args: string[]): string {
    return execFileSync('git', ['-C', this.repo, ...args], { encoding: 'utf8', maxBuffer: 64 * 1024 * 1024, stdio: ['ignore', 'pipe', 'ignore'] })
  }

  /** Every directory with a `+page.md`, sorted. */
  componentDirs(): string[] {
    const prefix = `${COMPONENTS_IN_REPO}/`
    return this.git('ls-tree', '-r', '--name-only', this.tag, '--', COMPONENTS_IN_REPO)
      .split('\n')
      .filter(file => file.startsWith(prefix) && file.endsWith('/+page.md'))
      .map(file => file.slice(prefix.length, -'/+page.md'.length))
      .filter(dir => !dir.includes('/'))
      .sort()
  }

  /** The page's markdown, or null when this tag has no such page. */
  page(componentDir: string): string | null {
    try {
      return this.git('show', `${this.tag}:${COMPONENTS_IN_REPO}/${componentDir}/+page.md`)
    } catch {
      return null
    }
  }
}

function componentsInBoth(older: ComponentDocs, newer: ComponentDocs): string[] {
  const before = new Set(older.componentDirs())
  return newer.componentDirs().filter(dir => before.has(dir))
}

/** Members of a set that one release has and the other does not. */
export interface SetChange {
  readonly added: readonly string[]
  readonly removed: readonly string[]
}

function setChange(before: Iterable<string>, after: Iterable<string>): SetChange {
  const was = new Set(before)
  const is = new Set(after)
  return {
    added: [...is].filter(item => !was.has(item)).sort(),
    removed: [...was].filter(item => !is.has(item)).sort(),
  }
}

/** Classes a component's frontmatter declares in one release and not the other. */
export interface ClassChange extends SetChange {
  readonly component: string
}

function declaredClasses(markdown: string | null): Set<string> {
  const classnames = (markdown === null ? null : parseFrontmatter(markdown))?.classnames ?? {}
  return new Set(Object.keys(classnames).flatMap(category => getClassesByCategory(classnames, category)))
}

/** Per component documented in both releases, the classes its frontmatter gained and lost. */
export function classChanges(older: ComponentDocs, newer: ComponentDocs): ClassChange[] {
  return componentsInBoth(older, newer).flatMap(component => {
    const { added, removed } = setChange(declaredClasses(older.page(component)), declaredClasses(newer.page(component)))
    return added.length + removed.length === 0 ? [] : [{ component, added, removed }]
  })
}

/** A class DaisyUI's marked examples put on a different set of elements in the newer release. */
export interface ElementChange {
  readonly component: string
  readonly cssClass: string
  readonly before: readonly string[]
  readonly after: readonly string[]
}

function elementSets(markdown: string | null): ReadonlyMap<string, ReadonlySet<string>> {
  return documentedElementSetsIn(fencedHtmlBlocks(markdown ?? ''))
}

/** Per class marked in both releases, where the elements it is documented on changed. */
export function elementChanges(older: ComponentDocs, newer: ComponentDocs): ElementChange[] {
  return componentsInBoth(older, newer).flatMap(component => {
    const before = elementSets(older.page(component))
    const after = elementSets(newer.page(component))
    return [...after.keys()].sort().flatMap(cssClass => {
      const was = [...(before.get(cssClass) ?? [])].sort()
      const is = [...(after.get(cssClass) ?? [])].sort()
      return was.length === 0 || was.join() === is.join() ? [] : [{ component, cssClass, before: was, after: is }]
    })
  })
}

/** The component directories one release documents and the other does not. */
export function componentChanges(older: ComponentDocs, newer: ComponentDocs): SetChange {
  return setChange(older.componentDirs(), newer.componentDirs())
}

/**
 * Every unprefixed daisyUI class DaisyUI shows on an element that ALSO carries `join-item`.
 *
 * The unit is the element, not the example block: a join whose children are a button and a
 * select documents two separate items, and reading the block whole would make each child's
 * class a companion of the other's.
 *
 * A class a Tailwind variant applies conditionally is not a companion. `lg:$$btn` is the
 * button class above `lg` and nothing below it, while `daisyButton` always emits `btn` — so a
 * member derived from that element would mark something DaisyUI does not document as a join
 * item at most widths.
 */
export function classesJoinedWith(html: string): ReadonlySet<string> {
  const companions = new Set<string>()
  for (const element of documentedElementClasses(html, JOIN_ITEM_CLASS)) {
    for (const documented of element) {
      if (documented.isPrefixed) continue
      if (documented.className === JOIN_ITEM_CLASS) continue
      companions.add(documented.className)
    }
  }
  return companions
}

function joinItemCompanions(docs: ComponentDocs): string[] {
  return docs.componentDirs().flatMap(dir => [...classesJoinedWith(fencedHtmlBlocks(docs.page(dir) ?? ''))])
}

/** The component classes DaisyUI documents beside `join-item` in one release and not the other. */
export function joinItemChanges(older: ComponentDocs, newer: ComponentDocs): SetChange {
  return setChange(joinItemCompanions(older), joinItemCompanions(newer))
}

function setLine(label: string, change: SetChange): string[] {
  const parts = [
    ...(change.added.length > 0 ? [`added ${change.added.join(', ')}`] : []),
    ...(change.removed.length > 0 ? [`removed ${change.removed.join(', ')}`] : []),
  ]
  return parts.length === 0 ? [] : [`${label}: ${parts.join('; ')}`]
}

/** The report a human reads during a bump; the model is then edited by hand. */
export function renderDelta(older: ComponentDocs, newer: ComponentDocs, from: string, to: string): string {
  const lines = [
    ...setLine('Components', componentChanges(older, newer)),
    ...classChanges(older, newer).flatMap(change => setLine(`Classes of ${change.component}`, change)),
    ...elementChanges(older, newer).map(change => `Elements of ${change.cssClass} (${change.component}): ${change.before.join(', ')} became ${change.after.join(', ')}`),
    ...setLine('Join items', joinItemChanges(older, newer)),
  ]
  const body = lines.length === 0 ? ['No class, element, component or join-item change found.'] : lines
  return [`DaisyUI documentation, ${from} to ${to}`, '', ...body].join('\n')
}
