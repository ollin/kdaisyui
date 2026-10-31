package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class SnippetDocumentTest {

    @Test
    fun everyKotlinFenceOfTheDocumentBecomesOneSource() {
        val markdown = "```kotlin\na()\n```\n\n```kotlin\nb()\n```\n"
        assertEquals(listOf("docs_x_md_L1.kt", "docs_x_md_L5.kt"), SnippetDocument("docs/x.md", markdown).sources.map { it.fileName })
    }
}
