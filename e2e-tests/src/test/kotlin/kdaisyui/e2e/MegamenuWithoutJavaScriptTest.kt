package kdaisyui.e2e

import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.options.AriaRole
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

/**
 * The megamenu reference page with scripting off: the documented construction is served, and a
 * panel opens on the browser's popover alone. The markup test only fetches HTML, which scripting
 * does not touch.
 */
class MegamenuWithoutJavaScriptTest : PlaywrightSpec(javaScriptEnabled = false) {

    init {
        test("The served markup carries the documented megamenu construction") {
            val response = page.request().get("$BASE_URL/megamenu-reference")
            response.status() shouldBe 200
            val body = response.text()
            body shouldContain """class="megamenu"""
            body shouldContain """popover="""""
            body shouldContain "megamenu-active"
        }

        test("A panel opens with JavaScript disabled") {
            page.navigate("/megamenu-reference")
            page.isPopoverOpen(PANEL).shouldBeFalse()

            page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Components").setExact(true)).click()

            page.isPopoverOpen(PANEL).shouldBeTrue()
        }
    }

    private companion object {
        const val PANEL = "megamenu-panel-one"
    }
}
