package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class OtpTest {

    private fun assertDocumented(html: String, tag: String, classes: String, case: String) {
        assertEquals(classes, html.substringAfter("class=\"").substringBefore("\"").split(" ").sorted().joinToString(" "), "Class mismatch for $case")
        assertEquals(tag, html.substringBefore(" class=\"").substringAfterLast("<").substringBefore(" "), "Tag mismatch for $case")
    }

    @Test
    fun otp() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp() {
            }
        }
        assertDocumented(html, "label", "otp", "OTP")
    }

    @Test
    fun otp_joined() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(joined = true) {
            }
        }
        assertDocumented(html, "label", "otp otp-joined", "OTP joined")
    }

    @Test
    fun otp_with_different_sizes() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Xs) {
            }
        }
        assertDocumented(html, "label", "otp otp-xs", "OTP with different sizes")
    }

    @Test
    fun otp_with_different_sizes_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Sm) {
            }
        }
        assertDocumented(html, "label", "otp otp-sm", "OTP with different sizes")
    }

    @Test
    fun otp_with_different_sizes_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Md) {
            }
        }
        assertDocumented(html, "label", "otp otp-md", "OTP with different sizes")
    }

    @Test
    fun otp_with_different_sizes_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Lg) {
            }
        }
        assertDocumented(html, "label", "otp otp-lg", "OTP with different sizes")
    }

    @Test
    fun otp_with_different_sizes_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(size = OtpSize.Xl) {
            }
        }
        assertDocumented(html, "label", "otp otp-xl", "OTP with different sizes")
    }

    @Test
    fun otp_with_different_colors() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Neutral) {
            }
        }
        assertDocumented(html, "label", "otp otp-neutral", "OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_2() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Primary) {
            }
        }
        assertDocumented(html, "label", "otp otp-primary", "OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_3() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Secondary) {
            }
        }
        assertDocumented(html, "label", "otp otp-secondary", "OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_4() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Accent) {
            }
        }
        assertDocumented(html, "label", "otp otp-accent", "OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_5() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Info) {
            }
        }
        assertDocumented(html, "label", "otp otp-info", "OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_6() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Success) {
            }
        }
        assertDocumented(html, "label", "otp otp-success", "OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_7() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Warning) {
            }
        }
        assertDocumented(html, "label", "otp otp-warning", "OTP with different colors")
    }

    @Test
    fun otp_with_different_colors_8() {
        val html = createHTML(prettyPrint = false).div {
            daisyOtp(color = OtpColor.Error) {
            }
        }
        assertDocumented(html, "label", "otp otp-error", "OTP with different colors")
    }
}
