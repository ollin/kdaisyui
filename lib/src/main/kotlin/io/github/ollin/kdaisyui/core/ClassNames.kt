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

fun Tag.addClassNames(classNames: String?) {
    if (classNames == null) return

    // Split only. Trimming and dropping empties is the vararg overload's job — it does
    // both to every element it receives (see `trimmed` above), so doing it here as well
    // changes no output and only adds a branch no test can distinguish.
    addClassNames(*classNames.split(' ', '\n', '\t', '\r').toTypedArray())
}
