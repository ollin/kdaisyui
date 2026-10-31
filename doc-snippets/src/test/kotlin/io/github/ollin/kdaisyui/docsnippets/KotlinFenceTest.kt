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
        assertEquals(listOf(KotlinFence(line = 3)), kotlinFencesIn(markdown))
    }
}
