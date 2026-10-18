package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SkeletonTest {

    @Test
    fun skeleton() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeleton() {
            }
        }
        val expectedClasses = "skeleton"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Skeleton")
        assertEquals("div", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for Skeleton")
    }

    @Test
    fun skeleton_text_animated_gradient_text_daisySkeletonText() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeletonText() {
            }
        }
        val expectedClasses = "skeleton skeleton-text"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for skeleton-text - animated gradient text")
        assertEquals("span", html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for skeleton-text - animated gradient text")
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
