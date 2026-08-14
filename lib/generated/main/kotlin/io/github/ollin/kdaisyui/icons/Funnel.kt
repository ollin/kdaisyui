// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/funnel.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Funnel icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconFunnel(
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
        HeroIconVariant.Outline -> heroIconFunnelOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconFunnelSolid16
            HeroIconSize.Md -> heroIconFunnelSolid20
            HeroIconSize.Lg -> heroIconFunnelSolid24
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
internal const val heroIconFunnelOutline24 = """<path fill="none" d="M12.0001 3C14.7548 3 17.4552 3.23205 20.0831 3.67767C20.6159 3.76803 21 4.23355 21 4.77402V5.81802C21 6.41476 20.7629 6.98705 20.341 7.40901L14.909 12.841C14.4871 13.2629 14.25 13.8352 14.25 14.432V17.3594C14.25 18.2117 13.7685 18.9908 13.0062 19.3719L9.75 21V14.432C9.75 13.8352 9.51295 13.2629 9.09099 12.841L3.65901 7.40901C3.23705 6.98705 3 6.41476 3 5.81802V4.77404C3 4.23357 3.38408 3.76805 3.91694 3.67769C6.54479 3.23206 9.24533 3 12.0001 3Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconFunnelSolid16 = """<path d="M14 2C14 1.44772 13.5523 1 13 1H3C2.44772 1 2 1.44772 2 2V4.17157C2 4.70201 2.21071 5.21071 2.58579 5.58579L5.41421 8.41421C5.78929 8.78929 6 9.29799 6 9.82843V14.191C6 14.5627 6.39116 14.8044 6.72361 14.6382L8.89443 13.5528C9.572 13.214 10 12.5215 10 11.7639V9.82843C10 9.29799 10.2107 8.78929 10.5858 8.41421L13.4142 5.58579C13.7893 5.21071 14 4.70201 14 4.17157V2Z" fill="black" />"""

internal const val heroIconFunnelSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2.62803 1.60102C5.02718 1.2056 7.48986 1 10 1C12.5101 1 14.9728 1.2056 17.372 1.60102C17.7342 1.66072 18 1.97389 18 2.34103V4.62868C18 5.22542 17.7629 5.79771 17.341 6.21967L12.659 10.9017C12.2371 11.3236 12 11.8959 12 12.4926V15.5291C12 16.2126 11.6893 16.859 11.1556 17.286L9.21852 18.8357C8.99339 19.0158 8.68496 19.0509 8.42511 18.926C8.16526 18.8011 8 18.5383 8 18.25V12.4926C8 11.8959 7.76295 11.3236 7.34099 10.9017L2.65901 6.21967C2.23705 5.79771 2 5.22542 2 4.62868V2.34103C2 1.97389 2.26578 1.66072 2.62803 1.60102Z" fill="currentColor" />"""

internal const val heroIconFunnelSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M3.79154 2.93825C6.46066 2.48562 9.20314 2.25 12.0001 2.25C14.7969 2.25 17.5394 2.48561 20.2085 2.93822C21.1108 3.09125 21.75 3.87676 21.75 4.77402V5.81802C21.75 6.61367 21.4339 7.37673 20.8713 7.93934L14.6893 14.1213C14.408 14.4026 14.25 14.7842 14.25 15.182V18.1094C14.25 19.2457 13.608 20.2845 12.5916 20.7927L10.8354 21.6708C10.6029 21.7871 10.3268 21.7746 10.1057 21.638C9.88459 21.5013 9.75 21.2599 9.75 21V15.182C9.75 14.7842 9.59196 14.4026 9.31066 14.1213L3.12868 7.93934C2.56607 7.37673 2.25 6.61367 2.25 5.81802V4.77404C2.25 3.87678 2.88917 3.09127 3.79154 2.93825Z" fill="currentColor" />"""

