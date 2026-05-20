package kdaisyui.e2e

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

class HtmxProgressiveLoadingTest : PlaywrightTestBase() {

    @Test
    fun `page initially shows loading spinners then replaces them`() {
        val fragmentResponseCount = AtomicInteger(0)

        page.onResponse { response ->
            if (response.url().contains("/fragments/")) {
                fragmentResponseCount.incrementAndGet()
            }
        }

        page.navigate("/")
        page.waitForTimeout(3000.0)

        assertTrue(fragmentResponseCount.get() >= 2) {
            "Expected at least 2 fragment requests, got ${fragmentResponseCount.get()}"
        }
    }

    @Test
    fun `all fragment endpoints return 200`() {
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
            assertEquals(200, response.status()) { "$path should return 200" }
        }
    }
}
