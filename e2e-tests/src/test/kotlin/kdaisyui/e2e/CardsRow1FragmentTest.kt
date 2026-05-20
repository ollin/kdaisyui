package kdaisyui.e2e

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.junit.jupiter.api.Test

class CardsRow1FragmentTest : PlaywrightTestBase() {

    @Test
    fun `pipeline table loads`() {
        page.navigate("/")

        assertThat(page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName("Recent Pipeline Runs")))
            .isVisible(IsVisibleOptions().setTimeout(5000.0))
        assertThat(page.getByRole(AriaRole.CELL, GetByRoleOptions().setName("api-gateway"))).isVisible()
        assertThat(page.getByRole(AriaRole.CELL, GetByRoleOptions().setName("frontend"))).isVisible()
    }

    @Test
    fun `open issues card loads`() {
        page.navigate("/")

        assertThat(page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName("Open Issues")))
            .isVisible(IsVisibleOptions().setTimeout(5000.0))
        assertThat(page.getByText("Good First Issue")).isVisible()
    }
}
