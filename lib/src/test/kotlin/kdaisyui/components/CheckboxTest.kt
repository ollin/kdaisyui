package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CheckboxTest {
    @Test
    fun rendersBaseCheckbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox()
        }
        assertEquals(
            expected = """<div><input type="checkbox" class="checkbox"></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersCheckedSmCheckbox() {
        val html = createHTML(prettyPrint = false).div {
            daisyCheckbox(size = CheckboxSize.Sm, checked = true)
        }
        assertEquals(
            expected = """<div><input type="checkbox" class="checkbox checkbox-sm" checked="checked"></div>""",
            actual = html.trim(),
        )
    }
}
