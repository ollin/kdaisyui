package io.github.ollin.kdaisyui.gradle

/**
 * The themes the [references] of compiled code name, as `data-theme` values: an entry its own,
 * a runtime choice every one, a member such as `getValue` or `name` none. [themes] maps each
 * `DaisyTheme` entry to its value, as the library's `kdaisyui-themes.txt` does.
 */
fun themesNamedIn(references: List<MemberReference>, themes: Map<String, String>): Set<String> =
    // `intersect`, not the inline `any`, whose `is Collection` short-cut is a mutant no test can tell apart.
    if ((references intersect RUNTIME_CHOICES).isNotEmpty()) themes.values.toSet()
    else references.filter { it.owner == DAISY_THEME }.mapNotNull { themes[it.name] }.toSet()

/** `kdaisyui-themes.txt` read back: each `DaisyTheme` entry and its `data-theme` value. */
fun themeMap(resource: String): Map<String, String> =
    resource.lines().filter { it.isNotEmpty() && !it.startsWith("#") }.associate { it.substringBefore('\t') to it.substringAfter('\t') }

/** The themes a compiled stylesheet carries: the names of its `[data-theme=…]` selectors, minified or not. */
fun themesCarriedBy(css: String): Set<String> = DATA_THEME.findAll(css).map { it.groupValues[1] }.toSet()

/** The themes [named] by the code that the stylesheet does not carry, in order. */
fun missingThemes(named: Set<String>, carried: Set<String>): List<String> = (named - carried).sorted()

/**
 * The warning for [missing] themes and where to add them; null when nothing is missing. It shows
 * the entry's existing themes as `…`: the plugin cannot know which flags, such as `--default`, the
 * entry gives them.
 */
fun themeWarning(missing: List<String>): String? {
    if (missing.isEmpty()) return null
    val names = missing.joinToString(", ")
    return "kdaisyui: the code names themes the stylesheet does not carry: $names.\n" +
        "  Add them to the themes of the entry's @plugin \"daisyui\" block: themes: …, $names;"
}

private val DATA_THEME = Regex("""\[data-theme=([a-z]+)]""")

private const val DAISY_THEME = "io/github/ollin/kdaisyui/themes/DaisyTheme"

/** Code reaching any of these can choose every theme at runtime: the whole set, or a stored value. */
private val RUNTIME_CHOICES = listOf("getEntries", "values", "valueOf").map { MemberReference(DAISY_THEME, it) } +
    MemberReference("io/github/ollin/kdaisyui/themes/DaisyThemesKt", "daisyThemeOf")
