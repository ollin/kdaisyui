package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class OtpCoverageTest {

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
    fun otp_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                content = { },
            )
        }
        assertRendered(html, "otp", "Otp defaults", closes = "</label></div>")
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
        assertRendered(html, "otp otp-joined zz-extra", "Otp all flags", closes = "</label></div>")
        assertCommonFlags(html, "Otp")
    }

    @Test
    fun otp_variant_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Neutral,
                content = { },
            )
        }
        assertRendered(html, "otp otp-neutral", "Otp variant Neutral")
    }

    @Test
    fun otp_variant_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Primary,
                content = { },
            )
        }
        assertRendered(html, "otp otp-primary", "Otp variant Primary")
    }

    @Test
    fun otp_variant_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Secondary,
                content = { },
            )
        }
        assertRendered(html, "otp otp-secondary", "Otp variant Secondary")
    }

    @Test
    fun otp_variant_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Accent,
                content = { },
            )
        }
        assertRendered(html, "otp otp-accent", "Otp variant Accent")
    }

    @Test
    fun otp_variant_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Success,
                content = { },
            )
        }
        assertRendered(html, "otp otp-success", "Otp variant Success")
    }

    @Test
    fun otp_variant_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Info,
                content = { },
            )
        }
        assertRendered(html, "otp otp-info", "Otp variant Info")
    }

    @Test
    fun otp_variant_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Warning,
                content = { },
            )
        }
        assertRendered(html, "otp otp-warning", "Otp variant Warning")
    }

    @Test
    fun otp_variant_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                variant = OtpVariant.Error,
                content = { },
            )
        }
        assertRendered(html, "otp otp-error", "Otp variant Error")
    }

    @Test
    fun otp_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Xs,
                content = { },
            )
        }
        assertRendered(html, "otp otp-xs", "Otp size Xs")
    }

    @Test
    fun otp_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Sm,
                content = { },
            )
        }
        assertRendered(html, "otp otp-sm", "Otp size Sm")
    }

    @Test
    fun otp_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Md,
                content = { },
            )
        }
        assertRendered(html, "otp otp-md", "Otp size Md")
    }

    @Test
    fun otp_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Lg,
                content = { },
            )
        }
        assertRendered(html, "otp otp-lg", "Otp size Lg")
    }

    @Test
    fun otp_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Xl,
                content = { },
            )
        }
        assertRendered(html, "otp otp-xl", "Otp size Xl")
    }
}
