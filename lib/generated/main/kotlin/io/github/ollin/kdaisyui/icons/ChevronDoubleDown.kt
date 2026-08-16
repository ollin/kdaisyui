// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/chevron-double-down.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Chevron Double Down icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconChevronDoubleDown(
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
        HeroIconVariant.Outline -> heroIconChevronDoubleDownOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconChevronDoubleDownSolid16
            HeroIconSize.Md -> heroIconChevronDoubleDownSolid20
            HeroIconSize.Lg -> heroIconChevronDoubleDownSolid24
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
internal const val heroIconChevronDoubleDownOutline24 = """<path fill="none" d="M4.5 5.25L12 12.75L19.5 5.25M4.5 11.25L12 18.75L19.5 11.25" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconChevronDoubleDownSolid16 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M7.46967 12.7803C7.76256 13.0732 8.23744 13.0732 8.53033 12.7803L11.7803 9.53033C12.0732 9.23744 12.0732 8.76256 11.7803 8.46967C11.4874 8.17678 11.0126 8.17678 10.7197 8.46967L8 11.1893L5.28033 8.46967C4.98744 8.17678 4.51256 8.17678 4.21967 8.46967C3.92678 8.76256 3.92678 9.23744 4.21967 9.53033L7.46967 12.7803ZM4.21967 4.53033L7.46967 7.78033C7.76256 8.07322 8.23744 8.07322 8.53033 7.78033L11.7803 4.53033C12.0732 4.23744 12.0732 3.76256 11.7803 3.46967C11.4874 3.17678 11.0126 3.17678 10.7197 3.46967L8 6.18934L5.28033 3.46967C4.98744 3.17678 4.51256 3.17678 4.21967 3.46967C3.92678 3.76256 3.92678 4.23744 4.21967 4.53033Z" fill="black" />"""

internal const val heroIconChevronDoubleDownSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M9.46967 15.2803C9.76256 15.5732 10.2374 15.5732 10.5303 15.2803L14.7803 11.0303C15.0732 10.7374 15.0732 10.2626 14.7803 9.96967C14.4874 9.67678 14.0126 9.67678 13.7197 9.96967L10 13.6893L6.28033 9.96967C5.98744 9.67678 5.51256 9.67678 5.21967 9.96967C4.92678 10.2626 4.92678 10.7374 5.21967 11.0303L9.46967 15.2803ZM5.21967 6.03033L9.46967 10.2803C9.76256 10.5732 10.2374 10.5732 10.5303 10.2803L14.7803 6.03033C15.0732 5.73744 15.0732 5.26256 14.7803 4.96967C14.4874 4.67678 14.0126 4.67678 13.7197 4.96967L10 8.68934L6.28033 4.96967C5.98744 4.67678 5.51256 4.67678 5.21967 4.96967C4.92678 5.26256 4.92678 5.73744 5.21967 6.03033Z" fill="currentColor" />"""

internal const val heroIconChevronDoubleDownSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M11.4697 13.2803C11.7626 13.5732 12.2374 13.5732 12.5303 13.2803L20.0303 5.78033C20.3232 5.48744 20.3232 5.01256 20.0303 4.71967C19.7374 4.42678 19.2626 4.42678 18.9697 4.71967L12 11.6893L5.03033 4.71967C4.73744 4.42678 4.26256 4.42678 3.96967 4.71967C3.67678 5.01256 3.67678 5.48744 3.96967 5.78033L11.4697 13.2803Z" fill="currentColor" />"""+
        """<path fill-rule="evenodd" clip-rule="evenodd" d="M11.4697 19.2803C11.7626 19.5732 12.2374 19.5732 12.5303 19.2803L20.0303 11.7803C20.3232 11.4874 20.3232 11.0126 20.0303 10.7197C19.7374 10.4268 19.2626 10.4268 18.9697 10.7197L12 17.6893L5.03033 10.7197C4.73744 10.4268 4.26256 10.4268 3.96967 10.7197C3.67678 11.0126 3.67678 11.4874 3.96967 11.7803L11.4697 19.2803Z" fill="currentColor" />"""

