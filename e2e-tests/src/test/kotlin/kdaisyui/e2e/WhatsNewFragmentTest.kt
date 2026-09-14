package kdaisyui.e2e

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole

/** Mirrors `OTP_DIGITS` in the example app: spans, maxlength and pattern must agree. */
private const val OTP_DIGITS = 6

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
            assertThat(otp.locator("span")).hasCount(OTP_DIGITS)

            // The input is the half this test could not see until 2026-09-14, and its absence is
            // exactly what it should have caught: the card rendered six spans carrying digits and
            // NO input, so the component was not being used at all while every assertion passed.
            //
            // otp.css paints the characters from the `> input` layer, so without it the boxes are
            // empty frames and anything written into the spans misses their alignment.
            val digitEntry = otp.locator("input")
            assertThat(digitEntry).hasCount(1)
            assertThat(digitEntry).hasAttribute("maxlength", OTP_DIGITS.toString())

            // DaisyUI: "make sure the number of spans matches the maxlength and the pattern".
            // Asserting all three together is what makes a mismatch a failure rather than a
            // component that silently renders one box too few.
            assertThat(digitEntry).hasAttribute("pattern", "[0-9]{$OTP_DIGITS}")

            // Typed into, not pre-filled: no DaisyUI example pre-fills an OTP, and asserting the
            // value renders proves the input layer is the one drawing the characters.
            digitEntry.fill("481523")
            assertThat(digitEntry).hasValue("481523")
        }

        test("megamenu opens its panel on the trigger") {
            page.navigate("/")

            assertThat(page.getByRole(AriaRole.HEADING, GetByRoleOptions().setName("Navigation Preview")))
                .isVisible(IsVisibleOptions().setTimeout(5000.0))

            val megamenu = page.locator("#dashboard-whats-new-megamenu")
            assertThat(megamenu).hasClass(Regex(".*\\bmegamenu\\b.*").toPattern())
            assertThat(megamenu).hasClass(Regex(".*\\bmegamenu-wide\\b.*").toPattern())
            // span, not div: the panels beside it are selected by :nth-of-type, so a div
            // indicator would take div index 1 and shift every panel by one.
            assertThat(megamenu.locator("span.megamenu-active")).hasCount(1)

            // The menu now lives in a closed popover panel, so it starts hidden. Asserting that
            // first is what makes the click below evidence: the previous version of this test
            // asserted only visibility, which a megamenu that could never open also satisfied.
            val panel = page.locator("#dashboard-whats-new-megamenu-panel")
            assertThat(panel).hasCount(1)
            assertThat(megamenu.getByText("Repositories")).isHidden()

            // By name and exact: getByText("Browse") is a case-insensitive substring match and
            // would also match prose elsewhere on the dashboard.
            page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Browse").setExact(true)).click()

            assertThat(panel).isVisible()
            // By text, not by AriaRole.LINK: these anchors carry no href, so they have no link
            // role — same as the existing Team Activity menu this fragment is modelled on.
            assertThat(megamenu.getByText("Repositories")).isVisible()
        }
    }
}
