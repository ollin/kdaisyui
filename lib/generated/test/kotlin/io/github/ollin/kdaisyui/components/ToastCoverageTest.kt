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
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "toast zz-extra", "Toast all flags", closes = "</div></div>")
        assertCommonFlags(html, "Toast")
    }

    @Test
    fun toast_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                verticalPlacement = ToastVerticalPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, "toast toast-top", "Toast verticalPlacement Top")
    }

    @Test
    fun toast_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                verticalPlacement = ToastVerticalPlacement.Middle,
                content = { },
            )
        }
        assertRendered(html, "toast toast-middle", "Toast verticalPlacement Middle")
    }

    @Test
    fun toast_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                verticalPlacement = ToastVerticalPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, "toast toast-bottom", "Toast verticalPlacement Bottom")
    }

    @Test
    fun toast_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                horizontalPlacement = ToastHorizontalPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, "toast toast-start", "Toast horizontalPlacement Start")
    }

    @Test
    fun toast_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                horizontalPlacement = ToastHorizontalPlacement.Center,
                content = { },
            )
        }
        assertRendered(html, "toast toast-center", "Toast horizontalPlacement Center")
    }

    @Test
    fun toast_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                horizontalPlacement = ToastHorizontalPlacement.End,
                content = { },
            )
        }
        assertRendered(html, "toast toast-end", "Toast horizontalPlacement End")
    }
}
