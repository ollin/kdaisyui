package io.github.ollin.kdaisyui.docsnippets

import kotlin.io.path.createDirectories
import kotlin.io.path.createTempDirectory
import kotlin.io.path.exists
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertTrue

class ExtractSnippetsTest {

    @Test
    fun everySnippetOfTheNamedDocumentsIsWrittenToTheOutputDirectory() {
        val root = createTempDirectory()
        root.resolve("docs").createDirectories().resolve("x.md").writeText("```kotlin\na()\n```\n")
        val out = root.resolve("out").createDirectories()
        main(arrayOf(root.toString(), out.toString(), "docs/x.md"))
        assertTrue(out.resolve("docs/x.md/L1.kt").exists())
    }
}
