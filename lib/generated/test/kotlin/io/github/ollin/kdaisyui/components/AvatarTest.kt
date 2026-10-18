package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AvatarTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun avatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        assertDocumented(html, "div", "avatar", "Avatar")
    }

    @Test
    fun avatar_with_mask_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyMaskClasses(style = MaskStyle.Heart) }
        }
        assertDocumented(html, "div", "mask mask-heart", "Avatar with mask")
    }

    @Test
    fun avatar_with_mask_daisyMask_2() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyMaskClasses(style = MaskStyle.Squircle) }
        }
        assertDocumented(html, "div", "mask mask-squircle", "Avatar with mask")
    }

    @Test
    fun avatar_with_mask_daisyMask_3() {
        val html = createHTML(prettyPrint = false).div {
            div { daisyMaskClasses(style = MaskStyle.Hexagon2) }
        }
        assertDocumented(html, "div", "mask mask-hexagon-2", "Avatar with mask")
    }

    @Test
    fun avatar_group_daisyAvatarGroup() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatarGroup() {
            }
        }
        assertDocumented(html, "div", "avatar-group", "Avatar group")
    }

    @Test
    fun avatar_group_with_counter() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(placeholder = true) {
            }
        }
        assertDocumented(html, "div", "avatar avatar-placeholder", "Avatar group with counter")
    }

    @Test
    fun avatar_with_presence_indicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(presence = AvatarPresence.Online) {
            }
        }
        assertDocumented(html, "div", "avatar avatar-online", "Avatar with presence indicator")
    }

    @Test
    fun avatar_with_presence_indicator_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(presence = AvatarPresence.Offline) {
            }
        }
        assertDocumented(html, "div", "avatar avatar-offline", "Avatar with presence indicator")
    }

    @Test
    fun avatar_placeholder() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(presence = AvatarPresence.Online, placeholder = true) {
            }
        }
        assertDocumented(html, "div", "avatar avatar-online avatar-placeholder", "Avatar placeholder")
    }
}
