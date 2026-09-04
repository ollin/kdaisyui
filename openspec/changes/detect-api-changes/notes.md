# Notes

Measurements from sections 1 and 2. Both assumptions held, so sections 3-5 stand as planned.

## 1.3 — the mechanism is Kotlin's built-in ABI validation

Kotlin 2.4.10 has it. `kotlin { abiValidation { } }` configures, and `:lib` gains:

| Task | What |
|---|---|
| `updateKotlinAbi` | writes the reference dump |
| `checkKotlinAbi` | fails when the current ABI diverges from it |
| `internalDumpKotlinAbi` | dumps into the build directory; both of the above depend on it |

So `binary-compatibility-validator` (task 1.2) is not needed: **no new dependency, no new
plugin.** The dump lands at `lib/api/lib.api`.

**How it was established, since the earlier attempt read as absence.** Applying the DSL wrongly
is what proved it exists — the compiler rejected `enabled.set(true)` with *"Property was removed,
to enable ABI validation call function abiValidation(), abiValidation { … }"* and flagged the
block as *"The ABI Validation DSL is experimental"*. Both messages are about a DSL that is
present. An unresolved reference would have said so. Two consequences for the wiring:

- the opt-in annotation is `org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation`, and it
  goes on the `kotlin { }` block rather than inside it — an annotation argument inside the block
  is not a compile-time constant there;
- there is no `enabled` property any more. Calling `abiValidation { }` *is* enabling it.

**The one thing that shapes the rest of the change:** `checkKotlinAbi` puts itself into
`:lib:check`. Verified with `:lib:check --dry-run`, which lists `:lib:internalDumpKotlinAbi` and
`:lib:checkKotlinAbi`. So the gate needs no CI wiring to *run* locally — but it also means the
DSL cannot be committed before the baseline exists, or `check` is red on that commit. Task 4.2
inherits the real question: CI's `unit-tests` job runs `:lib:test koverVerify koverXmlReport`,
**not** `check`, so the gate would not run in CI by itself.

## 2.5 — both cases are caught, including the silent one

Method: dump, edit `lib/generated` by hand in the working tree, re-dump, `git diff` the dump,
revert. The dump is a JVM-descriptor listing, so the questions are about descriptors.

### The silent case — a parameter inserted mid-signature (assumption 2)

Inserted `dummyProbe: Boolean = false` between `center` and `end` on `daisyTooltip`, which is the
shape of the 0.2.0 hazard: parameters are sorted alphabetically, so a new modifier lands in the
middle and every positional call site keeps compiling with shifted meaning.

```diff
-	public static final fun daisyTooltip (…TooltipVariant;ZZZZZZZZLjava/lang/String;…)V
-	public static synthetic fun daisyTooltip$default (…TooltipVariant;ZZZZZZZZLjava/lang/String;…)V
+	public static final fun daisyTooltip (…TooltipVariant;ZZZZZZZZZLjava/lang/String;…)V
+	public static synthetic fun daisyTooltip$default (…TooltipVariant;ZZZZZZZZZLjava/lang/String;…)V
```

**Detected** — eight `Z` became nine, on the function and on its `$default` synthetic.

**With one limit worth stating in the spec.** Parameter *names* are not part of an ABI, so the
dump says *the signature changed*; it does not say the parameter was inserted rather than
appended. Appending a ninth boolean produces the same diff. That is enough for the purpose —
the gate's job is to stop a signature change passing unread — but it is not a parameter-shift
detector, and the requirements should not claim it is.

### The control — a removed enum entry

Deleted `TooltipVariant.Error`, standing in for the `TooltipVariant.Neutral` removal that 0.2.0
actually shipped.

```diff
 public final class io/github/ollin/kdaisyui/components/TooltipVariant : java/lang/Enum {
 	public static final field Accent Lio/github/ollin/kdaisyui/components/TooltipVariant;
-	public static final field Error Lio/github/ollin/kdaisyui/components/TooltipVariant;
 	public static final field Info Lio/github/ollin/kdaisyui/components/TooltipVariant;
```

**Detected**, as expected — one deleted line, and a reviewer can read what it means without
knowing anything about descriptors.

### What this buys

The 0.2.0 break would have turned the dump red and required a human to re-dump deliberately.
That is the whole gap this change set out to close, and it closes with no new dependency.

## 4.3 — the gate bites locally; the CI half is still owed

Deleted `TooltipVariant.Error` again, this time against the **committed** baseline, and ran the
command the `api-baseline` job runs:

```
> Task :lib:checkKotlinAbi FAILED
ABI check failed for project lib

<<<ABI has changed>>>
--- lib/api/lib.api
+++ lib/build/kotlin/abi/lib.api
@@ -939,7 +939,6 @@
 public final class io/github/ollin/kdaisyui/components/TooltipVariant : java/lang/Enum {
 	public static final field Accent …;
-	public static final field Error …;
 	public static final field Info …;

You can run ':lib:updateKotlinAbi' task to create or overwrite reference ABI declarations
```

Non-zero exit, the diff in the failure output, and a message naming the remedy. So **the command
the job runs is proven to fail on the 0.2.0 break's exact shape.**

**What is not yet proven, and is the point of this task:** that the *job* fails — that
`api-baseline` triggers on a pull request and reports red. That needs a branch pushed and a PR
opened, and the change set is not adopted yet. Deliberately left open rather than ticked on the
strength of the local run, because "a gate nobody has seen fail is not known to be a gate" is
about the CI wiring, not about the Gradle task. It also explains the `! F` on the job's commit.

Watch for the trap recorded in the `kdaisyui-release` skill: a PR whose `mergeable_state` is
`dirty` runs **no** workflows at all, so an absent check reads like a passing one.
