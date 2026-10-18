package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ModalCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    private fun assertCommonFlags(html: String, label: String, content: Boolean = true) {
        assertTrue(html.contains("id=\"x-cov-id\""), "$label id")
        assertTrue(html.contains("data-attrs=\"yes\""), "$label attrs")
        if (content) assertTrue(html.contains("data-content=\"yes\""), "$label content")
    }

    @Test
    fun modal_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                content = { },
            )
        }
        assertRendered(html, "modal", "Modal defaults", closes = "</dialog></div>")
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
        assertRendered(html, "modal modal-open zz-extra", "Modal all flags", closes = "</dialog></div>")
        assertCommonFlags(html, "Modal")
    }

    @Test
    fun modal_verticalPlacement_top() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                verticalPlacement = ModalVerticalPlacement.Top,
                content = { },
            )
        }
        assertRendered(html, "modal modal-top", "Modal verticalPlacement Top")
    }

    @Test
    fun modal_verticalPlacement_middle() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                verticalPlacement = ModalVerticalPlacement.Middle,
                content = { },
            )
        }
        assertRendered(html, "modal modal-middle", "Modal verticalPlacement Middle")
    }

    @Test
    fun modal_verticalPlacement_bottom() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                verticalPlacement = ModalVerticalPlacement.Bottom,
                content = { },
            )
        }
        assertRendered(html, "modal modal-bottom", "Modal verticalPlacement Bottom")
    }

    @Test
    fun modal_horizontalPlacement_start() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                horizontalPlacement = ModalHorizontalPlacement.Start,
                content = { },
            )
        }
        assertRendered(html, "modal modal-start", "Modal horizontalPlacement Start")
    }

    @Test
    fun modal_horizontalPlacement_end() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                horizontalPlacement = ModalHorizontalPlacement.End,
                content = { },
            )
        }
        assertRendered(html, "modal modal-end", "Modal horizontalPlacement End")
    }

    @Test
    fun modalBox_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBox(
                content = { },
            )
        }
        assertRendered(html, "modal-box", "ModalBox defaults", closes = "</div></div>")
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
        assertRendered(html, "modal-box zz-extra", "ModalBox all flags", closes = "</div></div>")
        assertCommonFlags(html, "ModalBox")
    }

    @Test
    fun modalAction_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalAction(
                content = { },
            )
        }
        assertRendered(html, "modal-action", "ModalAction defaults", closes = "</div></div>")
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
        assertRendered(html, "modal-action zz-extra", "ModalAction all flags", closes = "</div></div>")
        assertCommonFlags(html, "ModalAction")
    }

    @Test
    fun modalBackdrop_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBackdrop(
                content = { },
            )
        }
        assertRendered(html, "modal-backdrop", "ModalBackdrop defaults", closes = "</div></div>")
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
        assertRendered(html, "modal-backdrop zz-extra", "ModalBackdrop all flags", closes = "</div></div>")
        assertCommonFlags(html, "ModalBackdrop")
    }

    @Test
    fun modalToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggle()
        }
        assertRendered(html, "modal-toggle", "ModalToggle defaults")
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
        assertRendered(html, "modal-toggle zz-extra", "ModalToggle all flags")
        assertCommonFlags(html, "ModalToggle", content = false)
    }

    @Test
    fun modalPopover_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalPopover(
                content = { },
            )
        }
        assertRendered(html, "modal", "ModalPopover defaults", closes = "</div></div>")
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
        assertRendered(html, "modal zz-extra", "ModalPopover all flags", closes = "</div></div>")
        assertCommonFlags(html, "ModalPopover")
    }
}
