package kdaisyui.e2e

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class TeamFragmentTest : PlaywrightSpec() {

    init {
        test("team form loads after scroll to bottom") {
            page.navigate("/")
            page.evaluate("() => window.scrollTo(0, document.body.scrollHeight)")

            assertThat(page.getByText("Team management"))
                .isVisible(IsVisibleOptions().setTimeout(10000.0))
            assertThat(page.getByText("Invitation sent successfully")).isVisible()
            assertThat(page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Send invitation"))).isVisible()
        }

        test("team fragment endpoint returns HTML") {
            val response = page.request().get("$BASE_URL/fragments/team")
            response.status() shouldBe 200

            val body = response.text()
            body shouldContain "Send invitation"
            body shouldContain "alert-success"
            body shouldContain "Active team members"
        }
    }
}
