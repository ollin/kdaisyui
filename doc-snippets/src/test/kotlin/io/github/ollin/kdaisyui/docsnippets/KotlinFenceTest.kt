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
}
