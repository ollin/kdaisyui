package io.github.ollin.kdaisyui.gradle

/**
 * The themes the [references] of compiled code name, as `data-theme` values: an entry its own,
 * a runtime choice every one, a member such as `getValue` or `name` none. [themes] maps each
 * `DaisyTheme` entry to its value, as the library's `kdaisyui-themes.txt` does.
 */
fun themesNamedIn(references: List<MemberReference>, themes: Map<String, String>): Set<String> =
    if (references.any { it in RUNTIME_CHOICES }) themes.values.toSet()
    else references.filter { it.owner == DAISY_THEME }.mapNotNull { themes[it.name] }.toSet()

/** `kdaisyui-themes.txt` read back: each `DaisyTheme` entry and its `data-theme` value. */
fun themeMap(resource: String): Map<String, String> =
    resource.lines().filter { it.isNotEmpty() && !it.startsWith("#") }.associate { it.substringBefore('\t') to it.substringAfter('\t') }

private const val DAISY_THEME = "io/github/ollin/kdaisyui/themes/DaisyTheme"

/** Code reaching any of these can choose every theme at runtime: the whole set, or a stored value. */
private val RUNTIME_CHOICES = listOf("getEntries", "values", "valueOf").map { MemberReference(DAISY_THEME, it) } +
    MemberReference("io/github/ollin/kdaisyui/themes/DaisyThemesKt", "daisyThemeOf")
