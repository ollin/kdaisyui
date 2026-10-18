/**
 * The declared component model: one committed file per DaisyUI component, stating every
 * structural fact the generators emit. `shapeFromModel` turns one file into the `ComponentShape`
 * both emitters render, so the generated API changes only by an edit to a model file.
 *
 * From DaisyUI the model needs only the frontmatter's descriptions: the component's own sentence
 * and one per class. Everything structural — elements, receivers, parameters, names — is in the
 * file.
 */

import fs from 'fs'
import path from 'path'
import {
  functionParameters,
  type ExtraParameter,
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
import { joinScopeMembers, withJoinScope } from './join-scope.ts'
import type { DocSummary } from './generator-docs.ts'

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

/** A hand-written parameter: its Kotlin type, default and the fragment that applies it. */
export interface ModelExtraParameter extends ExtraParameter {
  readonly kind: 'extra'
  /** The classes its fragment writes, e.g. `btn-disabled`, so the class counts as placed. */
  readonly writes?: readonly string[]
}

export type ModelParameter = ModelEnumParameter | ModelToggleParameter | ModelExtraParameter

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
  /** Classes written beside `class` on every call, e.g. `skeleton-text`. */
  readonly always?: readonly string[]
  /** Attributes written on every call, e.g. `{ "popover": "" }`. */
  readonly attributes?: Readonly<Record<string, string>>
  /** On a main function: inside a join's content, this call marks itself `join-item`. */
  readonly joinItem?: true
  /** On a main function: the ARIA role it always sets, e.g. `alert`. */
  readonly role?: string
  /** On a main function rendering `<input>`: the `InputType` it always sets, e.g. `checkBox`. */
  readonly inputType?: string
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
  /** The main function's content runs in the join scope, whose members are the join items. */
  readonly joinScope?: true
  /** The hand-written line for the reference index, and the optional longer opening. */
  readonly summary?: DocSummary
  /** Classes DaisyUI declares that no function offers, each with the reason. */
  readonly notOffered?: Readonly<Record<string, string>>
}

/** A DaisyUI directory the library deliberately generates nothing for, and why. */
export interface SkippedModel {
  readonly component: string
  readonly skip: string
}

/** Where the committed model lives: one file per DaisyUI directory. */
export const MODEL_DIR = path.resolve(import.meta.dirname, '../model')

export function readModel(componentDir: string, modelDir = MODEL_DIR): ComponentModel | SkippedModel {
  return JSON.parse(fs.readFileSync(path.join(modelDir, `${componentDir}.json`), 'utf8'))
}

/** The DaisyUI directory each committed model file names. */
export function modelFileNames(modelDir = MODEL_DIR): string[] {
  return fs.readdirSync(modelDir).filter(file => file.endsWith('.json')).map(file => file.slice(0, -'.json'.length)).sort()
}

export function isSkipped(model: ComponentModel | SkippedModel): model is SkippedModel {
  return 'skip' in model
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
  if (parameter.kind === 'extra') return { name: parameter.name, type: parameter.type, default: parameter.default, doc: null }
  return {
    name: parameter.name,
    type: `ClassValues<${parameter.enum}>?`,
    default: 'null',
    doc: parameter.doc,
    enumName: parameter.enum,
  }
}

/**
 * A function's prose sentence. The main function's is the component's; a part's is looked up by
 * its unstripped class, which matches only where a part carries no component prefix; a
 * construction method has none. Faithful to the inferred shape, so the switch changes nothing.
 */
function functionDesc(declared: ModelFunction, descriptions: Descriptions): string {
  if (declared.kind === 'main') return descriptions.desc
  if (declared.kind === 'part' && declared.class !== null) return descriptions.classes[declared.class] ?? ''
  return ''
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
    modifierClasses: (declared.always ?? []) as CssClass[],
    staticAttributes: Object.entries(declared.attributes ?? {}),
    desc: functionDesc(declared, descriptions),
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

/** What the main function's body needs beyond its shape: the extras' fragments, text, role, input type. */
export interface MainFunctionConfig {
  readonly extras: readonly ExtraParameter[]
  readonly hasTextParam: boolean
  readonly role: string | null
  readonly inputType: string | null
}

export function mainFunctionConfig(model: ComponentModel): MainFunctionConfig {
  const main = model.functions.find(fn => fn.kind === 'main')
  return {
    extras: (main?.parameters ?? []).flatMap(parameter => {
      if (parameter.kind !== 'extra') return []
      const { kind, writes, ...extra } = parameter
      return [extra]
    }),
    hasTextParam: main?.text ?? false,
    role: main?.role ?? null,
    inputType: main?.inputType ?? null,
  }
}

/** One component as both the model declares it and the shape built from it. */
export interface DeclaredComponent {
  readonly model: ComponentModel
  readonly shape: ComponentShape
}

function isJoinItem(model: ComponentModel): boolean {
  return model.functions.some(fn => fn.kind === 'main' && fn.joinItem === true)
}

/**
 * The join scope, attached across components: its members are the main functions the models
 * declare as join items, and it imports whatever their extra parameters' types need.
 */
export function withDeclaredScopes(components: readonly DeclaredComponent[]): ComponentShape[] {
  const items = components.filter(component => isJoinItem(component.model))
  const members = joinScopeMembers(items.map(component => component.shape), new Set(items.map(component => component.model.class ?? '')))
  const imports = items.flatMap(component => component.model.functions.filter(fn => fn.kind === 'main'))
    .flatMap(fn => fn.parameters)
    .flatMap(parameter => (parameter.kind === 'extra' ? parameter.imports ?? [] : []))
  return components.map(component => (component.model.joinScope ? withJoinScope(component.shape, members, imports) : component.shape))
}
