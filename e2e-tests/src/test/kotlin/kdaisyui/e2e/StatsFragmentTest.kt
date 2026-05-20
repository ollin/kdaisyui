package kdaisyui.e2e

import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class StatsFragmentTest : PlaywrightSpec() {

    init {
        test("loads via htmx after page load") {
            page.navigate("/")

            assertThat(page.getByText("Active Repositories").first()).isVisible(
                IsVisibleOptions().setTimeout(5000.0)
            )
            assertThat(page.getByText("142").first()).isVisible()
            assertThat(page.getByText("Open Issues").first()).isVisible()
        }

        test("fragment endpoint returns HTML directly") {
            val response = page.request().get("$BASE_URL/fragments/stats")
            response.status() shouldBe 200
            response.headers()["content-type"]!! shouldContain "text/html"

            val body = response.text()
            body shouldContain "stat-title"
            body shouldContain "Active Repositories"
        }
    }
}
