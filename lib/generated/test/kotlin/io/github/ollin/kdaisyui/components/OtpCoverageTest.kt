package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.htmlId
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.html.div
import kotlinx.html.stream.createHTML

class OtpCoverageTest {

    /** What a case must render: its classes, sorted, the tail it closes with, whether it took content. */
    private class Expected(val label: String, val classes: String, val closes: String = "", val content: Boolean = true)

    private fun assertRendered(html: String, expected: Expected) {
        assertEquals(expected.classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), expected.label)
        if (expected.closes.isNotEmpty()) assertTrue(html.endsWith(expected.closes), "${expected.label} closes")
    }

    private fun assertCommonFlags(html: String, expected: Expected) {
        assertTrue(html.contains("id=\"x-cov-id\""), "${expected.label} id")
        assertTrue(html.contains("data-attrs=\"yes\""), "${expected.label} attrs")
        if (expected.content) assertTrue(html.contains("data-content=\"yes\""), "${expected.label} content")
    }

    @Test
    fun otp_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                content = { },
            )
        }
        assertRendered(html, Expected("Otp defaults", "otp", closes = "</label></div>"))
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
        val expected = Expected("Otp all flags", "otp otp-joined zz-extra", closes = "</label></div>")
        assertRendered(html, expected)
        assertCommonFlags(html, expected)
    }

    @Test
    fun otp_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Neutral,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp color Neutral", "otp otp-neutral"))
    }

    @Test
    fun otp_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Primary,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp color Primary", "otp otp-primary"))
    }

    @Test
    fun otp_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Secondary,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp color Secondary", "otp otp-secondary"))
    }

    @Test
    fun otp_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Accent,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp color Accent", "otp otp-accent"))
    }

    @Test
    fun otp_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Success,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp color Success", "otp otp-success"))
    }

    @Test
    fun otp_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Info,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp color Info", "otp otp-info"))
    }

    @Test
    fun otp_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Warning,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp color Warning", "otp otp-warning"))
    }

    @Test
    fun otp_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                color = OtpColor.Error,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp color Error", "otp otp-error"))
    }

    @Test
    fun otp_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Xs,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp size Xs", "otp otp-xs"))
    }

    @Test
    fun otp_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Sm,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp size Sm", "otp otp-sm"))
    }

    @Test
    fun otp_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Md,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp size Md", "otp otp-md"))
    }

    @Test
    fun otp_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Lg,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp size Lg", "otp otp-lg"))
    }

    @Test
    fun otp_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(
                size = OtpSize.Xl,
                content = { },
            )
        }
        assertRendered(html, Expected("Otp size Xl", "otp otp-xl"))
    }

    @Test
    fun otpClasses_defaults() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses()
        }
        assertRendered(html, Expected("OtpClasses defaults", "otp"))
    }

    @Test
    fun otpClasses_all_flags() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                joined = true,
            )
        }
        val expected = Expected("OtpClasses all flags", "otp otp-joined", content = false)
        assertRendered(html, expected)
    }

    @Test
    fun otpClasses_color_neutral() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Neutral,
            )
        }
        assertRendered(html, Expected("OtpClasses color Neutral", "otp otp-neutral"))
    }

    @Test
    fun otpClasses_color_primary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Primary,
            )
        }
        assertRendered(html, Expected("OtpClasses color Primary", "otp otp-primary"))
    }

    @Test
    fun otpClasses_color_secondary() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Secondary,
            )
        }
        assertRendered(html, Expected("OtpClasses color Secondary", "otp otp-secondary"))
    }

    @Test
    fun otpClasses_color_accent() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Accent,
            )
        }
        assertRendered(html, Expected("OtpClasses color Accent", "otp otp-accent"))
    }

    @Test
    fun otpClasses_color_success() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Success,
            )
        }
        assertRendered(html, Expected("OtpClasses color Success", "otp otp-success"))
    }

    @Test
    fun otpClasses_color_info() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Info,
            )
        }
        assertRendered(html, Expected("OtpClasses color Info", "otp otp-info"))
    }

    @Test
    fun otpClasses_color_warning() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Warning,
            )
        }
        assertRendered(html, Expected("OtpClasses color Warning", "otp otp-warning"))
    }

    @Test
    fun otpClasses_color_error() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                color = OtpColor.Error,
            )
        }
        assertRendered(html, Expected("OtpClasses color Error", "otp otp-error"))
    }

    @Test
    fun otpClasses_size_xs() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Xs,
            )
        }
        assertRendered(html, Expected("OtpClasses size Xs", "otp otp-xs"))
    }

    @Test
    fun otpClasses_size_sm() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Sm,
            )
        }
        assertRendered(html, Expected("OtpClasses size Sm", "otp otp-sm"))
    }

    @Test
    fun otpClasses_size_md() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Md,
            )
        }
        assertRendered(html, Expected("OtpClasses size Md", "otp otp-md"))
    }

    @Test
    fun otpClasses_size_lg() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Lg,
            )
        }
        assertRendered(html, Expected("OtpClasses size Lg", "otp otp-lg"))
    }

    @Test
    fun otpClasses_size_xl() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtpClasses(
                size = OtpSize.Xl,
            )
        }
        assertRendered(html, Expected("OtpClasses size Xl", "otp otp-xl"))
    }
}
