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
    fun otp_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, "otp otp-neutral", "Otp color Neutral")
    }

    @Test
    fun otp_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Primary,
                content = { },
            )
        }
        assertRendered(html, "otp otp-primary", "Otp color Primary")
    }

    @Test
    fun otp_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, "otp otp-secondary", "Otp color Secondary")
    }

    @Test
    fun otp_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Accent,
                content = { },
            )
        }
        assertRendered(html, "otp otp-accent", "Otp color Accent")
    }

    @Test
    fun otp_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Success,
                content = { },
            )
        }
        assertRendered(html, "otp otp-success", "Otp color Success")
    }

    @Test
    fun otp_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Info,
                content = { },
            )
        }
        assertRendered(html, "otp otp-info", "Otp color Info")
    }

    @Test
    fun otp_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Warning,
                content = { },
            )
        }
        assertRendered(html, "otp otp-warning", "Otp color Warning")
    }

    @Test
    fun otp_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Error,
                content = { },
            )
        }
        assertRendered(html, "otp otp-error", "Otp color Error")
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

    @Test
    fun otpClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses()
        }
        assertRendered(html, "otp", "OtpClasses defaults")
    }

    @Test
    fun otpClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                joined = true,
            )
        }
        assertRendered(html, "otp otp-joined", "OtpClasses all flags")
    }

    @Test
    fun otpClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Neutral,
            )
        }
        assertRendered(html, "otp otp-neutral", "OtpClasses color Neutral")
    }

    @Test
    fun otpClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Primary,
            )
        }
        assertRendered(html, "otp otp-primary", "OtpClasses color Primary")
    }

    @Test
    fun otpClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Secondary,
            )
        }
        assertRendered(html, "otp otp-secondary", "OtpClasses color Secondary")
    }

    @Test
    fun otpClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Accent,
            )
        }
        assertRendered(html, "otp otp-accent", "OtpClasses color Accent")
    }

    @Test
    fun otpClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Success,
            )
        }
        assertRendered(html, "otp otp-success", "OtpClasses color Success")
    }

    @Test
    fun otpClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Info,
            )
        }
        assertRendered(html, "otp otp-info", "OtpClasses color Info")
    }

    @Test
    fun otpClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Warning,
            )
        }
        assertRendered(html, "otp otp-warning", "OtpClasses color Warning")
    }

    @Test
    fun otpClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Error,
            )
        }
        assertRendered(html, "otp otp-error", "OtpClasses color Error")
    }

    @Test
    fun otpClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Xs,
            )
        }
        assertRendered(html, "otp otp-xs", "OtpClasses size Xs")
    }

    @Test
    fun otpClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Sm,
            )
        }
        assertRendered(html, "otp otp-sm", "OtpClasses size Sm")
    }

    @Test
    fun otpClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Md,
            )
        }
        assertRendered(html, "otp otp-md", "OtpClasses size Md")
    }

    @Test
    fun otpClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Lg,
            )
        }
        assertRendered(html, "otp otp-lg", "OtpClasses size Lg")
    }

    @Test
    fun otpClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Xl,
            )
        }
        assertRendered(html, "otp otp-xl", "OtpClasses size Xl")
    }
}
