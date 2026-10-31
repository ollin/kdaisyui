package io.github.ollin.kdaisyui.gradle

/**
 * The kdaisyui component classes a compiled class file names, such as `ButtonKt` or `ButtonColor`.
 * A class name in a reference is a plain string in the constant pool, so finding it reads bytes and
 * resolves nothing. A nested class (`ButtonKt$...`) counts as its outer one.
 */
fun componentClassesNamedIn(classFile: ByteArray): Set<String> =
    componentReference.findAll(String(classFile, Charsets.ISO_8859_1)).map { it.groupValues[1] }.toSet()

private val componentReference = Regex("io/github/ollin/kdaisyui/components/([A-Za-z0-9_]+)")
