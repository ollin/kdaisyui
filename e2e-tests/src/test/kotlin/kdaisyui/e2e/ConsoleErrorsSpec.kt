package kdaisyui.e2e

import com.microsoft.playwright.Route
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

/**
 * The proof that the console check can go red. No route of the example app breaks the policy on
 * purpose, so this test serves itself a page with an inline style under the header.
 */
class ConsoleErrorsSpec : PlaywrightSpec() {
    init {
        test("the failure message names every message") {
            ConsoleErrors.failureMessage(listOf("first", "second")) shouldBe
                "The page logged 2 error-level message(s):\n- first\n- second"
        }

        test("a refused inline style is held, named, and drained") {
            page.route("**/csp-probe") { route -> route.fulfill(pageWithAnInlineStyle()) }
            page.navigate("/csp-probe")

            val held = consoleErrors.drain()

            held shouldHaveSize 1
            ConsoleErrors.failureMessage(held) shouldContain "Applying inline style violates"
            consoleErrors.drain() shouldHaveSize 0
        }
    }

    private fun pageWithAnInlineStyle(): Route.FulfillOptions = Route.FulfillOptions()
        .setContentType("text/html")
        .setHeaders(mapOf("Content-Security-Policy" to "default-src 'self'; img-src 'self' data:"))
        .setBody("""<!doctype html><title>probe</title><p style="color:red">inline</p>""")
}
