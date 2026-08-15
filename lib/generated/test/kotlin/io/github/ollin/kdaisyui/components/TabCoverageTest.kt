package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class TabCoverageTest {

    @Test
    fun tab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tabs", actualClasses, "Tab defaults")
    }

    @Test
    fun tab_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                id = htmlId("x-cov-id"),
                border = true,
                bottom = true,
                box = true,
                lift = true,
                tabActive = true,
                tabDisabled = true,
                top = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tabs tabs-border tabs-bottom tabs-box tabs-lift tabs-tab-active tabs-tab-disabled tabs-top zz-extra", actualClasses, "Tab all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Tab id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Tab attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Tab content")
    }

    @Test
    fun tab_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tabs tabs-xs", actualClasses, "Tab size Xs")
    }

    @Test
    fun tab_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tabs tabs-sm", actualClasses, "Tab size Sm")
    }

    @Test
    fun tab_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tabs tabs-md", actualClasses, "Tab size Md")
    }

    @Test
    fun tab_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tabs tabs-lg", actualClasses, "Tab size Lg")
    }

    @Test
    fun tab_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyTab(
                size = TabSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tabs tabs-xl", actualClasses, "Tab size Xl")
    }

    @Test
    fun tabTab_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabTab(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tab", actualClasses, "TabTab defaults")
    }

    @Test
    fun tabTab_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabTab(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tab zz-extra", actualClasses, "TabTab all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "TabTab id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TabTab attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TabTab content")
    }

    @Test
    fun tabTabContent_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabTabContent(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tab-content", actualClasses, "TabTabContent defaults")
    }

    @Test
    fun tabTabContent_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyTabTabContent(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("tab-content zz-extra", actualClasses, "TabTabContent all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "TabTabContent id")
        assertTrue(html.contains("data-attrs=\"yes\""), "TabTabContent attrs")
        assertTrue(html.contains("data-content=\"yes\""), "TabTabContent content")
    }
}
