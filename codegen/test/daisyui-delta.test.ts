import { describe, it, before, after } from 'node:test'
import assert from 'node:assert/strict'
import { execFileSync } from 'node:child_process'
import fs from 'fs'
import os from 'os'
import path from 'path'

import { classChanges, COMPONENTS_IN_REPO, DocsAtTag, type ComponentDocs } from '../src/daisyui-delta.ts'

/** A throwaway repository with DaisyUI's layout and two tags. */
class FixtureRepository {
  readonly dir = fs.mkdtempSync(path.join(os.tmpdir(), 'daisyui-delta-'))

  private git(...args: string[]): void {
    execFileSync('git', ['-C', this.dir, '-c', 'user.name=t', '-c', 'user.email=t@t', ...args], { stdio: 'ignore' })
  }

  writePage(componentDir: string, markdown: string): void {
    const file = path.join(this.dir, COMPONENTS_IN_REPO, componentDir, '+page.md')
    fs.mkdirSync(path.dirname(file), { recursive: true })
    fs.writeFileSync(file, markdown)
  }

  tag(name: string): void {
    this.git('add', '-A')
    this.git('commit', '-q', '-m', name)
    this.git('tag', name)
  }

  init(): void {
    this.git('init', '-q')
  }

  remove(): void {
    fs.rmSync(this.dir, { recursive: true, force: true })
  }
}

describe('DocsAtTag', () => {
  const repository = new FixtureRepository()

  before(() => {
    repository.init()
    repository.writePage('button', 'button at v1')
    repository.tag('v1')
    repository.writePage('button', 'button at v2')
    repository.writePage('card', 'card at v2')
    repository.tag('v2')
  })

  after(() => repository.remove())

  it('lists the component directories each tag documents', () => {
    assert.deepEqual(new DocsAtTag(repository.dir, 'v1').componentDirs(), ['button'])
    assert.deepEqual(new DocsAtTag(repository.dir, 'v2').componentDirs(), ['button', 'card'])
  })

  it('reads a page as the tag has it, not as the working tree has it', () => {
    assert.equal(new DocsAtTag(repository.dir, 'v1').page('button'), 'button at v1')
  })

  it('reads no page where the tag has none', () => {
    assert.equal(new DocsAtTag(repository.dir, 'v1').page('card'), null)
  })
})

/** A release held in memory: directory to page markdown. */
function release(pages: Record<string, string>): ComponentDocs {
  return { componentDirs: () => Object.keys(pages).sort(), page: dir => pages[dir] ?? null }
}

function frontmatter(...classes: string[]): string {
  return `---\ntitle: X\nclassnames:\n  component:\n${classes.map(cls => `  - class: ${cls}\n    desc: d`).join('\n')}\n---\n`
}

describe('classChanges', () => {
  it('names the classes a component gained and lost', () => {
    const older = release({ mask: frontmatter('mask', 'mask-square', 'mask-circle') })
    const newer = release({ mask: frontmatter('mask', 'mask-circle', 'mask-star') })

    assert.deepEqual(classChanges(older, newer), [{ component: 'mask', added: ['mask-star'], removed: ['mask-square'] }])
  })

  it('says nothing about a component whose classes did not change', () => {
    const page = release({ btn: frontmatter('btn') })

    assert.deepEqual(classChanges(page, page), [])
  })
})
