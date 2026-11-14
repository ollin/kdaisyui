package io.github.ollin.kdaisyui.gradle

// Regexes, not `all`, `none` or `trim { }` over the tokens: those inline functions carry an
// `is Collection` short-cut or loop bounds whose mutants no test can tell apart (ThemeCheck.kt).
/**
 * The tokens of [string] when every one is shaped like a CSS class, else null. The holes of a
 * string-concatenation recipe (`\u0001`) separate tokens as whitespace does.
 */
fun classTokens(string: String): List<String>? =
    if (CLASS_LIST.matches(string)) CLASS_TOKEN.findAll(string).map { it.value }.toList() else null

/** The components of the bare DaisyUI classes among [tokens]; none unless a token holds `-` or `:`, which marks a class list. */
fun daisyComponentsIn(tokens: List<String>, map: ComponentMap): Set<String> {
    val joined = tokens.joinToString(" ")
    return if ('-' !in joined && ':' !in joined) emptySet() else tokens.filter { ':' !in it }.mapNotNull(map::componentOf).toSet()
}

/** A string of compiled code that writes DaisyUI classes by hand: the class holding it, the string, and its components. */
data class RawClassList(val owner: String, val string: String, val components: Set<String>)

/** The raw DaisyUI class lists of [classFile], whose internal name is [owner]; a lambda's class reports its file's class. */
fun rawClassListsIn(owner: String, classFile: ByteArray, map: ComponentMap): List<RawClassList> =
    stringConstantsIn(classFile).mapNotNull { string ->
        val components = classTokens(string)?.let { daisyComponentsIn(it, map) }.orEmpty()
        if (components.isEmpty()) null else RawClassList(owner.substringBefore('$'), string, components)
    }

/** The warning for [found], or null when there is none: one line per class and string, sorted, and what to use instead. */
fun rawClassesWarning(found: List<RawClassList>): String? {
    if (found.isEmpty()) return null
    val lines = found.map { "  ${it.owner}: \"${it.string}\" (${it.components.sorted().joinToString()})" }.distinct().sorted()
    return "kdaisyui: strings that write DaisyUI classes the typed API writes for you:\n" + lines.joinToString("\n") +
        "\n  Use the component's function, or a { daisy<Component>Classes(…) } on another element."
}

private const val CLASS = """[a-z0-9!:/\[\]().%_#-]+"""
private const val SEPARATOR = """[\s\u0001]"""
private val CLASS_TOKEN = Regex(CLASS)
private val CLASS_LIST = Regex("""$SEPARATOR*$CLASS(?:$SEPARATOR+$CLASS)*$SEPARATOR*""")
