package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertTrue

class ButtonTest {
    @Test
    fun rendersBaseButtonClass() {
        val html = createHTML().div {
            daisyButton("Hello")
        }

        assertTrue(html.contains("<button"))
        assertTrue(html.contains("class=\"btn\"") || html.contains("class=\"btn "))
    }

    @Test
    fun rendersVariantAndDisabled() {
        val html = createHTML().div {
            daisyButton("Hello", variant = ButtonVariant.Primary, disabled = true)
        }

        assertTrue(html.contains("btn-primary"))
        assertTrue(html.contains("disabled"))
    }
}
