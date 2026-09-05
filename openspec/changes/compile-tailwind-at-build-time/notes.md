# Notes

## 1.1 — the premise was wrong: five prefixes work, the rest do not

**Refuted.** The proposal claimed no Tailwind variant of a DaisyUI class compiles in the setup the
docs teach. Measured on the running example app:

| Variant prefix | In the prebuilt `daisyui.css` webjar | Rules |
|---|---|---|
| `sm:` `md:` `lg:` `xl:` | yes | 1198 each |
| `hover:` | yes | 60 |
| `max-sm:` `max-lg:` … | **no** | — |
| `dark:` `focus:` | **no** | — |

Exactly five distinct prefixes across the whole 1.1 MB file.

The Tailwind **browser** build contributes nothing to DaisyUI classes: `lg:btn-lg` is absent from
its 5.8 KB of output. It only generates variants of Tailwind's own utilities, which is why
`sm:hidden` works. Everything that works for a DaisyUI class works because DaisyUI pre-generated
it, not because anything compiles at runtime.

**Consequence for the docs:** `docs/explanation.md:137` promises `lg:btn-lg`, and that promise
holds. The earlier reading of it as a false promise was wrong.

### Measurement

`/tailwind-variant` renders a matched pair: `btn btn-lg` as control, `btn lg:btn-lg` as subject.
Comparing the two hard-codes no size and survives a DaisyUI restyle.

| Viewport | control | subject `lg:btn-lg` | subject `max-lg:btn-lg` |
|---|---|---|---|
| 1280 | 48px | 48px | — |
| 800 | 48px | 40px (correct: below `lg`) | **40px — should be 48px** |

## 1.1b — the gate, seen failing

```
Expected #variant-control and #variant-subject-max to share height, got 48px and 40px
```

`max-lg:btn-lg` at 800px leaves the button at base size. That is the assertion 3.2 has to make
pass, and it has now been watched failing. Reverted rather than landed red; the button it needs
stays on the page, inert, so 3.2 adds only the scenario.

## Why the change still stands, on a different reason

The original justification — "variants do not work" — is gone. The real problem is subtler:

**It works just often enough to teach a wrong general rule.** A developer tries `lg:btn-lg`, sees
it work, and concludes DaisyUI classes accept Tailwind variants. Later `max-sm:card-side` or
`dark:alert-info` silently does nothing, and they now hold a prior belief saying the fault must be
elsewhere. Nothing marks the boundary: no error, no warning, no failing test.

The boundary is also not a contract. It is whatever DaisyUI chose to pre-generate in the release
we happen to pin, and a future DaisyUI could shrink that set without it being breaking for anyone
except consumers of a prebuilt stylesheet.

## Method note

The `lg:` scenarios passing is what exposed the mistake. Had this change started by building the
Gradle task, the five working prefixes would have kept everything looking correct and the wrong
justification would have shipped in `README.md`. Writing the assertion first cost about twenty
minutes and replaced a false premise with a measured one.
