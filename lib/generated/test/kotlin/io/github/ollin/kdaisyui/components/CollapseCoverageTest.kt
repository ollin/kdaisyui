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
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "collapse zz-extra", "Collapse all flags", closes = "</div></div>")
        assertCommonFlags(html, "Collapse")
    }

    @Test
    fun collapse_icon_arrow() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(
                icon = CollapseIcon.Arrow,
                content = { },
            )
        }
        assertRendered(html, "collapse collapse-arrow", "Collapse icon Arrow")
    }

    @Test
    fun collapse_icon_plus() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(
                icon = CollapseIcon.Plus,
                content = { },
            )
        }
        assertRendered(html, "collapse collapse-plus", "Collapse icon Plus")
    }

    @Test
    fun collapse_state_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(
                state = CollapseState.Open,
                content = { },
            )
        }
        assertRendered(html, "collapse collapse-open", "Collapse state Open")
    }

    @Test
    fun collapse_state_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapse(
                state = CollapseState.Close,
                content = { },
            )
        }
        assertRendered(html, "collapse collapse-close", "Collapse state Close")
    }

    @Test
    fun collapseClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseClasses()
        }
        assertRendered(html, "collapse", "CollapseClasses defaults")
    }

    @Test
    fun collapseClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseClasses()
        }
        assertRendered(html, "collapse", "CollapseClasses all flags")
    }

    @Test
    fun collapseClasses_icon_arrow() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseClasses(
                icon = CollapseIcon.Arrow,
            )
        }
        assertRendered(html, "collapse collapse-arrow", "CollapseClasses icon Arrow")
    }

    @Test
    fun collapseClasses_icon_plus() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseClasses(
                icon = CollapseIcon.Plus,
            )
        }
        assertRendered(html, "collapse collapse-plus", "CollapseClasses icon Plus")
    }

    @Test
    fun collapseClasses_state_open() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseClasses(
                state = CollapseState.Open,
            )
        }
        assertRendered(html, "collapse collapse-open", "CollapseClasses state Open")
    }

    @Test
    fun collapseClasses_state_close() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseClasses(
                state = CollapseState.Close,
            )
        }
        assertRendered(html, "collapse collapse-close", "CollapseClasses state Close")
    }

    @Test
    fun collapseTitle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitle()
        }
        assertRendered(html, "collapse-title", "CollapseTitle defaults", closes = "</div></div>")
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
        assertRendered(html, "collapse-title zz-extra", "CollapseTitle all flags", closes = "</div></div>")
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
    fun collapseTitleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitleClasses()
        }
        assertRendered(html, "collapse-title", "CollapseTitleClasses defaults")
    }

    @Test
    fun collapseTitleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseTitleClasses()
        }
        assertRendered(html, "collapse-title", "CollapseTitleClasses all flags")
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

    @Test
    fun collapseContentClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContentClasses()
        }
        assertRendered(html, "collapse-content", "CollapseContentClasses defaults")
    }

    @Test
    fun collapseContentClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyCollapseContentClasses()
        }
        assertRendered(html, "collapse-content", "CollapseContentClasses all flags")
    }
}
