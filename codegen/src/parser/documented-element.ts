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

/** The documented element for one component, read from its `+page.md`. */
export function documentedElementFor(
  componentName: ComponentName,
  componentClass: string,
): string | null {
  const file = path.join(COMPONENTS_PATH, componentName, '+page.md')
  if (!fs.existsSync(file)) return null
  return documentedElementIn(fencedHtmlBlocks(fs.readFileSync(file, 'utf8')), componentClass)
}
