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

/**
 * The theme whose `data-theme` value is [value], or `null` for any other string, such as a stored
 * choice that is not, or no longer, one of DaisyUI's themes. Matches exactly: `"Nord"` is no theme.
 */
fun daisyThemeOf(value: String): DaisyTheme? = DaisyTheme.entries.firstOrNull { it.value == value }
