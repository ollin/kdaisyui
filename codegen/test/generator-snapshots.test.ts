import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import path from 'path'

import { readComponentSet } from '../src/component-set.ts'
import { generateKotlinFile as componentKotlin } from '../src/generator-new.ts'
import { generateKotlinFile as iconKotlin } from '../src/generator-heroicons.ts'
import { mainFunctionConfig } from '../src/model.ts'
import { heroiconDirectories, parseIconFiles } from '../src/parser/svg-heroicons.ts'
import { repositoryRootAbove } from '../src/submodules.ts'
import { heroIconsTestFile } from '../src/test-generator-heroicons.ts'

/**
 * The component emitter, the icon emitter and the icon test generator, against what they
 * committed. Their inputs are the committed models and the pinned DaisyUI and Heroicons
 * submodules, which `codegen-tests` checks out; a missing one fails with the command that fixes
 * it. Any change to any generated component or icon fails here, naming the file that moved.
 */
const GENERATED = path.join(repositoryRootAbove(import.meta.dirname), 'lib/generated')
const PACKAGE = 'kotlin/io/github/ollin/kdaisyui'
const committed = (...parts: string[]) => fs.readFileSync(path.join(GENERATED, ...parts), 'utf8')

describe('the component emitter reproduces every committed component', () => {
  const components = readComponentSet().generated

  test('all 66 of them', () => {
    assert.equal(components.length, 66)
  })

  for (const component of components) {
    const name = component.classified.componentName
    test(name, () => {
      assert.equal(componentKotlin(component.shape, mainFunctionConfig(component.model)), committed('main', PACKAGE, 'components', `${name}.kt`))
    })
  }
})

describe('the icon emitter reproduces every committed icon', () => {
  const icons = parseIconFiles(heroiconDirectories())

  test('all 324 of them', () => {
    assert.equal(icons.size, 324)
  })

  for (const [name, paths] of icons) {
    test(name, () => {
      assert.equal(iconKotlin(name, paths), committed('main', PACKAGE, 'icons', `${name}.kt`))
    })
  }

  test('the icon render tests reproduce the committed HeroIconsGeneratedTest.kt', () => {
    assert.equal(heroIconsTestFile(icons), committed('test', PACKAGE, 'icons', 'HeroIconsGeneratedTest.kt'))
  })
})
