## Context

The probe settled the granularity (component) and the size (46.0 → 22.6 KB gzip on `example-app`).
Oliver settled the delivery (a Gradle plugin). What is left is how the plugin knows the components,
where it lives so the repository itself can use it, and how it is released.

## Decisions

### 1. Detect by JVM class name, from a map the library ships

The plugin scans the bytes of the consumer's compiled classes for
`io/github/ollin/kdaisyui/components/<Name>` and looks every name found up in
`kdaisyui-components.txt`. The codegen writes that file into the `kdaisyui` jar, listing for each
component every JVM class its generated file declares (`ButtonKt`, `ButtonColor`, …) and every CSS
class it can emit.

**Rejected: prefix matching on names** (`Button*` → button). `Stat` is a prefix of `Status`. The
codegen knows the exact names, so the map says them.

**Rejected: ASM or another bytecode library.** A class name in a reference is a plain UTF-8 string
in the constant pool; finding it needs no parsing. Nothing is resolved, so nothing can be resolved
wrongly.

**Rejected: the plugin carrying its own copy of the lists.** A plugin at version X and a library at
version Y would disagree. Reading the map from the `kdaisyui` artifact on the consumer's runtime
classpath makes the lists the library's own.

### 2. Fail safe, loudly

Two cases give the full list plus a warning naming the reason: a referenced
`io/github/ollin/kdaisyui/components/` class the map does not know (versions out of step), and a
build that references no component at all (most likely a wrong classes directory). A narrower list
that is wrong renders an unstyled page with no error, the failure `css-delivery` exists to remove,
so every doubt resolves to more CSS.

### 3. An included build, so the repository applies its own plugin

A plugin cannot be applied from a sibling subproject's `plugins {}`. The usual answer is an
included build: `gradle-plugin/` with its own `settings.gradle.kts`, and
`pluginManagement { includeBuild("gradle-plugin") }` in the root settings. Then `example-app`
applies `id("io.github.ollin.kdaisyui")` and its stylesheet and E2E run on the narrowed list.

**Rejected: `buildSrc`.** Not publishable, and its tests do not run in `build`.

**Rejected: example-app staying on the full list,** with the plugin tested by TestKit only. Then
nothing would show that a narrowed stylesheet still styles every page. Showing that is A3's local
half.

The cost is in the release: the root `stageAll` must also stage the included build's publications
into `build/staging-deploy`. That is A6, checked first (task 1.1).

### 4. The consumer's side stays one file

The task writes `build/kdaisyui/kdaisyui-classes.txt`, the path today's README already points
Tailwind's `@source` at. A consumer swaps the `Copy`-from-jar snippet for the plugin and keeps its
CSS entry as it is. The consumer's CSS build must run after `kdaisyuiClasses`; the README shows the
`dependsOn`.

## Risks / trade-offs

- **One demo app measured.** A3 stays open until a real consumer reports a size.
- **Maven Central only** (A5). Consumers add `mavenCentral()` to `pluginManagement`, one line, and
  the README shows it.
