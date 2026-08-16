// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/lock-open.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Lock Open icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconLockOpen(
    variant: HeroIconVariant = HeroIconVariant.Outline,
    size: HeroIconSize = HeroIconSize.Lg,
    extraClasses: String? = null,
) {
    val classes = buildList {
        add(size.className)
        add(variant.className)
        if (extraClasses != null) add(extraClasses)
    }.joinToString(" ")

    val svgContent = when (variant) {
        HeroIconVariant.Outline -> heroIconLockOpenOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconLockOpenSolid16
            HeroIconSize.Md -> heroIconLockOpenSolid20
            HeroIconSize.Lg -> heroIconLockOpenSolid24
        }
    }
    val viewBox = when (variant) {
        HeroIconVariant.Outline -> "0 0 24 24"
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> "0 0 16 16"
            HeroIconSize.Md -> "0 0 20 20"
            HeroIconSize.Lg -> "0 0 24 24"
        }
    }

    span {
        unsafe {
            raw("""<svg xmlns="http://www.w3.org/2000/svg" viewBox="$viewBox" class="$classes" width="${size.dimension}" height="${size.dimension}">$svgContent</svg>""")
        }
    }
}
internal const val heroIconLockOpenOutline24 = """<path fill="none" d="M13.5 10.5V6.75C13.5 4.26472 15.5147 2.25 18 2.25C20.4853 2.25 22.5 4.26472 22.5 6.75V10.5M3.75 21.75H14.25C15.4926 21.75 16.5 20.7426 16.5 19.5V12.75C16.5 11.5074 15.4926 10.5 14.25 10.5H3.75C2.50736 10.5 1.5 11.5074 1.5 12.75V19.5C1.5 20.7426 2.50736 21.75 3.75 21.75Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconLockOpenSolid16 = """<path d="M11.5 1C9.567 1 8 2.567 8 4.5V7H2.5C1.67157 7 1 7.67157 1 8.5V13.5C1 14.3284 1.67157 15 2.5 15H9.5C10.3284 15 11 14.3284 11 13.5V8.5C11 7.67157 10.3284 7 9.5 7V4.5C9.5 3.39543 10.3954 2.5 11.5 2.5C12.6046 2.5 13.5 3.39543 13.5 4.5V6.25C13.5 6.66421 13.8358 7 14.25 7C14.6642 7 15 6.66421 15 6.25V4.5C15 2.567 13.433 1 11.5 1Z" fill="black" />"""

internal const val heroIconLockOpenSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M14.5 1C12.0147 1 10 3.01472 10 5.5V9H3C1.89543 9 1 9.89543 1 11V17C1 18.1046 1.89543 19 3 19H13C14.1046 19 15 18.1046 15 17V11C15 9.89543 14.1046 9 13 9H11.5V5.5C11.5 3.84315 12.8431 2.5 14.5 2.5C16.1569 2.5 17.5 3.84315 17.5 5.5V8.25C17.5 8.66421 17.8358 9 18.25 9C18.6642 9 19 8.66421 19 8.25V5.5C19 3.01472 16.9853 1 14.5 1Z" fill="currentColor" />"""

internal const val heroIconLockOpenSolid24 = """<path d="M18 1.5C20.8995 1.5 23.25 3.85051 23.25 6.75V10.5C23.25 10.9142 22.9142 11.25 22.5 11.25C22.0858 11.25 21.75 10.9142 21.75 10.5V6.75C21.75 4.67893 20.0711 3 18 3C15.9289 3 14.25 4.67893 14.25 6.75V9.75C15.9069 9.75 17.25 11.0931 17.25 12.75V19.5C17.25 21.1569 15.9069 22.5 14.25 22.5H3.75C2.09315 22.5 0.75 21.1569 0.75 19.5V12.75C0.75 11.0931 2.09315 9.75 3.75 9.75H12.75V6.75C12.75 3.85051 15.1005 1.5 18 1.5Z" fill="currentColor" />"""

