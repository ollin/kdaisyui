import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import path from 'path'

import { isSkipped, readModel } from '../src/model.ts'
import { getAllComponentDirs, readComponentPage } from '../src/parser/frontmatter.ts'
import { repositoryRootAbove } from '../src/submodules.ts'
import { documentedContext, documentedTestFor, toClassName } from '../src/test-generator.ts'

/**
 * The documented-element half of the test generator, against its committed output: one
 * `<Name>Test.kt` per DaisyUI page with examples, from the pinned page, the committed models and
 * every page's classes. Any change to any of them fails here, naming the page that moved.
 */
const TESTS = path.join(repositoryRootAbove(import.meta.dirname), 'lib/generated/test/kotlin/io/github/ollin/kdaisyui/components')
const context = documentedContext()
const pages = getAllComponentDirs()

describe('documentedTestFor reproduces the committed documented-element tests', () => {
  test('of every page: 66 components and two skipped pages', () => {
    assert.equal(pages.length, 68)
  })

  for (const page of pages) {
    test(page, () => {
      const model = readModel(page)
      const generated = documentedTestFor(page, readComponentPage(page) ?? '', isSkipped(model) ? null : model, context)
      const committed = fs.readFileSync(path.join(TESTS, `${toClassName(page)}Test.kt`), 'utf8')

      assert.equal(generated?.kotlin, committed)
    })
  }
})

describe('documentedTestFor', () => {
  test('gives nothing for a component page without examples', () => {
    assert.equal(documentedTestFor('kbd', '---\ntitle: Kbd\n---\nNo examples.\n', readModel('kbd') as never, context), null)
  })
})
