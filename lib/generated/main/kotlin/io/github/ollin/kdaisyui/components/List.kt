// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/list/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.ul
import kotlinx.html.UL

/** Modifier variants for this component (CSS prefix: `list-`) */
enum class ListModifier(internal val className: String) : ClassValues<ListModifier> {
    /** CSS: `list-col-wrap` — For one of direct children of list-row to push it to the next line */
    ColWrap("list-col-wrap"),
    /** CSS: `list-col-grow` — For one of direct children of list-row to make it fill the remaining space */
    ColGrow("list-col-grow"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * List is a vertical layout to display information in rows. Renders `<ul class="list ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param modifier — Modifier variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyList(
    id: HtmlId? = null,
    modifier: ClassValues<ListModifier>? = null,
    extraClasses: String? = null,
    attrs: (UL.() -> Unit)? = null,
    content: (UL.() -> Unit),
) {
    ul {
        if (id != null) attributes["id"] = id.id
        addClassNames("list")
        addClassNames(modifier)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
