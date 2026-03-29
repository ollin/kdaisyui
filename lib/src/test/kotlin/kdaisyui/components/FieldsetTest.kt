package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.label
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class FieldsetTest {
    @Test
    fun rendersBaseFieldset() {
        val html = createHTML(prettyPrint = false).div {
            daisyFieldset {
                label { +"Product name" }
            }
        }
        assertEquals(
            expected = """<div><fieldset class="fieldset"><label>Product name</label></fieldset></div>""",
            actual = html.trim(),
        )
    }
}
