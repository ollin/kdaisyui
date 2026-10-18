// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/aura/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/** Size variants for this component (CSS prefix: `aura-`) */
enum class AuraSize(internal val className: String) : ClassValues<AuraSize> {
    /** CSS: `aura-xs` — Extra small size */
    Xs("aura-xs"),
    /** CSS: `aura-sm` — Small size */
    Sm("aura-sm"),
    /** CSS: `aura-md` — Medium size (default) */
    Md("aura-md"),
    /** CSS: `aura-lg` — Large size */
    Lg("aura-lg"),
    /** CSS: `aura-xl` — Extra large size */
    Xl("aura-xl"),
    ;

    override val classNames: List<String> get() = listOf(className)
}

/** Style variants for this component (CSS prefix: `aura-`) */
enum class AuraStyle(internal val className: String) : ClassValues<AuraStyle> {
    /** CSS: `aura-dual` — Uses two light effects */
    Dual("aura-dual"),
    /** CSS: `aura-rainbow` — Uses rainbow colors for the light effect */
    Rainbow("aura-rainbow"),
    /** CSS: `aura-holo` — Uses holographic colors for the light effect */
    Holo("aura-holo"),
    /** CSS: `aura-gold` — Uses gold colors for the light effect */
    Gold("aura-gold"),
    /** CSS: `aura-silver` — Uses silver colors for the light effect */
    Silver("aura-silver"),
    /** CSS: `aura-glow` — Uses a glowing effect for the light instead of a rotating border */
    Glow("aura-glow"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Aura is a border light effect that can wrap around any component. It is a great way to add a cool, eye-catching visual effect to your components. Aura is useful for the most important button, card, or div that you want to highlight. Renders `<div class="aura ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param style — Style variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyAura(
    id: HtmlId? = null,
    size: ClassValues<AuraSize>? = null,
    style: ClassValues<AuraStyle>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("aura")
        addClassNames(size)
        addClassNames(style)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/** The classes `daisyAura` writes, on the element this is called on. */
fun Tag.daisyAuraClasses(
    size: ClassValues<AuraSize>? = null,
    style: ClassValues<AuraStyle>? = null,
) {
    addClassNames("aura")
    addClassNames(size)
    addClassNames(style)
}
