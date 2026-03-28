package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class InputTest {
    @Test
    fun rendersBaseInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(placeholder = "Type here")
        }
        assertEquals(
            expected = """<div><input type="text" class="input" placeholder="Type here"></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersSizedInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(size = InputSize.Sm, placeholder = "Search", extraClasses = "rounded-full")
        }
        assertEquals(
            expected = """<div><input type="text" class="input input-sm rounded-full" placeholder="Search"></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersGhostInput() {
        val html = createHTML(prettyPrint = false).div {
            daisyInput(ghost = true, size = InputSize.Xs, extraClasses = "w-10 text-center") {
                value = "1"
            }
        }
        assertEquals(
            expected = """<div><input type="text" class="input input-xs input-ghost w-10 text-center" value="1"></div>""",
            actual = html.trim(),
        )
    }
}
