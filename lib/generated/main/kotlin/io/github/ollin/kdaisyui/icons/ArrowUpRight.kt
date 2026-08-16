// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/arrow-up-right.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Arrow Up Right icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconArrowUpRight(
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
        HeroIconVariant.Outline -> heroIconArrowUpRightOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconArrowUpRightSolid16
            HeroIconSize.Md -> heroIconArrowUpRightSolid20
            HeroIconSize.Lg -> heroIconArrowUpRightSolid24
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
internal const val heroIconArrowUpRightOutline24 = """<path fill="none" d="M4.5 19.5L19.5 4.5M19.5 4.5L8.25 4.5M19.5 4.5V15.75" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconArrowUpRightSolid16 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M4.21967 11.7803C3.92678 11.4874 3.92678 11.0126 4.21967 10.7197L9.43934 5.5H5.75C5.33579 5.5 5 5.16421 5 4.75C5 4.33579 5.33579 4 5.75 4H11.25C11.6642 4 12 4.33579 12 4.75V10.25C12 10.6642 11.6642 11 11.25 11C10.8358 11 10.5 10.6642 10.5 10.25V6.56066L5.28033 11.7803C4.98744 12.0732 4.51256 12.0732 4.21967 11.7803Z" fill="currentColor" />"""

internal const val heroIconArrowUpRightSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M5.21967 14.7803C5.51256 15.0732 5.98744 15.0732 6.28033 14.7803L13.5 7.56066V13.25C13.5 13.6642 13.8358 14 14.25 14C14.6642 14 15 13.6642 15 13.25V5.75C15 5.33579 14.6642 5 14.25 5H6.75C6.33579 5 6 5.33579 6 5.75C6 6.16421 6.33579 6.5 6.75 6.5H12.4393L5.21967 13.7197C4.92678 14.0126 4.92678 14.4874 5.21967 14.7803Z" fill="currentColor" />"""

internal const val heroIconArrowUpRightSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M8.25 3.75L19.5 3.75C19.6989 3.75 19.8897 3.82902 20.0303 3.96967C20.171 4.11032 20.25 4.30109 20.25 4.5V15.75C20.25 16.1642 19.9142 16.5 19.5 16.5C19.0858 16.5 18.75 16.1642 18.75 15.75V6.31066L5.03033 20.0303C4.73744 20.3232 4.26256 20.3232 3.96967 20.0303C3.67678 19.7374 3.67678 19.2626 3.96967 18.9697L17.6893 5.25L8.25 5.25C7.83579 5.25 7.5 4.91421 7.5 4.5C7.5 4.08579 7.83579 3.75 8.25 3.75Z" fill="currentColor" />"""

