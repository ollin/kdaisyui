package kdaisyui.e2e

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.options.FormData
import com.microsoft.playwright.options.RequestOptions
import io.github.ollin.kdaisyui.themes.DaisyTheme
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContainDuplicates
import io.kotest.matchers.doubles.shouldBeGreaterThanOrEqual
import io.kotest.matchers.doubles.shouldBeLessThanOrEqual
import io.kotest.matchers.maps.shouldNotContainKey
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import kdaisyui.example.Dashboard
import kdaisyui.example.Profile

/** The user's profile, where a theme is chosen with a preview of each. */
class ProfileTest : PlaywrightSpec() {

    private val form: Locator get() = page.locator("#${Profile.ThemeForm().id}")

    init {
        test("The profile page is served in the app's frame") {
            page.navigate("/profile")
            assertThat(heading("Profile")).isVisible()
            val sidebar = page.locator(Dashboard.Sidebar().target)
            assertThat(sidebar.locator("a", Locator.LocatorOptions().setHasText("Pipelines"))).isVisible()
        }

        test("Every built-in theme is offered, and the system default") {
            page.navigate("/profile")
            form.locator("input[type=radio][name=theme]").count() shouldBe DaisyTheme.entries.size + 1
        }

        test("The stylesheet carries the themes beyond DaisyUI's default two") {
            val response = page.request().get("$BASE_URL/static/app.css")
            response.status() shouldBe 200
            val stylesheet = response.text()
            stylesheet shouldContain "[data-theme=nord]"
            stylesheet shouldContain "[data-theme=abyss]"
        }

        // Every preview computing a distinct value is what shows each wears its own theme, not
        // the page's.
        test("Every theme is previewed in itself") {
            page.navigate("/profile")
            val primaries = form.locator("[data-theme]")
                .evaluateAll("els => els.map(e => getComputedStyle(e).getPropertyValue('--color-primary'))") as List<*>
            primaries shouldHaveSize DaisyTheme.entries.size
            primaries.shouldNotContainDuplicates()
        }

        test("Saving a theme keeps it in a cookie and returns to the profile") {
            page.navigate("/profile")
            chooseThemeAndSave(NORD)

            assertThat(heading("Profile")).isVisible()
            context.cookies().firstOrNull { it.name == "theme" }?.value shouldBe NORD
            themeOfPage() shouldBe NORD
            assertThat(choice(NORD)).isChecked()
            assertThat(tileOf(NORD)).containsText("Current")
        }

        test("A value that is not a theme is refused") {
            val request = RequestOptions.create()
                .setForm(FormData.create().set("theme", "no-such-theme"))
                .setMaxRedirects(0)
            val response = page.request().post("$BASE_URL/profile/theme", request)
            response.status() shouldBe 400
            response.headers() shouldNotContainKey "set-cookie"
        }

        test("The saved theme is worn by the dashboard") {
            page.navigate("/profile")
            chooseThemeAndSave(NORD)
            page.navigate("/")
            themeOfPage() shouldBe NORD
        }

        test("The system default forgets the saved theme") {
            page.navigate("/profile")
            chooseThemeAndSave(NORD)
            chooseThemeAndSave(SYSTEM)
            themeOfPage().shouldBeNull()
            assertThat(choice(SYSTEM)).isChecked()
        }

        test("The avatar menu leads to the profile") {
            page.navigate("/")
            page.locator("#${Dashboard.Header.Avatar().id} [tabindex]").first().click()
            page.getByRole(AriaRole.LINK, GetByRoleOptions().setName("Profile")).click()
            assertThat(heading("Profile")).isVisible()
        }

        test("The sidebar leads back to the dashboard") {
            page.navigate("/profile")
            page.getByText("Overview").first().click()
            assertThat(heading("Overview")).isVisible()
        }

        test("The themes are laid out as tiles, not stacked") {
            page.navigate("/profile")
            // Side by side: the second tile starts on the first one's row, to its right.
            val first = checkNotNull(form.locator("label").nth(0).boundingBox())
            val second = checkNotNull(form.locator("label").nth(1).boundingBox())
            second.y shouldBe first.y
            second.x shouldBeGreaterThanOrEqual first.x + first.width

            val save = checkNotNull(saveButton().boundingBox())
            save.y + save.height shouldBeLessThanOrEqual checkNotNull(page.viewportSize()).height.toDouble()
        }
    }

    private fun heading(name: String): Locator = page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName(name))

    private fun saveButton(): Locator = page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Save"))

    private fun choice(theme: String): Locator = form.locator("input[name=theme][value=$theme]")

    private fun tileOf(theme: String): Locator =
        form.locator("label").filter(Locator.FilterOptions().setHas(page.locator("input[value=$theme]")))

    private fun chooseThemeAndSave(theme: String) {
        choice(theme).check()
        saveButton().click()
    }

    private fun themeOfPage(): String? = page.locator("html").getAttribute("data-theme")

    private companion object {
        const val NORD = "nord"
        const val SYSTEM = "system"
    }
}
