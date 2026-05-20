package kdaisyui.e2e.steps

import com.microsoft.playwright.Locator.FilterOptions
import com.microsoft.playwright.Locator.LocatorOptions
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class NavigationSteps(private val world: PlaywrightWorld) {

    @Given("the user navigates to the dashboard")
    fun navigateToDashboard() {
        world.page.navigate("/")
    }

    @Then("the text {string} is visible")
    fun textIsVisible(text: String) {
        assertThat(world.page.getByText(text).first()).isVisible()
    }

    @Then("the heading {string} is visible")
    fun headingIsVisible(name: String) {
        assertThat(world.page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName(name))).isVisible()
    }

    @Then("the placeholder {string} is visible")
    fun placeholderIsVisible(placeholder: String) {
        assertThat(world.page.getByPlaceholder(placeholder).first()).isVisible()
    }

    @Then("the button {string} is visible")
    fun buttonIsVisible(name: String) {
        assertThat(world.page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName(name))).isVisible()
    }

    @Then("the sidebar contains the following navigation items:")
    fun sidebarContainsNavigationItems(dataTable: DataTable) {
        val sidebar = world.page.locator("aside.drawer-side nav")
        for (row in dataTable.asMaps()) {
            val label = row["label"]!!
            val link = sidebar.locator("a", LocatorOptions().setHasText(label)).first()
            val summary = sidebar.locator("summary", LocatorOptions().setHasText(label))
            val either = link.or(summary)
            assertThat(either.first()).isAttached()
        }
    }

    @When("the user clicks the sidebar summary {string}")
    fun clickSidebarSummary(text: String) {
        val sidebar = world.page.locator("aside.drawer-side nav")
        sidebar.locator("summary").filter(FilterOptions().setHasText(text)).click()
    }

    @Then("the text {string} is visible in the sidebar")
    fun textIsVisibleInSidebar(text: String) {
        val sidebar = world.page.locator("aside.drawer-side nav")
        assertThat(sidebar.locator("a", LocatorOptions().setHasText(text))).isVisible()
    }

    @Then("the first nav element has a non-transparent background color")
    fun firstNavHasBackground() {
        val sidebar = world.page.locator("nav").first()
        val bgColor = sidebar.evaluate("el => window.getComputedStyle(el).backgroundColor") as String
        check(bgColor != "rgba(0, 0, 0, 0)") { "Expected non-transparent background, got: $bgColor" }
        check(bgColor.isNotEmpty()) { "Background color is empty" }
    }
}
