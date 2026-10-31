package io.github.ollin.kdaisyui.docsnippets

import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

/**
 * Writes one Kotlin file per snippet into a directory: `<root> <out> <document>...`, each document
 * relative to the repository root, which is how it is named in a compile error.
 */
fun main(args: Array<String>) {
    val (root, out) = args.take(2).map(::Path)
    args.drop(2).flatMap { SnippetDocument(it, root.resolve(it).readText()).sources }
        .forEach { out.resolve(it.relativePath).writeText(it.text) }
}
