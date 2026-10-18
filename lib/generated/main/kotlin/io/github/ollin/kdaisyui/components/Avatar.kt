// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/avatar/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

enum class AvatarPresence(internal val className: String) : ClassValues<AvatarPresence> {
    /** CSS: `avatar-online` */
    Online("avatar-online"),
    /** CSS: `avatar-offline` */
    Offline("avatar-offline"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Avatars are used to show a thumbnail representation of an individual or business in the interface. Renders `<div class="avatar ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param presence — Presence variant
 * @param placeholder
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyAvatar(
    id: HtmlId? = null,
    presence: ClassValues<AvatarPresence>? = null,
    placeholder: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("avatar")
        addClassNames(presence)
        if (placeholder) addClassNames("avatar-placeholder")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyAvatar` writes, on the element this is called on. */
fun Tag.daisyAvatarClasses(
    presence: ClassValues<AvatarPresence>? = null,
    placeholder: Boolean = false,
) {
    addClassNames("avatar")
    addClassNames(presence)
    if (placeholder) addClassNames("avatar-placeholder")
}

/** Renders `<div class="avatar-group ...">`. */
fun FlowContent.daisyAvatarGroup(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("avatar-group")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyAvatarGroup` writes, on the element this is called on. */
fun Tag.daisyAvatarGroupClasses() {
    addClassNames("avatar-group")
}
