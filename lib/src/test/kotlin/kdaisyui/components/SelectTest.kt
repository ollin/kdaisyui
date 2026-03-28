package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.option
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class SelectTest {
    @Test
    fun rendersBaseSelect() {
        val html = createHTML(prettyPrint = false).div {
            daisySelect(extraClasses = "w-full") {
                option { disabled = true; selected = true; +"Pick" }
                option { +"T-shirts" }
            }
        }
        assertEquals(
            expected = """<div><select class="select w-full"><option disabled="disabled" selected="selected">Pick</option><option>T-shirts</option></select></div>""",
            actual = html.trim(),
        )
    }
}
