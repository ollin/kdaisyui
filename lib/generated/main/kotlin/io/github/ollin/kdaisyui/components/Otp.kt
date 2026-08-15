// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/otp/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.FlowContent

/** Color variants for this component (CSS prefix: `otp-`) */
enum class OtpVariant(internal val className: String) {
    /** CSS: `otp-neutral` — neutral color */
    Neutral("otp-neutral"),
    /** CSS: `otp-primary` — primary color */
    Primary("otp-primary"),
    /** CSS: `otp-secondary` — secondary color */
    Secondary("otp-secondary"),
    /** CSS: `otp-accent` — accent color */
    Accent("otp-accent"),
    /** CSS: `otp-success` — success color */
    Success("otp-success"),
    /** CSS: `otp-info` — info color */
    Info("otp-info"),
    /** CSS: `otp-warning` — warning color */
    Warning("otp-warning"),
    /** CSS: `otp-error` — error color */
    Error("otp-error"),
}

/** Size variants for this component (CSS prefix: `otp-`) */
enum class OtpSize(internal val className: String) {
    /** CSS: `otp-xs` — Extra small size */
    Xs("otp-xs"),
    /** CSS: `otp-sm` — Small size */
    Sm("otp-sm"),
    /** CSS: `otp-md` — Medium size */
    Md("otp-md"),
    /** CSS: `otp-lg` — Large size */
    Lg("otp-lg"),
    /** CSS: `otp-xl` — Extra large size */
    Xl("otp-xl"),
}


/**
 * OTP (One-Time Password) component for inputting verification codes. It's usually 4 to 6 digits long and is used for two-factor authentication (2FA) or passwordless login. Renders `<div class="otp ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param variant — Color variant
 * @param size — Size variant
 * @param joined — Connects the character boxes together
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyOtp(
    id: HtmlId? = null,
    variant: OtpVariant? = null,
    size: OtpSize? = null,
    joined: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("otp")
        if (variant != null) addClassNames(variant.className)
        if (size != null) addClassNames(size.className)
        if (joined) addClassNames("otp-joined")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
