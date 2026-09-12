<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/otp/+page.md
Regenerate: just generate
-->

# Otp

[DaisyUI documentation →](https://daisyui.com/components/otp/)

One-time password input for verification codes, typically four to six digits, used for two-factor authentication or passwordless login. Renders `<label class="otp ...">`.

```kotlin
// OtpVariant: Neutral | Primary | Secondary | Accent | Success | Info | Warning | Error
// OtpSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyOtp(
    id: HtmlId? = null,
    variant: OtpVariant? = null,
    size: OtpSize? = null,
    joined: Boolean = false,  // Connects the character boxes together
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
)
```
