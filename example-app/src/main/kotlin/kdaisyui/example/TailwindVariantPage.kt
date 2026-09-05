package kdaisyui.example

import io.github.ollin.kdaisyui.components.ButtonSize
import io.github.ollin.kdaisyui.components.daisyButton
import kotlinx.html.*

/**
 * Whether a Tailwind variant of a DaisyUI class actually applies.
 *
 * `docs/explanation.md` promises consumers that `extraClasses` takes "responsive variants
 * (`lg:btn-lg`)". Whether that is true depends entirely on how the application compiles its CSS,
 * and the page exists so the answer is measured rather than assumed.
 *
 * The two buttons are a matched pair, which is what makes the assertion self-calibrating:
 *
 * - **control** carries `btn-lg` outright and is therefore large at every width;
 * - **variant** carries `lg:btn-lg` and should match the control from the `lg` breakpoint up,
 *   and be smaller below it.
 *
 * Comparing them needs no hard-coded pixel value and survives any DaisyUI restyle of `btn-lg`.
 */
fun HTML.tailwindVariantPage() {
    lang = "en"
    head {
        meta { charset = "utf-8" }
        title { +"Tailwind variant check" }
        meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
        link { rel = "stylesheet"; href = "/webjars/daisyui/daisyui.css" }
        link { rel = "stylesheet"; href = "/webjars/daisyui/themes.css" }
        script { src = "/webjars/tailwindcss__browser/dist/index.global.js" }
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
        }
    }
}
