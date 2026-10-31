package io.github.ollin.kdaisyui.gradle

/** `kdaisyui-components.txt` from the kdaisyui jar: per component, its JVM classes and its CSS classes. */
class ComponentMap(text: String) {
    private val components = text.lines().filter { it.isNotBlank() && !it.startsWith("#") }.map(::component)

    /**
     * The CSS classes of every component one of [found] belongs to. In doubt, every class, with the
     * reason: a narrower list that is wrong renders an unstyled page and says nothing.
     */
    fun select(found: Set<String>): Selection {
        val unknown = found - components.flatMap { it.jvmClasses }.toSet()
        return when {
            found.isEmpty() -> Selection(classesOf(components), "the compiled classes name no kdaisyui component")
            unknown.isNotEmpty() -> Selection(classesOf(components), "kdaisyui-components.txt does not know ${unknown.sorted().joinToString()}")
            else -> Selection(classesOf(components.filter { it.jvmClasses.any(found::contains) }), null)
        }
    }

    private fun classesOf(selected: List<Component>): List<String> = selected.flatMap { it.cssClasses }.distinct().sorted()

    private class Component(val jvmClasses: Set<String>, val cssClasses: List<String>)

    private fun component(line: String): Component =
        line.split("\t").let { (_, jvm, css) -> Component(jvm.split(" ").toSet(), css.split(" ")) }
}

/** The classes to write, and why they are all of them when they are. */
data class Selection(val classes: List<String>, val fullListBecause: String?)
