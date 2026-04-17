package com.github.ollin.kdaisyui.components

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
    fun avatar_in_custom_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        val expectedClasses = "avatar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar in custom sizes")
    }

    @Test
    fun avatar_rounded() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        val expectedClasses = "avatar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar rounded")
    }

    @Test
    fun avatar_with_mask() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        val expectedClasses = "avatar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar with mask")
    }

    @Test
    fun avatar_group() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        val expectedClasses = "avatar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar group")
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
    fun avatar_with_ring() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar() {
            }
        }
        val expectedClasses = "avatar"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar with ring")
    }

    @Test
    fun avatar_with_presence_indicator() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(online = true, offline = true) {
            }
        }
        val expectedClasses = "avatar avatar-offline avatar-online"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar with presence indicator")
    }

    @Test
    fun avatar_placeholder() {
        val html = createHTML(prettyPrint = false).div {
            daisyAvatar(placeholder = true, online = true) {
            }
        }
        val expectedClasses = "avatar avatar-online avatar-placeholder"
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for Avatar placeholder")
    }
}
