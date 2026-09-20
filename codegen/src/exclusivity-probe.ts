/**
 * Build the page that measures which DaisyUI classes can be worn at once.
 *
 * This is half of the measurement behind `codegen/exclusivity.json`. It emits one case per
 * class and one per pair, rendered into DaisyUI's OWN documented example for that component;
 * `exclusivity-verdicts.js` is the other half and decides the verdicts inside the browser,
 * because a cascade, a layout and a theme only exist there.
 *
 * `index-exclusivity.ts` is the entry point and `:e2e-tests:measureExclusivity` drives the
 * browser. Nothing here runs during `check`: like every other generator, the output is
 * committed and drift-checked instead.
 *
 * ## Four details that are load-bearing
 *
 * Each was found by reading what the browser computed for a verdict that looked wrong — never
 * by re-reading this file — and each has a test named after it. Do not simplify them away.
 *
 * 1. **Strip the `$$` sentinel.** DaisyUI's docs mark the component class as `class="$$btn"`.
 *    Left in, the element matches no rule and every pair looks identical.
 * 2. **Strip the group's own members from the example.** The mask example is
 *    `class="mask mask-squircle"`, so injecting `mask-square` measured which of the two comes
 *    later in the stylesheet rather than whether they conflict.
 * 3. **Keep one colour class on in every case.** `alert-outline` and `alert-soft` both mix
 *    `--alert-color`; with no colour present both degrade to the same neutral, which reads as
 *    "indistinguishable" when it only means "unlit". A constant applied to all three cases of
 *    a pair cannot invent a difference, only stop one from being hidden.
 * 4. **Render DaisyUI's real example, not a stub.** `pin-rows` styles `:where(thead tr)`, so
 *    against a bare `div` it changes nothing and the pair reads as exclusive. A false "inert"
 *    INVENTS exclusivity, which is the error whose cost is asymmetric.
 * 5. **Render a baseline with nothing injected.** A class that changes nothing — `tooltip-top`
 *    restates `.tooltip`'s own declarations — equals every other member alone, so the pair
 *    rule calls it exclusive with all of them and the group looks like two overlapping
 *    cliques. Against the baseline it is simply `inert`, and the derivation leaves it out.
 */
import { existsSync, readdirSync, readFileSync } from 'node:fs'
import path from 'node:path'
import { getAllComponentDirs, readComponentFrontmatter } from './parser/frontmatter.ts'
import { classifyFromFrontmatter } from './classifier.ts'
import { ClassPair } from './measurement.ts'
import { daisyuiVersion, provenance } from './exclusivity-document.ts'

const DOCS = path.resolve(
  import.meta.dirname,
  '../../daisyui/packages/docs/src/routes/(routes)/components',
)

/** The categories that group several classes answering one question. */
export const PROBE_CATEGORIES = [
  'styles',
  'modifiers',
  'behaviors',
  'directions',
  'placements',
] as const

/** The first documented example whose markup carries the component class as a whole token. */
export function exampleFor(dir: string, prefix: string): string | null {
  const base = path.join(DOCS, dir)
  if (!existsSync(base)) return null
  const token = new RegExp(`(^|\\s)${prefix}(\\s|$)`)
  for (const file of readdirSync(base).sort()) {
    if (!file.endsWith('.md')) continue
    const text = readFileSync(path.join(base, file), 'utf8')
    for (const [, block] of text.matchAll(/```html\n([\s\S]*?)```/g)) {
      const clean = stripSentinel(block)
      for (const [, value] of clean.matchAll(/class="([^"]*)"/g)) {
        if (token.test(value)) return clean.trim()
      }
    }
  }
  return null
}

/** Remove the `$$` the docs use to mark the component class. See detail 1. */
export function stripSentinel(markup: string): string {
  return markup.replaceAll('$$', '')
}

/**
 * Put `added` on the first element carrying the component class, having cleared the group.
 *
 * @param group every member of the group under test, so the example's own choice cannot
 *   compete with the injected one — detail 2
 * @param context classes held constant across all three cases of a pair — detail 3
 */
export function inject(
  markup: string,
  prefix: string,
  group: readonly string[],
  added: readonly string[],
  context: readonly string[],
): string {
  const token = new RegExp(`(^|\\s)${prefix}(\\s|$)`)
  const owned = new Set(group.map((member) => `${prefix}-${member}`))
  let done = false
  return markup.replace(/class="([^"]*)"/g, (whole, value: string) => {
    if (done || !token.test(value)) return whole
    done = true
    const kept = value.split(/\s+/).filter((cls) => cls !== '' && !owned.has(cls))
    const put = added.map((member) => `${prefix}-${member}`)
    return `class="${[...kept, ...context, ...put].join(' ')}"`
  })
}

export interface ProbeCase {
  /** `<directory>.<category>`, the key `exclusivity.json` uses. */
  group: string
  /** The component's class prefix, so the browser can find the rules a member's class matches. */
  prefix: string
  /**
   * One member, two joined by `|` — the pair key the verdicts are recorded under — or the
   * empty string for the group's baseline: the example with every member stripped and nothing
   * injected. A member whose single case equals the baseline is `inert` (detail 5).
   */
  pair: string
  html: string
}

export interface ProbePage {
  html: string
  cases: readonly ProbeCase[]
  groups: number
  /** Groups DaisyUI documents no usable example for, which therefore cannot be measured. */
  unmeasurable: readonly string[]
}

