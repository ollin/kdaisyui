package io.github.ollin.kdaisyui.core

import kotlinx.html.button
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class PopoverTest {

    @Test
    fun aTriggerTargetsThePopoverAndNamesNoAnchor() {
        val html = createHTML(prettyPrint = false).button { popoverTarget(htmlId("panel")) }
        assertEquals("""<button popovertarget="panel"></button>""", html)
    }

    @Test
    @Suppress("DEPRECATION")
    fun aDeprecatedAnchoredTriggerRendersTheSame() {
        val html = createHTML(prettyPrint = false).button { popoverTarget(htmlId("menu"), PopoverTargetAction.Show, anchored = true) }
        assertEquals("""<button popovertarget="menu" popovertargetaction="show"></button>""", html)
    }

    @Test
    fun aStyleTheTriggerAlreadyHasIsLeftAsItIs() {
        val html = createHTML(prettyPrint = false).button { attributes["style"] = "color:red"; popoverTarget(htmlId("menu")) }
        assertEquals("""<button style="color:red" popovertarget="menu"></button>""", html)
    }

    @Test
    fun aCloseButtonNamesItsAction() {
        val html = createHTML(prettyPrint = false).button { popoverTarget(htmlId("modal"), PopoverTargetAction.Hide) }
        assertEquals("""<button popovertarget="modal" popovertargetaction="hide"></button>""", html)
    }
}
