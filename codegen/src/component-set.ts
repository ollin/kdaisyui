/**
 * Which components get generated, and their shapes.
 *
 * Three entry points need this and each had its own copy of it, with a comment asking the next
 * person to keep them identical. Comments do not keep anything identical: the set must be the
 * same everywhere or the outputs disagree — a reference page for a component with no Kotlin
 * file links at nothing, and an API baseline covering a different set reports changes nobody
 * made.
 *
 * So the skip conditions and the element resolution live here, once.
 */

import {
  getAllComponentDirs,
  readComponentFrontmatter,
  type ComponentName,
} from './parser/frontmatter.ts'
import { parseLlmsTxt, getElementForComponent } from './parser/llms-txt.ts'
import { classifyFromFrontmatter, type ClassifiedComponent } from './classifier.ts'
import { buildComponentShape, type ComponentShape } from './component-shape.ts'

/** Why a component produces no output. Reported rather than swallowed, so a caller can log it. */
export type SkipReason = 'configured-skip' | 'no-frontmatter' | 'no-component-class'

export interface GeneratedComponent {
  readonly componentDir: ComponentName
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
 * The element the component renders: a `componentElements` override, else the heuristic over
 * DaisyUI's syntax blocks.
 *
 * The heuristic takes the first variant DaisyUI documents, and when that variant only works with
 * attributes the generator cannot emit, the result compiles but does not function — which is why
 * the override exists and why `checkComponentApi`'s sibling cross-check compares this against
 * DaisyUI's own documentation.
 */
function elementFor(componentDir: ComponentName, config, elementRules): string | undefined {
  return config.componentElements?.[componentDir] ?? getElementForComponent(elementRules, componentDir)
}

function classify(componentDir: ComponentName, config, elementRules): GeneratedComponent | SkipReason {
  if (config.skip?.includes(componentDir)) return 'configured-skip'

  const frontmatter = readComponentFrontmatter(componentDir)
  if (!frontmatter) return 'no-frontmatter'
  if (!frontmatter.classnames?.component?.length) return 'no-component-class'

  const classified = classifyFromFrontmatter(frontmatter, componentDir)
  const element = elementFor(componentDir, config, elementRules)
  return {
    componentDir,
    classified,
    frontmatter,
    shape: buildComponentShape(classified, { componentDir, element }, config),
  }
}

/** Every component that produces output, and every one that does not, with the reason. */
export function readComponentSet(config): ComponentSet {
  const elementRules = parseLlmsTxt()
  const generated: GeneratedComponent[] = []
  const skipped: SkippedComponent[] = []

  for (const componentDir of getAllComponentDirs() as ComponentName[]) {
    const result = classify(componentDir, config, elementRules)
    if (typeof result === 'string') skipped.push({ componentDir, reason: result })
    else generated.push(result)
  }

  return { generated, skipped }
}
