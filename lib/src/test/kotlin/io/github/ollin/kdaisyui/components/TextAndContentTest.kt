package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.span
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * A function's `text` and its lambda add up: the lambda runs first, then the text is appended.
 *
 * Until 0.9.0 the lambda won and the text was dropped, so the ordinary trailing-lambda call
 * `daisyButton("Save") { … }` rendered a button with no label. That cost a red end-to-end test
 * to diagnose while writing the popover-modal demo.
 *
 * The lambda has to run first because kotlinx.html rejects an attribute once an element's text
 * is written; `AttributeOrderTest` pins that rule.
 */
class TextAndContentTest {

    private fun render(block: kotlinx.html.FlowContent.() -> Unit) = createHTML(prettyPrint = false).div { block() }

    @Test
    fun `text alone is rendered`() {
        assertEquals("<div><button class=\"btn\">Save</button></div>", render { daisyButton("Save") })
    }

    @Test
    fun `content alone is rendered`() {
        assertEquals("<div><button class=\"btn\">Save</button></div>", render { daisyButton { +"Save" } })
    }

    @Test
    fun `text with an attribute lambda renders both`() {
        assertEquals(
            "<div><button class=\"btn\" data-probe=\"yes\">Save</button></div>",
            render { daisyButton("Save") { attributes["data-probe"] = "yes" } },
        )
    }

    @Test
    fun `text is appended after the children the lambda writes`() {
        assertEquals(
            "<div><button class=\"btn\"><span>icon</span>Save</button></div>",
            render { daisyButton("Save") { span { +"icon" } } },
        )
    }

    @Test
    fun `neither renders an empty element`() {
        assertEquals("<div><button class=\"btn\"></button></div>", render { daisyButton() })
    }
}
