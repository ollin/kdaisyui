<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/chat/+page.md
Regenerate: just generate
-->

# Chat

[DaisyUI documentation →](https://daisyui.com/components/chat/)

Chat bubble layout for conversations. Renders `<div class="chat ...">`.

```kotlin
// ChatVariant: BubbleNeutral | BubblePrimary | BubbleSecondary | BubbleAccent | BubbleInfo | BubbleSuccess | BubbleWarning | BubbleError
fun FlowContent.daisyChat(
    id: HtmlId? = null,
    variant: ChatVariant? = null,
    end: Boolean = false,
    start: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyChatImage(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyChatHeader(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyChatFooter(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```

```kotlin
fun FlowContent.daisyChatBubble(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)
```
