package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ToggleTest {
    @Test
    fun rendersBaseToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle()
        }
        assertEquals(
            expected = """<div><input type="checkbox" class="toggle"></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersCheckedSmToggle() {
        val html = createHTML(prettyPrint = false).div {
            daisyToggle(size = ToggleSize.Sm, checked = true)
        }
        assertEquals(
            expected = """<div><input type="checkbox" class="toggle toggle-sm" checked="checked"></div>""",
            actual = html.trim(),
        )
    }
}
