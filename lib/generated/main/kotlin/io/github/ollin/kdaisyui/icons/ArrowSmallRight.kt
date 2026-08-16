// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/arrow-small-right.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Arrow Small Right icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconArrowSmallRight(
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
        HeroIconVariant.Outline -> heroIconArrowSmallRightOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Md -> heroIconArrowSmallRightSolid20
            HeroIconSize.Lg -> heroIconArrowSmallRightSolid24
            else -> heroIconArrowSmallRightSolid24
        }
    }
    val viewBox = when (variant) {
        HeroIconVariant.Outline -> "0 0 24 24"
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Md -> "0 0 20 20"
            else -> "0 0 24 24"
        }
    }

    span {
        unsafe {
            raw("""<svg xmlns="http://www.w3.org/2000/svg" viewBox="$viewBox" class="$classes" width="${size.dimension}" height="${size.dimension}">$svgContent</svg>""")
        }
    }
}
internal const val heroIconArrowSmallRightOutline24 = """<path fill="none" d="M4.5 12L19.5 12M19.5 12L12.75 5.25M19.5 12L12.75 18.75" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconArrowSmallRightSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M5 10C5 9.58579 5.33579 9.25 5.75 9.25H12.3879L10.2302 7.29063C9.93159 7.00353 9.92228 6.52875 10.2094 6.23017C10.4965 5.93159 10.9713 5.92228 11.2698 6.20938L14.7698 9.45938C14.9169 9.60078 15 9.79599 15 10C15 10.204 14.9169 10.3992 14.7698 10.5406L11.2698 13.7906C10.9713 14.0777 10.4965 14.0684 10.2094 13.7698C9.92228 13.4713 9.93159 12.9965 10.2302 12.7094L12.3879 10.75H5.75C5.33579 10.75 5 10.4142 5 10Z" fill="currentColor" />"""

internal const val heroIconArrowSmallRightSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M3.75 12C3.75 11.5858 4.08579 11.25 4.5 11.25L17.6893 11.25L12.2197 5.78033C11.9268 5.48744 11.9268 5.01256 12.2197 4.71967C12.5126 4.42678 12.9874 4.42678 13.2803 4.71967L20.0303 11.4697C20.3232 11.7626 20.3232 12.2374 20.0303 12.5303L13.2803 19.2803C12.9874 19.5732 12.5126 19.5732 12.2197 19.2803C11.9268 18.9874 11.9268 18.5126 12.2197 18.2197L17.6893 12.75L4.5 12.75C4.08579 12.75 3.75 12.4142 3.75 12Z" fill="currentColor" />"""

