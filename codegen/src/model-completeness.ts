/**
 * The model accounts for every class DaisyUI declares, and for nothing else.
 *
 * A set comparison per component, between the frontmatter's classes and the classes the model
 * places or lists as not offered. It replaces three guards that each caught one way a class could
 * go missing: the unread-configuration check, the offered-twice check and the measured-group
 * completeness check.
 */

import type { ComponentModel, ModelEnumParameter, ModelExtraParameter, ModelFunction, ModelParameter } from './model.ts'
import { JOIN_ITEM_CLASS } from './join-scope.ts'
import { toPascalCase } from './classifier.ts'

/** Every class one function writes or offers, once per way it can reach the page. */
export function offeredBy(model: ComponentModel, fn: ModelFunction): string[] {
  return [
    ...[fn.class, ...(fn.always ?? [])].filter((cls): cls is string => cls !== null),
    ...fn.parameters.flatMap(parameter => writtenBy(model, parameter)),
  ]
}

/** The classes one parameter can write: its axis's members, its toggle's class, or its extra's. */
export function writtenBy(model: ComponentModel, parameter: ModelParameter): string[] {
  // Every enum parameter names a declared enum; committed-model.test.ts checks all of them.
  if (parameter.kind === 'enum') return [...model.enums.find(declared => declared.name === parameter.enum)!.classes]
  if (parameter.kind === 'toggle') return [parameter.class]
  return [...(parameter.writes ?? [])]
}

/** Every class the model can put on the page: what a consumer's Tailwind must compile. */
export function offeredClasses(model: ComponentModel): string[] {
  const declined = new Set(Object.keys(model.notOffered ?? {}))
  return [...placedClasses(model)].filter(cls => !declined.has(cls))
}

/** Every class the model puts on the page, or says on purpose that it does not. */
export function placedClasses(model: ComponentModel): Set<string> {
  return new Set([
    ...model.functions.flatMap(fn => offeredBy(model, fn)),
    ...model.enums.flatMap(declared => declared.classes),
    ...(model.joinScope ? [JOIN_ITEM_CLASS] : []),
    ...Object.keys(model.notOffered ?? {}),
  ])
}

/** What is wrong between one component's model and the classes its frontmatter declares. */
export function classFindings(model: ComponentModel, declared: readonly string[]): string[] {
  const placed = placedClasses(model)
  const documented = new Set(declared)
  return [
    ...declared.filter(cls => !placed.has(cls)).map(cls => `${model.component}: DaisyUI declares ${cls}, and the model places it nowhere`),
    ...[...placed].filter(cls => !documented.has(cls)).map(cls => `${model.component}: the model places ${cls}, which DaisyUI does not declare`),
    ...model.functions.flatMap(fn => offeredBy(model, fn).filter((cls, index, all) => all.indexOf(cls) !== index).map(cls => `${model.component}: ${fn.name} offers ${cls} twice`)),
  ]
}

/** DaisyUI directories with no model file, and model files naming no DaisyUI directory. */
export function directoryFindings(componentDirs: readonly string[], modelFiles: readonly string[]): string[] {
  return [
    ...componentDirs.filter(dir => !modelFiles.includes(dir)).map(dir => `${dir}: DaisyUI documents it, and it has no model file`),
    ...modelFiles.filter(file => !componentDirs.includes(file)).map(file => `${file}: a model file for a directory DaisyUI does not have`),
  ]
}

/** Main functions, parts and their enums named otherwise than the naming rule says. */
export function namingFindings(model: ComponentModel): string[] {
  return model.functions
    .filter(fn => fn.kind !== 'custom' && fn.class !== null)
    .flatMap(fn => {
      // The filter above let through only functions with a class.
      const stem = fn.stem ?? toPascalCase(fn.class!)
      const enums = fn.parameters.filter((parameter): parameter is ModelEnumParameter => parameter.kind === 'enum')
        .map(parameter => ({ name: parameter.enum, expected: `${stem}${toPascalCase(parameter.name)}` }))
      return [{ name: fn.name, expected: `daisy${stem}` }, ...enums]
    })
    .filter(({ name, expected }) => name !== expected)
    .map(({ name, expected }) => `${model.component}: ${name} should be called ${expected}`)
}

/**
 * A nullable extra the generated coverage tests cannot drive on their own: neither `String`,
 * `Boolean` nor one of the component's enums, bare or in `ClassValues<…>`, and with no `sample`.
 */
export function extraSampleFindings(model: ComponentModel): string[] {
  const enums = new Set(model.enums.map(declared => declared.name))
  return model.functions.flatMap(fn => fn.parameters
    .filter((parameter): parameter is ModelExtraParameter => parameter.kind === 'extra' && parameter.type.endsWith('?') && parameter.sample === undefined)
    .filter(parameter => !drivenWithoutSample(parameter.type.slice(0, -1), enums))
    .map(parameter => `${model.component}: ${fn.name}.${parameter.name} needs a sample`))
}

function drivenWithoutSample(type: string, enums: ReadonlySet<string>): boolean {
  return type === 'String' || type === 'Boolean' || enums.has(type.match(/ClassValues<(\w+)>/)?.[1] ?? type)
}
