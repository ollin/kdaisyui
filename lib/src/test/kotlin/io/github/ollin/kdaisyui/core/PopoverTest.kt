package io.github.ollin.kdaisyui.core

import kotlinx.html.button
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class PopoverTest {

    @Test
    fun aTriggerTargetsThePopoverAndNamesItsAnchor() {
        val html = createHTML(prettyPrint = false).button { popoverTarget(htmlId("menu")) }
        assertEquals("""<button popovertarget="menu" style="anchor-name:--menu"></button>""", html)
    }
}
