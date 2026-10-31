package io.github.ollin.kdaisyui.gradle

/** `kdaisyui-components.txt` from the kdaisyui jar: per component, its JVM classes and its CSS classes. */
class ComponentMap(text: String) {
    private val components = text.lines().filter { it.isNotBlank() && !it.startsWith("#") }.map(::component)

    /** The CSS classes of every component one of [found] belongs to, sorted. */
    fun classesFor(found: Set<String>): List<String> =
        components.filter { it.jvmClasses.any(found::contains) }.flatMap { it.cssClasses }.distinct().sorted()

    private class Component(val jvmClasses: Set<String>, val cssClasses: List<String>)

    private fun component(line: String): Component =
        line.split("\t").let { (_, jvm, css) -> Component(jvm.split(" ").toSet(), css.split(" ")) }
}
