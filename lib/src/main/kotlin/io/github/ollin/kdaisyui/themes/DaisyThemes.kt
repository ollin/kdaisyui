package io.github.ollin.kdaisyui.themes

import kotlinx.html.Tag

/**
 * Renders this element and everything inside it in [theme], by writing its `data-theme`.
 *
 * On `html` it themes the page; on any other element only that element, which is how a theme is
 * previewed inside a page wearing another. The stylesheet must carry the theme: a compiled one
 * holds only the themes its entry's `@plugin "daisyui"` block names.
 */
fun Tag.daisyTheme(theme: DaisyTheme) {
    attributes["data-theme"] = theme.value
}
