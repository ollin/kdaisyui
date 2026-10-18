/**
 * Builders for hand-written component models, so an emitter test states the component it renders
 * the same way `codegen/model/` does. Not a test file: the runner's patterns do not match it.
 */

import { shapeFromModel, type ComponentModel, type Descriptions, type ModelFunction, type ModelParameter } from '../src/model.ts'
import type { ComponentShape } from '../src/component-shape.ts'

/** A main function rendering `<div>` with no parameters, unless told otherwise. */
export function mainFunction(name: string, cssClass: string, overrides: Partial<ModelFunction> = {}): ModelFunction {
  return { kind: 'main', name, receiver: 'FlowContent', element: 'DIV', class: cssClass, text: false, parameters: [], ...overrides }
}

/** A part rendering `<div>` with no parameters, unless told otherwise. */
export function part(name: string, cssClass: string, overrides: Partial<ModelFunction> = {}): ModelFunction {
  return { ...mainFunction(name, cssClass, overrides), kind: 'part' }
}

/** A component with one main function named after it, plus whatever else is given. */
export function component(dir: string, stem: string, cssClass: string, overrides: Partial<ComponentModel> = {}, main: Partial<ModelFunction> = {}): ComponentModel {
  return { component: dir, name: stem, class: cssClass, enums: [], functions: [mainFunction(`daisy${stem}`, cssClass, main)], ...overrides }
}

export function enumParameter(name: string, enumName: string, doc: string): ModelParameter {
  return { kind: 'enum', name, enum: enumName, doc }
}

export function toggle(name: string, cssClass: string): ModelParameter {
  return { kind: 'toggle', name, class: cssClass }
}

export function shapeOf(model: ComponentModel, descriptions: Descriptions = { desc: '', classes: {} }): ComponentShape {
  return shapeFromModel(model, descriptions)
}