/** One component's documented example, ready to have a group's classes injected into it. */
interface ComponentProbe {
  readonly directory: string
  readonly prefix: string
  readonly example: string
  /** Held constant across every case of this component — detail 3. */
  readonly context: readonly string[]
}

/** What one component contributes: its cases, or the groups it cannot supply an example for. */
interface ComponentCases {
  readonly cases: readonly ProbeCase[]
  readonly unmeasurable: readonly string[]
}

const NOTHING: ComponentCases = { cases: [], unmeasurable: [] }

/** One case: the members to inject, keyed the way `exclusivity.json` records them. */
function caseFor(probe: ComponentProbe, group: GroupUnderTest, injected: readonly string[]): ProbeCase {
  return {
    group: `${probe.directory}.${group.category}`,
    prefix: probe.prefix,
    pair: injected.join('|'),
    html: inject(probe.example, probe.prefix, group.members, injected, probe.context),
  }
}

interface GroupUnderTest {
  readonly category: string
  readonly members: readonly string[]
}

/**
 * The baseline, every member on its own, then every pair.
 *
 * The baseline is what exposes a member that changes nothing: `tooltip-top` declares exactly
 * what `.tooltip` already sets, so alone it equals the baseline and against any other member
 * it equals that member alone — which the pair rule reads as `exclusive` six times over. Only
 * a case with nothing injected can tell "inert" from "exclusive with everything".
 */
function casesFor(probe: ComponentProbe, group: GroupUnderTest): ProbeCase[] {
  const baseline = caseFor(probe, group, [])
  const singles = group.members.map((member) => caseFor(probe, group, [member]))
  const pairs = ClassPair.allOf(group.members).map((pair) =>
    caseFor(probe, group, [pair.left, pair.right]),
  )
  return [baseline, ...singles, ...pairs]
}

function componentCases(directory: string): ComponentCases {
  const frontmatter = readComponentFrontmatter(directory)
  if (!frontmatter) return NOTHING

  const classified = classifyFromFrontmatter(frontmatter, directory)
  const prefix = classified.prefix
  if (!prefix) return NOTHING

  const groups: GroupUnderTest[] = PROBE_CATEGORIES.filter(
    (category) => classified[category].length > 1,
  ).map((category) => ({ category, members: classified[category] }))
  if (groups.length === 0) return NOTHING

  const example = exampleFor(directory, prefix)
  if (!example) {
    return { cases: [], unmeasurable: groups.map((group) => `${directory}.${group.category}`) }
  }

  const colours = classified.colors
  const probe: ComponentProbe = {
    directory,
    prefix,
    example,
    context: colours.length > 0 ? [`${prefix}-${colours[0]}`] : [],
  }
  return { cases: groups.flatMap((group) => casesFor(probe, group)), unmeasurable: [] }
}

/**
 * Every case for every multi-member group, plus the page that holds them.
 *
 * @param stylesheet the complete webjar `daisyui.css`, inlined rather than linked: the page is
 *   opened from `file://`, where Chromium treats a linked sheet as cross-origin and refuses
 *   `cssRules` — which `declares` reads. Inline, the sheet is the page's own.
 */
export function buildProbePage(stylesheet: string): ProbePage {
  const perComponent = getAllComponentDirs().map(componentCases)
  const cases = perComponent.flatMap((component) => component.cases)
  const unmeasurable = perComponent.flatMap((component) => component.unmeasurable)

  return {
    // The submodule is read HERE, at the edge, rather than inside `pageFor`. This function
    // already reads it (`getAllComponentDirs`) and only runs when a measurement is happening.
    html: pageFor(cases, stylesheet, daisyuiVersion()),
    cases,
    groups: new Set(cases.map((probeCase) => probeCase.group)).size,
    unmeasurable,
  }
}

/**
 * The probe page, built from its arguments and nothing else.
 *
 * `daisyui` is passed in rather than read here. It used to call `daisyuiVersion()` itself,
 * which put a FILE READ of the DaisyUI submodule inside a function that otherwise only joins
 * strings — so a unit test asserting how the stylesheet is embedded could not run without the
 * submodule checked out, and `codegen-tests` deliberately checks out none. The job went red
 * for a test that is not about the submodule at all.
 */
export function pageFor(cases: readonly ProbeCase[], stylesheet: string, daisyui: string): string {
  const body = cases
    .map(
      (c) =>
        `<div class="probe-case" data-group="${c.group}" data-prefix="${c.prefix}" data-pair="${c.pair}">${c.html}</div>`,
    )
    .join('\n')

  return [
    '<!doctype html>',
    // `light` explicitly, so a change to DaisyUI's default theme cannot move every verdict at
    // once without the diff saying why.
    '<html data-theme="light"><head><meta charset="utf-8">',
    // The whole bundle. A hand-picked subset of the webjar omitted every theme — leaving
    // --color-neutral undefined, so colour-dependent classes computed to the same transparent
    // black — and omitted utilities/join.css, where join-vertical and join-horizontal live.
    `<style>${stylesheet}</style>`,
    // The provenance travels INTO the page, so the browser can emit a COMPLETE
    // `exclusivity.json` and the file stays generated wholesale. Hand-writing it into the
    // JSON would mean the next measurement silently deletes it.
    `<script type="application/json" id="kdaisyui-provenance">${JSON.stringify(provenance(daisyui))}</script>`,
    '<script src="exclusivity-verdicts.js"></script>',
    '</head><body>',
    '<pre id="kdaisyui-summary"></pre>',
    body,
    '<pre id="kdaisyui-verdicts"></pre>',
    '</body></html>',
  ].join('\n')
}
