package kdaisyui.e2e

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import kotlin.math.abs

/**
 * DaisyUI's popover dropdown, anchored to its trigger with no JavaScript. The whole spec runs with
 * scripting off; the markup test only fetches HTML, which scripting does not touch.
 */
class DropdownPopoverTest : PlaywrightSpec(javaScriptEnabled = false) {

    init {
        test("The served markup pairs the trigger and the menu") {
            val response = page.request().get("$BASE_URL/dropdown-popover")
            response.status() shouldBe 200
            val body = response.text()
            body shouldContain """popovertarget="$MENU""""
            body shouldContain """id="$MENU""""
            body shouldNotContain "data-anchor-name"
            body shouldNotContain "data-position-anchor"
            body shouldNotContain "style="
        }

        test("The menu opens below its trigger with JavaScript disabled") {
            page.navigate("/dropdown-popover")
            page.isPopoverOpen(MENU).shouldBeFalse()

            page.getByText(TRIGGER).first().click()

            page.isPopoverOpen(MENU).shouldBeTrue()
            menuSitsBelowTrigger()
        }
    }

    /**
     * Where the menu opens. A popover whose anchor does not resolve opens in the middle of the
     * viewport, so "it is open" alone would pass with the anchoring broken. Polls, because DaisyUI
     * scales the menu in over 200ms and a box measured mid-transition is shifted by the scale.
     */
    private fun menuSitsBelowTrigger() {
        val trigger = checkNotNull(page.getByText(TRIGGER).first().boundingBox()) { "\"$TRIGGER\" is not rendered" }
        val menu = page.locator("#$MENU")
        val deadline = System.currentTimeMillis() + SETTLE_TIMEOUT_MS
        var gap = Double.MAX_VALUE
        while (System.currentTimeMillis() < deadline) {
            gap = abs(checkNotNull(menu.boundingBox()) { "#$MENU is not rendered" }.y - (trigger.y + trigger.height))
            if (gap <= TOLERANCE_PX) return
            Thread.sleep(POLL_MS)
        }
        error("#$MENU's top edge is ${gap}px from the bottom of \"$TRIGGER\"")
    }

    private companion object {
        const val MENU = "dashboard-dropdown-popover"
        const val TRIGGER = "Open menu"
        const val TOLERANCE_PX = 4.0
        const val SETTLE_TIMEOUT_MS = 2_000L
        const val POLL_MS = 50L
    }
}
