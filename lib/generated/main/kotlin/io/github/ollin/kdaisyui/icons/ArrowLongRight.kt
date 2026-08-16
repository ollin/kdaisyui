// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/arrow-long-right.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Arrow Long Right icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconArrowLongRight(
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
        HeroIconVariant.Outline -> heroIconArrowLongRightOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconArrowLongRightSolid16
            HeroIconSize.Md -> heroIconArrowLongRightSolid20
            HeroIconSize.Lg -> heroIconArrowLongRightSolid24
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
internal const val heroIconArrowLongRightOutline24 = """<path fill="none" d="M17.25 8.25L21 12M21 12L17.25 15.75M21 12H3" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconArrowLongRightSolid16 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2 8C2 8.41421 2.33579 8.75 2.75 8.75L11.4393 8.75L10.2197 9.96967C9.92678 10.2626 9.92678 10.7374 10.2197 11.0303C10.5126 11.3232 10.9874 11.3232 11.2803 11.0303L13.7803 8.53033C14.0732 8.23744 14.0732 7.76256 13.7803 7.46967L11.2803 4.96967C10.9874 4.67678 10.5126 4.67678 10.2197 4.96967C9.92678 5.26256 9.92678 5.73744 10.2197 6.03033L11.4393 7.25L2.75 7.25C2.33579 7.25 2 7.58579 2 8Z" fill="currentColor" />"""

internal const val heroIconArrowLongRightSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2 10C2 9.58579 2.33579 9.25 2.75 9.25L15.3401 9.25L13.2397 7.2996C12.9361 7.01775 12.9186 6.5432 13.2004 6.23966C13.4823 5.93613 13.9568 5.91856 14.2603 6.20041L17.7603 9.45041C17.9132 9.59232 18 9.79145 18 10C18 10.2086 17.9132 10.4077 17.7603 10.5496L14.2603 13.7996C13.9568 14.0815 13.4823 14.0639 13.2004 13.7603C12.9186 13.4568 12.9361 12.9823 13.2397 12.7004L15.3401 10.75L2.75 10.75C2.33579 10.75 2 10.4142 2 10Z" fill="currentColor" />"""

internal const val heroIconArrowLongRightSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M16.7197 7.71967C17.0126 7.42678 17.4874 7.42678 17.7803 7.71967L21.5303 11.4697C21.8232 11.7626 21.8232 12.2374 21.5303 12.5303L17.7803 16.2803C17.4874 16.5732 17.0126 16.5732 16.7197 16.2803C16.4268 15.9874 16.4268 15.5126 16.7197 15.2197L19.1893 12.75H3C2.58579 12.75 2.25 12.4142 2.25 12C2.25 11.5858 2.58579 11.25 3 11.25H19.1893L16.7197 8.78033C16.4268 8.48744 16.4268 8.01256 16.7197 7.71967Z" fill="currentColor" />"""

