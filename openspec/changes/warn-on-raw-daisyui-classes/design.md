## Context

See proposal.md. The plugin already reads the compiled main classes twice:

- `kdaisyuiClasses` regex-scans the bytes for component class names, against
  `kdaisyui-components.txt` from the runtime classpath.
- `kdaisyuiThemes` reads member references through its own constant-pool reader
  (`ConstantPool.kt`). That reader skips tag 8 (`CONSTANT_String`) by its size.

The component map lists each component's CSS classes in its third column. The plugin does not
depend on `lib`.

## Decisions

### 1. Read string constants from the pool, not the bytes

**Chosen:** the pool reader keeps tag 8 as a reference to its UTF-8 entry. A new function returns
the class file's string constants. A string literal and an indy concatenation recipe are both
`CONSTANT_String` (JVMS 4.4.3). The `\u0001` holes of a recipe split tokens like whitespace.

**Rejected: every UTF-8 entry.** That set also holds member names, descriptors and the Kotlin
metadata strings, so a method named `card` would count.

**Rejected: a regex over the bytes**, as `kdaisyuiClasses` uses. It cannot tell where a string
starts, so `"card bg-base-100"` could not be reported as the string it is.

### 2. The class-list rule

**Chosen:** the three conditions of the spec, measured in proposal A2. The `-`/`:` condition
separates `"the link"` from `"link font-mono"`.

**Rejected: hyphenated DaisyUI classes only.** That would miss the base class in
`"card bg-base-100"`, the commonest raw site of the example app (12 of its 32 literals).

**Rejected: also reporting single bare words.** `"link"`, `"dropdown"` and `"menu"` are as
likely to be text or a route segment as a class.

### 3. A finalizer of `kdaisyuiClasses`, uncached, in a task of its own

**Chosen:** `kdaisyuiRawClasses`, `@DisableCachingByDefault`, with no output, and
`kdaisyuiClasses.finalizedBy(it)`. Every build that compiles or packages a stylesheet runs
`kdaisyuiClasses`, so the check runs there. It is cheaper than the theme check's anchor because
it needs no stylesheet.

**Rejected: inside `kdaisyuiClasses`.** That task is cacheable, and a cache hit would skip the
warning.

**Rejected: merging into `kdaisyuiThemes`**, which reads the same classes. It finalizes the
stylesheet, not the class list, and its name would then lie. One more scan of the class files is
measured in milliseconds.

**Rejected: a dependency of `check`.** A build that applies the plugin only for its class list
would have to run a check it did not ask for, and `check` would not be where a `failBuild` user
first sees the result anyway.

### 4. No ignore list

**Chosen:** a false positive is fixed in the code that triggers it, as the example's id
`tab-active` is renamed. The one measured case is an id named after a class.

**Rejected: `rawClasses { ignore = setOf(…) }`.** It is API for one measured case, and a list a
build carries forever. It can be added without breaking anyone if a consumer needs it.

### 5. Option shape

**Chosen:** `kdaisyui { rawClasses { failBuild = true } }`, a nested `RawClassesSettings` beside
`stylesheet`. Its convention is `false`.

**Rejected: a flat `failOnRawClasses`.** The plugin's settings are grouped by task, and a second
setting of the check would need the group anyway.

### 6. The warning text

```
kdaisyui: 2 strings write DaisyUI classes the typed API writes for you:
  kdaisyui/example/DashboardShellKt: "btn btn-circle btn-ghost" (button)
  kdaisyui/example/team/TeamFragmentKt: "card-body" (card)
  Use the component's function, or a { daisy<Component>Classes(…) } on another element.
```

The owner is the class file's internal name without `$…`, so a lambda reports its file's class.
Lines are sorted, and equal lines are listed once.

**Rejected: the source file and line.** The `SourceFile` attribute and the line table are
attributes of the class and the method, which the reader does not parse. The `…Kt` name already
names the file.

### 7. Tested in-process

As for the theme check: pure functions return values and text, and the task action runs on a
`ProjectBuilder` project. The plugin's own test classes give real class files. No TestKit, which
Kover cannot see.

## Enablers and desired changes

- **Enablers:** none in the plugin; tag 8 is new behaviour of the reader.
- **Enablers in the example app**: moving the 32 literals to typed calls. They change class
  *order*, never class *sets*. The E2E suite cannot prove that, because it asserts the classes of
  only five places (`TabsTest.kt:22`, `MegamenuWithoutJavaScriptTest.kt:22,24`,
  `PopoverModalTest.kt:19`, `StatsFragmentTest.kt:27`, `TeamFragmentTest.kt:29`). So each step is
  also proven by the class-set diff of task 1.3: every page and fragment rendered, and each
  element's `class` attribute compared as a set, before and after. Renaming the id `tab-active`
  is a step of its own, with `TabsTest.kt`.
- **Desired changes:** the string constants, the rule, the warning, the task, the option, the
  wiring, and the example app's `failBuild`.

## Risks / Trade-offs

- **A consumer whose ids or `data-` values look like class lists gets warnings it cannot
  silence.** The same goes for Tailwind utilities that share a DaisyUI class's name: `table`,
  `filter` and `collapse`, in a string such as `"filter drop-shadow-lg"`. Neither measured
  codebase has one. The default is a warning, and decision 4 says how an ignore list can follow.
- **The pool's modified UTF-8 is decoded as UTF-8.** NUL and supplementary characters come out
  mangled, which is harmless here: such a string is not class-shaped.
- **A class list built at runtime from pieces escapes the check**, for example
  `"btn-" + size`. The check sees only literals. Such code is the problem `css-delivery` already
  names for Tailwind, so it is broken twice and Tailwind will not style it either.
