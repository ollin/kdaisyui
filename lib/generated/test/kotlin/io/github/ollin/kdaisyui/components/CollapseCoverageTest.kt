package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class CollapseCoverageTest {

    @Test
    fun collapse_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("collapse", actualClasses, "Collapse defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("collapse collapse-arrow collapse-close collapse-open collapse-plus zz-extra", actualClasses, "Collapse all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Collapse id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Collapse attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Collapse content")
    }

    @Test
    fun collapseTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle()
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("collapse-title", actualClasses, "CollapseTitle defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("collapse-title zz-extra", actualClasses, "CollapseTitle all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "CollapseTitle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "CollapseTitle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "CollapseTitle content")
    }

    @Test
    fun collapseTitle_text() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle(
                text = "txtmark",
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("collapse-title", actualClasses, "CollapseTitle text")
        assertTrue(html.contains("txtmark"), "CollapseTitle text content")
    }

    @Test
    fun collapseContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContent(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("collapse-content", actualClasses, "CollapseContent defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("collapse-content zz-extra", actualClasses, "CollapseContent all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "CollapseContent id")
        assertTrue(html.contains("data-attrs=\"yes\""), "CollapseContent attrs")
        assertTrue(html.contains("data-content=\"yes\""), "CollapseContent content")
    }
}
