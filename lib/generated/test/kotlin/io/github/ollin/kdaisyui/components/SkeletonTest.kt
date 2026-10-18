package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SkeletonTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun skeleton() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeleton() {
            }
        }
        assertDocumented(html, "div", "skeleton", "Skeleton")
    }

    @Test
    fun skeleton_text_animated_gradient_text_daisySkeletonText() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeletonText() {
            }
        }
        assertDocumented(html, "span", "skeleton skeleton-text", "skeleton-text - animated gradient text")
    }

    @Test
    fun custom_part_text_renders_span() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeletonText {
            }
        }
        assertTrue(html.contains("<span"))
        assertTrue(html.contains("class=\"skeleton"))
        assertTrue(html.contains("skeleton-text"))
    }
}
