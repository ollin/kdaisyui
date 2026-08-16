package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SkeletonCoverageTest {

    @Test
    fun skeleton_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeleton(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("skeleton", actualClasses, "Skeleton defaults")
    }

    @Test
    fun skeleton_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeleton(
                id = htmlId("x-cov-id"),
                text = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("skeleton skeleton-text zz-extra", actualClasses, "Skeleton all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Skeleton id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Skeleton attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Skeleton content")
    }
}
