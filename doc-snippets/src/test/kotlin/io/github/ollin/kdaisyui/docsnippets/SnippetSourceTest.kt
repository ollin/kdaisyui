package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class SnippetSourceTest {

    @Test
    fun theFileSitsUnderTheDocumentNamedForTheLineOfTheFence() {
        val source = SnippetSource("docs/how-to.md", KotlinFence(line = 71, code = ""))
        assertEquals("docs/how-to.md/L71.kt", source.relativePath)
    }

    @Test
    fun aStatementIsWrappedInAFlowContentBuilderInItsOwnPackage() {
        val text = textOf("daisyBadge(\"New\")")
        assertEquals("// README.md:9\npackage snippets.README_md_L9", text.lines().take(2).joinToString("\n"))
        assertEquals("fun FlowContent.snippet() {\ndaisyBadge(\"New\")\n}\n", bodyOf(text))
    }

    @Test
    fun aSnippetDeclaringSomethingAtColumnZeroIsNotWrapped() {
        assertEquals("fun FlowContent.card() {\n}\n", bodyOf(textOf("fun FlowContent.card() {\n}")))
    }

    @Test
    fun theSnippetsOwnImportsJoinTheImportBlock() {
        val text = textOf("import a.B\nval b = B()")
        assertEquals("import a.B", importsOf(text).last())
        assertEquals("val b = B()\n", bodyOf(text))
    }

    @Test
    fun theNamesTheDocumentPutsInScopeAreImported() {
        val source = SnippetSource("README.md", KotlinFence(9, ""), scope = listOf("snippets.README_md_L3.shellSidebar"))
        assertEquals("import snippets.README_md_L3.shellSidebar", importsOf(source.text).last())
    }

    @Test
    fun theLibraryKotlinxHtmlAndTheKtorServerAreImported() {
        val imports = importsOf(textOf(""))
        assertEquals(11, imports.size)
        assertEquals(listOf("import kotlinx.html.stream.*", "import io.github.ollin.kdaisyui.core.*", "import io.ktor.server.webjars.*"),
            imports.filter { it.endsWith("stream.*") || it.endsWith("core.*") || it.endsWith("webjars.*") })
    }

    @Test
    fun aSnippetMarkedInAReceiverIsWrappedInAnExtensionOnIt() {
        assertEquals("fun Route.snippet() {\nget(\"/\") { }\n}\n", bodyOf(textOf("get(\"/\") { }", marker = "in Route")))
    }

    @Test
    fun aSnippetMarkedInAReceiverIsWrappedEvenWhenItDeclaresSomething() {
        assertEquals("fun Route.snippet() {\nfun helper() { }\nhelper()\n}\n", bodyOf(textOf("fun helper() { }\nhelper()", marker = "in Route")))
    }

    private fun textOf(code: String, marker: String? = null) = SnippetSource("README.md", KotlinFence(9, code, marker)).text

    private fun importsOf(text: String) = text.lines().filter { it.startsWith("import ") }

    private fun bodyOf(text: String) = text.substringAfterLast("\n\n")
}
