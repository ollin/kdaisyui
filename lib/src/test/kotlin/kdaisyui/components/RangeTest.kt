package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class RangeTest {
    @Test
    fun rendersBaseRange() {
        val html = createHTML(prettyPrint = false).div {
            daisyRange(min = "0", max = "100", value = "25", step = "25", size = RangeSize.Xs)
        }
        assertEquals(
            expected = """<div><input type="range" class="range range-xs" min="0" max="100" value="25" step="25"></div>""",
            actual = html.trim(),
        )
    }
}
