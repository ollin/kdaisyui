// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/bars3-center-left.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Bars3 Center Left icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconBars3CenterLeft(
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
        HeroIconVariant.Outline -> heroIconBars3CenterLeftOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconBars3CenterLeftSolid16
            HeroIconSize.Md -> heroIconBars3CenterLeftSolid20
            HeroIconSize.Lg -> heroIconBars3CenterLeftSolid24
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
internal const val heroIconBars3CenterLeftOutline24 = """<path fill="none" d="M3.75 6.75H20.25M3.75 12H12M3.75 17.25H20.25" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconBars3CenterLeftSolid16 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2 3.75C2 3.33579 2.33579 3 2.75 3H13.25C13.6642 3 14 3.33579 14 3.75C14 4.16421 13.6642 4.5 13.25 4.5H2.75C2.33579 4.5 2 4.16421 2 3.75ZM2 8C2 7.58579 2.33579 7.25 2.75 7.25H7.25C7.66421 7.25 8 7.58579 8 8C8 8.41421 7.66421 8.75 7.25 8.75H2.75C2.33579 8.75 2 8.41421 2 8ZM2 12.25C2 11.8358 2.33579 11.5 2.75 11.5H13.25C13.6642 11.5 14 11.8358 14 12.25C14 12.6642 13.6642 13 13.25 13H2.75C2.33579 13 2 12.6642 2 12.25Z" fill="currentColor" />"""

internal const val heroIconBars3CenterLeftSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2 4.75C2 4.33579 2.33579 4 2.75 4H17.25C17.6642 4 18 4.33579 18 4.75C18 5.16421 17.6642 5.5 17.25 5.5H2.75C2.33579 5.5 2 5.16421 2 4.75ZM2 15.25C2 14.8358 2.33579 14.5 2.75 14.5H17.25C17.6642 14.5 18 14.8358 18 15.25C18 15.6642 17.6642 16 17.25 16H2.75C2.33579 16 2 15.6642 2 15.25Z" fill="currentColor" />"""+
        """<path fill-rule="evenodd" clip-rule="evenodd" d="M2 10C2 9.58579 2.33579 9.25 2.75 9.25H10.25C10.6642 9.25 11 9.58579 11 10C11 10.4142 10.6642 10.75 10.25 10.75H2.75C2.33579 10.75 2 10.4142 2 10Z" fill="currentColor" />"""

internal const val heroIconBars3CenterLeftSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M3 6.75C3 6.33579 3.33579 6 3.75 6H20.25C20.6642 6 21 6.33579 21 6.75C21 7.16421 20.6642 7.5 20.25 7.5H3.75C3.33579 7.5 3 7.16421 3 6.75ZM3 12C3 11.5858 3.33579 11.25 3.75 11.25H12C12.4142 11.25 12.75 11.5858 12.75 12C12.75 12.4142 12.4142 12.75 12 12.75H3.75C3.33579 12.75 3 12.4142 3 12ZM3 17.25C3 16.8358 3.33579 16.5 3.75 16.5H20.25C20.6642 16.5 21 16.8358 21 17.25C21 17.6642 20.6642 18 20.25 18H3.75C3.33579 18 3 17.6642 3 17.25Z" fill="currentColor" />"""

