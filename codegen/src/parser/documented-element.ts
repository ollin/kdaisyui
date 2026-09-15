/**
 * The element DaisyUI's own documentation shows for a component.
 *
 * The generator picks a component's element with a heuristic over `llms.txt`-style syntax
 * blocks, and a heuristic is a thing that can be wrong: `dropdown` rendered a popover variant
 * that could not open, `otp` renders a `<div>` where a `<label>` is documented, and `tab`
 * renders the container class on a `<button>`. None of those changes a CSS class, so nothing in
 * the class-based safety net could see them.
 *
 * This module reads what DaisyUI states, so the generator can be checked against it instead of
 * trusted. The statement is in every component's `+page.md`: fenced ```html examples in which
 * daisyUI class names carry a `$$` marker.
 *
 *     <label class="$$swap">
 *       <div class="$$swap-on">ON</div>
 *     </label>
 *
 * That marker is what makes this mechanical rather than another heuristic — it separates
 * daisyUI's own classes from Tailwind utilities without inferring anything.
 *
 * Parsed with a real HTML parser rather than a regex. Measured on the three constructs that
 * defeat a regex — a `>` inside a quoted attribute value, a comment containing a tag, and
 * `<script>` content — the regex gets all three wrong and the parser gets all three right.
 */

import fs from 'fs'
import path from 'path'
import { parseDocument, DomUtils } from 'htmlparser2'
import type { ComponentName } from './frontmatter.ts'
import { DocumentedClass } from './documented-classes.ts'

/** The sentinel DaisyUI's documentation puts in front of its OWN class names. */
const MARKER = '$$'

const COMPONENTS_PATH = path.resolve(
  import.meta.dirname,
  '../../../daisyui/packages/docs/src/routes/(routes)/components',
)

/** Every fenced ```html block in a documentation page, concatenated in document order. */
export function fencedHtmlBlocks(markdown: string): string {
  return [...markdown.matchAll(/```html\n([\s\S]*?)```/g)].map(match => match[1]).join('\n')
}

/**
 * The tag name of the first element whose class list carries `$$<componentClass>`, or null when
 * the documentation shows no such element.
 *
 * Null is a real answer and not an error: it means DaisyUI documents nothing this check can be
 * made against, which is exactly the case the caller must refuse to pass over in silence.
 */
export function documentedElementIn(html: string, componentClass: string): string | null {
  const marker = `$$${componentClass}`
  const element = DomUtils.findOne(
    node => (node.attribs.class ?? '').split(/\s+/).includes(marker),
    parseDocument(html).children,
    true,
  )
  return element?.name.toUpperCase() ?? null
}

/**
 * The element DaisyUI documents for EVERY `$$`-marked class on a page — where it documents
 * exactly one.
 *
 * The same reading as `documentedElementIn`, once per class instead of once per component — a
 * modifier such as `menu-active` sits on a child `<li>`, and a generator that declares it on the
 * container's function emits it where it does nothing. This is what the class cross-check reads.
 *
 * A class shown on several elements is left out: `badge` appears on a `<span>` once and on a
 * `<div>` seventeen times, and "the first one" is a coin toss, not a statement. Absent means
 * unchecked, which is the honest answer to a source that does not say.
 */
export function documentedElementsIn(html: string): ReadonlyMap<string, string> {
  const unambiguous = new Map<string, string>()
  for (const [className, elements] of documentedElementSetsIn(html)) {
    if (elements.size === 1) unambiguous.set(className, [...elements][0])
  }
  return unambiguous
}

/**
 * EVERY element each marked class is shown on. What `documentedElementsIn` decides from, and
 * what a caller needs when "shown on several" is itself the answer: `dropdown` is shown on a
 * `<div>` and a `<details>`, and a class shown on either of those is on the dropdown.
 */
export function documentedElementSetsIn(html: string): ReadonlyMap<string, ReadonlySet<string>> {
  const seen = new Map<string, Set<string>>()
  DomUtils.findAll(
    node => (node.attribs?.class ?? '').includes(MARKER),
    parseDocument(html).children,
  ).forEach(element => {
    for (const token of (element.attribs.class ?? '').split(/\s+/)) {
      const parsed = DocumentedClass.parse(token)
      if (parsed === null || parsed.isPrefixed) continue
      const elements = seen.get(parsed.className) ?? new Set()
      elements.add(element.name.toUpperCase())
      seen.set(parsed.className, elements)
    }
  })
  return seen
}

/**
 * The element each marked class's PARENT is shown as, where that is one element.
 *
 * What a part needs as its extension receiver when kotlinx.html opens its element only inside
 * a specific parent: `<legend>` inside `<fieldset>`, `<li>` inside `<ul>`. Read the same way
 * as the element itself, and absent for the same reasons — shown nowhere, on several, or at
 * the top of an example with no parent at all.
 */
export function documentedParentsIn(html: string): ReadonlyMap<string, string> {
  const seen = new Map<string, Set<string>>()
  DomUtils.findAll(
    node => (node.attribs?.class ?? '').includes(MARKER),
    parseDocument(html).children,
  ).forEach(element => {
    const parent = element.parent
    if (parent === null || parent.type !== 'tag') return
    for (const token of (element.attribs.class ?? '').split(/\s+/)) {
      const parsed = DocumentedClass.parse(token)
      if (parsed === null || parsed.isPrefixed) continue
      const parents = seen.get(parsed.className) ?? new Set()
      parents.add(parent.name.toUpperCase())
      seen.set(parsed.className, parents)
    }
  })
  const unambiguous = new Map<string, string>()
  for (const [className, parents] of seen) {
    if (parents.size === 1) unambiguous.set(className, [...parents][0])
  }
  return unambiguous
}

/** `documentedParentsIn` for one component's page; empty when the page does not exist. */
export function documentedParentsFor(componentName: ComponentName): ReadonlyMap<string, string> {
  const file = path.join(COMPONENTS_PATH, componentName, '+page.md')
  if (!fs.existsSync(file)) return new Map()
  return documentedParentsIn(fencedHtmlBlocks(fs.readFileSync(file, 'utf8')))
}

/** `documentedElementSetsIn` for one component's page; empty when the page does not exist. */
export function documentedElementSetsFor(componentName: ComponentName): ReadonlyMap<string, ReadonlySet<string>> {
  const file = path.join(COMPONENTS_PATH, componentName, '+page.md')
  if (!fs.existsSync(file)) return new Map()
  return documentedElementSetsIn(fencedHtmlBlocks(fs.readFileSync(file, 'utf8')))
}

/** `documentedElementsIn` for one component's page; empty when the page does not exist. */
export function documentedElementsFor(componentName: ComponentName): ReadonlyMap<string, string> {
  const file = path.join(COMPONENTS_PATH, componentName, '+page.md')
  if (!fs.existsSync(file)) return new Map()
  return documentedElementsIn(fencedHtmlBlocks(fs.readFileSync(file, 'utf8')))
}

/** The documented element for one component, read from its `+page.md`. */
export function documentedElementFor(
  componentName: ComponentName,
  componentClass: string,
): string | null {
  const file = path.join(COMPONENTS_PATH, componentName, '+page.md')
  if (!fs.existsSync(file)) return null
  return documentedElementIn(fencedHtmlBlocks(fs.readFileSync(file, 'utf8')), componentClass)
}
