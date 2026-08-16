// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/stop-circle.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Stop Circle icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconStopCircle(
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
        HeroIconVariant.Outline -> heroIconStopCircleOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconStopCircleSolid16
            HeroIconSize.Md -> heroIconStopCircleSolid20
            HeroIconSize.Lg -> heroIconStopCircleSolid24
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
internal const val heroIconStopCircleOutline24 = """<path fill="none" d="M21 12C21 16.9706 16.9706 21 12 21C7.02944 21 3 16.9706 3 12C3 7.02944 7.02944 3 12 3C16.9706 3 21 7.02944 21 12Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""+
        """<path fill="none" d="M9 9.5625C9 9.25184 9.25184 9 9.5625 9H14.4375C14.7482 9 15 9.25184 15 9.5625V14.4375C15 14.7482 14.7482 15 14.4375 15H9.5625C9.25184 15 9 14.7482 9 14.4375V9.5625Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconStopCircleSolid16 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M8 15C11.866 15 15 11.866 15 8C15 4.13401 11.866 1 8 1C4.13401 1 1 4.13401 1 8C1 11.866 4.13401 15 8 15ZM6.5 5.5C5.94772 5.5 5.5 5.94772 5.5 6.5V9.5C5.5 10.0523 5.94772 10.5 6.5 10.5H9.5C10.0523 10.5 10.5 10.0523 10.5 9.5V6.5C10.5 5.94772 10.0523 5.5 9.5 5.5H6.5Z" fill="black" />"""

internal const val heroIconStopCircleSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2 10C2 5.58172 5.58172 2 10 2C14.4183 2 18 5.58172 18 10C18 14.4183 14.4183 18 10 18C5.58172 18 2 14.4183 2 10ZM7 7.75C7 7.33579 7.33579 7 7.75 7H12.25C12.6642 7 13 7.33579 13 7.75V12.25C13 12.6642 12.6642 13 12.25 13H7.75C7.33579 13 7 12.6642 7 12.25V7.75Z" fill="currentColor" />"""

internal const val heroIconStopCircleSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2.25 12C2.25 6.61522 6.61522 2.25 12 2.25C17.3848 2.25 21.75 6.61522 21.75 12C21.75 17.3848 17.3848 21.75 12 21.75C6.61522 21.75 2.25 17.3848 2.25 12ZM8.25 9.5625C8.25 8.83763 8.83763 8.25 9.5625 8.25H14.4375C15.1624 8.25 15.75 8.83763 15.75 9.5625V14.4375C15.75 15.1624 15.1624 15.75 14.4375 15.75H9.5625C8.83763 15.75 8.25 15.1624 8.25 14.4375V9.5625Z" fill="currentColor" />"""

