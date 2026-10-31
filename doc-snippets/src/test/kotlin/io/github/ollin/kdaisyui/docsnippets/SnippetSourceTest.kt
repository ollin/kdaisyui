package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class SnippetSourceTest {

    @Test
    fun theFileIsNamedForTheDocumentAndTheLineOfTheFence() {
        val source = SnippetSource("docs/how-to.md", KotlinFence(line = 71, code = ""))
        assertEquals("docs_how_to_md_L71.kt", source.fileName)
    }
}
