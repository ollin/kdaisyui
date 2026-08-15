package kdaisyui.e2e

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole

/**
 * End-to-end coverage for the components DaisyUI 5.6/5.7 added: `aura`, `otp`, `megamenu`.
 *
 * The generated unit tests already assert the rendered class strings. What they cannot show is
 * that the wrapper produces a usable element inside a real page — `daisyDropdown` passed every
 * generated test at 5.7.16 while rendering a `<ul>` that could never open. These assertions go
 * through the served HTML instead.
 */
class WhatsNewFragmentTest : PlaywrightSpec() {

    init {
        test("aura wraps the deploy status") {
            page.navigate("/")

            assertThat(page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName("Deploy Status")))
                .isVisible(IsVisibleOptions().setTimeout(5000.0))

            val aura = page.locator("div.aura")
            assertThat(aura).hasCount(1)
            assertThat(aura).hasClass(Regex(".*\\baura-lg\\b.*").toPattern())
            assertThat(aura).hasClass(Regex(".*\\baura-rainbow\\b.*").toPattern())
            assertThat(page.getByText("v2.4.1 live")).isVisible()
        }

        test("otp renders its joined digit boxes") {
            page.navigate("/")

            assertThat(page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName("Two-Factor Code")))
                .isVisible(IsVisibleOptions().setTimeout(5000.0))

            val otp = page.locator("#dashboard-whats-new-otp")
            assertThat(otp).hasClass(Regex(".*\\botp\\b.*").toPattern())
            assertThat(otp).hasClass(Regex(".*\\botp-primary\\b.*").toPattern())
            assertThat(otp).hasClass(Regex(".*\\botp-joined\\b.*").toPattern())
            assertThat(otp.locator("span")).hasCount(6)
        }

        test("megamenu renders its active popover and menu") {
            page.navigate("/")

            assertThat(page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName("Navigation Preview")))
                .isVisible(IsVisibleOptions().setTimeout(5000.0))

            val megamenu = page.locator("#dashboard-whats-new-megamenu")
            assertThat(megamenu).hasClass(Regex(".*\\bmegamenu\\b.*").toPattern())
            assertThat(megamenu).hasClass(Regex(".*\\bmegamenu-wide\\b.*").toPattern())
            assertThat(megamenu.locator("div.megamenu-active")).hasCount(1)
            assertThat(megamenu.locator("ul.menu")).hasCount(1)
            // By text, not by AriaRole.LINK: these anchors carry no href, so they have no link
            // role — same as the existing Team Activity menu this fragment is modelled on.
            assertThat(megamenu.getByText("Repositories")).isVisible()
        }
    }
}
