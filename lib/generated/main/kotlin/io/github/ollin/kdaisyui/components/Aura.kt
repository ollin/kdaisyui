// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/aura/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Size variants for this component (CSS prefix: `aura-`) */
enum class AuraSize(internal val className: String) {
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
}


/**
 * Aura is a border light effect that can wrap around any component. It is a great way to add a cool, eye-catching visual effect to your components. Aura is useful for the most important button, card, or div that you want to highlight. Renders `<div class="aura ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param dual — Uses two light effects
 * @param glow — Uses a glowing effect for the light instead of a rotating border
 * @param gold — Uses gold colors for the light effect
 * @param holo — Uses holographic colors for the light effect
 * @param rainbow — Uses rainbow colors for the light effect
 * @param silver — Uses silver colors for the light effect
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyAura(
    id: HtmlId? = null,
    size: AuraSize? = null,
    dual: Boolean = false,
    glow: Boolean = false,
    gold: Boolean = false,
    holo: Boolean = false,
    rainbow: Boolean = false,
    silver: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("aura")
        if (size != null) addClassNames(size.className)
        if (dual) addClassNames("aura-dual")
        if (glow) addClassNames("aura-glow")
        if (gold) addClassNames("aura-gold")
        if (holo) addClassNames("aura-holo")
        if (rainbow) addClassNames("aura-rainbow")
        if (silver) addClassNames("aura-silver")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
