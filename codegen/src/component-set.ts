/**
 * Which components get generated, and their shapes.
 *
 * Three entry points need this and each had its own copy of it, with a comment asking the next
 * person to keep them identical. Comments do not keep anything identical: the set must be the
 * same everywhere or the outputs disagree — a reference page for a component with no Kotlin
 * file links at nothing, and an API baseline covering a different set reports changes nobody
 * made.
 *
 * So the set lives here, once, read from the committed model in `codegen/model/`.
 */

import {
  getAllComponentDirs,
  readComponentFrontmatter,
  type ComponentName,
} from './parser/frontmatter.ts'
import { classifyFromFrontmatter, type ClassifiedComponent } from './classifier.ts'
import type { ComponentShape } from './component-shape.ts'
import { isSkipped, readModel, shapeFromModel, withDeclaredScopes, type ComponentModel } from './model.ts'

/** Why a component produces no output. Reported rather than swallowed, so a caller can log it. */
export type SkipReason = 'configured-skip' | 'no-frontmatter' | 'no-component-class'

export interface GeneratedComponent {
  readonly componentDir: ComponentName
  readonly model: ComponentModel
  readonly classified: ClassifiedComponent
  readonly shape: ComponentShape
  /** The raw frontmatter, for the callers that need its class names. */
  readonly frontmatter
}

export interface SkippedComponent {
  readonly componentDir: ComponentName
  readonly reason: SkipReason
}

export interface ComponentSet {
  readonly generated: readonly GeneratedComponent[]
  readonly skipped: readonly SkippedComponent[]
}

/**
 * Every component as the committed model declares it, and every one it skips, with the reason.
 *
 * The frontmatter still supplies the descriptions, which `classified` carries, and the class list
 * some callers need.
 */
export function readComponentSet(): ComponentSet {
  const declared: { model: ComponentModel, component: Omit<GeneratedComponent, 'shape'> }[] = []
  const skipped: SkippedComponent[] = []

  for (const componentDir of getAllComponentDirs() as ComponentName[]) {
    const model = readModel(componentDir)
    if (isSkipped(model)) {
      skipped.push({ componentDir, reason: model.skip as SkipReason })
      continue
    }
    const frontmatter = readComponentFrontmatter(componentDir)
    declared.push({ model, component: { componentDir, model, frontmatter, classified: classifyFromFrontmatter(frontmatter, componentDir) } })
  }

  const shapes = withDeclaredScopes(declared.map(({ model, component }) => ({
    model,
    shape: shapeFromModel(model, { desc: component.classified.desc ?? '', classes: component.classified.descs ?? {} }),
  })))
  return { generated: declared.map(({ component }, index) => ({ ...component, shape: shapes[index] })), skipped }
}
