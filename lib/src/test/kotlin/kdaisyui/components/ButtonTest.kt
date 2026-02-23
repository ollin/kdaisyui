package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class ButtonTest {
    @Test
    fun rendersBaseButtonClass() {
        val html = createHTML().div {
            daisyButton("Hello")
        }

        assertEquals(
            expected = """
                <div><button class="btn">Hello</button></div>
            """.trimIndent(),
            actual = html
        )
    }

    @Test
    fun rendersVariantAndDisabled() {
        val html = createHTML().div {
            daisyButton("Hello", variant = ButtonVariant.Primary, disabled = true)
        }

        assertEquals(
            expected = """
                <div><button class="btn btn-primary" disabled="disabled">Hello</button></div>
            """.trimIndent(),
            actual = html
        )
    }
}
