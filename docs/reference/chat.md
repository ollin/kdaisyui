<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/chat/+page.md
Regenerate: just generate
-->

# Chat

[DaisyUI documentation →](https://daisyui.com/components/chat/)

Chat bubble layout for conversations. Renders `<div class="chat ...">`.

```kotlin
// ChatBubbleColor: Neutral | Primary | Secondary | Accent | Info | Success | Warning | Error
// ChatPlacement: Start | End
fun FlowContent.daisyChat(
    id: HtmlId? = null,
    placement: ClassValues<ChatPlacement>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyChatClasses(placement: ClassValues<ChatPlacement>? = null)
```

```kotlin
fun FlowContent.daisyChatImage(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyChatImageClasses()
```

```kotlin
fun FlowContent.daisyChatHeader(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyChatHeaderClasses()
```

```kotlin
fun FlowContent.daisyChatFooter(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyChatFooterClasses()
```

```kotlin
fun FlowContent.daisyChatBubble(
    id: HtmlId? = null,
    color: ClassValues<ChatBubbleColor>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyChatBubbleClasses(color: ClassValues<ChatBubbleColor>? = null)
```
