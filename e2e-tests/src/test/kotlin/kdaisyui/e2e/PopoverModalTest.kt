package kdaisyui.e2e

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

/**
 * DaisyUI's popover modal method, the one that needs no JavaScript. The whole spec runs with
 * scripting off; the markup test only fetches HTML, which scripting does not touch.
 */
class PopoverModalTest : PlaywrightSpec(javaScriptEnabled = false) {

    init {
        test("The served markup carries the popover construction method") {
            val response = page.request().get("$BASE_URL/popover-modal")
            response.status() shouldBe 200
            val body = response.text()
            body shouldContain """class="modal""""
            body shouldContain """popover="""""
        }

        test("The modal opens and closes with JavaScript disabled") {
            page.navigate("/popover-modal")
            page.isPopoverOpen(MODAL).shouldBeFalse()

            page.getByText("Open modal").first().click()
            page.isPopoverOpen(MODAL).shouldBeTrue()

            // Escape rather than the modal's own close button. Both are browser-native, but
            // clicking inside the open modal needs Playwright to see a stable box, and with
            // scripting off the modal is only half-styled. Escape closes an auto popover without
            // touching layout at all.
            page.keyboard().press("Escape")
            page.isPopoverOpen(MODAL).shouldBeFalse()
        }
    }

    private companion object {
        const val MODAL = "dashboard-popover-modal"
    }
}
