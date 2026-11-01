import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import fs from 'fs'
import os from 'os'
import path from 'path'

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

  it('gives every generated component exactly one main function, which mainFunctionConfig relies on', () => {
    const mains = modelFileNames().map(file => readModel(file)).filter(model => !isSkipped(model))
      .map(model => [(model as ComponentModel).component, (model as ComponentModel).functions.filter(fn => fn.kind === 'main').length])

    assert.deepEqual(mains.filter(([, count]) => count !== 1), [])
  })

  it('gives every generated component its own class, which the shape types as always present', () => {
    const classless = modelFileNames().map(file => readModel(file)).filter(model => !isSkipped(model))
      .filter(model => typeof (model as ComponentModel).class !== 'string' || (model as ComponentModel).class === '')

    assert.deepEqual(classless, [])
  })

  it('names a declared enum in every enum parameter, which writtenBy relies on', () => {
    const undeclared = modelFileNames().map(file => readModel(file)).filter(model => !isSkipped(model)).flatMap(model => {
      const declared = new Set((model as ComponentModel).enums.map(e => e.name))
      return (model as ComponentModel).functions.flatMap(fn => fn.parameters).filter(parameter => parameter.kind === 'enum' && !declared.has(parameter.enum))
    })

    assert.deepEqual(undeclared, [])
  })

  it('lists the model files by directory name, sorted, ignoring anything that is not JSON', () => {
    const dir = fs.mkdtempSync(path.join(os.tmpdir(), 'model-dir-'))
    for (const file of ['zeta.json', 'alpha.json', 'notes.md']) fs.writeFileSync(path.join(dir, file), '{}')

    assert.deepEqual(modelFileNames(dir), ['alpha', 'zeta'])
    fs.rmSync(dir, { recursive: true })
  })
})
