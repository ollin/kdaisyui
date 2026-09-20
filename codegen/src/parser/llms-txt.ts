import fs from 'fs'
import path from 'path'
import type { TagName } from '../html-names.ts'
import type { ComponentName } from './frontmatter.ts'

/**
 * Which HTML elements DaisyUI's own documentation says a component may be built from.
 *
 * `elements` and `primaryElement` are TAG names, sharing the brand `test-generator.ts`
 * writes into Kotlin — this is the boundary where documentation becomes a type, so it is
 * the right place to say which kind of string these are.
 *
 * `primaryElement` is null until the two passes at the end of `parseLlmsTxtContent` fill
 * it in, which is why it is nullable rather than merely optional.
 */
export interface ElementRule {
  component: ComponentName
  elements: TagName[]
  primaryElement: TagName | null
}

const DAISYUI_ROOT = path.resolve(import.meta.dirname, '../../../daisyui')
const STATIC_LLMS_TXT = path.join(DAISYUI_ROOT, 'packages/docs/static/llms.txt')
const COMPONENT_SKILLS_DIR = path.join(DAISYUI_ROOT, 'skills/daisyui/components')

/**
 * Each component's skill document, keyed by FILENAME; null when the directory is absent.
 *
 * The files used to be concatenated into one string and parsed as a single blob, which threw
 * the filenames away and left the `### ` heading as the only name available. Keeping them
 * apart is what lets the authoritative name survive.
 */
function readComponentSkillFiles(): Map<ComponentName, string> | null {
  if (!fs.existsSync(COMPONENT_SKILLS_DIR)) return null
  return new Map(
    fs.readdirSync(COMPONENT_SKILLS_DIR)
      .filter((name) => name.endsWith('.md'))
      .sort()
      .map((name) => [
        // The directory name of the component — the same string every caller looks up by.
        name.slice(0, -'.md'.length) as ComponentName,
        fs.readFileSync(path.join(COMPONENT_SKILLS_DIR, name), 'utf8'),
      ]),
  )
}

/**
 * One component's rule, read from one file, named by that FILE rather than by its heading.
 *
 * The heading is a human title and cannot be relied on. DaisyUI 5.7.42 retitled 67 of its 68
 * component documents — `### card` became `### Card`, `### file-input` became `### File input`
 * — and reworded four of them outright, so `mockup-browser.md` is headed "Browser mockup".
 * Lowercasing and hyphenating recovers the first two kinds and gets `browser-mockup` for the
 * third, which matches no component.
 *
 * The name is load-bearing twice over: it keys the map every caller looks up by, and
 * `findComponentInSyntax` matches it against the component's CSS class, which is named after
 * the directory and not after the prose.
 */
function parseComponentDoc(content: string, name: ComponentName): ElementRule {
  const rule: ElementRule = { component: name, elements: [], primaryElement: null }
  const syntaxBlock: string[] = []
  let section: 'syntax' | 'rules' | null = null

  for (const line of content.split('\n')) {
    if (line.startsWith('#### Syntax')) section = 'syntax'
    else if (line.startsWith('#### Rules')) section = 'rules'
    else if (line.startsWith('#### ') || line.startsWith('### ')) section = null
    else if (section === 'syntax') collectSyntaxLine(line, syntaxBlock)
    else if (section === 'rules') collectRuleElements(line, rule)
  }

  resolvePrimaryElement(rule, syntaxBlock)
  return rule
}

function collectSyntaxLine(line: string, syntaxBlock: string[]): void {
  if (line.startsWith('```')) return
  if (line.trim()) syntaxBlock.push(line)
}

function collectRuleElements(line: string, rule: ElementRule): void {
  if (!line.includes('<') || !line.includes('>')) return
  for (const wrapped of line.match(/<(\w+)>/g) ?? []) {
    // `<button>` in the Rules prose — one of the places a TagName is created.
    const element = wrapped.slice(1, -1) as TagName
    if (!rule.elements.includes(element)) rule.elements.push(element)
  }
}

function resolvePrimaryElement(rule: ElementRule, syntaxBlock: string[]): void {
  const documented = findComponentInSyntax(syntaxBlock, rule.component)
  if (documented) {
    rule.primaryElement = documented.element
    if (!rule.elements.includes(documented.element)) rule.elements.push(documented.element)
  }
  if (!rule.primaryElement && rule.elements.length > 0) rule.primaryElement = rule.elements[0]
}

/** The element rules of DaisyUI's per-component skill documents, keyed by filename. */
export function parseComponentSkills(
  files: ReadonlyMap<ComponentName, string>,
): Map<ComponentName, ElementRule> {
  return new Map([...files].map(([name, content]) => [name, parseComponentDoc(content, name)]))
}

export function parseLlmsTxt(): Map<ComponentName, ElementRule> {
  // Order preserved from before: the single file wins where it still exists, so an older pin
  // behaves exactly as it did.
  if (fs.existsSync(STATIC_LLMS_TXT)) {
    return parseLlmsTxtContent(fs.readFileSync(STATIC_LLMS_TXT, 'utf8'))
  }
  const skills = readComponentSkillFiles()
  if (skills) return parseComponentSkills(skills)
  throw new Error(
    `DaisyUI layout changed: found neither ${STATIC_LLMS_TXT} nor ${COMPONENT_SKILLS_DIR}. ` +
    `Check the pinned daisyui version and the codegen path.`
  )
}

/**
 * The legacy single-file source, where the `### ` heading is the only name there is.
 *
 * `packages/docs/static/llms.txt` vanished in DaisyUI 5.5.23. Kept so an older pin behaves as
 * it always did; a blob has no filenames, so heading-keyed is correct HERE and nowhere else.
 */
export function parseLlmsTxtContent(content: string): Map<ComponentName, ElementRule> {
  const rules = new Map<ComponentName, ElementRule>()
  for (const section of content.split(/^### /m).slice(1)) {
    const name = section.slice(0, section.indexOf('\n')).trim() as ComponentName
    rules.set(name, parseComponentDoc(section, name))
  }
  return rules
}

function findComponentInSyntax(
  syntaxBlock: string[],
  componentName: ComponentName,
): { element: TagName; baseClass: string } | null {
  for (const line of syntaxBlock) {
    const elementMatch = line.match(/<(\w+)[\s>]/)
    if (elementMatch) {
      // The opening tag of a documented example — the third place a TagName is created.
      const element = elementMatch[1] as TagName
      const classMatch = line.match(/class="([^"]+)"/)
      if (classMatch) {
        const classes = classMatch[1].split(/\s+/)
        const baseClass = classes.find(c => c === componentName || c.startsWith(componentName + '-'))
        if (baseClass) {
          return { element, baseClass }
        }
      }
    }
  }
  return null
}

// `div` is the documented fallback for a component DaisyUI says nothing about. It is a tag
// name like any other, so it is branded once here rather than cast at each return.
const DIV = 'div' as TagName

export function getElementForComponent(
  rules: Map<ComponentName, ElementRule>,
  componentName: ComponentName,
): TagName {
  const rule = rules.get(componentName)
  if (!rule) return DIV
  return rule.primaryElement || DIV
}

export function getAllowedElements(
  rules: Map<ComponentName, ElementRule>,
  componentName: ComponentName,
): TagName[] {
  const rule = rules.get(componentName)
  if (!rule) return [DIV]
  return rule.elements.length > 0 ? rule.elements : [DIV]
}
