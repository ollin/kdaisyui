package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class RadioTest {
    @Test
    fun rendersBaseRadio() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(name = "color")
        }
        assertEquals(
            expected = """<div><input type="radio" class="radio" name="color"></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersCheckedSmRadio() {
        val html = createHTML(prettyPrint = false).div {
            daisyRadio(name = "visibility", size = RadioSize.Sm, checked = true)
        }
        assertEquals(
            expected = """<div><input type="radio" class="radio radio-sm" name="visibility" checked="checked"></div>""",
            actual = html.trim(),
        )
    }
}
