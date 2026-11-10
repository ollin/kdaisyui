// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/hero/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.Tag

/**
 * Hero is a component for displaying a large box or image with a title and description. Renders `<div class="hero ...">`.
 * The same classes on another element: `a { daisyHeroClasses(…) }`; see [daisyHeroClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyHero(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("hero")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyHero` writes, on the element this is called on. */
fun Tag.daisyHeroClasses() {
    addClassNames("hero")
}

/**
 * Renders `<div class="hero-content ...">`.
 * The same classes on another element: `a { daisyHeroContentClasses(…) }`; see [daisyHeroContentClasses].
 */
fun FlowContent.daisyHeroContent(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("hero-content")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyHeroContent` writes, on the element this is called on. */
fun Tag.daisyHeroContentClasses() {
    addClassNames("hero-content")
}

/**
 * Renders `<div class="hero-overlay ...">`.
 * The same classes on another element: `a { daisyHeroOverlayClasses(…) }`; see [daisyHeroOverlayClasses].
 */
fun FlowContent.daisyHeroOverlay(
    id: HtmlId? = null,
    extraClasses: String? = null,
    block: (DIV.() -> Unit) = {},
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("hero-overlay")
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyHeroOverlay` writes, on the element this is called on. */
fun Tag.daisyHeroOverlayClasses() {
    addClassNames("hero-overlay")
}
