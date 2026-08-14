// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/arrow-long-down.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Arrow Long Down icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconArrowLongDown(
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
        HeroIconVariant.Outline -> heroIconArrowLongDownOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconArrowLongDownSolid16
            HeroIconSize.Md -> heroIconArrowLongDownSolid20
            HeroIconSize.Lg -> heroIconArrowLongDownSolid24
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
internal const val heroIconArrowLongDownOutline24 = """<path fill="none" d="M15.75 17.25L12 21M12 21L8.25 17.25M12 21L12 3" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconArrowLongDownSolid16 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M8 2C8.41421 2 8.75 2.33579 8.75 2.75V11.4393L9.96967 10.2197C10.2626 9.92678 10.7374 9.92678 11.0303 10.2197C11.3232 10.5126 11.3232 10.9874 11.0303 11.2803L8.53033 13.7803C8.23744 14.0732 7.76256 14.0732 7.46967 13.7803L4.96967 11.2803C4.67678 10.9874 4.67678 10.5126 4.96967 10.2197C5.26256 9.92678 5.73744 9.92678 6.03033 10.2197L7.25 11.4393V2.75C7.25 2.33579 7.58579 2 8 2Z" fill="currentColor" />"""

internal const val heroIconArrowLongDownSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M10 2C10.4142 2 10.75 2.33579 10.75 2.75V15.3401L12.7004 13.2397C12.9823 12.9361 13.4568 12.9186 13.7603 13.2004C14.0639 13.4823 14.0815 13.9568 13.7996 14.2603L10.5496 17.7603C10.4077 17.9132 10.2086 18 10 18C9.79145 18 9.59232 17.9132 9.45041 17.7603L6.20041 14.2603C5.91856 13.9568 5.93613 13.4823 6.23966 13.2004C6.5432 12.9186 7.01775 12.9361 7.2996 13.2397L9.25 15.3401V2.75C9.25 2.33579 9.58579 2 10 2Z" fill="currentColor" />"""

internal const val heroIconArrowLongDownSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M12 2.25C12.4142 2.25 12.75 2.58579 12.75 3L12.75 19.1893L15.2197 16.7197C15.5126 16.4268 15.9874 16.4268 16.2803 16.7197C16.5732 17.0126 16.5732 17.4874 16.2803 17.7803L12.5303 21.5303C12.2374 21.8232 11.7626 21.8232 11.4697 21.5303L7.71967 17.7803C7.42678 17.4874 7.42678 17.0126 7.71967 16.7197C8.01256 16.4268 8.48744 16.4268 8.78033 16.7197L11.25 19.1893L11.25 3C11.25 2.58579 11.5858 2.25 12 2.25Z" fill="currentColor" />"""

