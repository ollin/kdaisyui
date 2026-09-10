package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlinx.html.ul

class BreadcrumbsCoverageTest {

    private fun assertRendered(html: String, classes: String, label: String, closes: String = "") {
        assertEquals(
            classes,
            html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "),
            label,
        )
        if (closes.isNotEmpty()) assertTrue(html.endsWith(closes), "$label closes")
    }

    @Test
    fun breadcrumbs_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbs(
                content = { },
            )
        }
        assertRendered(html, "breadcrumbs", "Breadcrumbs defaults", closes = "</div></div>")
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
        assertRendered(html, "breadcrumbs zz-extra", "Breadcrumbs all flags", closes = "</div></div>")
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
        assertTrue(html.endsWith("</ul></div>"), "BreadcrumbsItems closes")
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
        assertRendered(html, "zz-extra", "BreadcrumbsItems all flags", closes = "</ul></div>")
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
        assertTrue(html.endsWith("</li></ul>"), "BreadcrumbsItem closes")
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
        assertRendered(html, "zz-extra", "BreadcrumbsItem all flags", closes = "</li></ul>")
        assertTrue(html.contains("id=\"x-cov-id\""), "BreadcrumbsItem id")
        assertTrue(html.contains("data-attrs=\"yes\""), "BreadcrumbsItem attrs")
        assertTrue(html.contains("data-content=\"yes\""), "BreadcrumbsItem content")
    }
}
