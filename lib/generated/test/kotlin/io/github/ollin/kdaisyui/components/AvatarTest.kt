package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class AvatarTest {

    @Test
    fun avatar() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        val expectedClasses = "avatar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar")
    }

    @Test
    fun avatar_with_mask_daisyMask() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Heart) {
            }
        }
        val expectedClasses = "mask mask-heart"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar with mask")
    }

    @Test
    fun avatar_with_mask_daisyMask_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Squircle) {
            }
        }
        val expectedClasses = "mask mask-squircle"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar with mask")
    }

    @Test
    fun avatar_with_mask_daisyMask_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyMask(style = MaskStyle.Hexagon2) {
            }
        }
        val expectedClasses = "mask mask-hexagon-2"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar with mask")
    }

    @Test
    fun avatar_group_with_counter() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(placeholder = true) {
            }
        }
        val expectedClasses = "avatar avatar-placeholder"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar group with counter")
    }

    @Test
    fun avatar_with_presence_indicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(presence = AvatarPresence.Online) {
            }
        }
        val expectedClasses = "avatar avatar-online"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar with presence indicator")
    }

    @Test
    fun avatar_with_presence_indicator_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(presence = AvatarPresence.Offline) {
            }
        }
        val expectedClasses = "avatar avatar-offline"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar with presence indicator")
    }

    @Test
    fun avatar_placeholder() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(presence = AvatarPresence.Online, placeholder = true) {
            }
        }
        val expectedClasses = "avatar avatar-online avatar-placeholder"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar placeholder")
    }
}
