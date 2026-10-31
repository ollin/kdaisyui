package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ToastCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun toast_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                content = { },
            )
        }
        assertRendered(html, Expected("Toast defaults", "toast", closes = "</div></div>"))
    }

    @Test
    fun toast_all_flags() {
        val html = allFlags(Expected("Toast all flags", "toast zz-extra", closes = "</div></div>")) { daisyToast(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun toast_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                horizontalPlacement = ToastHorizontalPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("Toast horizontalPlacement Start", "toast toast-start"))
    }

    @Test
    fun toast_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                horizontalPlacement = ToastHorizontalPlacement.Center,
                content = { },
            )
        }
        assertRendered(html, Expected("Toast horizontalPlacement Center", "toast toast-center"))
    }

    @Test
    fun toast_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                horizontalPlacement = ToastHorizontalPlacement.End,
                content = { },
            )
        }
        assertRendered(html, Expected("Toast horizontalPlacement End", "toast toast-end"))
    }

    @Test
    fun toast_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                verticalPlacement = ToastVerticalPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, Expected("Toast verticalPlacement Top", "toast toast-top"))
    }

    @Test
    fun toast_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                verticalPlacement = ToastVerticalPlacement.Middle,
                content = { },
            )
        }
        assertRendered(html, Expected("Toast verticalPlacement Middle", "toast toast-middle"))
    }

    @Test
    fun toast_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyToast(
                verticalPlacement = ToastVerticalPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, Expected("Toast verticalPlacement Bottom", "toast toast-bottom"))
    }

    @Test
    fun toastClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses()
        }
        assertRendered(html, Expected("ToastClasses defaults", "toast"))
    }

    @Test
    fun toastClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses()
        }
        val expected = Expected("ToastClasses all flags", "toast", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun toastClasses_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses(
                horizontalPlacement = ToastHorizontalPlacement.Start,
            )
        }
        assertRendered(html, Expected("ToastClasses horizontalPlacement Start", "toast toast-start"))
    }

    @Test
    fun toastClasses_horizontalPlacement_center() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses(
                horizontalPlacement = ToastHorizontalPlacement.Center,
            )
        }
        assertRendered(html, Expected("ToastClasses horizontalPlacement Center", "toast toast-center"))
    }

    @Test
    fun toastClasses_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses(
                horizontalPlacement = ToastHorizontalPlacement.End,
            )
        }
        assertRendered(html, Expected("ToastClasses horizontalPlacement End", "toast toast-end"))
    }

    @Test
    fun toastClasses_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses(
                verticalPlacement = ToastVerticalPlacement.Top,
            )
        }
        assertRendered(html, Expected("ToastClasses verticalPlacement Top", "toast toast-top"))
    }

    @Test
    fun toastClasses_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses(
                verticalPlacement = ToastVerticalPlacement.Middle,
            )
        }
        assertRendered(html, Expected("ToastClasses verticalPlacement Middle", "toast toast-middle"))
    }

    @Test
    fun toastClasses_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyToastClasses(
                verticalPlacement = ToastVerticalPlacement.Bottom,
            )
        }
        assertRendered(html, Expected("ToastClasses verticalPlacement Bottom", "toast toast-bottom"))
    }
}
