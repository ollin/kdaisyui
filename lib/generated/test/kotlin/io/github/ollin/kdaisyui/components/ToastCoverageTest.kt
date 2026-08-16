package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToastCoverageTest {

    @Test
    fun toast_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toast", actualClasses, "Toast defaults")
    }

    @Test
    fun toast_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                id = htmlId("x-cov-id"),
                bottom = true,
                center = true,
                end = true,
                middle = true,
                start = true,
                top = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("toast toast-bottom toast-center toast-end toast-middle toast-start toast-top zz-extra", actualClasses, "Toast all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Toast id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Toast attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Toast content")
    }
}
