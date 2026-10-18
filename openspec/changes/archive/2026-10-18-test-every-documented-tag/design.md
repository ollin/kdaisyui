## Context

See `proposal.md` for why. The classifier (`codegen/src/documented-elements.ts`) already splits an
element's classes into claiming groups and builds a call; the test generator wraps it by the
function's receiver and compares the sorted class list. Class siblings exist for every function
since `express-every-documented-element`.

kotlinx.html 0.12.0 has a builder for every tag DaisyUI's examples use except `calendar-date`. Two
builders need a particular receiver: `li` on `UL`/`OL`, `legend` on `DETAILS`/`FIELDSET`/`FIGURE`;
all others are reachable from a `div`.

## Decisions

### D1. The call carries the tag it renders

`documentedCall` returns, besides the function and arguments, whether the primary function renders
the documented tag. When it does not, the test writes the tag's builder and every group, the
primary's included, as siblings:

```kotlin
val html = createHTML(prettyPrint = false).div {
    a { daisyButtonClasses(color = ButtonColor.Primary) }
}
```

*Rejected:* a function per (component, tag), such as `daisyButtonLink`. 45 shapes would become 45
functions to name and keep, for what a sibling on the tag already says.

### D2. The test asserts the tag through the first class attribute

The documented element is the first element of the rendered HTML carrying a `class`: wrappers and
parents (`div`, `ul`, `fieldSet`) carry none. The test asserts the tag that attribute sits on, then
the classes, as now.

### D3. Owner tie-break: equally much, then the documented tag

`ownerOf` keeps "offers the most of the element's classes" first; among equals, the function whose
element is the documented tag. It changes which function some existing tests call (`daisyTabRadio`
for `<input class="tab">`), and those tests then need no sibling form.

### D4. A tag without a builder fails generation; `calendar-date` gets one in core

The gate knows the builders as a table in `codegen/src/html-names.ts` beside the existing
builder-name exceptions, plus the library's own. `calendar-date` becomes a hand-written custom
element in `lib/src/main/kotlin/io/github/ollin/kdaisyui/core/`:

```kotlin
class CALENDAR_DATE(consumer: TagConsumer<*>) : HTMLTag("calendar-date", consumer, emptyMap(), null, false, false), HtmlBlockTag
fun FlowContent.calendarDate(block: CALENDAR_DATE.() -> Unit = {}) = CALENDAR_DATE(consumer).visit(block)
```

and `daisyCalendar` renders it. The generator imports the element and builder from core instead of
`kotlinx.html` for this one tag, read from the same table.

*Rejected:* `customTag`-style raw strings in the generated body: the lambda receiver would lose its
type, and `attrs` would no longer see a named element.

## Enablers and desired changes

| Enabler, zero diff | Desired change |
|---|---|
| the call reports whether its function renders the documented tag | tests at the documented tag (D1, D2) |
| | the owner tie-break (D3) |
| the builder table (D4) | the gate on missing builders, `daisyCalendar` on `<calendar-date>` |

## Risks / Trade-offs

- [Tests at the tag rewrite many generated tests] → each regenerated file is reviewed by its count
  of tag-form tests; the class assertions do not change.
- [A custom element in core is hand-written Kotlin] → covered by the generated calendar tests and
  `koverVerify`, like the rest of core.
