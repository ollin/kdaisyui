package kdaisyui.e2e

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TeamFragmentTest : PlaywrightTestBase() {

    @Test
    fun `team form loads after scroll to bottom`() {
        page.navigate("/")
        page.evaluate("() => window.scrollTo(0, document.body.scrollHeight)")

        assertThat(page.getByText("Team management"))
            .isVisible(IsVisibleOptions().setTimeout(10000.0))
        assertThat(page.getByText("Invitation sent successfully")).isVisible()
        assertThat(page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Send invitation"))).isVisible()
    }

    @Test
    fun `team fragment endpoint returns HTML`() {
        val response = page.request().get("$BASE_URL/fragments/team")
        assertEquals(200, response.status())

        val body = response.text()
        assertTrue(body.contains("Send invitation"))
        assertTrue(body.contains("alert-success"))
        assertTrue(body.contains("Active team members"))
    }
}
