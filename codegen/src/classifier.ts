import {
  toPascalCase as toPascalCaseBase,
  getBaseClass,
  getClassesByCategory,
  getEntriesByCategory,
  getDefaultSize,
  type ComponentName,
  type PascalComponentName,
  type Classnames,
  type FrontmatterData,
} from './parser/frontmatter.ts'

/**
 * Kebab-to-Pascal, used here on BOTH component names (`file-input` → `FileInput`) and CSS
 * prefixes (`btn` → `Btn`).
 *
 * The types surfaced that double duty. `frontmatter.toPascalCase` is branded
 * `ComponentName → PascalComponentName`, which is right for its own callers, and a CSS
 * prefix is not a component name. The cast is where that second use is acknowledged rather
 * than hidden: the transformation is generic, the brand describes the caller's intent.
 *
 * Worth leaving as a cast rather than "fixing" by un-branding the base — the brand is
 * earning its keep for `test-generator.ts`, and one documented exception costs less than
 * losing it everywhere.
 */
export function toPascalCase(name: string): PascalComponentName {
  return toPascalCaseBase(name as ComponentName)
}

/**
 * A component reduced to what the Kotlin generator needs.
 *
 * This was a JSDoc `@typedef`, and it had ALREADY GONE STALE: it did not list
 * `componentClass`, which `classifyFromFrontmatter` has been returning all along. That is
 * the argument for this whole port in one field — documentation nobody checks drifts from
 * the code it describes, and nothing says so.
 */
export interface ClassifiedComponent {
  /** PascalCase component name, e.g. `Button`. */
  componentName: PascalComponentName
  /** PascalCase of the CSS prefix, e.g. `Btn`. Missing from the original typedef. */
  componentClass: PascalComponentName
  desc: string
  /** CSS prefix, e.g. `btn`. Null when the component declares no component class. */
  prefix: string | null
  colors: string[]
  styles: string[]
  sizes: string[]
  modifiers: string[]
  behaviors: string[]
  parts: string[]
  directions: string[]
  placements: string[]
  defaultSize: string | null
  /** Stripped class name → its description from the frontmatter. */
  descs: Record<string, string>
}
export function classifyFromFrontmatter(
  frontmatter: FrontmatterData,
  componentName: ComponentName,
): ClassifiedComponent {
  const classnames = frontmatter.classnames
  const prefix = getBaseClass(classnames)
  const componentClass = prefix || componentName

  const descs = buildDescMap(classnames, prefix)

  return {
    componentName: toPascalCase(componentName),
    componentClass: toPascalCase(componentClass),
    desc: frontmatter.desc || '',
    prefix,
    colors: getClassesByCategory(classnames, 'color').map(stripPrefix(prefix)),
    styles: getClassesByCategory(classnames, 'style').map(stripPrefix(prefix)),
    sizes: getClassesByCategory(classnames, 'size').map(stripPrefix(prefix)),
    modifiers: getClassesByCategory(classnames, 'modifier').map(stripPrefix(prefix)),
    behaviors: getClassesByCategory(classnames, 'behavior').map(stripPrefix(prefix)),
    parts: getClassesByCategory(classnames, 'part'),
    directions: getClassesByCategory(classnames, 'direction').map(stripPrefix(prefix)),
    placements: getClassesByCategory(classnames, 'placement').map(stripPrefix(prefix)),
    defaultSize: getDefaultSize(classnames),
    descs,
  }
}

function buildDescMap(classnames: Classnames | undefined, prefix: string | null): Record<string, string> {
  const descs = {}
  const categories = ['component', 'color', 'style', 'size', 'modifier', 'behavior', 'part', 'direction', 'placement']
  for (const cat of categories) {
    const entries = getEntriesByCategory(classnames, cat)
    for (const entry of entries) {
      if (!entry.desc) continue
      const key = entry.class.startsWith(prefix + '-')
        ? entry.class.slice(prefix.length + 1)
        : entry.class
      descs[key] = entry.desc
    }
  }
  return descs
}

function stripPrefix(prefix: string | null): (cls: string) => string {
  return (className) => {
    if (className.startsWith(prefix + '-')) {
      return className.slice(prefix.length + 1)
    }
    return className
  }
}

export function toCamelCase(suffix: string): string {
  const pascal = toPascalCase(suffix)
  return pascal.charAt(0).toLowerCase() + pascal.slice(1)
}

export function hasVariants(classified: ClassifiedComponent): boolean {
  return classified.colors.length > 0 || classified.styles.length > 0
}

export function hasSizes(classified: ClassifiedComponent): boolean {
  return classified.sizes.length > 0
}

export function hasModifiers(classified: ClassifiedComponent): boolean {
  return classified.modifiers.length > 0 || classified.behaviors.length > 0
}

export function hasParts(classified: ClassifiedComponent): boolean {
  return classified.parts.length > 0
}

export function getAllBooleanParams(classified: ClassifiedComponent): string[] {
  return [...classified.modifiers, ...classified.behaviors].sort()
}
