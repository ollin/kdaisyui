import type { IconPaths, PascalName } from './parser/svg-heroicons.ts'

// A PascalName starts with a capital, so the result starts with the one hyphen to drop.
function kebabFromPascal(pascal: string): string {
  return pascal.replace(/([A-Z])/g, '-$1').toLowerCase().slice(1)
}

/** One variant's paths as a Kotlin constant, one string per path; '' for a variant not shipped. */
function constVal(valName: string, content: string | null): string {
  // A variant without paths is '' already, as svgPaths returns it.
  if (!content) return ''
  // svgPaths writes every path as `<path ` and one space.
  const parts = [...content.matchAll(/<path [^>]*\/>/g)].map(match => `"""${match[0]}"""`)
  const continued = parts.slice(1).map(part => `${' '.repeat(8)}${part}`)
  return `\ninternal const val ${valName} = ${[parts[0], ...continued].join('+\n')}\n`
}

/** The solid variant's `svgContent` branch, by which smaller sizes the icon ships. */
function solidContentBranch(fnName: string, hasSolid16: boolean, hasSolid20: boolean): string[] {
  if (hasSolid16 && hasSolid20) {
    return ['        HeroIconVariant.Solid -> when (size) {', `            HeroIconSize.Sm -> ${fnName}Solid16`, `            HeroIconSize.Md -> ${fnName}Solid20`, `            HeroIconSize.Lg -> ${fnName}Solid24`, '        }']
  }
  if (hasSolid20) {
    return ['        HeroIconVariant.Solid -> when (size) {', `            HeroIconSize.Md -> ${fnName}Solid20`, `            HeroIconSize.Lg -> ${fnName}Solid24`, `            else -> ${fnName}Solid24`, '        }']
  }
  return [`        HeroIconVariant.Solid -> ${fnName}Solid24`]
}

/** The solid variant's `viewBox` branch, by which smaller sizes the icon ships. */
function solidViewBoxBranch(hasSolid16: boolean, hasSolid20: boolean): string[] {
  if (hasSolid16 && hasSolid20) {
    return ['        HeroIconVariant.Solid -> when (size) {', '            HeroIconSize.Sm -> "0 0 16 16"', '            HeroIconSize.Md -> "0 0 20 20"', '            HeroIconSize.Lg -> "0 0 24 24"', '        }']
  }
  if (hasSolid20) {
    return ['        HeroIconVariant.Solid -> when (size) {', '            HeroIconSize.Md -> "0 0 20 20"', '            else -> "0 0 24 24"', '        }']
  }
  return ['        HeroIconVariant.Solid -> "0 0 24 24"']
}

/**
 * Emit one `heroIcon*` function. `struct` carries a null per variant the icon does not
 * ship, and the `when` branches below read exactly those fields — so the nullability in
 * `IconPaths` is the branch table, not decoration.
 */
export function generateKotlinFile(pascalName: PascalName, struct: IconPaths): string {
  const fnName = `heroIcon${pascalName}`
  const kebabName = kebabFromPascal(pascalName)
  const spacedName = pascalName.replace(/([A-Z])/g, ' $1').trim()
  const constants = [
    constVal(`${fnName}Outline24`, struct.outline24),
    constVal(`${fnName}Solid16`, struct.solid16),
    constVal(`${fnName}Solid20`, struct.solid20),
    constVal(`${fnName}Solid24`, struct.solid24),
  ].join('')
  const hasSolid16 = !!struct.solid16
  const hasSolid20 = !!struct.solid20
  const svgContentBranches = [`        HeroIconVariant.Outline -> ${fnName}Outline24`, ...solidContentBranch(fnName, hasSolid16, hasSolid20)]
  const viewBoxBranches = ['        HeroIconVariant.Outline -> "0 0 24 24"', ...solidViewBoxBranch(hasSolid16, hasSolid20)]
  const svgContentWhen = `val svgContent = when (variant) {\n${svgContentBranches.join('\n')}\n    }`
  const viewBoxWhen = `val viewBox = when (variant) {\n${viewBoxBranches.join('\n')}\n    }`

  return `// GENERATED — DO NOT EDIT
// Source: heroicons/src/{16,20,24}/${kebabName}.svg
// Regenerate: cd codegen && npm run generate:heroicons

package io.github.ollin.kdaisyui.icons

import kotlinx.html.FlowContent
import kotlinx.html.SPAN
import kotlinx.html.span
import kotlinx.html.unsafe

/**
 * ${spacedName} icon from Heroicons.
 * @param variant Icon style — [HeroIconVariant.Outline] (default) or [HeroIconVariant.Solid]
 * @param size Icon size — [HeroIconSize.Sm] (16px), [HeroIconSize.Md] (20px), or [HeroIconSize.Lg] (24px, default)
 * @param extraClasses Additional CSS classes appended to the icon
 */
fun FlowContent.${fnName}(
    variant: HeroIconVariant = HeroIconVariant.Outline,
    size: HeroIconSize = HeroIconSize.Lg,
    extraClasses: String? = null,
) {
    val classes = buildList {
        add(size.className)
        add(variant.className)
        if (extraClasses != null) add(extraClasses)
    }.joinToString(" ")

    ${svgContentWhen}
    ${viewBoxWhen}

    span {
        unsafe {
            raw("""<svg xmlns="http://www.w3.org/2000/svg" viewBox="\$viewBox" class="\$classes" width="\${size.dimension}" height="\${size.dimension}">\$svgContent</svg>""")
        }
    }
}${constants}
`
}
