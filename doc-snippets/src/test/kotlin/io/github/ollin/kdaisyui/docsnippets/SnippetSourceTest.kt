package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class SnippetSourceTest {

    @Test
    fun theFileIsNamedForTheDocumentAndTheLineOfTheFence() {
        val source = SnippetSource("docs/how-to.md", KotlinFence(line = 71, code = ""))
        assertEquals("docs_how_to_md_L71.kt", source.fileName)
    }

    @Test
    fun aStatementIsWrappedInAFlowContentBuilderInItsOwnPackage() {
        val text = SnippetSource("README.md", KotlinFence(line = 9, code = "daisyBadge(\"New\")")).text
        assertEquals("// README.md:9\npackage snippets.README_md_L9", text.lines().take(2).joinToString("\n"))
        assertEquals("fun FlowContent.snippet() {\ndaisyBadge(\"New\")\n}\n", text.substringAfterLast("\n\n"))
    }

    @Test
    fun kotlinxHtmlAndTheComponentsAreImported() {
        val imports = SnippetSource("README.md", KotlinFence(line = 9, code = "")).text.lines()
        assertEquals(listOf("import kotlinx.html.*", "import io.github.ollin.kdaisyui.components.*"), imports.filter { it.startsWith("import ") })
    }
}
