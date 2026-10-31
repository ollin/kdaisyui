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
    fun aSnippetSeesTheNearestEarlierDeclarationOfANameElseTheNearestLaterOne() {
        val imports = scopeImportsOf("fun FlowContent.a() { }", "fun FlowContent.a() { }", "fun FlowContent.b() { }", "a()")
        val fromL5 = "import snippets.x_md_L5.a"
        val fromL9 = "import snippets.x_md_L9.b"
        assertEquals(listOf(listOf(fromL9), listOf(fromL9), listOf(fromL5), listOf(fromL5, fromL9)), imports)
    }

    @Test
    fun aFenceMarkedSkipWithAReasonIsNotCompiled() {
        val markdown = "<!-- snippet: skip - the 0.7.x API -->\n```kotlin\nold()\n```\n\n```kotlin\nnew()\n```\n"
        assertEquals(listOf("x_md_L6.kt"), SnippetDocument("x.md", markdown).sources.map { it.fileName })
    }

    private fun scopeImportsOf(vararg snippets: String): List<List<String>> {
        val markdown = snippets.joinToString("\n") { "```kotlin\n$it\n```\n" }
        return SnippetDocument("x.md", markdown).sources.map { source -> source.text.lines().filter { it.startsWith("import snippets.") } }
    }
}
