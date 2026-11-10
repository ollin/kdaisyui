// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/divider/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/** Color variants for this component (CSS prefix: `divider-`) */
enum class DividerColor(internal val className: String) : ClassValues<DividerColor> {
    /** CSS: `divider-neutral` — neutral color */
    Neutral("divider-neutral"),
    /** CSS: `divider-primary` — primary color */
    Primary("divider-primary"),
    /** CSS: `divider-secondary` — secondary color */
    Secondary("divider-secondary"),
    /** CSS: `divider-accent` — accent color */
    Accent("divider-accent"),
    /** CSS: `divider-success` — success color */
    Success("divider-success"),
    /** CSS: `divider-warning` — warning color */
    Warning("divider-warning"),
    /** CSS: `divider-info` — info color */
    Info("divider-info"),
    /** CSS: `divider-error` — error color */
    Error("divider-error"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Direction variants for this component (CSS prefix: `divider-`) */
enum class DividerDirection(internal val className: String) : ClassValues<DividerDirection> {
    /** CSS: `divider-vertical` — Divide vertical elements (on top of each other) */
    Vertical("divider-vertical"),
    /** CSS: `divider-horizontal` — Divide horizontal elements (next to each other) */
    Horizontal("divider-horizontal"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Placement variants for this component (CSS prefix: `divider-`) */
enum class DividerPlacement(internal val className: String) : ClassValues<DividerPlacement> {
    /** CSS: `divider-start` — Pushes the divider text to the start */
    Start("divider-start"),
    /** CSS: `divider-end` — Pushes the divider text to the end */
    End("divider-end"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Divider will be used to separate content vertically or horizontally. Renders `<div class="divider ...">`.
 * The same classes on another element: `a { daisyDividerClasses(…) }`; see [daisyDividerClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param color — Color variant
 * @param direction — Direction variant
 * @param placement — Placement variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyDivider(
    id: HtmlId? = null,
    color: ClassValues<DividerColor>? = null,
    direction: ClassValues<DividerDirection>? = null,
    placement: ClassValues<DividerPlacement>? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("divider")
        addClassNames(color)
        addClassNames(direction)
        addClassNames(placement)
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyDivider` writes, on the element this is called on. */
fun Tag.daisyDividerClasses(
    color: ClassValues<DividerColor>? = null,
    direction: ClassValues<DividerDirection>? = null,
    placement: ClassValues<DividerPlacement>? = null,
) {
    addClassNames("divider")
    addClassNames(color)
    addClassNames(direction)
    addClassNames(placement)
}
