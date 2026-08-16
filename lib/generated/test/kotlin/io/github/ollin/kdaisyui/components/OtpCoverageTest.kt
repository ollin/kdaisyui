package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class OtpCoverageTest {

    @Test
    fun otp_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp", actualClasses, "Otp defaults")
    }

    @Test
    fun otp_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                id = htmlId("x-cov-id"),
                joined = true,
                extraClasses = "zz-extra",
                attrs = { attributes["data-attrs"] = "yes" },
                content = { attributes["data-content"] = "yes" },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-joined zz-extra", actualClasses, "Otp all flags")
        assertTrue(html.contains("id=\"x-cov-id\""), "Otp id")
        assertTrue(html.contains("data-attrs=\"yes\""), "Otp attrs")
        assertTrue(html.contains("data-content=\"yes\""), "Otp content")
    }

    @Test
    fun otp_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Neutral,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-neutral", actualClasses, "Otp variant Neutral")
    }

    @Test
    fun otp_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Primary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-primary", actualClasses, "Otp variant Primary")
    }

    @Test
    fun otp_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Secondary,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-secondary", actualClasses, "Otp variant Secondary")
    }

    @Test
    fun otp_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Accent,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-accent", actualClasses, "Otp variant Accent")
    }

    @Test
    fun otp_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Success,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-success", actualClasses, "Otp variant Success")
    }

    @Test
    fun otp_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Info,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-info", actualClasses, "Otp variant Info")
    }

    @Test
    fun otp_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Warning,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-warning", actualClasses, "Otp variant Warning")
    }

    @Test
    fun otp_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Error,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-error", actualClasses, "Otp variant Error")
    }

    @Test
    fun otp_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Xs,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-xs", actualClasses, "Otp size Xs")
    }

    @Test
    fun otp_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Sm,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-sm", actualClasses, "Otp size Sm")
    }

    @Test
    fun otp_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Md,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-md", actualClasses, "Otp size Md")
    }

    @Test
    fun otp_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Lg,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-lg", actualClasses, "Otp size Lg")
    }

    @Test
    fun otp_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Xl,
                content = { },
            )
        }
        val actualClasses = html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" ")
        assertEquals("otp otp-xl", actualClasses, "Otp size Xl")
    }
}
