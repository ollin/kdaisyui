package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToastCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun toast_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                content = { },
            )
        }
        assertRendered(html, "toast", "Toast defaults", closes = "</div></div>")
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
        assertRendered(html, "toast toast-bottom toast-center toast-end toast-middle toast-start toast-top zz-extra", "Toast all flags", closes = "</div></div>")
        assertCommonFlags(html, "Toast")
    }
}
