package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SkeletonCoverageTest {

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
    fun skeleton_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeleton(
                content = { },
            )
        }
        assertRendered(html, "skeleton", "Skeleton defaults", closes = "</div></div>")
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
        assertRendered(html, "skeleton skeleton-text zz-extra", "Skeleton all flags", closes = "</div></div>")
        assertCommonFlags(html, "Skeleton")
    }
}
