package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlinx.html.ul

class BreadcrumbsCoverageTest {

    @Test
    fun breadcrumbs_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbs(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("breadcrumbs", actualClasses, "Breadcrumbs defaults")
    }

    @Test
    fun breadcrumbs_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbs(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("breadcrumbs zz-extra", actualClasses, "Breadcrumbs all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Breadcrumbs id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Breadcrumbs attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Breadcrumbs content")
    }

    @Test
    fun breadcrumbsItems_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbsItems(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "BreadcrumbsItems defaults emits no class")
    }

    @Test
    fun breadcrumbsItems_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbsItems(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("zz-extra", actualClasses, "BreadcrumbsItems all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "BreadcrumbsItems id")
        assertTrue(html.contains("data-attrs=\"yes\""), "BreadcrumbsItems attrs")
        assertTrue(html.contains("data-content=\"yes\""), "BreadcrumbsItems content")
    }

    @Test
    fun breadcrumbsItem_defaults() {
        val html = createHTML(prettyPrint = false).ul {
            daisyBreadcrumbsItem(
                content = { },
            )
        }
        assertTrue(!html.contains("class=\""), "BreadcrumbsItem defaults emits no class")
    }

    @Test
    fun breadcrumbsItem_all_flags() {
        val html = createHTML(prettyPrint = false).ul {
            daisyBreadcrumbsItem(
                id = htmlId("x-cov-id"),
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("zz-extra", actualClasses, "BreadcrumbsItem all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "BreadcrumbsItem id")
        assertTrue(html.contains("data-attrs=\"yes\""), "BreadcrumbsItem attrs")
        assertTrue(html.contains("data-content=\"yes\""), "BreadcrumbsItem content")
    }
}
