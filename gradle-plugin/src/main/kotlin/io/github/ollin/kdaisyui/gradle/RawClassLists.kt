package io.github.ollin.kdaisyui.gradle

/**
 * The tokens of [string] when every one is shaped like a CSS class, else null. The holes of a
 * string-concatenation recipe (`\u0001`) separate tokens as whitespace does.
 */
fun classTokens(string: String): List<String>? =
    string.split(SEPARATORS).filter { it.isNotEmpty() }.takeIf { tokens -> tokens.isNotEmpty() && tokens.all(CLASS_SHAPED::matches) }

private val SEPARATORS = Regex("""[\s\u0001]+""")
private val CLASS_SHAPED = Regex("""[a-z0-9!:/\[\]().%_#-]+""")
