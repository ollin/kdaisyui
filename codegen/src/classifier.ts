import {
  toPascalCase as toPascalCaseBase,
  getBaseClass,
  getEntriesByCategory,
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
 * What generation still reads from a component's frontmatter: its name, its description and the
 * descriptions of its classes. Everything else about the API is declared in `codegen/model/`
 * since 0.8.0; the class categories this used to sort out went with the inference they fed.
 */
export interface ClassifiedComponent {
  /** PascalCase component name, e.g. `Button`. */
  componentName: PascalComponentName
  desc: string
  /** Class name without the component prefix → its description from the frontmatter. */
  descs: Record<string, string>
}

export function classifyFromFrontmatter(
  frontmatter: FrontmatterData,
  componentName: ComponentName,
): ClassifiedComponent {
  const classnames = frontmatter.classnames
  return {
    componentName: toPascalCase(componentName),
    desc: frontmatter.desc || '',
    descs: buildDescMap(classnames, getBaseClass(classnames)),
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

export function toCamelCase(suffix: string): string {
  const pascal = toPascalCase(suffix)
  return pascal.charAt(0).toLowerCase() + pascal.slice(1)
}
