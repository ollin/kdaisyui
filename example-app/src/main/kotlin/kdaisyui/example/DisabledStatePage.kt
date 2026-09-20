package kdaisyui.example

import io.github.ollin.kdaisyui.components.daisyButton
import kotlinx.html.*

/**
 * Whether DaisyUI styles a disabled button from its NATIVE attribute alone.
 *
 * `daisyButton(disabled = true)` sets the native attribute, which is the accessible way to say
 * it — a screen reader learns the button is unavailable, and the browser stops it receiving
 * focus or firing. Whether it also LOOKS disabled is a CSS question, and no class assertion can
 * answer it: a button can carry every expected class and render unstyled.
 *
 * Three buttons, so the comparison calibrates itself:
 *
 * - **enabled** is the positive control. Without it, two unstyled buttons would agree and the
 *   test would pass while proving nothing.
 * - **native** carries the attribute and no disabled class.
 * - **class** carries the class and no attribute.
 *
 * native and class must agree; enabled must differ from both.
 *
 * **Equal styling is not equal meaning, and this page deliberately measures only the styling.**
 * DaisyUI documents the two as separate techniques and the class one as
 * `tabindex="-1" role="button" aria-disabled="true"`, because the class conveys nothing to a
 * screen reader by itself — its own description is "looks disabled". It exists for elements
 * that cannot take the attribute, such as an anchor styled as a button. A component rendering
 * a real `<button>` should set the attribute; anything reading this page as licence to swap one
 * for the other has read it backwards.
 *
 * The disabled class is spelled in the call below and nowhere else. Tailwind scans this file as
 * TEXT and cannot tell a comment from code, so naming it in prose would keep its rule compiled
 * even after nothing rendered it — and this page would then measure a rule that no longer
 * reaches any component. The sibling `TailwindVariantPage` carries the same warning after that
 * mistake made its assertion unfalsifiable for a time.
 */
fun HTML.disabledStatePage() {
    lang = "en"
    head {
        meta { charset = "utf-8" }
        title { +"Disabled state check" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        daisyuiStylesheet()
    }
    body("bg-base-200 min-h-screen p-10") {
        h1("text-xl font-bold mb-6") { +"Disabled state check" }
        p("mb-6 text-sm opacity-70") {
            +"The middle and right buttons should look identical, and both should look "
            +"different from the left one. If the middle one matches the left instead, the "
            +"native attribute carries no style and removing the class was wrong."
        }

        div("flex items-start gap-4") {
            daisyButton(
                text = "Enabled",
                attrs = { attributes["id"] = "disabled-enabled" },
            )
            daisyButton(
                text = "Native",
                disabled = true,
                attrs = { attributes["id"] = "disabled-native" },
            )
            button(classes = "btn btn-disabled") {
                attributes["id"] = "disabled-class"
                +"Class"
            }
        }
    }
}
