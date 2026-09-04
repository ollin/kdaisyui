package kdaisyui.e2e.steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

/**
 * Steps for DaisyUI's popover modal method.
 *
 * Openness is asserted through the `:popover-open` pseudo-class rather than through visibility.
 * That is deliberate: `:popover-open` is the browser's own record of whether the popover is
 * showing, so it holds whatever the stylesheet does — and in this example app the stylesheet is
 * compiled in the browser by Tailwind, which a `@nojs` scenario has switched off.
 */
class PopoverModalSteps(private val world: PlaywrightWorld) {

    @Given("the user opens the popover modal page")
    fun openPopoverModalPage() {
        world.page.navigate("/popover-modal")
    }

    @When("the user clicks {string}")
    fun clickText(text: String) {
        world.page.getByText(text).first().click()
    }

    /**
     * Escape rather than the modal's own close button. Both are browser-native, but clicking
     * inside the open modal needs Playwright to see a stable box, and in a `@nojs` scenario the
     * modal is only half-styled — Tailwind never compiled the `@apply` rules that lay it out.
     * Escape closes an auto popover without touching layout at all.
     */
    @When("the user presses Escape")
    fun pressEscape() {
        world.page.keyboard().press("Escape")
    }

    @Then("the popover {string} is open")
    fun popoverIsOpen(elementId: String) {
        check(isPopoverOpen(elementId)) { "Expected #$elementId to be open" }
    }

    @Then("the popover {string} is closed")
    fun popoverIsClosed(elementId: String) {
        check(!isPopoverOpen(elementId)) { "Expected #$elementId to be closed" }
    }

    private fun isPopoverOpen(elementId: String): Boolean =
        world.page.locator("#$elementId:popover-open").count() == 1
}
