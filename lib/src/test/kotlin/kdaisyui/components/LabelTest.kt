package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class LabelTest {
    @Test
    fun rendersLabel() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabel("Product name")
        }
        assertEquals(
            expected = """<div><label class="label">Product name</label></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersLabelText() {
        val html = createHTML(prettyPrint = false).div {
            daisyLabelText("Card Number")
        }
        assertEquals(
            expected = """<div><span class="label-text">Card Number</span></div>""",
            actual = html.trim(),
        )
    }
}
