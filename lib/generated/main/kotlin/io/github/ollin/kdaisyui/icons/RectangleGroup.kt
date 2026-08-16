// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/rectangle-group.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * Rectangle Group icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.heroIconRectangleGroup(
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
        HeroIconVariant.Outline -> heroIconRectangleGroupOutline24
        HeroIconVariant.Solid -> when (size) {
            HeroIconSize.Sm -> heroIconRectangleGroupSolid16
            HeroIconSize.Md -> heroIconRectangleGroupSolid20
            HeroIconSize.Lg -> heroIconRectangleGroupSolid24
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
internal const val heroIconRectangleGroupOutline24 = """<path fill="none" d="M2.25 7.125C2.25 6.50368 2.75368 6 3.375 6H9.375C9.99632 6 10.5 6.50368 10.5 7.125V10.875C10.5 11.4963 9.99632 12 9.375 12H3.375C2.75368 12 2.25 11.4963 2.25 10.875V7.125Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""+
        """<path fill="none" d="M14.25 8.625C14.25 8.00368 14.7537 7.5 15.375 7.5H20.625C21.2463 7.5 21.75 8.00368 21.75 8.625V16.875C21.75 17.4963 21.2463 18 20.625 18H15.375C14.7537 18 14.25 17.4963 14.25 16.875V8.625Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""+
        """<path fill="none" d="M3.75 16.125C3.75 15.5037 4.25368 15 4.875 15H10.125C10.7463 15 11.25 15.5037 11.25 16.125V18.375C11.25 18.9963 10.7463 19.5 10.125 19.5H4.875C4.25368 19.5 3.75 18.9963 3.75 18.375V16.125Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />"""

internal const val heroIconRectangleGroupSolid16 = """<path d="M1 4C1 3.44772 1.44772 3 2 3H7C7.55228 3 8 3.44772 8 4V7C8 7.55228 7.55228 8 7 8H2C1.44772 8 1 7.55228 1 7V4Z" fill="black" />"""+
        """<path d="M10 5C10 4.44772 10.4477 4 11 4H14C14.5523 4 15 4.44772 15 5V11C15 11.5523 14.5523 12 14 12H11C10.4477 12 10 11.5523 10 11V5Z" fill="black" />"""+
        """<path d="M4 10C3.44772 10 3 10.4477 3 11V12C3 12.5523 3.44772 13 4 13H8C8.55228 13 9 12.5523 9 12V11C9 10.4477 8.55228 10 8 10H4Z" fill="black" />"""

internal const val heroIconRectangleGroupSolid20 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M2.5 3C1.67157 3 1 3.67157 1 4.5V8.5C1 9.32843 1.67157 10 2.5 10H8.5C9.32843 10 10 9.32843 10 8.5V4.5C10 3.67157 9.32843 3 8.5 3H2.5ZM13.5 5C12.6716 5 12 5.67157 12 6.5V13.5C12 14.3284 12.6716 15 13.5 15H17.5C18.3284 15 19 14.3284 19 13.5V6.5C19 5.67157 18.3284 5 17.5 5H13.5ZM3.5 12C2.67157 12 2 12.6716 2 13.5V15.5C2 16.3284 2.67157 17 3.5 17H9.5C10.3284 17 11 16.3284 11 15.5V13.5C11 12.6716 10.3284 12 9.5 12H3.5Z" fill="currentColor" />"""

internal const val heroIconRectangleGroupSolid24 = """<path fill-rule="evenodd" clip-rule="evenodd" d="M1.5 7.125C1.5 6.08947 2.33947 5.25 3.375 5.25H9.375C10.4105 5.25 11.25 6.08947 11.25 7.125V10.875C11.25 11.9105 10.4105 12.75 9.375 12.75H3.375C2.33947 12.75 1.5 11.9105 1.5 10.875V7.125ZM13.5 8.625C13.5 7.58947 14.3395 6.75 15.375 6.75H20.625C21.6605 6.75 22.5 7.58947 22.5 8.625V16.875C22.5 17.9105 21.6605 18.75 20.625 18.75H15.375C14.3395 18.75 13.5 17.9105 13.5 16.875V8.625ZM3 16.125C3 15.0895 3.83947 14.25 4.875 14.25H10.125C11.1605 14.25 12 15.0895 12 16.125V18.375C12 19.4105 11.1605 20.25 10.125 20.25H4.875C3.83947 20.25 3 19.4105 3 18.375V16.125Z" fill="currentColor" />"""

