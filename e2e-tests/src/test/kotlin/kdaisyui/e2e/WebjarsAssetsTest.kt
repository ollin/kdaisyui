package kdaisyui.e2e

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class WebjarsAssetsTest : PlaywrightSpec() {

    init {
        test("DaisyUI CSS is served from webjars") {
            val response = page.request().get("$BASE_URL/webjars/daisyui/daisyui.css")
            response.status() shouldBe 200
            response.headers()["content-type"]!! shouldContain "text/css"
        }

        test("htmx JS is served from webjars") {
            val response = page.request().get("$BASE_URL/webjars/htmx.org/dist/htmx.min.js")
            response.status() shouldBe 200
            response.headers()["content-type"]!! shouldContain "javascript"
        }

        test("Tailwind browser JS is served from webjars") {
            val response = page.request().get("$BASE_URL/webjars/tailwindcss__browser/dist/index.global.js")
            response.status() shouldBe 200
            response.headers()["content-type"]!! shouldContain "javascript"
        }
    }
}
