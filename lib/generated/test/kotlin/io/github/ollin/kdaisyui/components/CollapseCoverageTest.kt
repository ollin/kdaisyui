package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CollapseCoverageTest {

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
    fun collapse_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(
                content = { },
            )
        }
        assertRendered(html, "collapse", "Collapse defaults", closes = "</div></div>")
    }

    @Test
    fun collapse_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(
                id = htmlId("x-cov-id"),
                arrow = true,
                close = true,
                open = true,
                plus = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "collapse collapse-arrow collapse-close collapse-open collapse-plus zz-extra", "Collapse all flags", closes = "</div></div>")
        assertCommonFlags(html, "Collapse")
    }

    @Test
    fun collapseTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle()
        }
        assertRendered(html, "collapse-title", "CollapseTitle defaults", closes = "</h2></div>")
    }

    @Test
    fun collapseTitle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "collapse-title zz-extra", "CollapseTitle all flags", closes = "</h2></div>")
        assertCommonFlags(html, "CollapseTitle")
    }

    @Test
    fun collapseTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle(
                text = "txtmark",
            )
        }
        assertRendered(html, "collapse-title", "CollapseTitle text")
        assertTrue(html.contains("txtmark"), "CollapseTitle text content")
    }

    @Test
    fun collapseContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent(
                content = { },
            )
        }
        assertRendered(html, "collapse-content", "CollapseContent defaults", closes = "</div></div>")
    }

    @Test
    fun collapseContent_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "collapse-content zz-extra", "CollapseContent all flags", closes = "</div></div>")
        assertCommonFlags(html, "CollapseContent")
    }
}
