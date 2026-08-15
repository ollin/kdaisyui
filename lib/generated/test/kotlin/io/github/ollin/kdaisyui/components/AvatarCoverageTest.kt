package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class AvatarCoverageTest {

    @Test
    fun avatar_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("avatar", actualClasses, "Avatar defaults")
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
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("avatar avatar-offline avatar-online avatar-placeholder zz-extra", actualClasses, "Avatar all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Avatar id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Avatar attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Avatar content")
    }
}
