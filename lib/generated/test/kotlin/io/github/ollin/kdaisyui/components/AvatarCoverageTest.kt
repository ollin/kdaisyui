package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AvatarCoverageTest {

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
    fun avatar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                content = { },
            )
        }
        assertRendered(html, "avatar", "Avatar defaults", closes = "</div></div>")
    }

    @Test
    fun avatar_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                id = htmlId("x-cov-id"),
                offline = true,
                online = true,
                placeholder = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        assertRendered(html, "avatar avatar-offline avatar-online avatar-placeholder zz-extra", "Avatar all flags", closes = "</div></div>")
        assertCommonFlags(html, "Avatar")
    }
}
