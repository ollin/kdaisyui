## What we expected

Copied from probe.md before writing anything below.

**Amplify:** emitter source visibly shorter or clearer than `generator-new.js` for the same
output; the five axes land as distinct types and `daisyButton(outline = true, ghost = true)`
becomes impossible; KotlinPoet handles receiver lambdas and defaults without escape hatches;
Tooltip works from the same model without special-casing.

**Dampen:** KotlinPoet needs `CodeBlock.of("…")` string assembly for the constructs we care
about; the typed model needs a per-component escape hatch; Button takes substantially more
Kotlin than the JS it replaces without reading better; Tooltip's `placement` does not fit the
model built for Button's `style`.

## What happened

All six steps completed inside the timebox.

**KotlinPoet carried every construct with no escape hatch.** Receiver extension
(`FlowContent.daisyButton`), nullable lambda-with-receiver (`(BUTTON.() -> Unit)? = null`),
default arguments, and enums with a constructor property plus per-constant KDoc. The only
`CodeBlock` use is for the function body's statements, which is what `CodeBlock` is for.

**The axes became types.** `ButtonColor`, `ButtonStyle`, `ButtonSize` — and
`daisyButton(outline = true, ghost = true)` is now unwriteable, because `style` is one
parameter. Button emits **13 parameters against today's 19**.

**Tooltip needed no special-casing.** `placement` became `TooltipPlacement` from the same
`EXCLUSIVE_CATEGORIES` set that produced `ButtonStyle`; its single `modifier` stayed a flag.
Nothing in the emitter knows what a tooltip is.

**DaisyUI's `default: true` marker survived.** `TooltipPlacement` carries "DaisyUI default:
tooltip-top" in its KDoc. The JavaScript parser drops this field.

**All three failures were Gradle configuration, none were the emitter:**
1. `kotlin("plugin.serialization")` cannot be versioned per-module when the Kotlin plugin is
   already on the classpath — it had to go into `settings.gradle.kts` `pluginManagement`.
   **The declared blast radius grew from one line of shared config to two.**
2. `run` executes with the module directory as working directory; needed
   `workingDir = rootDir`.
3. I wrote `kaml:0.114.0` from memory. It does not exist. Looked it up: 0.104.0.

## Surprises

**The line-count comparison is not honest, and I nearly reported it as if it were.**
`Emitter.kt` is ~130 lines against `generator-new.js`'s ~470, but the Kotlin version is *not
feature-equivalent*. It omits `attrs`, the `type: ButtonType?` extra that
`codegen-config.json` injects, the `disabled` special case that also sets the HTML attribute,
and the text/content precedence (today: `when { content != null -> …; text != null -> … }`;
mine calls both). A fair number is unknown until those exist.

**KotlinPoet emitted `` `open` `` where plain `open` is correct** — and I first read this as
KotlinPoet catching a bug the JS generator missed. Wrong. `open` is a Kotlin *soft* keyword,
legal as a parameter name; today's generated `Tooltip.kt`, `Dropdown.kt`, `Drawer.kt`,
`Modal.kt` and `Collapse.kt` all use it unescaped and compile. So this is KotlinPoet being
conservative and making the output slightly *worse*. A small point against, not for.

**My own `parameterCount` is off by one** — it counts `attrs`, which the probe does not emit.
The probe printed "button -> 14 parameters"; 13 are actually emitted. A reminder that a
number a tool prints about itself is not evidence.

**The resistance was entirely in Gradle, not in the idea.** Three failures, all build setup,
none in the model or the emitter. That is worth carrying into any estimate: the cost of this
port is likely dominated by build plumbing, not by translation.

## What writing the tests found, after the probe said it worked

The probe was declared done and "amplify" before a single test existed. Adding 21 tests
afterwards found **a defect that generating two components had not**:

`Emitter` derived the kotlinx.html builder function by lowercasing the element name. That is
correct for `BUTTON` and `DIV` — the two components the probe generated — and wrong for
`TEXTAREA` and `FIELDSET`, which kotlinx.html spells `textArea` and `fieldSet`. Any component
on those elements would have emitted Kotlin that does not compile. The JavaScript generator
has carried the exception table since generator-new.js:10-15; I did not look.

**A probe that only demonstrates success on the cases it chose under-reports its own risk.**
Two green components read as "the model generalises"; they were also two components that
happened to miss the assumption. Tooltip proved generalisation across *axes* and said nothing
about generalisation across *elements*.

The second defect was the parameter count the probe printed about itself — 14 where 13 were
emitted, because the model duplicated knowledge the emitter owns. Also invisible until
something checked it.

## What it cost

One session, comfortably inside the timebox, including the three failures. Roughly 350 lines
of Kotlin across four files, plus two lines of shared Gradle config.

Blast-radius obligation met: `:lib:test` passes and `:lib:generateComponents` still produces
its existing output. Nothing outside `codegen-kotlin/` and the two `settings.gradle.kts`
lines was touched; the JavaScript generator is untouched and still the one the build uses.
