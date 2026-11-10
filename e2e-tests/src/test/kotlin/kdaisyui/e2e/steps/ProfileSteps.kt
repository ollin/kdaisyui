package kdaisyui.e2e.steps

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.options.FormData
import com.microsoft.playwright.options.RequestOptions
import io.cucumber.java.ParameterType
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.github.ollin.kdaisyui.themes.DaisyTheme
import kdaisyui.e2e.SharedInfrastructure
import kdaisyui.example.Dashboard
import kdaisyui.example.Profile

/** A choice on the profile's theme form: a theme's `data-theme` value, `system`, or anything a request may send. */
data class ThemeChoice(val value: String)

/** Steps for the profile page: its theme picker, the cookie it keeps, and the way to it. */
class ProfileSteps(private val world: PlaywrightWorld) {

    private val form get() = world.page.locator("#${Profile.ThemeForm().id}")

    @ParameterType("\"([a-z-]+)\"")
    fun choice(value: String) = ThemeChoice(value)

    @Then("the theme form offers every theme and the system default")
    fun themeFormOffersEveryTheme() {
        val choices = form.locator("input[type=radio][name=theme]").count()
        check(choices == DaisyTheme.entries.size + 1) { "Expected ${DaisyTheme.entries.size + 1} theme choices, found $choices" }
    }

    /** Every preview computing a distinct value is what shows each wears its own theme, not the page's. */
    @Then("every theme preview computes its own primary colour")
    fun previewsComputeTheirOwnPrimary() {
        val values = form.locator("[data-theme]")
            .evaluateAll("els => els.map(e => getComputedStyle(e).getPropertyValue('--color-primary'))") as List<*>
        check(values.size == DaisyTheme.entries.size && values.toSet().size == values.size) { "Expected one distinct --color-primary per theme, got $values" }
    }

    @When("the user chooses the theme {choice} and saves")
    fun chooseThemeAndSave(choice: ThemeChoice) {
        form.locator("input[name=theme][value=${choice.value}]").check()
        world.page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Save")).click()
    }

    @Then("the browser keeps the theme {choice}")
    fun browserKeepsTheme(choice: ThemeChoice) {
        val actual = world.context.cookies().firstOrNull { it.name == "theme" }?.value
        check(actual == choice.value) { "Expected the theme cookie to be ${choice.value}, got $actual" }
    }

    @Then("saving the theme {choice} is refused without a cookie")
    fun savingIsRefused(choice: ThemeChoice) {
        val request = RequestOptions.create().setForm(FormData.create().set("theme", choice.value)).setMaxRedirects(0)
        val response = world.page.request().post("${SharedInfrastructure.BASE_URL}/profile/theme", request)
        check(response.status() == 400 && "set-cookie" !in response.headers()) { "Expected 400 without a cookie, got ${response.status()} ${response.headers()}" }
    }

    @Then("the page wears the theme {choice}")
    fun pageWearsTheme(choice: ThemeChoice) {
        val actual = world.page.locator("html").getAttribute("data-theme")
        check(actual == choice.value) { "Expected html data-theme=${choice.value}, got $actual" }
    }

    @Then("the page wears no theme of its own")
    fun pageWearsNoTheme() {
        val actual = world.page.locator("html").getAttribute("data-theme")
        check(actual == null) { "Expected html without data-theme, got $actual" }
    }

    @Then("the theme choice {choice} is checked")
    fun themeChoiceIsChecked(choice: ThemeChoice) {
        assertThat(form.locator("input[name=theme][value=${choice.value}]")).isChecked()
    }

    @When("the user opens the profile from the avatar menu")
    fun openProfileFromAvatarMenu() {
        world.page.locator("#${Dashboard.Header.Avatar().id} [tabindex]").first().click()
        world.page.getByRole(AriaRole.LINK, GetByRoleOptions().setName("Profile")).click()
    }

    /** Side by side, not stacked: the second tile starts on the first one's row, to its right. */
    @Then("the theme tiles sit side by side")
    fun tilesSitSideBySide() {
        val first = checkNotNull(form.locator("label").nth(0).boundingBox())
        val second = checkNotNull(form.locator("label").nth(1).boundingBox())
        check(first.y == second.y && second.x >= first.x + first.width) { "Expected two tiles in one row, got ${first.x},${first.y} and ${second.x},${second.y}" }
    }

    @Then("the theme {choice} is marked as current")
    fun themeIsMarkedAsCurrent(choice: ThemeChoice) {
        val tile = form.locator("label").filter(Locator.FilterOptions().setHas(world.page.locator("input[value=${choice.value}]")))
        assertThat(tile).containsText("Current")
    }
}
