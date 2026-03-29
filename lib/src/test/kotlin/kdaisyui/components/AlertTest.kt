package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.span
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AlertTest {
    @Test
    fun rendersBaseAlert() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert {
                span { +"Something happened" }
            }
        }
        assertEquals(
            expected = """<div><div role="alert" class="alert"><span>Something happened</span></div></div>""",
            actual = html.trim(),
        )
    }

    @Test
    fun rendersSuccessAlert() {
        val html = createHTML(prettyPrint = false).div {
            daisyAlert(variant = AlertVariant.Success) {
                span { +"Payment successful" }
            }
        }
        assertEquals(
            expected = """<div><div role="alert" class="alert alert-success"><span>Payment successful</span></div></div>""",
            actual = html.trim(),
        )
    }
}
