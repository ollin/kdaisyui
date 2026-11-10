// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/theme-controller/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import io.github.ollin.kdaisyui.themes.DaisyTheme
import kotlinx.html.FlowContent
import kotlinx.html.input
import kotlinx.html.INPUT
import kotlinx.html.Tag

/**
 * If a checked checkbox input or a checked radio input with theme-controller class exists in the page, The page will have the same theme as that input's value. Renders `<input class="theme-controller ...">`.
 * The same classes on another element: `a { daisyThemeControllerClasses(…) }`; see [daisyThemeControllerClasses].
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param theme
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param block — Writes the element's attributes, and its children where it can hold any
 */
fun FlowContent.daisyThemeController(
    id: HtmlId? = null,
    theme: DaisyTheme? = null,
    extraClasses: String? = null,
    block: (INPUT.() -> Unit) = {},
) {
    input {
        if (id != null) attributes["id"] = id.id
        addClassNames("theme-controller")
        if (theme != null) this.value = theme.value
        addClassNames(extraClasses)
        block()
    }
}

/** The classes `daisyThemeController` writes, on the element this is called on. */
fun Tag.daisyThemeControllerClasses() {
    addClassNames("theme-controller")
}
