/**
 * The model accounts for every class DaisyUI declares, and for nothing else.
 *
 * A set comparison per component, between the frontmatter's classes and the classes the model
 * places or lists as not offered. It replaces three guards that each caught one way a class could
 * go missing: the unread-configuration check, the offered-twice check and the measured-group
 * completeness check.
 */

import type { ComponentModel, ModelFunction } from './model.ts'
import { JOIN_ITEM_CLASS } from './join-scope.ts'

/** Every class one function writes or offers, once per way it can reach the page. */
export function offeredBy(model: ComponentModel, fn: ModelFunction): string[] {
  const enums = new Map(model.enums.map(declared => [declared.name, declared.classes]))
  return [
    ...[fn.class, ...(fn.always ?? [])].filter((cls): cls is string => cls !== null),
    ...fn.parameters.flatMap(parameter => {
      if (parameter.kind === 'enum') return [...(enums.get(parameter.enum) ?? [])]
      if (parameter.kind === 'toggle') return [parameter.class]
      return [...(parameter.writes ?? [])]
    }),
  ]
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
