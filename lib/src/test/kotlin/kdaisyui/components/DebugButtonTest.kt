package kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test

class DebugButtonTest {
    @Test
    fun printActualHtml() {
        val html1 = createHTML().div {
            daisyButton("Hello")
        }
        println("=== Base Button ===")
        println("Length: ${html1.length}")
        println("Repr: ${html1.toCharArray().joinToString { "'$it'" }}")
        println("HTML: $html1")
        
        val html2 = createHTML().div {
            daisyButton("Hello", variant = ButtonVariant.Primary, disabled = true)
        }
        println("=== Variant + Disabled ===")
        println("Length: ${html2.length}")
        println("Repr: ${html2.toCharArray().joinToString { "'$it'" }}")
        println("HTML: $html2")
    }
}
