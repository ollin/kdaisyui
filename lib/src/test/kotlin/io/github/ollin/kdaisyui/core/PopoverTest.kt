package io.github.ollin.kdaisyui.core

import kotlinx.html.button
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class PopoverTest {

    @Test
    fun aTriggerTargetsThePopoverAndLeavesTheAnchorToItsCss() {
        val html = createHTML(prettyPrint = false).button { popoverTarget(htmlId("panel")) }
        assertEquals("""<button popovertarget="panel"></button>""", html)
    }

    @Test
    fun anAnchoredTriggerNamesItsAnchor() {
        val html = createHTML(prettyPrint = false).button { popoverTarget(htmlId("menu"), anchored = true) }
        assertEquals("""<button popovertarget="menu" style="anchor-name:--menu"></button>""", html)
    }

    @Test
    fun theAnchorNameJoinsAStyleTheTriggerAlreadyHas() {
        val html = createHTML(prettyPrint = false).button { attributes["style"] = "color:red"; popoverTarget(htmlId("menu"), anchored = true) }
        assertEquals("""<button style="color:red;anchor-name:--menu" popovertarget="menu"></button>""", html)
    }

    @Test
    fun aCloseButtonNamesItsAction() {
        val html = createHTML(prettyPrint = false).button { popoverTarget(htmlId("modal"), PopoverTargetAction.Hide) }
        assertEquals("""<button popovertarget="modal" popovertargetaction="hide"></button>""", html)
    }
}
