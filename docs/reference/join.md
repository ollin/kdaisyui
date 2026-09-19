<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/join/+page.md
Regenerate: just generate
-->

# Join

[DaisyUI documentation →](https://daisyui.com/components/join/)

Groups buttons and inputs together. Renders `<div class="join ...">`.

```kotlin
// JoinDirection: Vertical | Horizontal
fun FlowContent.daisyJoin(
    id: HtmlId? = null,
    direction: ClassValues<JoinDirection>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (JoinScope.() -> Unit),
)
```

## `join-item`

Inside `daisyJoin { }` a component call marks itself. The scope carries a member for each
component DaisyUI documents as an item, and a member wins over the top-level function of the
same name.

```kotlin
daisyJoin {
    daisyButton()                       // marked `join-item`
    div {
        daisyButton()                   // NOT marked — the scope is hidden here
        this@daisyJoin.daisyButton()    // marked, named deliberately
    }
}
```

The nested call is not marked because kotlinx.html's `@HtmlTagMarker` is a `@DslMarker`:
inside another builder this receiver is hidden, so the call reaches the ordinary top-level
function. That follows the markup — DaisyUI documents the wrapper's child as the item, not
the wrapper.

There is no parameter for this, deliberately. `.join` writes four corner-radius
variables onto its direct children and `.join-item` reads them, so outside a
`.join` the class strips the element's own corners rather than doing nothing.

Marks itself: `daisyButton`, `daisyCard`, `daisyCollapse`, `daisyInput`, `daisySelect`, `daisyThemeController`, `daisyValidator`.
