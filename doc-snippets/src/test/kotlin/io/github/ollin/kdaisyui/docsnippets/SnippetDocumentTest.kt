package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class SnippetDocumentTest {

    @Test
    fun everyKotlinFenceOfTheDocumentBecomesOneSource() {
        val markdown = "```kotlin\na()\n```\n\n```kotlin\nb()\n```\n"
        assertEquals(listOf("docs_x_md_L1.kt", "docs_x_md_L5.kt"), SnippetDocument("docs/x.md", markdown).sources.map { it.fileName })
    }

    @Test
    fun aSnippetSeesTheNearestEarlierDeclarationOfAName() {
        val imports = scopeImportsOf("fun FlowContent.a() { }", "fun FlowContent.a() { }", "fun FlowContent.b() { }", "a()")
        val fromL5 = "import snippets.x_md_L5.a"
        assertEquals(listOf(emptyList(), emptyList(), listOf(fromL5), listOf(fromL5, "import snippets.x_md_L9.b")), imports)
    }

    private fun scopeImportsOf(vararg snippets: String): List<List<String>> {
        val markdown = snippets.joinToString("\n") { "```kotlin\n$it\n```\n" }
        return SnippetDocument("x.md", markdown).sources.map { source -> source.text.lines().filter { it.startsWith("import snippets.") } }
    }
}
