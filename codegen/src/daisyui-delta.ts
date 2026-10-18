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
    return execFileSync('git', ['-C', this.repo, ...args], { encoding: 'utf8', maxBuffer: 64 * 1024 * 1024 })
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

/** Classes a component's frontmatter declares in one release and not the other. */
export interface ClassChange {
  readonly component: string
  readonly added: readonly string[]
  readonly removed: readonly string[]
}

function declaredClasses(markdown: string | null): Set<string> {
  const classnames = (markdown === null ? null : parseFrontmatter(markdown))?.classnames ?? {}
  return new Set(Object.keys(classnames).flatMap(category => getClassesByCategory(classnames, category)))
}

/** Per component documented in both releases, the classes its frontmatter gained and lost. */
export function classChanges(older: ComponentDocs, newer: ComponentDocs): ClassChange[] {
  const inBoth = newer.componentDirs().filter(dir => older.componentDirs().includes(dir))
  return inBoth.flatMap(component => {
    const before = declaredClasses(older.page(component))
    const after = declaredClasses(newer.page(component))
    const added = [...after].filter(cls => !before.has(cls)).sort()
    const removed = [...before].filter(cls => !after.has(cls)).sort()
    return added.length + removed.length === 0 ? [] : [{ component, added, removed }]
  })
}
