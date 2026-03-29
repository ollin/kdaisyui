package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class JoinTest {
    @Test
    fun rendersBaseJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin {
                daisyButton("A", extraClasses = "join-item")
                daisyButton("B", extraClasses = "join-item")
            }
        }
        assertEquals(
            expected = """<div><div class="join"><button class="btn join-item">A</button><button class="btn join-item">B</button></div></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersVerticalJoin() {
        val html = createHTML(prettyPrint = false).div {
            daisyJoin(vertical = true) {
                daisyButton("Top", extraClasses = "join-item")
                daisyButton("Bottom", extraClasses = "join-item")
            }
        }
        assertEquals(
            expected = """<div><div class="join join-vertical"><button class="btn join-item">Top</button><button class="btn join-item">Bottom</button></div></div>""",
            actual = html.trim(),
        )
    }
}
