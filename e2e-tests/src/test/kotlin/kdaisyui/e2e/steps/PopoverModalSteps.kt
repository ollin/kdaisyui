package kdaisyui.e2e.steps

import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
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

    /**
     * Settles the open animation before a screenshot. `:popover-open` flips synchronously on
     * click, but DaisyUI transitions opacity and scale over 200ms, so a screenshot taken straight
     * after the click catches the popover mid-fade or not at all. This assertion auto-waits, so it
     * both proves visibility and gives the transition time to finish.
     */
    @Then("the popover {string} is visible")
    fun popoverIsVisible(elementId: String) {
        assertThat(world.page.locator("#$elementId")).isVisible()
    }

    private fun isPopoverOpen(elementId: String): Boolean =
        world.page.locator("#$elementId:popover-open").count() == 1
}
