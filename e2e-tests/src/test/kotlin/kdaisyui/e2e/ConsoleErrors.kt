package kdaisyui.e2e

import com.microsoft.playwright.Page

/**
 * What a page logged at error level: a refused inline style, a refused image, a 404, a script
 * error. The example app runs under `default-src 'self'; img-src 'self' data:`, and kdaisyui's
 * claim is that its markup needs nothing that policy refuses, so any such message is a defect
 * (guarantee-strict-csp).
 *
 * Each text carries the message's location, because Chromium's 404 message names no URL.
 */
class ConsoleErrors(page: Page) {

    private val held = mutableListOf<String>()

    init {
        page.onConsoleMessage { message ->
            if (message.type() == "error") held += "${message.text()} (at ${message.location()})"
        }
        page.onPageError { error -> held += "page error: $error" }
    }

    /** What the page logged since the last call, and nothing after it. */
    fun drain(): List<String> = held.toList().also { held.clear() }

    companion object {
        fun failureMessage(texts: List<String>): String =
            "The page logged ${texts.size} error-level message(s):\n" + texts.joinToString("\n") { "- $it" }
    }
}
