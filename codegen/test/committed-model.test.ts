import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { isSkipped, modelFileNames, readModel, shapeFromModel, type ComponentModel } from '../src/model.ts'

/**
 * Every committed model file reads and builds a shape. Needs no submodule, so it runs in the
 * codegen unit tests: an emptied or half-edited file fails here, before any generator runs.
 */
describe('the committed model', () => {
  it('reads every file and builds a shape from each component that is not skipped', () => {
    const files = modelFileNames()
    const built = files.map(file => readModel(file)).filter(model => !isSkipped(model)).map(model => shapeFromModel(model as ComponentModel, { desc: '', classes: {} }))

    assert.ok(files.length > 60, `${files.length} model files`)
    assert.ok(built.every(shape => shape.functions.length > 0))
  })
})
