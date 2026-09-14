package io.github.ollin.kdaisyui.core

import kotlinx.html.Tag

fun Tag.addClassNames(vararg classNames: String) {
    val existing = attributes["class"].orEmpty()
    val tokens = LinkedHashSet<String>()

    existing
        .split(' ', '\n', '\t', '\r')
        .asSequence()
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .forEach { tokens.add(it) }

    for (className in classNames) {
        val trimmed = className.trim()
        if (trimmed.isNotEmpty()) tokens.add(trimmed)
    }

    if (tokens.isEmpty()) {
        attributes.remove("class")
        return
    }

    attributes["class"] = tokens.joinToString(" ")
}

/**
 * Add every class of a typed group application, e.g. `btn-sm` and `lg:btn-lg`.
 *
 * Nullable, so a generated component can call this unconditionally for a parameter that defaults
 * to `null`. That is why this overload exists rather than the generator emitting
 * `if (size != null) addClassNames(size.classNames…)`: the guard then lives here once instead of
 * once per enum parameter on all 66 components, which is 90-odd generated branches that every
 * coverage and mutation gate has to drive separately to say the same thing.
 */
fun Tag.addClassNames(values: ClassValues<*>?) {
    if (values == null) return

    addClassNames(*values.classNames.toTypedArray())
}

fun Tag.addClassNames(classNames: String?) {
    if (classNames == null) return

    // Split only. Trimming and dropping empties is the vararg overload's job — it does
    // both to every element it receives (see `trimmed` above), so doing it here as well
    // changes no output and only adds a branch no test can distinguish.
    addClassNames(*classNames.split(' ', '\n', '\t', '\r').toTypedArray())
}
