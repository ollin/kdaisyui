package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class MockupPhoneCoverageTest {

    @Test
    fun mockupPhone_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhone(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-phone", actualClasses, "MockupPhone defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-phone zz-extra", actualClasses, "MockupPhone all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupPhone id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupPhone attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupPhone content")
    }

    @Test
    fun mockupPhoneCamera_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneCamera(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-phone-camera", actualClasses, "MockupPhoneCamera defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-phone-camera zz-extra", actualClasses, "MockupPhoneCamera all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupPhoneCamera id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupPhoneCamera attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupPhoneCamera content")
    }

    @Test
    fun mockupPhoneDisplay_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyMockupPhoneDisplay(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-phone-display", actualClasses, "MockupPhoneDisplay defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("mockup-phone-display zz-extra", actualClasses, "MockupPhoneDisplay all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "MockupPhoneDisplay id")
        assertTrue(html.contains("data-attrs=\"yes\""), "MockupPhoneDisplay attrs")
        assertTrue(html.contains("data-content=\"yes\""), "MockupPhoneDisplay content")
    }
}
