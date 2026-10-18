package io.github.ollin.kdaisyui.core

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomElementsTest {

    @Test
    fun calendarDateRendersTheCallyWebComponentWithItsContent() {
        val html = createHTML(prettyPrint = false).div {
            calendarDate { attributes["data-month"] = "1" }
        }

        assertEquals("<div><calendar-date data-month=\"1\"></calendar-date></div>", html)
    }
}
