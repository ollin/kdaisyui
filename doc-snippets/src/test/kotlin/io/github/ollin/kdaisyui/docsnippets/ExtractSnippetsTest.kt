package io.github.ollin.kdaisyui.docsnippets

import kotlin.io.path.createDirectories
import kotlin.io.path.createTempDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertEquals

class ExtractSnippetsTest {

    @Test
    fun everySnippetOfTheNamedDocumentsIsWrittenToTheOutputDirectory() {
        val root = createTempDirectory()
        root.resolve("docs").createDirectories().resolve("x.md").writeText("```kotlin\na()\n```\n")
        val out = root.resolve("out").createDirectories()
        main(arrayOf(root.toString(), out.toString(), "docs/x.md"))
        assertEquals(listOf("docs_x_md_L1.kt"), out.listDirectoryEntries().map { it.fileName.toString() })
    }
}
