/**
 * The model accounts for every class DaisyUI declares, and for nothing else.
 *
 * A set comparison per component, between the frontmatter's classes and the classes the model
 * places or lists as not offered. It replaces three guards that each caught one way a class could
 * go missing: the unread-configuration check, the offered-twice check and the measured-group
 * completeness check.
 */

import type { ComponentModel } from './model.ts'
import { JOIN_ITEM_CLASS } from './join-scope.ts'

/** Every class the model puts on the page, or says on purpose that it does not. */
export function placedClasses(model: ComponentModel): Set<string> {
  return new Set([
    ...model.functions.flatMap(fn => [fn.class, ...(fn.always ?? [])]).filter((cls): cls is string => cls !== null),
    ...model.enums.flatMap(declared => declared.classes),
    ...model.functions.flatMap(fn => fn.parameters).flatMap(parameter => {
      if (parameter.kind === 'toggle') return [parameter.class]
      if (parameter.kind === 'extra') return [...(parameter.writes ?? [])]
      return []
    }),
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
  ]
}
