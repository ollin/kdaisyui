package kdaisyui.e2e

import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StatsFragmentTest : PlaywrightTestBase() {

    @Test
    fun `loads via htmx after page load`() {
        page.navigate("/")

        assertThat(page.getByText("Active Repositories").first()).isVisible(
            com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions().setTimeout(5000.0)
        )
        assertThat(page.getByText("142").first()).isVisible()
        assertThat(page.getByText("Open Issues").first()).isVisible()
    }

    @Test
    fun `fragment endpoint returns HTML directly`() {
        val response = page.request().get("$BASE_URL/fragments/stats")
        assertEquals(200, response.status())
        assertTrue(response.headers()["content-type"]!!.contains("text/html"))

        val body = response.text()
        assertTrue(body.contains("stat-title"))
        assertTrue(body.contains("Active Repositories"))
    }
}
