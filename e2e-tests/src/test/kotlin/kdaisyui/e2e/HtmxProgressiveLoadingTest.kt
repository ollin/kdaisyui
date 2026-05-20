package kdaisyui.e2e

import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import java.util.concurrent.atomic.AtomicInteger

class HtmxProgressiveLoadingTest : PlaywrightSpec() {

    init {
        test("page initially shows loading spinners then replaces them") {
            val fragmentResponseCount = AtomicInteger(0)

            page.onResponse { response ->
                if (response.url().contains("/fragments/")) {
                    fragmentResponseCount.incrementAndGet()
                }
            }

            page.navigate("/")
            page.waitForTimeout(3000.0)

            fragmentResponseCount.get() shouldBeGreaterThanOrEqual 2
        }

        test("all fragment endpoints return 200") {
            val fragments = listOf(
                "/fragments/stats",
                "/fragments/cards-row1",
                "/fragments/cards-row2",
                "/fragments/forms",
                "/fragments/form-sections",
                "/fragments/team",
            )

            for (path in fragments) {
                val response = page.request().get("$BASE_URL$path")
                response.status() shouldBe 200
            }
        }
    }
}
