package kdaisyui.e2e

import io.kotest.matchers.shouldBe
import java.util.concurrent.atomic.AtomicInteger

/**
 * The control for `javaScriptEnabled = false`: the dashboard loads its fragments through htmx, so
 * with scripting off none may arrive. `HtmxProgressiveLoadingTest` asserts the opposite with
 * scripting on, so the two together show the flag reaches the browser.
 */
class WithoutJavaScriptTest : PlaywrightSpec(javaScriptEnabled = false) {

    init {
        test("the dashboard requests no fragment") {
            val fragmentResponseCount = AtomicInteger(0)
            page.onResponse { response ->
                if (response.url().contains("/fragments/")) {
                    fragmentResponseCount.incrementAndGet()
                }
            }

            page.navigate("/")
            page.waitForTimeout(3000.0)

            fragmentResponseCount.get() shouldBe 0
        }
    }
}
