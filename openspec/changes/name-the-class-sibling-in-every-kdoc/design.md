## Context

See proposal.md, Why. `renderKdoc` in `codegen/src/generator-new.ts` builds one summary line
from the component's description and the `Renders <tag …>` clause, adds `@param` lines for a
main function, and emits a one-line or multi-line comment. The sibling,
`Tag.<name>Classes(…)`, is emitted further down for every function with an own class.

## Goals / Non-Goals

**Goals:**

- A developer hovering `daisyBadge` in the IDE learns that `daisyBadgeClasses` exists, and
  can click to it.

**Non-Goals:**

- Changing the reference pages or `llms-full.txt`; they already say it.
- Any change of signature.

## Decisions

### 1. One sentence with a KDoc link, after the summary line

**Chosen:** `The same classes on another element: \`a { [daisyBadgeClasses](…) }\`.` as the
second line of the comment, before the `@param` lines. The square-bracket link is what the IDE
resolves; the `a { … }` form is the one call shape a reader has to learn.

**Rejected: `@see daisyBadgeClasses`.** It links, and says nothing about why or how; the
Deploy-Radar's author needed the call shape.

**Rejected: naming the element the function renders instead of `a`**, such as
`span { daisyBadgeClasses() }`. True and useless: the point is another element.

### 2. The sentence is emitted where the sibling is decided

The condition is the one the sibling emitter uses, `shape.cssClass !== null`, read from the
shape in `renderKdoc`. One condition in two places would drift; the task extracts it into a
named predicate first (an enabler, provable by an empty regeneration), then uses it twice.

### 3. Enablers and the desired change

Enabler: the `hasClassSibling(shape)` predicate, with the regeneration diff empty. Desired
change: the sentence. Then the how-to.

## Risks / Trade-offs

- [129 generated files change in one commit] → by regeneration, with the diff read as "one
  KDoc line each, nothing else", which `git diff --stat` and a grep for `+ * The same classes`
  confirm mechanically.
- [Kotlin's KDoc renders the bracket link oddly in some tools] → `[name](…)` is standard KDoc;
  Dokka and IntelliJ both resolve it.
