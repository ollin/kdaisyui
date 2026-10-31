package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinFenceTest {

    @Test
    fun aDocumentWithoutFencesHasNoSnippet() {
        assertEquals(emptyList(), kotlinFencesIn("# Title\n\nProse only.\n"))
    }
}
