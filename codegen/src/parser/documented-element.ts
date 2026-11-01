/**
 * Which elements DaisyUI's own documentation shows each of its classes on.
 *
 * Read by the bump report, `just daisyui-delta`, to say when a release moves a class to another
 * element. The generators no longer read it: the element of every function is declared in
 * `codegen/model/`.
 *
 * The statement is in every component's `+page.md`: fenced ```html examples in which daisyUI
 * class names carry a `$$` marker.
 *
 *     <label class="$$swap">
 *       <div class="$$swap-on">ON</div>
 *     </label>
 *
 * That marker is what makes this mechanical rather than a heuristic — it separates daisyUI's
 * own classes from Tailwind utilities without inferring anything.
 *
 * Parsed with a real HTML parser rather than a regex. Measured on the three constructs that
 * defeat a regex — a `>` inside a quoted attribute value, a comment containing a tag, and
 * `<script>` content — the regex gets all three wrong and the parser gets all three right.
 */

import { parseDocument, DomUtils } from 'htmlparser2'
import { DocumentedClass } from './documented-classes.ts'

/** Every fenced ```html block in a documentation page, concatenated in document order. */
export function fencedHtmlBlocks(markdown: string): string {
  return [...markdown.matchAll(/```html\n([\s\S]*?)```/g)].map(match => match[1]).join('\n')
}



/**
 * EVERY element each marked class is shown on: `dropdown` is shown on a `<div>` and a
 * `<details>`, and a class shown on either of those is on the dropdown.
 *
 * Every class attribute is read, and `DocumentedClass.parse` drops each token without the marker.
 */
export function documentedElementSetsIn(html: string): ReadonlyMap<string, ReadonlySet<string>> {
  const sets = new Map<string, Set<string>>()
  for (const element of DomUtils.findAll(node => node.attribs.class !== undefined, parseDocument(html).children)) {
    for (const token of element.attribs.class.split(/\s/)) {
      const parsed = DocumentedClass.parse(token)
      if (parsed === null || parsed.isPrefixed) continue
      sets.set(parsed.className, (sets.get(parsed.className) ?? new Set()).add(element.name.toUpperCase()))
    }
  }
  return sets
}
