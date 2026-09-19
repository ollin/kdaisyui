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
    }

    @Test
    fun skeleton_circle_with_content() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeleton() {
            }
        }
        val expectedClasses = "skeleton"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Skeleton - circle with content")
    }

    @Test
    fun skeleton_rectangle_with_content() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeleton() {
            }
        }
        val expectedClasses = "skeleton"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Skeleton - rectangle with content")
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
