package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.ul

class BreadcrumbsTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun breadcrumbs() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbs() {
            }
        }
        assertDocumented(html, "div", "breadcrumbs", "Breadcrumbs")
    }

    @Test
    fun custom_part_items_renders_ul() {
        val html = createHTML(prettyPrint = false).div {
            daisyBreadcrumbsItems {
            }
        }
        assertTrue(html.contains("<ul"))
    }

    @Test
    fun custom_part_item_renders_li() {
        val html = createHTML(prettyPrint = false).ul {
            daisyBreadcrumbsItem {
            }
        }
        assertTrue(html.contains("<li"))
    }
}
