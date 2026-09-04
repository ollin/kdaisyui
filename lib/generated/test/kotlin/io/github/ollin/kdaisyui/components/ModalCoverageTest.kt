package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class ModalCoverageTest {

    @Test
    fun modal_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal", actualClasses, "Modal defaults")
    }

    @Test
    fun modal_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyModal(
                id = htmlId("x-cov-id"),
                bottom = true,
                end = true,
                middle = true,
                open = true,
                start = true,
                top = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal modal-bottom modal-end modal-middle modal-open modal-start modal-top zz-extra", actualClasses, "Modal all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Modal id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Modal attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Modal content")
    }

    @Test
    fun modalBox_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBox(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal-box", actualClasses, "ModalBox defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal-box zz-extra", actualClasses, "ModalBox all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "ModalBox id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ModalBox attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ModalBox content")
    }

    @Test
    fun modalAction_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalAction(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal-action", actualClasses, "ModalAction defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal-action zz-extra", actualClasses, "ModalAction all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "ModalAction id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ModalAction attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ModalAction content")
    }

    @Test
    fun modalBackdrop_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalBackdrop(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal-backdrop", actualClasses, "ModalBackdrop defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal-backdrop zz-extra", actualClasses, "ModalBackdrop all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "ModalBackdrop id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ModalBackdrop attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ModalBackdrop content")
    }

    @Test
    fun modalToggle_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggle(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal-toggle", actualClasses, "ModalToggle defaults")
    }

    @Test
    fun modalToggle_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalToggle(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal-toggle zz-extra", actualClasses, "ModalToggle all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "ModalToggle id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ModalToggle attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ModalToggle content")
    }

    @Test
    fun modalPopover_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyModalPopover(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal", actualClasses, "ModalPopover defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("modal zz-extra", actualClasses, "ModalPopover all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "ModalPopover id")
        assertTrue(html.contains("data-attrs=\"yes\""), "ModalPopover attrs")
        assertTrue(html.contains("data-content=\"yes\""), "ModalPopover content")
    }
}
