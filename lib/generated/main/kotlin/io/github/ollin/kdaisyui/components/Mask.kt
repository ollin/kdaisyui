// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/mask/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.img
import kotlinx.html.IMG

/** Shape variants for this component (CSS prefix: `mask-`) */
enum class MaskShape(internal val className: String) : ClassValues<MaskShape> {
    /** CSS: `mask-squircle` — squircle */
    Squircle("mask-squircle"),
    /** CSS: `mask-heart` — heart */
    Heart("mask-heart"),
    /** CSS: `mask-hexagon` — hexagon vertical */
    Hexagon("mask-hexagon"),
    /** CSS: `mask-hexagon-2` — hexagon horizontal */
    Hexagon2("mask-hexagon-2"),
    /** CSS: `mask-decagon` — decagon */
    Decagon("mask-decagon"),
    /** CSS: `mask-pentagon` — pentagon */
    Pentagon("mask-pentagon"),
    /** CSS: `mask-diamond` — diamond */
    Diamond("mask-diamond"),
    /** CSS: `mask-square` — square */
    Square("mask-square"),
    /** CSS: `mask-circle` — circle */
    Circle("mask-circle"),
    /** CSS: `mask-star` — star */
    Star("mask-star"),
    /** CSS: `mask-star-2` — star (bold) */
    Star2("mask-star-2"),
    /** CSS: `mask-triangle` — triangle pointing top */
    Triangle("mask-triangle"),
    /** CSS: `mask-triangle-2` — triangle pointing down */
    Triangle2("mask-triangle-2"),
    /** CSS: `mask-triangle-3` — triangle pointing left */
    Triangle3("mask-triangle-3"),
    /** CSS: `mask-triangle-4` — triangle pointing right */
    Triangle4("mask-triangle-4"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Modifier variants for this component (CSS prefix: `mask-`) */
enum class MaskModifier(internal val className: String) : ClassValues<MaskModifier> {
    /** CSS: `mask-half-1` — Crops only the first half of mask */
    Half1("mask-half-1"),
    /** CSS: `mask-half-2` — Crops only the second half of mask */
    Half2("mask-half-2"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Mask crops the content of the element to common shapes. Renders `<img class="mask ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param shape — Shape variant
 * @param modifier — Modifier variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 */
fun FlowContent.daisyMask(
    id: HtmlId? = null,
    shape: ClassValues<MaskShape>? = null,
    modifier: ClassValues<MaskModifier>? = null,
    extraClasses: String? = null,
    attrs: (IMG.() -> Unit)? = null,
) {
    img {
        if (id != null) attributes["id"] = id.id
        addClassNames("mask")
        addClassNames(shape)
        addClassNames(modifier)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
    }
}
