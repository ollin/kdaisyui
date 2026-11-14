package io.github.ollin.kdaisyui.gradle

/**
 * The tokens of [string] when every one is shaped like a CSS class, else null. The holes of a
 * string-concatenation recipe (`\u0001`) separate tokens as whitespace does.
 */
fun classTokens(string: String): List<String>? =
    string.split(SEPARATORS).filter { it.isNotEmpty() }.takeIf { tokens -> tokens.isNotEmpty() && tokens.all(CLASS_SHAPED::matches) }

/** The components of the bare DaisyUI classes among [tokens]; none unless a token holds `-` or `:`, which marks a class list. */
fun daisyComponentsIn(tokens: List<String>, map: ComponentMap): Set<String> =
    if (tokens.none { '-' in it || ':' in it }) emptySet() else tokens.filter { ':' !in it }.mapNotNull(map::componentOf).toSet()

/** A string of compiled code that writes DaisyUI classes by hand: the class holding it, the string, and its components. */
data class RawClassList(val owner: String, val string: String, val components: Set<String>)

/** The raw DaisyUI class lists of [classFile], whose internal name is [owner]; a lambda's class reports its file's class. */
fun rawClassListsIn(owner: String, classFile: ByteArray, map: ComponentMap): List<RawClassList> =
    stringConstantsIn(classFile).mapNotNull { string ->
        val components = classTokens(string)?.let { daisyComponentsIn(it, map) }.orEmpty()
        if (components.isEmpty()) null else RawClassList(owner.substringBefore('$'), string, components)
    }

private val SEPARATORS = Regex("""[\s\u0001]+""")
private val CLASS_SHAPED = Regex("""[a-z0-9!:/\[\]().%_#-]+""")
