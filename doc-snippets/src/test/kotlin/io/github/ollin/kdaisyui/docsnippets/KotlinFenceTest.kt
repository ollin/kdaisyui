package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinFenceTest {

    @Test
    fun aDocumentWithoutFencesHasNoSnippet() {
        assertEquals(emptyList(), kotlinFencesIn("# Title\n\nProse only.\n"))
    }

    @Test
    fun aKotlinFenceIsFoundAtTheLineOfItsOpeningFence() {
        val markdown = "# Title\n\n```kotlin\ndaisyButton(\"Save\")\n```\n"
        assertEquals(listOf(3), kotlinFencesIn(markdown).map { it.line })
    }

    @Test
    fun aKotlinFenceCarriesTheCodeBetweenItsFences() {
        val markdown = "```kotlin\nval a = 1\nval b = 2\n```\nafter\n"
        assertEquals(listOf("val a = 1\nval b = 2"), kotlinFencesIn(markdown).map { it.code })
    }

    @Test
    fun aFenceCarriesTheMarkerOnTheLineAboveIt() {
        val markdown = "<!-- snippet: in Route -->\n```kotlin\nget(\"/\") { }\n```\n"
        assertEquals(listOf("in Route"), kotlinFencesIn(markdown).map { it.marker })
    }

    @Test
    fun aFenceNamesWhatItDeclaresAtColumnZero() {
        val code = "fun FlowContent.shellSidebar() {\n    val inner = 1\n}\nval html = 2\nsuspend fun RoutingCall.respond() { }"
        assertEquals(listOf("shellSidebar", "html", "respond"), KotlinFence(1, code).declaredNames)
    }

    @Test
    fun aFenceMarkedInAReceiverDeclaresNothingAtTopLevel() {
        assertEquals(emptyList(), KotlinFence(1, "fun helper() { }", marker = "in Route").declaredNames)
    }
}
