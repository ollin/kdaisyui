/**
 * All three shapes below were already written in this file, as JSDoc `@typedef` blocks that
 * described the parsed YAML precisely and that nothing ever checked. Converting them is the
 * clearest win in the port: the knowledge existed, it was just unenforced.
 */

/** One entry under a `classnames` category, as written in the submodule's YAML. */
export interface ClassEntry {
  /** CSS class name, e.g. `btn-primary`. May arrive quoted; callers strip the quotes. */
  class: string
  desc: string
  /** Only meaningful under `size`, where exactly one entry is the default. */
  default?: boolean
}

/**
 * The `classnames` block. Every category is optional because a component declares only the
 * ones it has — `divider` has no `size`, `button` has no `placement`.
 */
export interface Classnames {
  component?: ClassEntry[]
  part?: ClassEntry[]
  style?: ClassEntry[]
  color?: ClassEntry[]
  size?: ClassEntry[]
  modifier?: ClassEntry[]
  behavior?: ClassEntry[]
  direction?: ClassEntry[]
  placement?: ClassEntry[]
}

/**
 * A category name, derived from `Classnames` so the two cannot drift.
 *
 * This is the annotation that pays here. Callers index `classnames` by a string — and
 * `test-generator.ts` keeps a hand-written list of five of them. A typo returned `undefined`,
 * the caller's `?? []` swallowed it, and the component silently lost every modifier in that
 * category. Now it is a call-site error.
 */
export type ClassCategory = keyof Classnames

/** A parsed `+page.md` frontmatter block. */
export interface FrontmatterData {
  title?: string
  desc?: string
  /** CSS source URL. */
  source?: string
  classnames?: Classnames
  [key: string]: unknown
}

/** What this hand-rolled YAML reader can produce for a scalar. */
type YamlScalar = string | number | boolean

/**
 * A component's directory name, e.g. `file-input` — what addresses a folder on disk.
 */
export type ComponentName = string & { readonly __brand: 'ComponentName' }

/**
 * The same component's Kotlin-facing name, e.g. `FileInput` — what becomes `daisyFileInput`.
 *
 * Same distinction, same failure, as `KebabName`/`PascalName` in the heroicon parser:
 * `toPascalCase` applied to an already-pascal name returns it unchanged, so the mistake
 * produces a plausible value rather than an error.
 *
 * Branding these also answers a real CodeScene finding rather than a stylistic preference.
 * Annotating this file's arguments as `string` raised **String Heavy Function Arguments** at
 * 41.7% — a smell that was always present and became visible only once the types were
 * written down. The `.js` version scored 0 on it because nothing could see the strings.
 */
export type PascalComponentName = string & { readonly __brand: 'PascalComponentName' }

export function parseFrontmatter(content: string): FrontmatterData | null {
  const match = content.match(/^---\n([\s\S]*?)\n---/)
  if (!match) return null
  return parseYamlFrontmatter(match[1])
}

function parseYamlFrontmatter(yaml) {
  const result = {}
  const lines = yaml.split('\n')
  let currentKey = null
  let currentCategory = null
  let currentObject = null
  
  for (const line of lines) {
    const trimmed = line.trim()
    if (!trimmed) continue
    
    const lineIndent = line.search(/\S/)
    const isTopLevel = lineIndent === 0
    const isSecondLevel = lineIndent === 2
    const isThirdLevel = lineIndent === 4
    
    if (isTopLevel) {
      const colonIndex = trimmed.indexOf(':')
      if (colonIndex === -1) continue
      
      const key = trimmed.slice(0, colonIndex).trim()
      const value = trimmed.slice(colonIndex + 1).trim()
      
      if (value) {
        result[key] = parseValue(value)
        currentKey = null
        currentCategory = null
      } else {
        currentKey = key
        if (key === 'classnames') {
          result[key] = {}
        } else {
          result[key] = []
        }
      }
    } else if (isSecondLevel && currentKey === 'classnames') {
      if (trimmed.startsWith('- ')) {
        const itemText = trimmed.slice(2).trim()
        if (itemText.includes(':')) {
          const obj = {}
          const colonIndex = itemText.indexOf(':')
          const key = itemText.slice(0, colonIndex).trim()
          const value = itemText.slice(colonIndex + 1).trim()
          obj[key] = parseValue(value)
          if (currentCategory && result.classnames[currentCategory]) {
            result.classnames[currentCategory].push(obj)
            currentObject = obj
          }
        } else {
          if (currentCategory && result.classnames[currentCategory]) {
            result.classnames[currentCategory].push({ class: parseValue(itemText) })
            currentObject = null
          }
        }
      } else {
        const colonIndex = trimmed.indexOf(':')
        if (colonIndex === -1) continue
        const key = trimmed.slice(0, colonIndex).trim()
        result.classnames[key] = []
        currentCategory = key
        currentObject = null
      }
    } else if (isThirdLevel && currentKey === 'classnames') {
      if (trimmed.startsWith('- ')) {
        const itemText = trimmed.slice(2).trim()
        if (itemText.includes(':')) {
          const obj = {}
          const colonIndex = itemText.indexOf(':')
          const key = itemText.slice(0, colonIndex).trim()
          const value = itemText.slice(colonIndex + 1).trim()
          obj[key] = parseValue(value)
          if (currentCategory && result.classnames[currentCategory]) {
            result.classnames[currentCategory].push(obj)
            currentObject = obj
          }
        } else {
          if (currentCategory && result.classnames[currentCategory]) {
            result.classnames[currentCategory].push({ class: parseValue(itemText) })
            currentObject = null
          }
        }
      } else if (trimmed.includes(':') && currentObject) {
        const colonIndex = trimmed.indexOf(':')
        const key = trimmed.slice(0, colonIndex).trim()
        const value = trimmed.slice(colonIndex + 1).trim()
        currentObject[key] = parseValue(value)
      }
    } else if (isThirdLevel && currentObject) {
      const colonIndex = trimmed.indexOf(':')
      if (colonIndex !== -1) {
        const key = trimmed.slice(0, colonIndex).trim()
        const value = trimmed.slice(colonIndex + 1).trim()
        currentObject[key] = parseValue(value)
      }
    } else if (isSecondLevel && Array.isArray(result[currentKey])) {
      if (trimmed.startsWith('- ')) {
        result[currentKey].push(parseValue(trimmed.slice(2).trim()))
      }
    }
  }
  
  return result
}

