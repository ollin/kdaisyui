import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import path from 'path'

import { coverageTestFor } from '../src/test-generator.ts'

/**
 * The coverage half of the test generator, against its committed output.
 *
 * Both sides are committed: the component sources it reads and the coverage tests it wrote, which
 * the drift job keeps in step. So this needs neither the DaisyUI submodule nor a regeneration, and
 * any change to the generated Kotlin of any component fails here, on the component that moved.
 */
const GENERATED = path.resolve(import.meta.dirname, '../../lib/generated')
const COMPONENTS = path.join(GENERATED, 'main/kotlin/io/github/ollin/kdaisyui/components')
const COVERAGE_TESTS = path.join(GENERATED, 'test/kotlin/io/github/ollin/kdaisyui/components')

const componentFiles = fs.readdirSync(COMPONENTS).filter(file => file.endsWith('.kt')).sort()

describe('coverageTestFor reproduces the committed coverage test', () => {
  test('of every committed component, all 66', () => {
    assert.equal(componentFiles.length, 66)
  })

  for (const file of componentFiles) {
    const className = file.replace(/\.kt$/, '')
    test(className, () => {
      const source = fs.readFileSync(path.join(COMPONENTS, file), 'utf8')
      const committed = fs.readFileSync(path.join(COVERAGE_TESTS, `${className}CoverageTest.kt`), 'utf8')

      assert.equal(coverageTestFor(className, source), committed)
    })
  }
})

describe('coverageTestFor', () => {
  test('gives nothing for a source that declares no function', () => {
    assert.equal(coverageTestFor('Empty', 'package io.github.ollin.kdaisyui.components\n'), null)
  })
})
