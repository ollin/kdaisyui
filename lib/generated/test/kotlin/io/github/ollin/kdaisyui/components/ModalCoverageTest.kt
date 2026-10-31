package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ModalCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
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
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                id = htmlId("x-cov-id"),
                open = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("Modal all flags", "modal modal-open zz-extra", closes = "</dialog></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun modal_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                verticalPlacement = ModalVerticalPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, Expected("Modal verticalPlacement Top", "modal modal-top"))
    }

    @Test
    fun modal_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                verticalPlacement = ModalVerticalPlacement.Middle,
                content = { },
            )
        }
        assertRendered(html, Expected("Modal verticalPlacement Middle", "modal modal-middle"))
    }

    @Test
    fun modal_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                verticalPlacement = ModalVerticalPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, Expected("Modal verticalPlacement Bottom", "modal modal-bottom"))
    }

    @Test
    fun modal_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                horizontalPlacement = ModalHorizontalPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, Expected("Modal horizontalPlacement Start", "modal modal-start"))
    }

    @Test
    fun modal_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                horizontalPlacement = ModalHorizontalPlacement.End,
                content = { },
            )
        }
        assertRendered(html, Expected("Modal horizontalPlacement End", "modal modal-end"))
    }

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
    fun modalClasses_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalClasses(
                verticalPlacement = ModalVerticalPlacement.Top,
            )
        }
        assertRendered(html, Expected("ModalClasses verticalPlacement Top", "modal modal-top"))
    }

    @Test
    fun modalClasses_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalClasses(
                verticalPlacement = ModalVerticalPlacement.Middle,
            )
        }
        assertRendered(html, Expected("ModalClasses verticalPlacement Middle", "modal modal-middle"))
    }

    @Test
    fun modalClasses_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalClasses(
                verticalPlacement = ModalVerticalPlacement.Bottom,
            )
        }
        assertRendered(html, Expected("ModalClasses verticalPlacement Bottom", "modal modal-bottom"))
    }

    @Test
    fun modalClasses_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalClasses(
                horizontalPlacement = ModalHorizontalPlacement.Start,
            )
        }
        assertRendered(html, Expected("ModalClasses horizontalPlacement Start", "modal modal-start"))
    }

    @Test
    fun modalClasses_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalClasses(
                horizontalPlacement = ModalHorizontalPlacement.End,
            )
        }
        assertRendered(html, Expected("ModalClasses horizontalPlacement End", "modal modal-end"))
    }

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
        val html = createHTML(prettyPrint = false).div {
            daisyModalBox(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ModalBox all flags", "modal-box zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
        val html = createHTML(prettyPrint = false).div {
            daisyModalAction(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ModalAction all flags", "modal-action zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
        val html = createHTML(prettyPrint = false).div {
            daisyModalBackdrop(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ModalBackdrop all flags", "modal-backdrop zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
            )
        }
        val expected = Expected("ModalToggle all flags", "modal-toggle zz-extra", content = false)
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
        val html = createHTML(prettyPrint = false).div {
            daisyModalPopover(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val expected = Expected("ModalPopover all flags", "modal zz-extra", closes = "</div></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
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
