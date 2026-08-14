// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/view-columns.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * View Columns icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconViewColumns(
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
        HeroIconVariant.Outline -> heroIconViewColumnsOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconViewColumnsSolid16
            HeroIconSize.Md -> heroIconViewColumnsSolid20
            HeroIconSize.Lg -> heroIconViewColumnsSolid24
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
internal const val heroIconViewColumnsOutline24 = """<path fill="none" d="M9 4.5V19.5M15 4.5V19.5M4.125 19.5H19.875C20.4963 19.5 21 18.9963 21 18.375V5.625C21 5.00368 20.4963 4.5 19.875 4.5H4.125C3.50368 4.5 3 5.00368 3 5.625V18.375C3 18.9963 3.50368 19.5 4.125 19.5Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconViewColumnsSolid16 = """<path d="M9.83594 3H6.16602V13H9.83594V3Z" fill="black" />"""+
        """<path d="M11.3359 13H13.5C14.3284 13 15 12.3284 15 11.5V4.5C15 3.67157 14.3284 3 13.5 3H11.3359V13Z" fill="black" />"""+
        """<path d="M2.5 3H4.66602V13H2.5C1.67157 13 1 12.3284 1 11.5V4.5C1 3.67157 1.67157 3 2.5 3Z" fill="black" />"""

internal const val heroIconViewColumnsSolid20 = """<path d="M14 17H16.75C17.9926 17 19 15.9926 19 14.75V5.25C19 4.00736 17.9926 3 16.75 3H14V17Z" fill="currentColor" />"""+
        """<path d="M12.5 3H7.5V17H12.5V3Z" fill="currentColor" />"""+
        """<path d="M3.25 3H6V17H3.25C2.00736 17 1 15.9926 1 14.75V5.25C1 4.00736 2.00736 3 3.25 3Z" fill="currentColor" />"""

internal const val heroIconViewColumnsSolid24 = """<path d="M15 3.75H9V20.25H15V3.75Z" fill="currentColor" />"""+
        """<path d="M16.5 20.25H19.875C20.9105 20.25 21.75 19.4105 21.75 18.375V5.625C21.75 4.58947 20.9105 3.75 19.875 3.75H16.5V20.25Z" fill="currentColor" />"""+
        """<path d="M4.125 3.75H7.5V20.25H4.125C3.08947 20.25 2.25 19.4105 2.25 18.375V5.625C2.25 4.58947 3.08947 3.75 4.125 3.75Z" fill="currentColor" />"""

