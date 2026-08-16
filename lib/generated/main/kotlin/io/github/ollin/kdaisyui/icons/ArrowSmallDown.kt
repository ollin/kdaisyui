// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/arrow-small-down.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Arrow Small Down icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconArrowSmallDown(
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
        HeroIconVariant.Outline -> heroIconArrowSmallDownOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Md -> heroIconArrowSmallDownSolid20
            HeroIconSize.Lg -> heroIconArrowSmallDownSolid24
            else -> heroIconArrowSmallDownSolid24
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
internal const val heroIconArrowSmallDownOutline24 = """<path fill="none" d="M12 4.5V19.5M12 19.5L18.75 12.75M12 19.5L5.25 12.75" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconArrowSmallDownSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M10 5C10.4142 5 10.75 5.33579 10.75 5.75V12.3879L12.7094 10.2302C12.9965 9.93159 13.4713 9.92228 13.7698 10.2094C14.0684 10.4965 14.0777 10.9713 13.7906 11.2698L10.5406 14.7698C10.3992 14.9169 10.204 15 10 15C9.79599 15 9.60078 14.9169 9.45938 14.7698L6.20938 11.2698C5.92228 10.9713 5.93159 10.4965 6.23017 10.2094C6.52875 9.92228 7.00353 9.93159 7.29063 10.2302L9.25 12.3879V5.75C9.25 5.33579 9.58579 5 10 5Z" fill="currentColor" />"""

internal const val heroIconArrowSmallDownSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M12 3.75C12.4142 3.75 12.75 4.08579 12.75 4.5L12.75 17.6893L18.2197 12.2197C18.5126 11.9268 18.9874 11.9268 19.2803 12.2197C19.5732 12.5126 19.5732 12.9874 19.2803 13.2803L12.5303 20.0303C12.2374 20.3232 11.7626 20.3232 11.4697 20.0303L4.71967 13.2803C4.42678 12.9874 4.42678 12.5126 4.71967 12.2197C5.01256 11.9268 5.48744 11.9268 5.78033 12.2197L11.25 17.6893L11.25 4.5C11.25 4.08579 11.5858 3.75 12 3.75Z" fill="currentColor" />"""

