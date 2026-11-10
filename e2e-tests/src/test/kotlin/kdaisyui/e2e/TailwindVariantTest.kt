package kdaisyui.e2e

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/**
 * Whether `lg:btn-lg` actually applies, which docs/explanation.md promises consumers.
 *
 * The control button carries `btn-lg` outright; the subject carries `lg:btn-lg`. Comparing the
 * two needs no hard-coded size and survives any DaisyUI restyle.
 */
class TailwindVariantTest : PlaywrightSpec() {

    init {
        test("The variant applies at the lg breakpoint") {
            openAt(DESKTOP)
            heightOf("variant-subject") shouldBe heightOf("variant-control")
        }

        test("The variant does not apply below the lg breakpoint") {
            openAt(TABLET)
            heightOf("variant-subject") shouldNotBe heightOf("variant-control")
        }

        test("A max-width variant applies below its breakpoint") {
            openAt(TABLET)
            heightOf("variant-subject-max") shouldBe heightOf("variant-control")
        }

        test("A max-width variant does not apply above its breakpoint") {
            openAt(DESKTOP)
            heightOf("variant-subject-max") shouldNotBe heightOf("variant-control")
        }
    }

    private fun openAt(viewport: Viewport) {
        page.setViewportSize(viewport.width, viewport.height)
        page.navigate("/tailwind-variant")
    }

    private fun heightOf(elementId: String): String = page.computedStyle(elementId, "height")

    private class Viewport(val width: Int, val height: Int)

    private companion object {
        val DESKTOP = Viewport(1280, 800)
        val TABLET = Viewport(800, 600)
    }
}
