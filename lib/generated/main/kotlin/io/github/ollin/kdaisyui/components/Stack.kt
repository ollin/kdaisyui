// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/stack/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/** Placement variants for this component (CSS prefix: `stack-`) */
enum class StackPlacement(internal val className: String) : ClassValues<StackPlacement> {
    /** CSS: `stack-top` — Aligns the children elements to the top */
    Top("stack-top"),
    /** CSS: `stack-bottom` — Aligns the children elements to the bottom */
    Bottom("stack-bottom"),
    /** CSS: `stack-start` — Aligns the children elements to the start (horizontally) */
    Start("stack-start"),
    /** CSS: `stack-end` — Aligns the children elements to the end (horizontally) */
    End("stack-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Stack visually puts elements on top of each other. Renders `<div class="stack ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param placement — Placement variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyStack(
    id: HtmlId? = null,
    placement: ClassValues<StackPlacement>? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("stack")
        addClassNames(placement)
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyStack` writes, on the element this is called on. */
fun Tag.daisyStackClasses(
    placement: ClassValues<StackPlacement>? = null,
) {
    addClassNames("stack")
    addClassNames(placement)
}
