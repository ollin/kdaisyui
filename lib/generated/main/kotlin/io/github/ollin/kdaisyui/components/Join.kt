// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/join/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Direction variants for this component (CSS prefix: `join-`) */
enum class JoinDirection(internal val className: String) : ClassValues<JoinDirection> {
    /** CSS: `join-vertical` — Show items vertically */
    Vertical("join-vertical"),
    /** CSS: `join-horizontal` — Show items horizontally */
    Horizontal("join-horizontal"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Join is a container for grouping multiple items, it can be used to group buttons, inputs, etc. Join applies border radius to the first and last item. Join can be used to create a horizontal or vertical list of items. Renders `<div class="join ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param direction — Direction variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyJoin(
    id: HtmlId? = null,
    direction: ClassValues<JoinDirection>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("join")
        addClassNames(direction)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
