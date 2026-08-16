// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/play.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Play icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconPlay(
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
        HeroIconVariant.Outline -> heroIconPlayOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconPlaySolid16
            HeroIconSize.Md -> heroIconPlaySolid20
            HeroIconSize.Lg -> heroIconPlaySolid24
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
internal const val heroIconPlayOutline24 = """<path fill="none" d="M5.25 5.65273C5.25 4.79705 6.1674 4.25462 6.91716 4.66698L18.4577 11.0143C19.2349 11.4417 19.2349 12.5584 18.4577 12.9858L6.91716 19.3331C6.1674 19.7455 5.25 19.203 5.25 18.3474V5.65273Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconPlaySolid16 = """<path d="M3 3.73241C3 2.54878 4.30673 1.83146 5.30531 2.46692L12.0114 6.73441C12.9376 7.32384 12.9376 8.67597 12.0114 9.2654L5.30532 13.5329C4.30673 14.1684 3 13.451 3 12.2674V3.73241Z" fill="black" />"""

internal const val heroIconPlaySolid20 = """<path d="M6.29995 2.84046C5.3011 2.21075 4 2.92857 4 4.10935V15.8905C4 17.0713 5.3011 17.7891 6.29995 17.1594L15.6436 11.2688C16.577 10.6804 16.577 9.31948 15.6436 8.73103L6.29995 2.84046Z" fill="currentColor" />"""

internal const val heroIconPlaySolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M4.5 5.65257C4.5 4.22644 6.029 3.32239 7.2786 4.00967L18.8192 10.357C20.1144 11.0694 20.1144 12.9304 18.8192 13.6428L7.2786 19.9901C6.029 20.6774 4.5 19.7733 4.5 18.3472V5.65257Z" fill="currentColor" />"""

