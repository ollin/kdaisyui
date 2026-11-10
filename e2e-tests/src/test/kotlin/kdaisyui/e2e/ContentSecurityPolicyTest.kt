package kdaisyui.e2e

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain

/** The example application runs under the policy kdaisyui's markup is guaranteed to work under. */
class ContentSecurityPolicyTest : PlaywrightSpec() {

    init {
        test("The header is on every response, static ones included") {
            for (path in listOf("/", "/static/app.css")) {
                val response = page.request().get("$BASE_URL$path")
                response.headers()["content-security-policy"] shouldBe POLICY
            }
        }

        test("The pages link an icon of their own origin") {
            page.request().get("$BASE_URL/static/favicon.svg").status() shouldBe 200
            val dashboard = page.request().get("$BASE_URL/")
            dashboard.status() shouldBe 200
            dashboard.text() shouldContain """rel="icon" type="image/svg+xml" href="/static/favicon.svg""""
        }

        for (path in PAGES_WITH_IMAGES) {
            test("A page loads no image from another host: $path") {
                val response = page.request().get("$BASE_URL$path")
                response.status() shouldBe 200
                response.text() shouldNotContain """src="http"""
            }
        }
    }

    private companion object {
        const val POLICY = "default-src 'self'; img-src 'self' data:"
        val PAGES_WITH_IMAGES = listOf(
            "/",
            "/fragments/team",
            "/fragments/forms",
            "/fragments/form-sections",
            "/fragments/cards-row2",
        )
    }
}
