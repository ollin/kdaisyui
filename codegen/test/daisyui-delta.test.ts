import { describe, it, before, after } from 'node:test'
import assert from 'node:assert/strict'
import { execFileSync } from 'node:child_process'
import fs from 'fs'
import os from 'os'
import path from 'path'

import { classChanges, classesJoinedWith, componentChanges, COMPONENTS_IN_REPO, DocsAtTag, elementChanges, joinItemChanges, renderDelta, type ComponentDocs } from '../src/daisyui-delta.ts'

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

function example(html: string): string {
  return `${frontmatter('otp')}\n\`\`\`html\n${html}\n\`\`\`\n`
}

describe('elementChanges', () => {
  it('names a class whose documented elements changed, with both sets', () => {
    const older = release({ otp: example('<div class="$$otp"></div>') })
    const newer = release({ otp: example('<label class="$$otp"></label>') })

    assert.deepEqual(elementChanges(older, newer), [{ component: 'otp', cssClass: 'otp', before: ['DIV'], after: ['LABEL'] }])
  })

  it('says nothing about a class documented on the same elements in both', () => {
    const page = release({ otp: example('<div class="$$otp"></div>') })

    assert.deepEqual(elementChanges(page, page), [])
  })
})

describe('componentChanges', () => {
  it('names the components one release documents and the other does not', () => {
    const older = release({ btn: frontmatter('btn'), pagination: frontmatter('join') })
    const newer = release({ btn: frontmatter('btn'), aura: frontmatter('aura') })

    assert.deepEqual(componentChanges(older, newer), { added: ['aura'], removed: ['pagination'] })
  })
})

describe('joinItemChanges', () => {
  it('names a component class newly or no longer documented beside join-item', () => {
    const older = release({ join: example('<div class="$$join"><button class="$$btn $$join-item"></button></div>') })
    const newer = release({ join: example('<div class="$$join"><input class="$$input $$join-item"></div>') })

    assert.deepEqual(joinItemChanges(older, newer), { added: ['input'], removed: ['btn'] })
  })
})

describe('renderDelta', () => {
  it('lists every kind of change under the two tags it compares', () => {
    const older = release({ mask: frontmatter('mask', 'mask-square'), otp: example('<div class="$$otp"></div>') })
    const newer = release({ mask: frontmatter('mask'), otp: example('<label class="$$otp"></label>'), aura: frontmatter('aura') })

    assert.equal(renderDelta(older, newer, 'v1', 'v2'), [
      'DaisyUI documentation, v1 to v2',
      '',
      'Components: added aura',
      'Classes of mask: removed mask-square',
      'Elements of otp (otp): DIV became LABEL',
    ].join('\n'))
  })

  it('says so when nothing structural changed', () => {
    const page = release({ btn: frontmatter('btn') })

    assert.match(renderDelta(page, page, 'v1', 'v1'), /No class, element, component or join-item change found\.$/)
  })
})

describe('classesJoinedWith', () => {
  it('collects a class that shares an element with join-item', () => {
    const classes = classesJoinedWith('<div class="$$join"><button class="$$btn $$join-item">A</button></div>')

    assert.deepEqual([...classes], ['btn'])
  })

  it('does not collect join-item itself', () => {
    const classes = classesJoinedWith('<button class="$$btn $$join-item">A</button>')

    assert.equal(classes.has('join-item'), false)
  })

  it('does not collect a class on a sibling element', () => {
    // The rule is about ONE element. A join whose children are a button and a select does not
    // make `select` a companion of `btn`, and a rule reading the whole example block would say
    // it did — the defect `documented-classes.ts` exists to prevent.
    const classes = classesJoinedWith(
      '<div class="$$join"><button class="$$btn $$join-item">A</button><select class="$$select"></select></div>',
    )

    assert.deepEqual([...classes], ['btn'])
  })

  it('does not collect a class a Tailwind variant applies conditionally', () => {
    // `lg:$$btn` is the button class at one breakpoint and not at others, while the generated
    // `daisyButton` always emits it. A member built from that element would mark an element
    // DaisyUI does not document as a join item below `lg`.
    const classes = classesJoinedWith('<button class="lg:$$btn $$join-item">A</button>')

    assert.deepEqual([...classes], [])
  })

  it('ignores an element that does not carry join-item at all', () => {
    const classes = classesJoinedWith('<button class="$$btn">A</button>')

    assert.deepEqual([...classes], [])
  })

  it('collects every companion when one element carries two component classes', () => {
    // Measured shape, not invented: DaisyUI documents
    // `<input class="$$btn $$theme-controller $$join-item">`, which is why the derived set has
    // seven members and only 72 sightings.
    const classes = classesJoinedWith('<input class="$$btn $$theme-controller $$join-item" />')

    assert.deepEqual([...classes].sort(), ['btn', 'theme-controller'])
  })
})
