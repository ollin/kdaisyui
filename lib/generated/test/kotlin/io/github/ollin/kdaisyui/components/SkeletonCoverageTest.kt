package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.FlowContent
import kotlinx.html.Tag
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class SkeletonCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private val ID = htmlId("x-cov-id")
    private val EXTRA = "zz-extra"
    private val ATTRS: Tag.() -> Unit = { attributes["data-attrs"] = "yes" }
    private val CONTENT: Tag.() -> Unit = { attributes["data-content"] = "yes" }

    /** Renders a call that passes every escape hatch, and asserts its classes and the hatches. */
    private fun allFlags(expected: Expected, render: FlowContent.() -> Unit): String {
        val html = createHTML(prettyPrint = false).div { render() }
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
        return html
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun skeleton_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeleton(
                content = { },
            )
        }
        assertRendered(html, Expected("Skeleton defaults", "skeleton", closes = "</div></div>"))
    }

    @Test
    fun skeleton_all_flags() {
        val html = allFlags(Expected("Skeleton all flags", "skeleton zz-extra", closes = "</div></div>")) { daisySkeleton(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun skeletonClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeletonClasses()
        }
        assertRendered(html, Expected("SkeletonClasses defaults", "skeleton"))
    }

    @Test
    fun skeletonClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeletonClasses()
        }
        val expected = Expected("SkeletonClasses all flags", "skeleton", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun skeletonText_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeletonText(
                content = { },
            )
        }
        assertRendered(html, Expected("SkeletonText defaults", "skeleton skeleton-text", closes = "</span></div>"))
    }

    @Test
    fun skeletonText_all_flags() {
        val html = allFlags(Expected("SkeletonText all flags", "skeleton skeleton-text zz-extra", closes = "</span></div>")) { daisySkeletonText(id = ID, extraClasses = EXTRA, attrs = ATTRS, content = CONTENT) }
    }

    @Test
    fun skeletonTextClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeletonTextClasses()
        }
        assertRendered(html, Expected("SkeletonTextClasses defaults", "skeleton skeleton-text"))
    }

    @Test
    fun skeletonTextClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisySkeletonTextClasses()
        }
        val expected = Expected("SkeletonTextClasses all flags", "skeleton skeleton-text", content = false)
        assertRendered(html, expected)
    }
}
