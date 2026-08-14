// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/chat/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

enum class ChatVariant(internal val className: String) {
    /** CSS: `chat-bubble-neutral` */
    BubbleNeutral("chat-bubble-neutral"),
    /** CSS: `chat-bubble-primary` */
    BubblePrimary("chat-bubble-primary"),
    /** CSS: `chat-bubble-secondary` */
    BubbleSecondary("chat-bubble-secondary"),
    /** CSS: `chat-bubble-accent` */
    BubbleAccent("chat-bubble-accent"),
    /** CSS: `chat-bubble-info` */
    BubbleInfo("chat-bubble-info"),
    /** CSS: `chat-bubble-success` */
    BubbleSuccess("chat-bubble-success"),
    /** CSS: `chat-bubble-warning` */
    BubbleWarning("chat-bubble-warning"),
    /** CSS: `chat-bubble-error` */
    BubbleError("chat-bubble-error"),
}


/**
 * Chat bubbles are used to show one line of conversation and all its data, including the author image, author name, time, etc. Renders `<div class="chat ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param end
 * @param start
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyChat(
    id: HtmlId? = null,
    variant: ChatVariant? = null,
    end: Boolean = false,
    start: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("chat")
        if (variant != null) addClassNames(variant.className)
        if (end) addClassNames("chat-end")
        if (start) addClassNames("chat-start")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="chat-image ...">`. */
fun FlowContent.daisyChatImage(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("chat-image")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="chat-header ...">`. */
fun FlowContent.daisyChatHeader(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("chat-header")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="chat-footer ...">`. */
fun FlowContent.daisyChatFooter(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("chat-footer")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** Renders `<div class="chat-bubble ...">`. */
fun FlowContent.daisyChatBubble(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("chat-bubble")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
