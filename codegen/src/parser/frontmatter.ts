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

/**
 * Where the reader is in the document.
 *
 * These four were loose locals threaded through one loop, which is what made the function
 * cc 32 with eight bumps: every branch could touch any of them, so none could be read
 * without simulating the whole scan. Collected here, each handler below states exactly
 * which parts of the position it moves.
 */
interface Scan {
  result: FrontmatterData
  /** The top-level key currently open, or null after a scalar. */
  currentKey: string | null
  /** The `classnames` category currently open. */
  currentCategory: string | null
  /** The list item currently open, which indented `key: value` lines extend. */
  currentObject: Record<string, YamlScalar> | null
}

/** A `key: value` pair, split on the FIRST colon so a value may contain colons. URLs do. */
interface KeyValue {
  readonly key: string
  readonly value: string
}

/**
 * One line of the frontmatter block, read once into everything the scan dispatches on.
 *
 * Handing `trimmed: string` down made each handler re-derive `startsWith('- ')` and threw
 * the indent away, so the caller had to keep the raw line as well — the swap that
 * `scanLine` previously carried a warning comment about. A line that knows its own shape
 * removes both the repetition and the chance of passing the wrong one.
 */
interface Line {
  /** Column of the first non-space character. Dispatch is on EXACTLY 0, 2 or 4. */
  readonly indent: number
  /** The line with surrounding whitespace removed. */
  readonly text: string
  /** Whether this is a `- ` list entry. */
  readonly isItem: boolean
  /** A list entry's text without its `- ` marker; empty for anything else. */
  readonly itemText: string
}

/** Null for a blank line, which carries no position and no content. */
function readLine(raw: string): Line | null {
  const text = raw.trim()
  if (!text) return null

  const isItem = text.startsWith('- ')
  return {
    indent: raw.search(/\S/),
    text,
    isItem,
    itemText: isItem ? text.slice(2).trim() : '',
  }
}

function splitOnFirstColon(text: string): KeyValue | null {
  const colonIndex = text.indexOf(':')
  if (colonIndex === -1) return null
  return {
    key: text.slice(0, colonIndex).trim(),
    value: text.slice(colonIndex + 1).trim(),
  }
}

function startTopLevelKey(scan: Scan, pair: KeyValue): void {
  scan.currentKey = pair.key
  // `classnames` is the only mapping; every other block key is a list. Note this ALSO
  // makes an empty `tags:` an empty array rather than an empty string, which the tests pin.
  scan.result[pair.key] = pair.key === 'classnames' ? {} : []
  scan.currentCategory = null
}

function setTopLevelScalar(scan: Scan, pair: KeyValue): void {
  scan.result[pair.key] = parseValue(pair.value)
  scan.currentKey = null
  scan.currentCategory = null
}

/**
 * A `- ` entry under a `classnames` category.
 *
 * This was written out TWICE, once for indent 2 and once for indent 4, character for
 * character. Collapsing the duplication is most of what this refactoring does — the two
 * copies were four of the function's eight bumps.
 */
function pushCategoryItem(scan: Scan, line: Line): void {
  const category = scan.currentCategory
  const categories = scan.result.classnames as Record<string, Record<string, YamlScalar>[]>
  // An item before any category has nowhere to go and is dropped, as it always was.
  if (!category || !categories?.[category]) return

  const pair = splitOnFirstColon(line.itemText)
  const item = pair
    ? { [pair.key]: parseValue(pair.value) }
    : { class: parseValue(line.itemText) }
  categories[category].push(item)
  // Only a `key: value` item can be extended by the indented lines that follow it.
  scan.currentObject = pair ? item : null
}

function startCategory(scan: Scan, pair: KeyValue): void {
  ;(scan.result.classnames as Record<string, unknown>)[pair.key] = []
  scan.currentCategory = pair.key
  scan.currentObject = null
}

function extendCurrentObject(scan: Scan, line: Line): void {
  const pair = splitOnFirstColon(line.text)
  if (pair && scan.currentObject) scan.currentObject[pair.key] = parseValue(pair.value)
}

function scanTopLevel(scan: Scan, line: Line): void {
  const pair = splitOnFirstColon(line.text)
  if (!pair) return
  if (pair.value) setTopLevelScalar(scan, pair)
  else startTopLevelKey(scan, pair)
}

function scanSecondLevel(scan: Scan, line: Line): void {
  if (scan.currentKey === 'classnames') {
    if (line.isItem) return pushCategoryItem(scan, line)
    const pair = splitOnFirstColon(line.text)
    if (pair) startCategory(scan, pair)
    return
  }

  const openList = scan.result[scan.currentKey as string]
  if (line.isItem && Array.isArray(openList)) openList.push(parseValue(line.itemText))
}

function scanThirdLevel(scan: Scan, line: Line): void {
  if (scan.currentKey === 'classnames' && line.isItem) return pushCategoryItem(scan, line)
  if (scan.currentObject) extendCurrentObject(scan, line)
}

function scanLine(scan: Scan, line: Line): void {
  // Dispatch is on EXACTLY 0, 2 or 4 — a line indented six spaces matches nothing and is
  // silently dropped. Pinned by test; preserved here deliberately.
  if (line.indent === 0) return scanTopLevel(scan, line)
  if (line.indent === 2) return scanSecondLevel(scan, line)
  if (line.indent === 4) return scanThirdLevel(scan, line)
}

function parseYamlFrontmatter(yaml: string): FrontmatterData {
  const scan: Scan = { result: {}, currentKey: null, currentCategory: null, currentObject: null }

  for (const raw of yaml.split('\n')) {
    const line = readLine(raw)
    if (line) scanLine(scan, line)
  }

  return scan.result
}

/** Wrapped in a matching pair of single or double quotes. */
function isQuoted(value: string): boolean {
  return (
    (value.startsWith("'") && value.endsWith("'")) ||
    (value.startsWith('"') && value.endsWith('"'))
  )
}

/**
 * Numeric to YAML's eye. `Number('')` is 0, so the emptiness check is load-bearing rather
 * than defensive — without it an all-whitespace value would parse as the number zero.
 */
function isNumeric(value: string): boolean {
  return value.trim() !== '' && !isNaN(Number(value))
}

function parseValue(value: string): YamlScalar {
  if (!value) return ''
  if (isQuoted(value)) return value.slice(1, -1)
  if (value === 'true') return true
  if (value === 'false') return false
  if (isNumeric(value)) return Number(value)
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
