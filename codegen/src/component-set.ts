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
import { buildComponentShape, readComponentConfig, type ComponentShape, type ComponentSource } from './component-shape.ts'
import { classifyGroups, type GroupClassification } from './class-groups.ts'
import { loadEvidence, type Evidence } from './measurement.ts'
import { documentedElementTalliesFor, documentedParentsFor } from './parser/documented-element.ts'
import { declaresJoinItem, documentedJoinItemCompanions, isJoinItemComponent, joinScopeMembers, withJoinScope } from './join-scope.ts'
import { isSkipped, readModel, shapeFromModel, withDeclaredScopes, type ComponentModel } from './model.ts'

/** Why a component produces no output. Reported rather than swallowed, so a caller can log it. */
export type SkipReason = 'configured-skip' | 'no-frontmatter' | 'no-component-class'

export interface GeneratedComponent {
  readonly componentDir: ComponentName
  readonly classified: ClassifiedComponent
  readonly shape: ComponentShape
  /** The raw frontmatter, for the callers that need its class names. */
  readonly frontmatter
}

/** A component as the generator used to infer it, with what it was inferred from. */
export interface InferredComponent extends GeneratedComponent {
  /**
   * What the shape was built FROM, carried so a caller can render without rebuilding it.
   *
   * The Kotlin emitter takes these rather than the finished shape, and an emitter handed the
   * inputs re-derives the shape from them. Returning what this module already computed is what
   * lets the Kotlin run drop its own copy of this loop — the copy `component-set.ts` was
   * created to delete and then did not, because `index-new.ts` still needed values this
   * interface did not expose.
   */
  readonly source: ComponentSource
  readonly groups: GroupClassification
}

export interface SkippedComponent {
  readonly componentDir: ComponentName
  readonly reason: SkipReason
}

export interface ComponentSet {
  readonly generated: readonly GeneratedComponent[]
  readonly skipped: readonly SkippedComponent[]
}

export interface InferredComponentSet {
  readonly generated: readonly InferredComponent[]
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

function classify(
  componentDir: ComponentName,
  config,
  elementRules,
  evidence: Evidence,
): InferredComponent | SkipReason {
  if (config.skip?.includes(componentDir)) return 'configured-skip'

  const frontmatter = readComponentFrontmatter(componentDir)
  if (!frontmatter) return 'no-frontmatter'
  if (!frontmatter.classnames?.component?.length) return 'no-component-class'

  const classified = classifyFromFrontmatter(frontmatter, componentDir)
  const element = elementFor(componentDir, config, elementRules)
  // Which class groups are one choice, decided by the browser rather than by their category.
  // A single choice is named after its category, axes after DaisyUI's property table where it
  // gives them a direction; `enumNames` names only what neither can.
  const groups: GroupClassification = classifyGroups(
    classified,
    componentDir,
    config.enumNames ?? {},
    evidence,
  )
  const source: ComponentSource = {
    componentDir,
    element,
    documentedElements: documentedElementTalliesFor(componentDir),
    documentedParents: documentedParentsFor(componentDir),
  }
  return {
    componentDir,
    classified,
    frontmatter,
    source,
    groups,
    shape: buildComponentShape(classified, source, config, groups),
  }
}

/**
 * The join component, given the scope its content lambda runs in.
 *
 * Here rather than in `buildComponentShape` because the scope's members are OTHER components'
 * functions: a builder that sees one component at a time cannot assemble it. And here rather
 * than in one emitter, because all three read this set — a scope attached in the Kotlin run
 * alone would leave the reference page and the API baseline describing a different signature.
 */
function withScopes(
  generated: readonly InferredComponent[],
  componentDirs: readonly ComponentName[],
  config,
): InferredComponent[] {
  const companions = documentedJoinItemCompanions(componentDirs)
  const members = joinScopeMembers(generated.map(component => component.shape), companions)
  const imports = generated
    .filter(component => isJoinItemComponent(component.shape, companions))
    .flatMap(component => readComponentConfig(config, component.shape.componentName).extras)
    .flatMap(extra => extra.imports ?? [])
  return generated.map(component =>
    declaresJoinItem(component.frontmatter)
      ? { ...component, shape: withJoinScope(component.shape, members, imports) }
      : component,
  )
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
    declared.push({ model, component: { componentDir, frontmatter, classified: classifyFromFrontmatter(frontmatter, componentDir) } })
  }

  const shapes = withDeclaredScopes(declared.map(({ model, component }) => ({
    model,
    shape: shapeFromModel(model, { desc: component.classified.desc ?? '', classes: component.classified.descs ?? {} }),
  })))
  return { generated: declared.map(({ component }, index) => ({ ...component, shape: shapes[index] })), skipped }
}

/**
 * Every component as the generator used to infer it from DaisyUI's documentation.
 *
 * Kept only for the equality test and the one-off export, and deleted with them in task 3.3 of
 * `declare-the-component-model`.
 */
export function readInferredComponentSet(config): InferredComponentSet {
  const elementRules = parseLlmsTxt()
  // Read once for the whole run: it is one file describing every component, and re-reading it
  // per component would make a 66-way loop do 66 times the I/O for the same answer.
  const evidence = loadEvidence()
  const generated: InferredComponent[] = []
  const skipped: SkippedComponent[] = []

  const componentDirs = getAllComponentDirs() as ComponentName[]
  for (const componentDir of componentDirs) {
    const result = classify(componentDir, config, elementRules, evidence)
    if (typeof result === 'string') skipped.push({ componentDir, reason: result })
    else generated.push(result)
  }

  return { generated: withScopes(generated, componentDirs, config), skipped }
}
