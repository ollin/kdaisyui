import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import os from 'os'
import path from 'path'

import { readComponentSet } from '../src/component-set.ts'
import { mainFunctionConfig } from '../src/model.ts'
import { repositoryRootAbove } from '../src/submodules.ts'
import { coverageTestFor, writeCoverageTest } from '../src/test-generator.ts'

/**
 * The coverage half of the test generator, against its committed output: from each component's
 * shape and main config, as the other generators read them, to the committed coverage test. Any
 * change to what a coverage test asserts fails here, on the component that moved.
 */
// From the repository root, so it also resolves inside StrykerJS's sandbox copy of codegen/.
const GENERATED = path.join(repositoryRootAbove(import.meta.dirname), 'lib/generated')
const COVERAGE_TESTS = path.join(GENERATED, 'test/kotlin/io/github/ollin/kdaisyui/components')

const components = new Map(readComponentSet().generated.map(component => [component.classified.componentName as string, component]))
/** What `coverageTestFor` reads of a component besides its name: its shape and main config. */
const inputsOf = (className: string) => {
  const component = components.get(className)!
  return [component.shape, mainFunctionConfig(component.model)] as const
}

describe('coverageTestFor reproduces the committed coverage test', () => {
  test('of every committed component, all 66', () => {
    assert.equal(components.size, 66)
  })

  for (const className of [...components.keys()].sort()) {
    test(className, () => {
      const committed = fs.readFileSync(path.join(COVERAGE_TESTS, `${className}CoverageTest.kt`), 'utf8')

      assert.equal(coverageTestFor(className, ...inputsOf(className)), committed)
    })
  }
})

describe('writeCoverageTest', () => {
  test('writes <Class>CoverageTest.kt with what coverageTestFor returns', () => {
    const dir = fs.mkdtempSync(path.join(os.tmpdir(), 'coverage-writer-'))

    writeCoverageTest(dir, 'Kbd', ...inputsOf('Kbd'))
    assert.equal(fs.readFileSync(path.join(dir, 'KbdCoverageTest.kt'), 'utf8'), coverageTestFor('Kbd', ...inputsOf('Kbd')))
    fs.rmSync(dir, { recursive: true })
  })
})
