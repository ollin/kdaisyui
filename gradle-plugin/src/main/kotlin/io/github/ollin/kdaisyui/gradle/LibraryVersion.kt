package io.github.ollin.kdaisyui.gradle

import java.io.File

/**
 * A warning when the kdaisyui jar on [classpath] is another version than the [plugin], naming both,
 * or null. The plugin's pinned toolchain is the one the library of its own version was generated
 * against. A project dependency is a directory and carries no version, so it never warns.
 */
fun versionMismatch(plugin: String, classpath: Iterable<File>): String? {
    val library = classpath.firstNotNullOfOrNull { libraryJar.matchEntire(it.name)?.groupValues[1] } ?: return null
    return if (library == plugin) null else
        "kdaisyui: the plugin is $plugin, the kdaisyui jar on the runtime classpath is $library; the stylesheet compile installs the toolchain of $plugin"
}

// The library's jar, not kdaisyui-ktor-integration's: its version starts with a digit.
private val libraryJar = Regex("kdaisyui-(\\d.*)\\.jar")
