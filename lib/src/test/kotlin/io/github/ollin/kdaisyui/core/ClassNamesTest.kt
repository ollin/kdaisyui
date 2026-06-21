package io.github.ollin.kdaisyui.core

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ClassNamesTest {

    @Test
    fun varargAddsClassesToTagWithoutExistingClass() {
        val html = createHTML(prettyPrint = false).div {
            addClassNames("btn", "btn-primary")
            assertEquals("btn btn-primary", attributes["class"])
        }
        assertEquals("""<div class="btn btn-primary"></div>""", html)
    }

    @Test
    fun varargMergesWithExistingClassAttribute() {
        createHTML().div {
            attributes["class"] = "btn"
            addClassNames("btn-primary", "btn-secondary")
            assertEquals("btn btn-primary btn-secondary", attributes["class"])
        }
    }

    @Test
    fun varargDeduplicatesExistingAndNewClasses() {
        createHTML().div {
            attributes["class"] = "btn shared"
            addClassNames("shared", "extra")
            assertEquals("btn shared extra", attributes["class"])
        }
    }

    @Test
    fun varargTrimsWhitespaceAroundEachClass() {
        createHTML().div {
            addClassNames("  btn  ", "  primary ")
            assertEquals("btn primary", attributes["class"])
        }
    }

    @Test
    fun varargSkipsBlankAndEmptyTokens() {
        createHTML().div {
            addClassNames("btn", "   ", "")
            assertEquals("btn", attributes["class"])
        }
    }

    @Test
    fun varargRemovesClassAttributeWhenAllTokensBlank() {
        createHTML().div {
            addClassNames("   ", "\t")
            assertNull(attributes["class"])
        }
    }

    @Test
    fun varargSplitsExistingClassOnAllWhitespaceDelimiters() {
        createHTML().div {
            attributes["class"] = "a b\nc\td\re"
            addClassNames("f", "g")
            assertEquals("a b c d e f g", attributes["class"])
        }
    }

    @Test
    fun nullableOverloadSplitsStringOnAllWhitespaceDelimiters() {
        val classes: String? = "a b\nc\td\re"
        createHTML().div {
            addClassNames(classes)
            assertEquals("a b c d e", attributes["class"])
        }
    }

    @Test
    fun nullableOverloadIgnoresNull() {
        val classes: String? = null
        createHTML().div {
            addClassNames(classes)
            assertNull(attributes["class"])
        }
    }

    @Test
    fun nullableOverloadWithBlankStringAddsNothing() {
        val classes: String? = "   "
        createHTML().div {
            addClassNames(classes)
            assertNull(attributes["class"])
        }
    }
}
