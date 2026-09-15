/**
 * The prose that ships inside `codegen/exclusivity.json`.
 *
 * The file is generated WHOLESALE, like everything under `lib/generated/**` — so its
 * provenance cannot be hand-written into it, or the next measurement would delete it. It
 * lives here instead, beside the procedure it describes, and the generator embeds it in the
 * page so the browser can emit a complete document.
 *
 * There is deliberately no timestamp. A date would change on every run and make the diff
 * noisy for no gain: git already records when the file was written, and the only thing that
 * makes one run differ from another is the DaisyUI version, which is recorded.
 */
import { readFileSync } from 'node:fs'
import path from 'node:path'

const DAISYUI_PACKAGE = path.resolve(
  import.meta.dirname,
  '../../daisyui/packages/daisyui/package.json',
)

/** The DaisyUI actually checked out, which is the thing measured — not what the build asks for. */
export function daisyuiVersion(): string {
  return JSON.parse(readFileSync(DAISYUI_PACKAGE, 'utf8')).version
}

export interface Provenance {
  readonly what: string
  readonly daisyui: string
  readonly procedure: readonly string[]
  readonly howToRead: Readonly<Record<string, string>>
  readonly whyPairsAndNotAxes: string
  readonly limitation: string
  readonly reproduce: string
  readonly defectsFoundInTheProbeItself: readonly string[]
}

/** Everything a reader needs to know what this file is and how far to trust it. */
export function provenance(version: string): Provenance {
  return {
    what: 'For every pair of classes in a multi-member DaisyUI class group, whether the two can be worn at once.',
    daisyui: version,
    procedure: [
      "Render DaisyUI's own documented example for the component three times: with class a, with class b, and with both — and once per group with nothing injected, the baseline.",
      'Every member of the group under test is stripped from the example first, so the case measures the injected classes and not what the example happened to carry.',
      'One colour class is held constant across all three cases, so classes that mix a colour variable are not compared unlit.',
      'The stylesheet is the complete webjar bundle daisyui.css under data-theme=light, so theme variables resolve.',
      "A signature is the full computed style of every element in the subtree, plus ::before and ::after, plus each element's box relative to the case root.",
      'sig(ab) == sig(a) or sig(ab) == sig(b)  ->  one class is inert against the other  ->  exclusive',
      'sig(ab) differs from both                ->  the combination is a third result      ->  compose',
      'sig(a) == sig(b) and neither of the above ->  this example cannot tell them apart   ->  same',
      'sig(a) == sig(baseline)                  ->  the class alone changed nothing        ->  a is inert',
      "For every member, `declares` lists the property names the stylesheet declares under its class, read from the CSSOM: what places an inert member on an axis, and what names an axis from DaisyUI's custom-property table.",
    ],
    howToRead: {
      exclusive:
        'Positively established: wearing both is no different from wearing one. Only a group whose every pair is exclusive may become an enum.',
      compose:
        'Positively refuted: the combination reaches CSS neither class reaches alone. Must stay boolean, or the combination becomes inexpressible.',
      same: 'Not established either way. Treated as compose, because inventing exclusivity is the error whose cost is asymmetric.',
      inert:
        'The class alone equals the baseline. Its pairs still read exclusive — together equals the other alone — and that is honest as an observation, but it is not membership of a clique: the axis derivation leaves an inert member out and places it by what it declares.',
    },
    whyPairsAndNotAxes:
      'Axes are an inference over these pairs. The pairs are what was observed; the inference — connected components of the exclusive graph over non-inert members — is made in class-groups.ts. Before the baseline existed, tooltip-top read exclusive with all six other members and the group looked like two overlapping cliques; it was an inert class, not a second axis.',
    limitation:
      'A verdict is only as good as the example. Two classes that each set a DIFFERENT subset of properties toward the same intent — `chat-start` and `chat-end` — compose by this procedure even though no author would combine them. That direction is safe: it leaves a useless combination expressible rather than making a useful one impossible.',
    reproduce:
      '`just measure-exclusivity`. Never edit this file by hand — it is overwritten wholesale, and `codegen/src/verify-exclusivity.ts` fails the build when it stops describing the DaisyUI in the submodule.',
    defectsFoundInTheProbeItself: [
      'DaisyUI\'s docs mark the component class with a `$$` sentinel — class="$$btn". Left in, the element matches no rule and every pair looks identical.',
      'The example already carried a member of the group under test: `class="mask mask-squircle"` made every single-member mask case render as a squircle, so the run measured stylesheet order and reported one of the 105 pairs as indistinguishable.',
      'No theme was loaded, so --color-neutral and its siblings were undefined and every colour-dependent class computed to the same transparent black. `badge-soft|ghost` and `alert-outline|soft` were called indistinguishable for that reason alone, and `utilities/join.css` was missing entirely so `join-vertical|horizontal` was measured against no rules.',
      'All three were found by reading what the browser computed for a suspicious verdict, never by re-reading the probe. Each now has a test in exclusivity-probe.test.ts named after it.',
    ],
  }
}
