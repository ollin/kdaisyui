// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/hovergallery/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.figure
import kotlinx.html.FIGURE
import kotlinx.html.FlowContent

/**
 * Hover Gallery is container of images. The first image is visible be default and when we hover it horizontally, other images show up. Hover Gallery is useful for product cards in ecommerce sites, portfoilios or in image galleries. Hover Gallery can include up to 10 images. Renders `<figure class="hover-gallery ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyHoverGallery(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (FIGURE.() -> Unit)? = null,
    content: (FIGURE.() -> Unit),
) {
    figure {
        if (id != null) attributes["id"] = id.id
        addClassNames("hover-gallery")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
