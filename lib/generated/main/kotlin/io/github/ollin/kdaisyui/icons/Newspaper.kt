// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/newspaper.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Newspaper icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconNewspaper(
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
        HeroIconVariant.Outline -> heroIconNewspaperOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconNewspaperSolid16
            HeroIconSize.Md -> heroIconNewspaperSolid20
            HeroIconSize.Lg -> heroIconNewspaperSolid24
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
internal const val heroIconNewspaperOutline24 = """<path fill="none" d="M12 7.5H13.5M12 10.5H13.5M6 13.5H13.5M6 16.5H13.5M16.5 7.5H19.875C20.4963 7.5 21 8.00368 21 8.625V18C21 19.2426 19.9926 20.25 18.75 20.25M16.5 7.5V18C16.5 19.2426 17.5074 20.25 18.75 20.25M16.5 7.5V4.875C16.5 4.25368 15.9963 3.75 15.375 3.75H4.125C3.50368 3.75 3 4.25368 3 4.875V18C3 19.2426 4.00736 20.25 5.25 20.25H18.75M6 7.5H9V10.5H6V7.5Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconNewspaperSolid16 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M10 3C10 2.44772 9.55229 2 9 2H3C2.44772 2 2 2.44772 2 3V12C2 13.1046 2.89543 14 4 14H12C10.8954 14 10 13.1046 10 12V3ZM4 4H8V6H4V4ZM8 7.5H4V9H8V7.5ZM4 10.5H8V12H4V10.5Z" fill="black" />"""+
        """<path d="M13 5H11.5V11.25C11.5 11.9404 12.0596 12.5 12.75 12.5C13.4404 12.5 14 11.9404 14 11.25V6C14 5.44772 13.5523 5 13 5Z" fill="black" />"""

internal const val heroIconNewspaperSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2 3.5C2 2.67157 2.67157 2 3.5 2H12.5C13.3284 2 14 2.67157 14 3.5V15.25C14 16.7688 15.2312 18 16.75 18H4.75C3.23122 18 2 16.7688 2 15.25V3.5ZM5.75 10.5C5.33579 10.5 5 10.8358 5 11.25C5 11.6642 5.33579 12 5.75 12H10.25C10.6642 12 11 11.6642 11 11.25C11 10.8358 10.6642 10.5 10.25 10.5H5.75ZM5.75 13.5C5.33579 13.5 5 13.8358 5 14.25C5 14.6642 5.33579 15 5.75 15H10.25C10.6642 15 11 14.6642 11 14.25C11 13.8358 10.6642 13.5 10.25 13.5H5.75ZM5 5.75C5 5.33579 5.33579 5 5.75 5H10.25C10.6642 5 11 5.33579 11 5.75V8.25C11 8.66421 10.6642 9 10.25 9H5.75C5.33579 9 5 8.66421 5 8.25V5.75Z" fill="currentColor" />"""+
        """<path d="M16.5 6.5H15.5V15.25C15.5 15.9404 16.0596 16.5 16.75 16.5C17.4404 16.5 18 15.9404 18 15.25V8C18 7.17157 17.3284 6.5 16.5 6.5Z" fill="currentColor" />"""

internal const val heroIconNewspaperSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M4.125 3C3.08947 3 2.25 3.83947 2.25 4.875V18C2.25 19.6569 3.59315 21 5.25 21H20.25C18.5931 21 17.25 19.6569 17.25 18V4.875C17.25 3.83947 16.4105 3 15.375 3H4.125ZM12 9.75C11.5858 9.75 11.25 10.0858 11.25 10.5C11.25 10.9142 11.5858 11.25 12 11.25H13.5C13.9142 11.25 14.25 10.9142 14.25 10.5C14.25 10.0858 13.9142 9.75 13.5 9.75H12ZM11.25 7.5C11.25 7.08579 11.5858 6.75 12 6.75H13.5C13.9142 6.75 14.25 7.08579 14.25 7.5C14.25 7.91421 13.9142 8.25 13.5 8.25H12C11.5858 8.25 11.25 7.91421 11.25 7.5ZM6 12.75C5.58579 12.75 5.25 13.0858 5.25 13.5C5.25 13.9142 5.58579 14.25 6 14.25H13.5C13.9142 14.25 14.25 13.9142 14.25 13.5C14.25 13.0858 13.9142 12.75 13.5 12.75H6ZM5.25 16.5C5.25 16.0858 5.58579 15.75 6 15.75H13.5C13.9142 15.75 14.25 16.0858 14.25 16.5C14.25 16.9142 13.9142 17.25 13.5 17.25H6C5.58579 17.25 5.25 16.9142 5.25 16.5ZM6 6.75C5.58579 6.75 5.25 7.08579 5.25 7.5V10.5C5.25 10.9142 5.58579 11.25 6 11.25H9C9.41421 11.25 9.75 10.9142 9.75 10.5V7.5C9.75 7.08579 9.41421 6.75 9 6.75H6Z" fill="currentColor" />"""+
        """<path d="M18.75 6.75H20.625C21.2463 6.75 21.75 7.25368 21.75 7.875V18C21.75 18.8284 21.0784 19.5 20.25 19.5C19.4216 19.5 18.75 18.8284 18.75 18V6.75Z" fill="currentColor" />"""

