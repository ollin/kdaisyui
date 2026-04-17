// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/chat/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

enum class ChatVariant(internal val className: String) {
    BubbleNeutral("chat-bubble-neutral"),
    BubblePrimary("chat-bubble-primary"),
    BubbleSecondary("chat-bubble-secondary"),
    BubbleAccent("chat-bubble-accent"),
    BubbleInfo("chat-bubble-info"),
    BubbleSuccess("chat-bubble-success"),
    BubbleWarning("chat-bubble-warning"),
    BubbleError("chat-bubble-error"),
}


fun FlowContent.daisyChat(
    variant: ChatVariant? = null,
    end: Boolean = false,
    start: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("chat")
        if (variant != null) addClassNames(variant.className)
        if (end) addClassNames("chat-end")
        if (start) addClassNames("chat-start")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyChatImage(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("chat-image")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyChatHeader(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("chat-header")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyChatFooter(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("chat-footer")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyChatBubble(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("chat-bubble")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