function parseValue(value: string): YamlScalar {
  if (!value) return ''
  if ((value.startsWith("'") && value.endsWith("'")) ||
      (value.startsWith('"') && value.endsWith('"'))) {
    return value.slice(1, -1)
  }
  if (value === 'true') return true
  if (value === 'false') return false
  const num = Number(value)
  if (!isNaN(num) && value.trim() !== '') return num
  return value
}

/**
 * Extract component name from directory path
 * @param {string} dirPath - e.g., '/path/to/components/button'
 * @returns {string} - e.g., 'button'
 */
export function getComponentName(dirPath: string): ComponentName {
  // `!` because `split` always yields at least one element, even for the empty string.
  return dirPath.split('/').pop()! as ComponentName
}

/**
 * Convert component name to PascalCase
 * @param {string} name - e.g., 'button'
 * @returns {string} - e.g., 'Button'
 */
export function toPascalCase(name: ComponentName): PascalComponentName {
  return name
    .split('-')
    .map((p) => p.charAt(0).toUpperCase() + p.slice(1))
    .join('') as PascalComponentName
}

export function getBaseClass(classnames: Classnames | undefined): string | null {
  if (!classnames?.component?.length) return null
  return classnames.component[0].class.replace(/^['"]|['"]$/g, '')
}

/**
 * Get classes by category (class names only)
 * @param {Classnames} classnames
 * @param {string} category
 * @returns {string[]}
 */
export function getClassesByCategory(
  classnames: Classnames | undefined,
  category: ClassCategory,
): string[] {
  if (!classnames?.[category]) return []
  return classnames[category].map(entry => {
    const cls = entry.class
    return cls.replace(/^['"]|['"]$/g, '')
  })
}

/**
 * Get full entries by category (class name + description + default flag)
 * @param {Classnames} classnames
 * @param {string} category
 * @returns {Array<{class: string, desc: string, default?: boolean}>}
 */
export function getEntriesByCategory(
  classnames: Classnames | undefined,
  category: ClassCategory,
): ClassEntry[] {
  if (!classnames?.[category]) return []
  return classnames[category].map(entry => ({
    class: (entry.class || '').replace(/^['"]|['"]$/g, ''),
    desc: (entry.desc || '').replace(/^['"]|['"]$/g, ''),
    ...(entry.default != null ? { default: entry.default } : {}),
  }))
}

export function getDefaultSize(classnames: Classnames | undefined): string | null {
  if (!classnames?.size) return null
  const defaultEntry = classnames.size.find(entry => entry.default === true)
  if (!defaultEntry) return null
  return defaultEntry.class.replace(/^['"]|['"]$/g, '')
}

import fs from 'fs'
import path from 'path'

const DAISYUI_DOCS_PATH = path.resolve(import.meta.dirname, '../../../daisyui/packages/docs')
const COMPONENTS_PATH = path.join(DAISYUI_DOCS_PATH, 'src/routes/(routes)/components')

export function getAllComponentDirs(): ComponentName[] {
  return fs.readdirSync(COMPONENTS_PATH)
    .filter((f) => {
      const stat = fs.statSync(path.join(COMPONENTS_PATH, f))
      return stat.isDirectory() && fs.existsSync(path.join(COMPONENTS_PATH, f, '+page.md'))
    })
    .sort() as ComponentName[]
}

export function readComponentFrontmatter(componentName: ComponentName): FrontmatterData | null {
  const filePath = path.join(COMPONENTS_PATH, componentName, '+page.md')
  if (!fs.existsSync(filePath)) return null
  return parseFrontmatter(fs.readFileSync(filePath, 'utf8'))
}
