package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ModalCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    /** Renders one call per entry of an axis and asserts the classes each must write. */
    private fun <E> assertEachEntry(label: String, classesOf: Map<E, String>, render: FlowContent.(E) -> Unit) {
        for ((entry, classes) in classesOf) {
            assertRendered(createHTML(prettyPrint = false).div { render(entry) }, Expected("$label $entry", classes))
        }
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun modal_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                content = { },
            )
        }
        assertRendered(html, Expected("Modal defaults", "modal", closes = "</dialog></div>"))
    }

    @Test
    fun modal_all_flags() {
        val html = allFlags(Expected("Modal all flags", "modal modal-open zz-extra", closes = "</dialog></div>")) { daisyModal(id = ID, open = true, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun modal_verticalPlacement() = assertEachEntry("Modal verticalPlacement", mapOf(ModalVerticalPlacement.Top to "modal modal-top", ModalVerticalPlacement.Middle to "modal modal-middle", ModalVerticalPlacement.Bottom to "modal modal-bottom")) { daisyModal(verticalPlacement = it, content = { }) }

    @Test
    fun modal_horizontalPlacement() = assertEachEntry("Modal horizontalPlacement", mapOf(ModalHorizontalPlacement.Start to "modal modal-start", ModalHorizontalPlacement.End to "modal modal-end")) { daisyModal(horizontalPlacement = it, content = { }) }

    @Test
    fun modalClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalClasses()
        }
        assertRendered(html, Expected("ModalClasses defaults", "modal"))
    }

    @Test
    fun modalClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalClasses(
                open = true,
            )
        }
        val expected = Expected("ModalClasses all flags", "modal modal-open", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun modalClasses_verticalPlacement() = assertEachEntry("ModalClasses verticalPlacement", mapOf(ModalVerticalPlacement.Top to "modal modal-top", ModalVerticalPlacement.Middle to "modal modal-middle", ModalVerticalPlacement.Bottom to "modal modal-bottom")) { daisyModalClasses(verticalPlacement = it) }

    @Test
    fun modalClasses_horizontalPlacement() = assertEachEntry("ModalClasses horizontalPlacement", mapOf(ModalHorizontalPlacement.Start to "modal modal-start", ModalHorizontalPlacement.End to "modal modal-end")) { daisyModalClasses(horizontalPlacement = it) }

    @Test
    fun modalBox_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBox(
                content = { },
            )
        }
        assertRendered(html, Expected("ModalBox defaults", "modal-box", closes = "</div></div>"))
    }

    @Test
    fun modalBox_all_flags() {
        val html = allFlags(Expected("ModalBox all flags", "modal-box zz-extra", closes = "</div></div>")) { daisyModalBox(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun modalBoxClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBoxClasses()
        }
        assertRendered(html, Expected("ModalBoxClasses defaults", "modal-box"))
    }

    @Test
    fun modalBoxClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBoxClasses()
        }
        val expected = Expected("ModalBoxClasses all flags", "modal-box", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun modalAction_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalAction(
                content = { },
            )
        }
        assertRendered(html, Expected("ModalAction defaults", "modal-action", closes = "</div></div>"))
    }

    @Test
    fun modalAction_all_flags() {
        val html = allFlags(Expected("ModalAction all flags", "modal-action zz-extra", closes = "</div></div>")) { daisyModalAction(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun modalActionClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalActionClasses()
        }
        assertRendered(html, Expected("ModalActionClasses defaults", "modal-action"))
    }

    @Test
    fun modalActionClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalActionClasses()
        }
        val expected = Expected("ModalActionClasses all flags", "modal-action", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun modalBackdrop_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBackdrop(
                content = { },
            )
        }
        assertRendered(html, Expected("ModalBackdrop defaults", "modal-backdrop", closes = "</div></div>"))
    }

    @Test
    fun modalBackdrop_all_flags() {
        val html = allFlags(Expected("ModalBackdrop all flags", "modal-backdrop zz-extra", closes = "</div></div>")) { daisyModalBackdrop(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun modalBackdropClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBackdropClasses()
        }
        assertRendered(html, Expected("ModalBackdropClasses defaults", "modal-backdrop"))
    }

    @Test
    fun modalBackdropClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBackdropClasses()
        }
        val expected = Expected("ModalBackdropClasses all flags", "modal-backdrop", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun modalToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggle()
        }
        assertRendered(html, Expected("ModalToggle defaults", "modal-toggle"))
    }

    @Test
    fun modalToggle_all_flags() {
        val html = allFlags(Expected("ModalToggle all flags", "modal-toggle zz-extra", content = false)) { daisyModalToggle(id = ID, extraClasses = EXTRA, attrs = ATTRS) }
    }

    @Test
    fun modalToggleClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggleClasses()
        }
        assertRendered(html, Expected("ModalToggleClasses defaults", "modal-toggle"))
    }

    @Test
    fun modalToggleClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggleClasses()
        }
        val expected = Expected("ModalToggleClasses all flags", "modal-toggle", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun modalPopover_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalPopover(
                content = { },
            )
        }
        assertRendered(html, Expected("ModalPopover defaults", "modal", closes = "</div></div>"))
    }

    @Test
    fun modalPopover_all_flags() {
        val html = allFlags(Expected("ModalPopover all flags", "modal zz-extra", closes = "</div></div>")) { daisyModalPopover(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun modalPopoverClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalPopoverClasses()
        }
        assertRendered(html, Expected("ModalPopoverClasses defaults", "modal"))
    }

    @Test
    fun modalPopoverClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalPopoverClasses()
        }
        val expected = Expected("ModalPopoverClasses all flags", "modal", content = false)
        assertRendered(html, expected)
    }
}
