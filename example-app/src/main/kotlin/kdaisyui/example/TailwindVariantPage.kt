package kdaisyui.example

import io.github.ollin.kdaisyui.components.ButtonSize
import io.github.ollin.kdaisyui.components.daisyButton
import kotlinx.html.*

/**
 * Whether a Tailwind variant of a DaisyUI class actually applies.
 *
 * `docs/explanation.md` promises consumers that `extraClasses` takes responsive variants of a
 * DaisyUI class. Whether that is true depends entirely on how the application compiles its CSS,
 * and the page exists so the answer is measured rather than assumed.
 *
 * The two buttons are a matched pair, which is what makes the assertion self-calibrating:
 *
 * - **control** carries `btn-lg` outright and is therefore large at every width;
 * - **variant** carries the same class behind an `lg` breakpoint and should match the control
 *   from that breakpoint up, and be smaller below it.
 *
 * Comparing them needs no hard-coded pixel value and survives any DaisyUI restyle of `btn-lg`.
 *
 * **NOTHING IN THIS FILE MAY SPELL THE PREFIXED CLASS EXCEPT THE `extraClasses` ARGUMENT ITSELF.**
 * Tailwind scans these sources as TEXT and does not know a comment from a call, so writing the
 * composed class in this doc comment generated the CSS rule from the comment — and the end-to-end
 * assertion below then passed whether or not `extraClasses` worked at all. It was unfalsifiable
 * for that reason until 2026-09-14. Say the variant and the class separately, as above.
 */
fun HTML.tailwindVariantPage() {
    lang = "en"
    head {
        meta { charset = "utf-8" }
        title { +"Tailwind variant check" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        daisyuiStylesheet()
    }
    body("bg-base-200 min-h-screen p-10") {
        h1("text-xl font-bold mb-6") { +"Tailwind variant check" }
        p("mb-6 text-sm opacity-70") {
            +"Both buttons should be the same size at lg and above. If they are not, a Tailwind "
            +"variant of a DaisyUI class is not being compiled."
        }

        div("flex items-start gap-4") {
            daisyButton(
                text = "Control",
                size = ButtonSize.Lg,
                attrs = { attributes["id"] = "variant-control" },
            )
            daisyButton(
                text = "Variant",
                extraClasses = "lg:btn-lg",
                attrs = { attributes["id"] = "variant-subject" },
            )
            daisyButton(
                text = "Max variant",
                extraClasses = "max-lg:btn-lg",
                attrs = { attributes["id"] = "variant-subject-max" },
            )
        }
    }
}
