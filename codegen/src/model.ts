/**
 * The declared component model: one committed file per DaisyUI component, stating every
 * structural fact the generators emit. `shapeFromModel` turns one file into the `ComponentShape`
 * both emitters render, so the generated API changes only by an edit to a model file.
 *
 * From DaisyUI the model needs only the frontmatter's descriptions: the component's own sentence
 * and one per class. Everything structural — elements, receivers, parameters, names — is in the
 * file.
 */

import {
  functionParameters,
  htmlTagNameFor,
  tagBuilderFor,
  type ComponentShape,
  type CssClass,
  type EnumShape,
  type FunctionKind,
  type FunctionShape,
  type ParameterShape,
  type TagClass,
} from './component-shape.ts'
import { toPascalCase } from './classifier.ts'

/** One enum the component declares, with its classes in declaration order. */
export interface ModelEnum {
  readonly name: string
  /** The class-level doc comment's label, e.g. `Size variants`. */
  readonly label: string
  readonly classes: readonly string[]
}

/** An enum-typed parameter: one axis of one function. */
export interface ModelEnumParameter {
  readonly kind: 'enum'
  readonly name: string
  readonly enum: string
  readonly doc: string
}

/** A class-bearing boolean: one on/off feature, named after its class. */
export interface ModelToggleParameter {
  readonly kind: 'toggle'
  readonly name: string
  readonly class: string
}

export type ModelParameter = ModelEnumParameter | ModelToggleParameter

export interface ModelFunction {
  readonly kind: FunctionKind
  readonly name: string
  readonly receiver: string
  readonly element: string
  /** The class this function always writes, or null for a structural wrapper. */
  readonly class: string | null
  /** Whether it takes a `text` shortcut for inline content. */
  readonly text: boolean
  /** The function's own parameters, between `id` and the escape hatches. */
  readonly parameters: readonly ModelParameter[]
}

export interface ComponentModel {
  /** DaisyUI's directory name, e.g. `file-input`. */
  readonly component: string
  /** The stem functions and enums are named with, e.g. `FileInput`. */
  readonly name: string
  /** The component's own class, e.g. `file-input`. */
  readonly class: string | null
  readonly enums: readonly ModelEnum[]
  readonly functions: readonly ModelFunction[]
}

/** What the frontmatter supplies: the component's sentence and each class's, by stripped name. */
export interface Descriptions {
  readonly desc: string
  readonly classes: Readonly<Record<string, string>>
}

function stripped(prefix: string | null, cls: string): string {
  return prefix !== null && cls.startsWith(`${prefix}-`) ? cls.slice(prefix.length + 1) : cls
}

function enumShape(model: ComponentModel, declared: ModelEnum, descriptions: Descriptions): EnumShape {
  return {
    name: declared.name,
    prefix: model.class ?? '',
    categoryLabel: declared.label,
    documented: Object.keys(descriptions.classes).length > 0,
    entries: declared.classes.map(cls => ({
      name: toPascalCase(stripped(model.class, cls)),
      cssClass: cls as CssClass,
      desc: descriptions.classes[stripped(model.class, cls)] ?? null,
    })),
  }
}

function toggleShape(model: ComponentModel, toggle: ModelToggleParameter, descriptions: Descriptions): ParameterShape {
  return {
    name: toggle.name,
    type: 'Boolean',
    default: 'false',
    doc: descriptions.classes[stripped(model.class, toggle.class)] ?? null,
    cssClass: toggle.class as CssClass,
  }
}

function parameterShape(model: ComponentModel, parameter: ModelParameter, descriptions: Descriptions): ParameterShape {
  if (parameter.kind === 'toggle') return toggleShape(model, parameter, descriptions)
  return {
    name: parameter.name,
    type: `ClassValues<${parameter.enum}>?`,
    default: 'null',
    doc: parameter.doc,
    enumName: parameter.enum,
  }
}

function functionShape(model: ComponentModel, declared: ModelFunction, descriptions: Descriptions): FunctionShape {
  const element = declared.element as TagClass
  return {
    kind: declared.kind,
    name: declared.name,
    receiver: declared.receiver,
    element,
    tagBuilder: tagBuilderFor(element),
    htmlTag: htmlTagNameFor(element),
    cssClass: declared.class as CssClass | null,
    modifierClasses: [],
    staticAttributes: [],
    desc: descriptions.desc,
    parameters: functionParameters(element, declared.text, declared.parameters.map(parameter => parameterShape(model, parameter, descriptions))),
  }
}

/** The shape both emitters render, built from one model file and the frontmatter's descriptions. */
export function shapeFromModel(model: ComponentModel, descriptions: Descriptions): ComponentShape {
  return {
    componentName: model.name,
    componentDir: model.component,
    prefix: model.class as CssClass | null,
    enums: model.enums.map(declared => enumShape(model, declared, descriptions)),
    functions: model.functions.map(declared => functionShape(model, declared, descriptions)),
  }
}
