package kdaisyui.e2e

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import io.kotest.matchers.booleans.shouldBeTrue

/**
 * DaisyUI's documented megamenu, kept so it can be looked at.
 *
 * The screenshots land in `e2e-tests/build/reports/screenshots/`. They exist to be reviewed by a
 * human: a megamenu that renders wrongly is something an eye catches and an assertion does not.
 * These tests were written against the same markup hand-written in raw kotlinx.html, and are
 * unchanged now that the page is built from the generated wrappers — which is what makes them
 * evidence that the wrappers produce the documented construction.
 */
class MegamenuReferenceTest : PlaywrightSpec() {

    init {
        test("Megamenu at a desktop viewport") {
            page.setViewportSize(1280, 800)
            page.navigate("/megamenu-reference")
            saveScreenshot("megamenu-desktop-closed")

            clickButton("Components")

            page.isPopoverOpen("megamenu-panel-one").shouldBeTrue()
            assertThat(page.locator("#megamenu-panel-one")).isVisible()
            saveScreenshot("megamenu-desktop-panel-open")
        }

        test("Megamenu at a small viewport") {
            page.setViewportSize(390, 844)
            page.navigate("/megamenu-reference")
            saveScreenshot("megamenu-small-closed")

            clickButton("Menu")

            page.isPopoverOpen("megamenu-reference").shouldBeTrue()
            assertThat(page.locator("#megamenu-reference")).isVisible()
            saveScreenshot("megamenu-small-opened")
        }
    }

    /**
     * By role and exact name, not by text. `getByText` is a case-insensitive substring match, so
     * `getByText("Menu").first()` returns the heading "Megamenu reference" and clicks that instead
     * — which looks exactly like the popover failing to open.
     */
    private fun clickButton(name: String) {
        page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName(name).setExact(true)).click()
    }
}
