## Context

See proposal.md, Why. `renderKdoc` in `codegen/src/generator-new.ts:88` builds one summary
line from the component's description and the `Renders <tag …>` clause, adds `@param` lines for
a main function, and emits a one-line comment when that is all, else a block. Whether a
function has a class sibling is one exported predicate, `hasSibling` in
`codegen/src/component-shape.ts:367`: an own class, or modifier classes, or a class-writing
parameter. The sibling emitter (`generator-new.ts:380`), the reference-page generator and the
API dump all read it.

## Goals / Non-Goals

**Goals:**

- A developer hovering `daisyBadge` in the IDE learns that `daisyBadgeClasses` exists, and
  can click to it.

**Non-Goals:**

- Changing the reference pages or `llms-full.txt`; they already say it.
- Any change of signature.

## Decisions

### 1. One sentence, second line of the comment, with the call shape and a KDoc link

**Chosen:** the exact emitted string, for `daisyBadge`:

```
The same classes on another element: `a { daisyBadgeClasses(…) }`; see [daisyBadgeClasses].
```

The code span shows the call shape; the square-bracket link, KDoc's only link syntax, is what
the IDE resolves. It is the comment's second line, before the `@param` lines of a main
function; a part whose comment was one line becomes a two-line block, which `renderKdoc`
already does once there is a second line.

**Rejected: `@see daisyBadgeClasses`.** It links, and says nothing about why or how; the
Deploy-Radar's author needed the call shape.

**Rejected: naming the element the function renders instead of `a`**, such as
`span { daisyBadgeClasses() }`. True and useless: the point is another element. A5 puts the
`a` to Oliver.

### 2. The condition is `hasSibling`, nothing new

The sentence is emitted when `hasSibling(shape)` holds, the same call the sibling emitter
makes, so the two cannot drift. The proposal first assumed the condition was "own class is not
null" and planned an enabler to extract it; the review found the predicate exists and is wider
(finding F2), so there is no enabler and the change is one line of emitter plus tests.

The join scope's member wrappers (`generator-new.ts:147`, `[name], marked …`) are not rendering
functions with a sibling; they keep their comment.

### 3. Enablers and the desired change

No enabler. Desired change: the sentence. Then the how-to.

## Risks / Trade-offs

- [66 generated files change in one commit] → by regeneration, with the diff read as "129
  sentences, comments reshaped, nothing else", which a grep for the sentence and `git diff
  --stat` confirm mechanically.
- [The wording turns out wrong for a function such as `daisyMenu`] → it stays true for every
  function (`a { daisyMenuClasses() }` is a valid call); A5 lets Oliver change the example
  before task 2.1.
