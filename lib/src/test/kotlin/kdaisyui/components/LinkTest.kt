package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class LinkTest {
    @Test
    fun rendersBaseLink() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink("Transactions")
        }
        assertEquals(
            expected = """<div><a class="link">Transactions</a></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersHoverLink() {
        val html = createHTML(prettyPrint = false).div {
            daisyLink("Revenue report →", hover = true)
        }
        assertEquals(
            expected = """<div><a class="link link-hover">Revenue report →</a></div>""",
            actual = html.trim(),
        )
    }
}
