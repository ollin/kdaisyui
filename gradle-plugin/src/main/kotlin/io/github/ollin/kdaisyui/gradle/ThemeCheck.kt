package io.github.ollin.kdaisyui.gradle

/**
 * The themes the [references] of compiled code name, as `data-theme` values. [themes] maps each
 * `DaisyTheme` entry to its value, as the library's `kdaisyui-themes.txt` does.
 */
fun themesNamedIn(references: List<MemberReference>, themes: Map<String, String>): Set<String> =
    references.filter { it.owner == DAISY_THEME }.mapNotNull { themes[it.name] }.toSet()

private const val DAISY_THEME = "io/github/ollin/kdaisyui/themes/DaisyTheme"
