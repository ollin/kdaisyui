import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import path from 'path'

import { readComponentSet } from '../src/component-set.ts'
import { docFileNameFor, generateComponentPage, generateIndexPage, type DocSummary } from '../src/generator-docs.ts'
import { generateLlmsFull } from '../src/generator-llms.ts'
import { documentationPages, generateLlmsIndex } from '../src/llms-index.ts'
import { repositoryRootAbove } from '../src/submodules.ts'

/**
 * The reference generator against what it committed: every page under `docs/reference/`, their
 * index, and both llms files, from the committed models and the pinned DaisyUI pages. Any change
 * to any of them fails here, naming the file that moved.
 */
const ROOT = repositoryRootAbove(import.meta.dirname)
const committed = (file: string) => fs.readFileSync(path.join(ROOT, file), 'utf8')

const components = readComponentSet().generated
const shapes = components.map(component => component.shape)
const summaries: Record<string, DocSummary> = Object.fromEntries(
  components.flatMap(({ componentDir, model }) => (model.summary === undefined ? [] : [[componentDir, model.summary]])),
)

describe('the reference generator reproduces every committed page', () => {
  test('all 66 of them', () => {
    assert.equal(components.length, 66)
  })

  for (const { componentDir, shape } of components) {
    test(componentDir, () => {
      assert.equal(generateComponentPage(shape, summaries[componentDir]), committed(`docs/reference/${docFileNameFor(componentDir)}.md`))
    })
  }

  test('the index', () => {
    assert.equal(generateIndexPage(shapes, summaries), committed('docs/reference/index.md'))
  })
})

describe('the llms generators reproduce the committed files', () => {
  test('llms-full.txt', () => {
    assert.equal(generateLlmsFull(committed('codegen/llms-full-template.md'), shapes, summaries), committed('llms-full.txt'))
  })

  test('llms.txt', () => {
    const repository = { exists: (file: string) => fs.existsSync(path.join(ROOT, file)), pages: documentationPages(ROOT) }
    assert.equal(generateLlmsIndex(committed('codegen/llms-template.md'), shapes, summaries, repository), committed('llms.txt'))
  })
})
