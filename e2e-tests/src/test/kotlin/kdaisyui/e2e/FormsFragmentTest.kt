package kdaisyui.e2e

import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import io.kotest.matchers.collections.shouldContain

class FormsFragmentTest : PlaywrightSpec() {

    init {
        test("repository form inputs are present after scroll") {
            page.navigate("/")
            page.evaluate("() => window.scrollTo(0, document.body.scrollHeight / 2)")

            assertThat(page.getByText("Repository management"))
                .isVisible(IsVisibleOptions().setTimeout(8000.0))
            assertThat(page.getByPlaceholder("devtrack/api-gateway").first()).isVisible()
        }

        test("select dropdown has gitignore options") {
            page.navigate("/")
            page.evaluate("() => window.scrollTo(0, document.body.scrollHeight / 2)")

            page.waitForSelector("select.select", com.microsoft.playwright.Page.WaitForSelectorOptions().setTimeout(8000.0))
            val select = page.locator("select.select").nth(1)
            assertThat(select).isVisible()

            val options = select.locator("option").allTextContents()
            options shouldContain "Node"
        }
    }
}
