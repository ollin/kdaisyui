import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import path from 'path'

import { readInferredComponentSet } from '../src/component-set.ts'
import { readComponentConfig } from '../src/component-shape.ts'
import { isSkipped, mainFunctionConfig, readModel, shapeFromModel, withDeclaredScopes, type ComponentModel } from '../src/model.ts'

/**
 * The proof that the committed model carries every fact today's inference produces
 * (`declare-the-component-model`, task 1.4). Reads the submodule, so it lives beside the other
 * integration tests. Deleted by task 3.3, when the inferred side it compares against is gone.
 */
const config = JSON.parse(fs.readFileSync(path.resolve(import.meta.dirname, '../codegen-config.json'), 'utf8'))
const { generated, skipped } = readInferredComponentSet(config)
const models = generated.map(component => readModel(component.componentDir) as ComponentModel)

describe('the committed model against the inferred shapes', () => {
  it('reproduces every component shape, join scope included', () => {
    const declared = generated.map((component, index) => ({
      model: models[index],
      shape: shapeFromModel(models[index], { desc: component.classified.desc ?? '', classes: component.classified.descs ?? {} }),
    }))

    withDeclaredScopes(declared).forEach((shape, index) => assert.deepEqual(shape, generated[index].shape, generated[index].componentDir))
  })

  it("reproduces what each main function's body reads from the configuration", () => {
    generated.forEach((component, index) => {
      const { extras, hasTextParam, role, inputType } = readComponentConfig(config, component.shape.componentName)
      assert.deepEqual(mainFunctionConfig(models[index]), { extras, hasTextParam, role, inputType }, component.componentDir)
    })
  })

  it('carries every editorial summary', () => {
    generated.forEach((component, index) => assert.deepEqual(models[index].summary, config.docSummaries?.[component.componentDir], component.componentDir))
  })

  it('marks every skipped directory as skipped, with the same reason', () => {
    for (const component of skipped) {
      const model = readModel(component.componentDir)
      assert.ok(isSkipped(model) && model.skip === component.reason, component.componentDir)
    }
  })
})
