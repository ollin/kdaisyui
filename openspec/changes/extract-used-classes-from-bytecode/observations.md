## What we expected

Copied from `probe.md` before anything below was written.

Against the baseline of the time (560 classes, 412 KB raw, 49.2 KB gzip):

- **Amplify, all three:** the pessimistic list (step 1) is **under 350 classes**; its compiled
  stylesheet (step 2) is **at or under 30 KB gzip**, roughly 60% of today's; the prototype (step 3)
  recovers **both halves** of the `at()` pair. "Two out of three is step 6, not a green light."
- **Dampen, any one:** the pessimistic stylesheet is **at or above 42 KB gzip** (85% of today's);
  the optimistic and pessimistic brackets are **within 5 KB gzip of each other**, in which case
  "precision then buys nothing, and the simple answer — ship a per-component list, no bytecode
  reading — wins"; step 4 loses more than one of four indirections; step 5 finds no way to make a
  miss loud.

Step 3 was to run only if steps 1 and 2 cleared the amplify threshold.

## What happened

Run 2026-10-31 on `main` at `17f56aa` (0.8.0 plus Ktor 3.6.1), DaisyUI 5.7.50, Tailwind 4.3.3,
in the repository's own `kdaisyui-tailwind:4.3.3` image. Scripts and outputs lived in
`/tmp/opencode/kdaisyui-bytecode-probe/`, not the `/tmp/kdaisyui-bytecode-probe/` `probe.md`
names: the house rule puts nothing directly under `/tmp`. Nothing in the repository was touched
except this file and `learning.md`.

**The baseline moved first.** The library now emits **551** classes (`kdaisyui-classes.txt`, 556
lines less the header), not 560. The stylesheet a consumer actually gets, the app's sources plus
the whole classes file, minified, is **373.8 KB raw / 46.0 KB gzip**. The September figures did
not say whether they were minified, so the thresholds are applied as written, in absolute KB. Both
size thresholds land the same way whether they are taken as absolute or rescaled to the new
baseline.

**Step 1.** Read from the sources, not the bytecode, which is what the probe allows: the example
app calls **29** distinct component functions across **19** of 66 components, and names **19**
enum entries. `values()`, `valueOf` and `entries` still appear zero times.

| list | rule | classes |
|---|---|---|
| all | `kdaisyui-classes.txt` | 551 |
| pessimistic, per component | every class of every component whose function is called | **227** |
| pessimistic, per function | base + boolean classes + every entry of every enum parameter of each called function | 202 |
| optimistic | base classes of the called functions + the named enum entries; no boolean | **47** |

**Step 2.** Each list compiled with `@import "tailwindcss" source(none)`, `@plugin "daisyui"`,
the app's Kotlin sources and the list as `@source`, minified, gzip `-9`:

| stylesheet | raw | gzip |
|---|---|---|
| today, app + all 551 | 373,793 | **45,996** |
| pessimistic, per component | 151,240 | **22,556** |
| pessimistic, per function | 144,860 | 21,860 |
| optimistic | 116,726 | **17,700** |
| app utilities only, no kdaisyui class | 100,374 | 15,605 |

Read against the signals:

- pessimistic list 227 < 350: **amplify condition met**;
- pessimistic stylesheet 22.6 KB ≤ 30 KB, 49% of today's: **amplify condition met**; the 42 KB
  dampen threshold is far away;
- optimistic to pessimistic: 22,556 − 17,700 = **4,856 bytes, under 5 KB: dampen signal fires.**

Steps 3 to 5 were **not run**. A dampen signal had fired, and its own text names the outcome.
`probe.md`'s step 3 gate ("only if 1 and 2 clear the amplify threshold") was met by the letter,
but building an extractor whose whole precision is worth 4.9 KB is what the dampen signal was
written to stop.

## Surprises

- **The first compile measured nothing, and looked like a result.** All six stylesheets came out
  byte-identical (same md5), including one with no kdaisyui class at all. Tailwind 4 also detects
  sources automatically from its working directory, and that directory held every list and the
  mounted library sources, so every compile saw all 551 classes. `source(none)` on the import
  fixed it. A flat result is the moment to distrust the instrument, not to conclude that DaisyUI
  ignores sources. It does not: with nothing scanned, the stylesheet is 11.0 KB.
- **`example-app`'s own `app.css` relies on that automatic detection without saying so.** It
  declares `@source "../lib-kotlin"`, but its import does not say `source(none)`, so anything else
  mounted under `/deps/project` is scanned too. Harmless today, because the task mounts only what
  it declares. It is an unstated dependency of the `css-delivery` setup.
- **The prize is component granularity, not class precision.** Of the 23.4 KB an extractor could
  save (46.0 → 22.6), the per-component rule already gets everything. Going from component to
  enum-entry precision adds only 4.9 KB, and knowing which **components** are called needs no
  constant-pool reading of enum `Fieldref`s, the part of the idea the hypothesis was proudest of.
- **DaisyUI has a per-component switch of its own,** `@plugin "daisyui" { include: … }`. With the
  19 touched components it compiled to 19.1 KB gzip, but it is not a drop-in: it also drops base
  layers nobody named (`rootscrolllock`, one of the six `data-theme` blocks). Not explored further.
- **`probe.md` points at a "step 6" that does not exist.** The amplify text says "Two out of three
  is step 6", and the experiment has five steps.

## What it cost

About 63 minutes, from opening the run to this file, against a half-day timebox and the two hours
`probe.md` gave steps 1 and 2. About 10 of those minutes went to the measurement that measured
nothing.
