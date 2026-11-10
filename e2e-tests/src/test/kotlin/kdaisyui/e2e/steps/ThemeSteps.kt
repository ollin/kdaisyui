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
}
