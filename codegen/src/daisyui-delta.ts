/**
 * What changed in DaisyUI's component documentation between two tags, for a human reading a bump.
 *
 * Compares DaisyUI with itself, never with the model: a deliberate divergence such as `daisyBadge`
 * rendering `<span>` does not change between releases, so it needs no exception here. Reads both
 * tags through `git show` in the submodule, so nothing is checked out and the working tree stays
 * at the pinned tag.
 */

import { execFileSync } from 'node:child_process'

/** Where the component pages live inside the DaisyUI repository. */
export const COMPONENTS_IN_REPO = 'packages/docs/src/routes/(routes)/components'

/** The component documentation of one DaisyUI tag. */
export class DocsAtTag {
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
