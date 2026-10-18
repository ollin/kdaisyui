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

/** The sentinel DaisyUI's documentation puts in front of its OWN class names. */
const MARKER = '$$'

/** Every fenced ```html block in a documentation page, concatenated in document order. */
export function fencedHtmlBlocks(markdown: string): string {
  return [...markdown.matchAll(/```html\n([\s\S]*?)```/g)].map(match => match[1]).join('\n')
}



/**
 * EVERY element each marked class is shown on: `dropdown` is shown on a `<div>` and a
 * `<details>`, and a class shown on either of those is on the dropdown.
 */
export function documentedElementSetsIn(html: string): ReadonlyMap<string, ReadonlySet<string>> {
  const sets = new Map<string, ReadonlySet<string>>()
  for (const [className, tally] of documentedElementTalliesIn(html)) sets.set(className, new Set(tally.keys()))
  return sets
}

/** How many times each marked class is shown on each element. */
function documentedElementTalliesIn(html: string): ReadonlyMap<string, ReadonlyMap<string, number>> {
  const tallies = new Map<string, Map<string, number>>()
  DomUtils.findAll(
    node => (node.attribs?.class ?? '').includes(MARKER),
    parseDocument(html).children,
  ).forEach(element => {
    for (const token of (element.attribs.class ?? '').split(/\s+/)) {
      const parsed = DocumentedClass.parse(token)
      if (parsed === null || parsed.isPrefixed) continue
      const tally = tallies.get(parsed.className) ?? new Map()
      const tag = element.name.toUpperCase()
      tally.set(tag, (tally.get(tag) ?? 0) + 1)
      tallies.set(parsed.className, tally)
    }
  })
  return tallies
}
