package kdaisyui.e2e

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain

class WebjarsAssetsTest : PlaywrightSpec() {

    init {
        // No DaisyUI-webjar test, for the reason given on the stylesheet test below: nothing
        // loads it, and it arrived only transitively from :ktor-integration until 0.7.0.
        test("htmx JS is served from webjars") {
            val response = page.request().get("$BASE_URL/webjars/htmx.org/dist/htmx.min.js")
            response.status() shouldBe 200
            response.headers()["content-type"]!! shouldContain "javascript"
        }

        // Replaced the Tailwind-browser-JS test. Nothing loads that script since the build
        // started compiling CSS, so asserting it was still downloadable tested a dependency
        // the application no longer has.
        test("the compiled stylesheet is served") {
            val response = page.request().get("$BASE_URL/static/app.css")
            response.status() shouldBe 200
            response.headers()["content-type"]!! shouldContain "text/css"
        }

        // Tailwind scans Kotlin sources as TEXT and cannot tell a comment from a call, so a
        // composed class written in a doc comment used to become a candidate and get a rule. Two
        // comments were doing it, and one of them kept lg:btn-lg alive in the stylesheet — which
        // made the four tests in TailwindVariantTest pass whether or not extraClasses worked.
        //
        // dark:alert-info is the canary: no code in the example app can put it on a page, so a
        // rule for it can only have come from prose. The first assertion is the calibration —
        // without it, the second would pass just as happily against an empty response body.
        test("the stylesheet carries no rule that only prose asked for") {
            val response = page.request().get("$BASE_URL/static/app.css")
            response.status() shouldBe 200
            val stylesheet = response.text()

            stylesheet shouldContain selectorFor("lg:btn-lg")
            stylesheet shouldNotContain selectorFor("dark:alert-info")
        }
    }

    /**
     * The selector Tailwind writes for a class, e.g. `dark:alert-info` -> `.dark\:alert-info`.
     * Written once here, because a hand-escaped selector is one transcription away from one that
     * matches nothing — which would make the absence check pass forever.
     */
    private fun selectorFor(cssClass: String): String = ".${cssClass.replace(":", "\\:")}"
}
