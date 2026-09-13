# generated-sources

## ADDED Requirements

### Requirement: A generated file's attribution resolves

The `// Source:` line every generated file carries SHALL name a path that exists in the pinned
DaisyUI submodule. Generated prose SHALL name the HTML element a function renders, not the
kotlinx.html builder that opens it.

An attribution that resolves to nothing is worse than none: it removes the reader's only way to
check generated output against its input while looking as though it provides one.

**Verified**, both halves, on 2026-09-12 against DaisyUI 5.7.17:

- **10 of 66** generated component files cite a directory that does not exist — every multi-word
  component, because the header lower-cases the PascalCase name (`FileInput.kt` cites
  `components/fileinput/`, the directory is `file-input`). The correct value is already carried as
  `ComponentSource.componentDir`.
- **2 files** name a kotlinx.html builder in prose rather than an element: `Fieldset.kt` says
  `Renders <fieldSet …>` and `Textarea.kt` says `<textArea …>`. The shape already distinguishes
  `tagBuilder` from `htmlTag`; only the Kotlin emitter conflates them.

*Wrong if:* a cited path stops resolving after a DaisyUI bump — which would mean the citation is
built from something other than the directory the component was read from.

#### Scenario: A multi-word component's attribution

- **WHEN** the codegen generates a component whose directory name contains a hyphen
- **THEN** its `// Source:` line names that directory
- **AND** the path exists in the pinned submodule

#### Scenario: Prose naming an element whose builder is spelled differently

- **WHEN** a generated function renders `<fieldset>`, `<textarea>` or any element kotlinx.html
  opens under a different name
- **THEN** its doc comment names the HTML element
- **AND** the emitted code still calls the kotlinx.html builder

### Requirement: Configuration that is never read is an error

The codegen SHALL fail when `codegen-config.json` holds a component-keyed entry that no lookup
consumed during a generation run.

A mis-keyed entry is indistinguishable from an absent one at runtime: the lookup returns
`undefined`, the caller's fallback swallows it, and the component is generated as though nothing
had been configured. Nothing fails, and the class-based safety net cannot help — a knob that
changes no CSS class is invisible to both the generated tests and the drift job.

**Verified** that this is the mechanism behind a shipped defect: `noContent` held `file-input` and
`theme-controller` while the lookup asked for `fileinput` and `themecontroller`, so
`daisyFileInput` and `daisyThemeController` demanded a content lambda on a void `<input>` for as
long as the entries existed.

**Verified** that the guard starts green: measured across all seven component-keyed sections on
2026-09-12, exactly two entries were unreachable, both in `noContent`, which is deleted. The other
six sections are clean, so a future failure is a new finding rather than inherited debt.

A key-**existence** check would not have caught it: `file-input` is a real DaisyUI directory. The
entry is not a typo, it is unread, and only consumption distinguishes the two.

*Wrong if:* the guard fires on an entry that is legitimately unread in some runs but not others,
which would mean consumption depends on run-time conditions rather than the component set.

#### Scenario: A config entry no lookup can reach

- **WHEN** a generation run completes with a component-keyed config entry that nothing read
- **THEN** the generator fails
- **AND** it names the section and the key

#### Scenario: A clean configuration

- **WHEN** every component-keyed config entry was consumed during the run
- **THEN** the generator succeeds

### Requirement: The Kotlin-level API surface is a committed, explicitly-updated artefact

The generated components' Kotlin signatures SHALL be dumped to a committed baseline covering
function name, extension receiver, parameter names, parameter types including lambda receiver
types, parameter order and default values. CI SHALL fail when the dump differs from the baseline.
The baseline SHALL be rewritten only by an explicit task, and SHALL NOT be part of the ordinary
regeneration command.

**Verified** that the existing JVM baseline cannot carry these facts. `lib/api/lib.api` is a dump
of JVM descriptors, which have no representation for receiver types, parameter names or default
values. Measured 2026-09-12:

```
daisyOtp (Lkotlinx/html/FlowContent;L…/HtmlId;L…/OtpVariant;L…/OtpSize;Z
          Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V
```

Changing a lambda's receiver from `DIV` to `LABEL` is source-breaking for every caller and
produces no diff there. In Kotlin, parameter names are equally part of the API because of named
arguments, and are equally absent.

**Verified** that the JVM baseline is not thereby useless: removing a parameter *is* visible, since
a `Function1` disappears and the arity changes. The two baselines answer different questions and
both are kept.

The explicit-update rule is what makes the artefact a gate rather than a mirror: a baseline
rewritten by the same command that regenerates the code would follow every change silently, which
is precisely the failure being fixed.

*Wrong if:* the dump is unstable across machines or runs — it is derived from the same model the
Kotlin is generated from, so instability there would already show as generated-source drift.

#### Scenario: A changed lambda receiver

- **WHEN** a component's rendered element changes, changing its lambda receiver type
- **THEN** the Kotlin API check fails
- **AND** the diff names the function and both receiver types

#### Scenario: A renamed parameter

- **WHEN** a generated function's parameter is renamed
- **THEN** the Kotlin API check fails, because named arguments make the name part of the API

#### Scenario: An intended API change

- **WHEN** a maintainer runs the explicit update task and commits the new baseline
- **THEN** the check passes
- **AND** the committed diff is the record of what changed
