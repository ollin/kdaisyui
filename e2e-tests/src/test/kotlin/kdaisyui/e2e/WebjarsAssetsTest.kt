package kdaisyui.e2e

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WebjarsAssetsTest : PlaywrightTestBase() {

    @Test
    fun `DaisyUI CSS is served from webjars`() {
        val response = page.request().get("$BASE_URL/webjars/daisyui/daisyui.css")
        assertEquals(200, response.status())
        assertTrue(response.headers()["content-type"]!!.contains("text/css"))
    }

    @Test
    fun `htmx JS is served from webjars`() {
        val response = page.request().get("$BASE_URL/webjars/htmx.org/dist/htmx.min.js")
        assertEquals(200, response.status())
        assertTrue(response.headers()["content-type"]!!.contains("javascript"))
    }

    @Test
    fun `Tailwind browser JS is served from webjars`() {
        val response = page.request().get("$BASE_URL/webjars/tailwindcss__browser/dist/index.global.js")
        assertEquals(200, response.status())
        assertTrue(response.headers()["content-type"]!!.contains("javascript"))
    }
}
