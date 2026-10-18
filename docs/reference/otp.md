<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/otp/+page.md
Regenerate: just generate
-->

# Otp

[DaisyUI documentation →](https://daisyui.com/components/otp/)

One-time password input for verification codes, typically four to six digits, used for two-factor authentication or passwordless login. Renders `<label class="otp ...">`.

```kotlin
// OtpColor: Neutral | Primary | Secondary | Accent | Success | Info | Warning | Error
// OtpSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyOtp(
    id: HtmlId? = null,
    color: ClassValues<OtpColor>? = null,
    size: ClassValues<OtpSize>? = null,
    joined: Boolean = false,  // Connects the character boxes together
    extraClasses: String? = null,
    attrs: (LABEL.() -> Unit)? = null,
    content: (LABEL.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyOtpClasses(color: ClassValues<OtpColor>? = null, size: ClassValues<OtpSize>? = null, joined: Boolean = false)
```
