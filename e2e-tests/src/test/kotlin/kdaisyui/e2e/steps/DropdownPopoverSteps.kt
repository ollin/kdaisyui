package kdaisyui.e2e.steps

import io.cucumber.java.en.Then
import kotlin.math.abs

/**
 * Where an anchored popover opens. A popover whose anchor does not resolve opens in the middle of
 * the viewport, so "it is open" alone would pass with the anchoring broken.
 */
class DropdownPopoverSteps(private val world: PlaywrightWorld) {

    /**
     * Polls, because DaisyUI scales the menu in over 200ms and a box measured mid-transition is
     * shifted by the scale.
     */
    @Then("the popover {string} sits below {string}")
    fun popoverSitsBelow(elementId: String, triggerText: String) {
        val trigger = checkNotNull(world.page.getByText(triggerText).first().boundingBox()) { "\"$triggerText\" is not rendered" }
        val menu = world.page.locator("#$elementId")
        val deadline = System.currentTimeMillis() + SETTLE_TIMEOUT_MS
        var gap = Double.MAX_VALUE
        while (System.currentTimeMillis() < deadline) {
            gap = abs(checkNotNull(menu.boundingBox()) { "#$elementId is not rendered" }.y - (trigger.y + trigger.height))
            if (gap <= TOLERANCE_PX) return
            Thread.sleep(POLL_MS)
        }
        error("#$elementId's top edge is ${gap}px from the bottom of \"$triggerText\"")
    }

    private companion object {
        const val TOLERANCE_PX = 4.0
        const val SETTLE_TIMEOUT_MS = 2_000L
        const val POLL_MS = 50L
    }
}
