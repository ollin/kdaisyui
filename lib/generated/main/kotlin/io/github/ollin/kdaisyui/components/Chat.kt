// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/chat/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

enum class ChatBubbleColor(internal val className: String) : ClassValues<ChatBubbleColor> {
    /** CSS: `chat-bubble-neutral` */
    Neutral("chat-bubble-neutral"),
    /** CSS: `chat-bubble-primary` */
    Primary("chat-bubble-primary"),
    /** CSS: `chat-bubble-secondary` */
    Secondary("chat-bubble-secondary"),
    /** CSS: `chat-bubble-accent` */
    Accent("chat-bubble-accent"),
    /** CSS: `chat-bubble-info` */
    Info("chat-bubble-info"),
    /** CSS: `chat-bubble-success` */
    Success("chat-bubble-success"),
    /** CSS: `chat-bubble-warning` */
    Warning("chat-bubble-warning"),
    /** CSS: `chat-bubble-error` */
    Error("chat-bubble-error"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

enum class ChatPlacement(internal val className: String) : ClassValues<ChatPlacement> {
    /** CSS: `chat-start` */
    Start("chat-start"),
    /** CSS: `chat-end` */
    End("chat-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Chat bubbles are used to show one line of conversation and all its data, including the author image, author name, time, etc. Renders `<div class="chat ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param placement — Placement variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyChat(
    id: HtmlId? = null,
    placement: ClassValues<ChatPlacement>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("chat")
        addClassNames(placement)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyChat` writes, on the element this is called on. */
fun Tag.daisyChatClasses(
    placement: ClassValues<ChatPlacement>? = null,
) {
    addClassNames("chat")
    addClassNames(placement)
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

/** The classes `daisyChatImage` writes, on the element this is called on. */
fun Tag.daisyChatImageClasses() {
    addClassNames("chat-image")
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

/** The classes `daisyChatHeader` writes, on the element this is called on. */
fun Tag.daisyChatHeaderClasses() {
    addClassNames("chat-header")
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

/** The classes `daisyChatFooter` writes, on the element this is called on. */
fun Tag.daisyChatFooterClasses() {
    addClassNames("chat-footer")
}

/** Renders `<div class="chat-bubble ...">`. */
fun FlowContent.daisyChatBubble(
    id: HtmlId? = null,
    color: ClassValues<ChatBubbleColor>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("chat-bubble")
        addClassNames(color)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyChatBubble` writes, on the element this is called on. */
fun Tag.daisyChatBubbleClasses(
    color: ClassValues<ChatBubbleColor>? = null,
) {
    addClassNames("chat-bubble")
    addClassNames(color)
}
