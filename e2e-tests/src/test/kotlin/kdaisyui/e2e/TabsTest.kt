package kdaisyui.e2e

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain

/**
 * `daisyTabs` renders the tablist and `daisyTab` the items, as DaisyUI documents them.
 *
 * Until declare-the-component-model the container rendered a button and an active item emitted
 * `tabs-tab-active`, a class DaisyUI does not have, so an active tab looked like any other. The
 * inactive tab is the positive control: without it, two unstyled tabs would agree.
 */
class TabsTest : PlaywrightSpec() {

    init {
        test("The served markup is a tablist with tab items") {
            val response = page.request().get("$BASE_URL/tabs")
            response.status() shouldBe 200
            val body = response.text()
            body shouldContain """role="tablist""""
            body shouldContain """class="tab tab-active""""
        }

        test("An active tab looks different from an inactive one") {
            page.navigate("/tabs")
            page.computedStyle("tab-active", "background-color") shouldNotBe
                page.computedStyle("tab-inactive", "background-color")
        }
    }
}
