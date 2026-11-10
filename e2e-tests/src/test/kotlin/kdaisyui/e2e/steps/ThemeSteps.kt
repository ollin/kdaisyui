package kdaisyui.e2e.steps

import io.cucumber.java.en.Then
import kdaisyui.example.Profile

/** Steps for the profile's theme picker. */
class ThemeSteps(private val world: PlaywrightWorld) {

    @Then("the theme form offers {int} choices")
    fun themeFormOffers(count: Int) {
        val choices = world.page.locator("#${Profile.ThemeForm().id} input[type=radio][name=theme]").count()
        check(choices == count) { "Expected $count theme choices, found $choices" }
    }

    /** Every preview computing a distinct value is what shows each wears its own theme, not the page's. */
    @Then("the {int} theme previews each compute their own {string}")
    fun previewsComputeTheirOwn(count: Int, property: String) {
        val values = world.page.locator("#${Profile.ThemeForm().id} [data-theme]")
            .evaluateAll("(els, prop) => els.map(e => getComputedStyle(e).getPropertyValue(prop))", property) as List<*>
        check(values.size == count && values.toSet().size == count) { "Expected $count previews with distinct $property, got $values" }
    }
}
