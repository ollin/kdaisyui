package kdaisyui.e2e

import com.microsoft.playwright.Locator.FilterOptions
import com.microsoft.playwright.Locator.LocatorOptions
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.junit.jupiter.api.Test

class DashboardShellTest : PlaywrightTestBase() {

    @Test
    fun `loads with sidebar and header visible immediately`() {
        page.navigate("/")

        assertThat(page.getByText("DevTrack")).isVisible()
        assertThat(page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName("Overview"))).isVisible()
        assertThat(page.getByPlaceholder("Search")).isVisible()
    }

    @Test
    fun `sidebar navigation items are present`() {
        page.navigate("/")

        val sidebar = page.locator("aside.drawer-side nav")
        assertThat(sidebar.locator("a", LocatorOptions().setHasText("Overview")).first()).isAttached()
        assertThat(sidebar.locator("summary", LocatorOptions().setHasText("Repositories"))).isAttached()
        assertThat(sidebar.locator("a", LocatorOptions().setHasText("Issues"))).isAttached()
        assertThat(sidebar.locator("a", LocatorOptions().setHasText("Pipelines"))).isAttached()
        assertThat(sidebar.locator("a", LocatorOptions().setHasText("Teams"))).isAttached()
        assertThat(sidebar.locator("a", LocatorOptions().setHasText("Settings"))).isAttached()
    }

    @Test
    fun `sidebar submenu expands on click`() {
        page.navigate("/")

        val sidebar = page.locator("aside.drawer-side nav")
        sidebar.locator("summary").filter(FilterOptions().setHasText("Repositories")).click()

        assertThat(sidebar.locator("a", LocatorOptions().setHasText("All Repos"))).isVisible()
        assertThat(sidebar.locator("a", LocatorOptions().setHasText("New Repository"))).isVisible()
    }

    @Test
    fun `CSS is loaded — DaisyUI classes are applied`() {
        page.navigate("/")

        val sidebar = page.locator("nav").first()
        val bgColor = sidebar.evaluate("el => window.getComputedStyle(el).backgroundColor") as String
        assert(bgColor != "rgba(0, 0, 0, 0)") { "Background color should not be transparent" }
        assert(bgColor.isNotEmpty()) { "Background color should not be empty" }
    }
}
