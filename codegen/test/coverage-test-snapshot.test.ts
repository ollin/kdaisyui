import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import path from 'path'

import os from 'os'

import { readComponentSet } from '../src/component-set.ts'
import { mainFunctionConfig } from '../src/model.ts'
import { repositoryRootAbove } from '../src/submodules.ts'
import { coverageTestFor, writeCoverageTest } from '../src/test-generator.ts'

/**
 * The coverage half of the test generator, against its committed output.
 *
 * Both sides are committed: the component sources it reads and the coverage tests it wrote, which
 * the drift job keeps in step. So this needs neither the DaisyUI submodule nor a regeneration, and
 * any change to the generated Kotlin of any component fails here, on the component that moved.
 */
// From the repository root, so it also resolves inside StrykerJS's sandbox copy of codegen/.
const GENERATED = path.join(repositoryRootAbove(import.meta.dirname), 'lib/generated')
const COMPONENTS = path.join(GENERATED, 'main/kotlin/io/github/ollin/kdaisyui/components')
const COVERAGE_TESTS = path.join(GENERATED, 'test/kotlin/io/github/ollin/kdaisyui/components')

const componentFiles = fs.readdirSync(COMPONENTS).filter(file => file.endsWith('.kt')).sort()
const components = new Map(readComponentSet().generated.map(component => [component.classified.componentName as string, component]))
/** What `coverageTestFor` reads of a component besides its name: its shape and main config. */
const inputsOf = (className: string) => {
  const component = components.get(className)!
  return [component.shape, mainFunctionConfig(component.model)] as const
}
const KBD = inputsOf('Kbd')

describe('coverageTestFor reproduces the committed coverage test', () => {
  test('of every committed component, all 66', () => {
    assert.equal(componentFiles.length, 66)
  })

  for (const file of componentFiles) {
    const className = file.replace(/\.kt$/, '')
    test(className, () => {
      const source = fs.readFileSync(path.join(COMPONENTS, file), 'utf8')
      const committed = fs.readFileSync(path.join(COVERAGE_TESTS, `${className}CoverageTest.kt`), 'utf8')

      assert.equal(coverageTestFor(className, source, ...inputsOf(className)), committed)
    })
  }
})

describe('coverageTestFor', () => {
  test('gives nothing for a source that declares no function', () => {
    assert.equal(coverageTestFor('Empty', 'package io.github.ollin.kdaisyui.components\n', ...KBD), null)
  })
})

describe('writeCoverageTest', () => {
  const temporaryDirs = () => {
    const root = fs.mkdtempSync(path.join(os.tmpdir(), 'coverage-writer-'))
    for (const dir of ['components', 'tests']) fs.mkdirSync(path.join(root, dir))
    return { root, components: path.join(root, 'components'), tests: path.join(root, 'tests') }
  }

  test('writes <Class>CoverageTest.kt with what coverageTestFor returns for the file', () => {
    const dirs = temporaryDirs()
    const source = fs.readFileSync(path.join(COMPONENTS, 'Kbd.kt'), 'utf8')
    fs.writeFileSync(path.join(dirs.components, 'Kbd.kt'), source)

    assert.deepEqual(writeCoverageTest(dirs.components, dirs.tests, 'Kbd.kt', ...KBD), { success: true })
    assert.equal(fs.readFileSync(path.join(dirs.tests, 'KbdCoverageTest.kt'), 'utf8'), coverageTestFor('Kbd', source, ...KBD))
    fs.rmSync(dirs.root, { recursive: true })
  })

  test('writes nothing for a source without a function, and says why', () => {
    const dirs = temporaryDirs()
    fs.writeFileSync(path.join(dirs.components, 'Empty.kt'), 'package io.github.ollin.kdaisyui.components\n')

    assert.deepEqual(writeCoverageTest(dirs.components, dirs.tests, 'Empty.kt', ...KBD), { success: false, error: 'No functions' })
    assert.deepEqual(fs.readdirSync(dirs.tests), [])
    fs.rmSync(dirs.root, { recursive: true })
  })
})
