// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/slash.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Slash icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconSlash(
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
        HeroIconVariant.Outline -> heroIconSlashOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconSlashSolid16
            HeroIconSize.Md -> heroIconSlashSolid20
            HeroIconSize.Lg -> heroIconSlashSolid24
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
internal const val heroIconSlashOutline24 = """<path fill="none" d="M9 20.2475L15 3.74707" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconSlashSolid16 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M10.0741 2.04731C10.4634 2.18887 10.6642 2.61919 10.5227 3.00846L6.70505 13.5069C6.56349 13.8962 6.13317 14.097 5.7439 13.9555C5.35462 13.8139 5.1538 13.3836 5.29536 12.9943L9.11299 2.49585C9.25454 2.10657 9.68486 1.90576 10.0741 2.04731Z" fill="black" />"""

internal const val heroIconSlashSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5284 3.04731C12.9177 3.18887 13.1185 3.61919 12.977 4.00846L8.43308 16.5042C8.29152 16.8935 7.8612 17.0943 7.47192 16.9527C7.08265 16.8112 6.88183 16.3809 7.02339 15.9916L11.5673 3.49585C11.7088 3.10657 12.1392 2.90576 12.5284 3.04731Z" fill="black" />"""

internal const val heroIconSlashSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M15.256 3.04243C15.6453 3.18399 15.8461 3.61434 15.7046 4.00364L9.7046 20.504C9.56304 20.8933 9.13271 21.0942 8.74342 20.9526C8.35414 20.811 8.15331 20.3807 8.29487 19.9914L14.2948 3.49099C14.4364 3.1017 14.8667 2.90087 15.256 3.04243Z" fill="black" />"""

