package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupPhoneCoverageTest {

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
    fun mockupPhone_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone(
                content = { },
            )
        }
        assertRendered(html, "mockup-phone", "MockupPhone defaults", closes = "</div></div>")
    }

    @Test
    fun mockupPhone_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "mockup-phone zz-extra", "MockupPhone all flags", closes = "</div></div>")
        assertCommonFlags(html, "MockupPhone")
    }

    @Test
    fun mockupPhoneCamera_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCamera(
                content = { },
            )
        }
        assertRendered(html, "mockup-phone-camera", "MockupPhoneCamera defaults", closes = "</div></div>")
    }

    @Test
    fun mockupPhoneCamera_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCamera(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "mockup-phone-camera zz-extra", "MockupPhoneCamera all flags", closes = "</div></div>")
        assertCommonFlags(html, "MockupPhoneCamera")
    }

    @Test
    fun mockupPhoneDisplay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplay(
                content = { },
            )
        }
        assertRendered(html, "mockup-phone-display", "MockupPhoneDisplay defaults", closes = "</div></div>")
    }

    @Test
    fun mockupPhoneDisplay_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplay(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "mockup-phone-display zz-extra", "MockupPhoneDisplay all flags", closes = "</div></div>")
        assertCommonFlags(html, "MockupPhoneDisplay")
    }
}
