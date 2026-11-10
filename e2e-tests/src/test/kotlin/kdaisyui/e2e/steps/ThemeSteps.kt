package kdaisyui.e2e.steps

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.options.FormData
import com.microsoft.playwright.options.RequestOptions
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kdaisyui.e2e.SharedInfrastructure
import kdaisyui.example.Dashboard
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

    @When("the user chooses the theme {string} and saves")
    fun chooseThemeAndSave(theme: String) {
        world.page.locator("#${Profile.ThemeForm().id} input[name=theme][value=$theme]").check()
        world.page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Save")).click()
    }

    @Then("the browser keeps the cookie {string} as {string}")
    fun browserKeepsCookie(name: String, value: String) {
        val actual = world.context.cookies().firstOrNull { it.name == name }?.value
        check(actual == value) { "Expected cookie $name=$value, got $actual" }
    }

    @Then("posting the theme {string} to {string} answers {int} and sets no cookie")
    fun postingTheme(theme: String, path: String, status: Int) {
        val form = RequestOptions.create().setForm(FormData.create().set("theme", theme)).setMaxRedirects(0)
        val response = world.page.request().post("${SharedInfrastructure.BASE_URL}$path", form)
        check(response.status() == status && "set-cookie" !in response.headers()) { "Expected $status without a cookie, got ${response.status()} ${response.headers()}" }
    }

    @Then("the page wears the theme {string}")
    fun pageWearsTheme(theme: String) {
        val actual = world.page.locator("html").getAttribute("data-theme")
        check(actual == theme) { "Expected html data-theme=$theme, got $actual" }
    }

    @Then("the theme choice {string} is checked")
    fun themeChoiceIsChecked(theme: String) {
        assertThat(world.page.locator("#${Profile.ThemeForm().id} input[name=theme][value=$theme]")).isChecked()
    }

    @Then("the page wears no theme of its own")
    fun pageWearsNoTheme() {
        val actual = world.page.locator("html").getAttribute("data-theme")
        check(actual == null) { "Expected html without data-theme, got $actual" }
    }

    @When("the user opens the profile from the avatar menu")
    fun openProfileFromAvatarMenu() {
        world.page.locator("#${Dashboard.Header.Avatar().id} [tabindex]").first().click()
        world.page.getByRole(AriaRole.LINK, GetByRoleOptions().setName("Profile")).click()
    }
}
