package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class StackCoverageTest {

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
    fun stack_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                content = { },
            )
        }
        assertRendered(html, "stack", "Stack defaults", closes = "</div></div>")
    }

    @Test
    fun stack_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "stack zz-extra", "Stack all flags", closes = "</div></div>")
        assertCommonFlags(html, "Stack")
    }

    @Test
    fun stack_placement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                placement = StackPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, "stack stack-top", "Stack placement Top")
    }

    @Test
    fun stack_placement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                placement = StackPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, "stack stack-bottom", "Stack placement Bottom")
    }

    @Test
    fun stack_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                placement = StackPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, "stack stack-start", "Stack placement Start")
    }

    @Test
    fun stack_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyStack(
                placement = StackPlacement.End,
                content = { },
            )
        }
        assertRendered(html, "stack stack-end", "Stack placement End")
    }

    @Test
    fun stackClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses()
        }
        assertRendered(html, "stack", "StackClasses defaults")
    }

    @Test
    fun stackClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses()
        }
        assertRendered(html, "stack", "StackClasses all flags")
    }

    @Test
    fun stackClasses_placement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses(
                placement = StackPlacement.Top,
            )
        }
        assertRendered(html, "stack stack-top", "StackClasses placement Top")
    }

    @Test
    fun stackClasses_placement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses(
                placement = StackPlacement.Bottom,
            )
        }
        assertRendered(html, "stack stack-bottom", "StackClasses placement Bottom")
    }

    @Test
    fun stackClasses_placement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses(
                placement = StackPlacement.Start,
            )
        }
        assertRendered(html, "stack stack-start", "StackClasses placement Start")
    }

    @Test
    fun stackClasses_placement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyStackClasses(
                placement = StackPlacement.End,
            )
        }
        assertRendered(html, "stack stack-end", "StackClasses placement End")
    }
}
