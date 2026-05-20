export function generateKotlinFile(pascalName, struct) {
  const fnName = `heroIcon${pascalName}`

  function kebabFromPascal(pascal) {
    return pascal.replace(/([A-Z])/g, '-$1').toLowerCase().replace(/^-/, '')
  }

  const kebabName = kebabFromPascal(pascalName)
  const spacedName = pascalName.replace(/([A-Z])/g, ' $1').trim()

  function buildConstVal(valName, content, indent) {
    if (!content) return ''
    const pathRegex = /<path\s+[^>]*\/>/g
    const parts = []
    for (const m of content.matchAll(pathRegex)) {
      parts.push(`"""${m[0]}"""`)
    }
    if (parts.length === 0) return ''
    if (parts.length === 1) {
      return `\n${indent}internal const val ${valName} = ${parts[0]}\n`
    }
    return `\n${indent}internal const val ${valName} = ${parts[0]}+\n` +
      parts.slice(1, -1).map(p => `${indent}${' '.repeat(8)}${p}+\n`).join('') +
      `${indent}${' '.repeat(8)}${parts[parts.length - 1]}\n`
  }

  const outline24Const = buildConstVal(`${fnName}Outline24`, struct.outline24, '')
  const solid16Const = buildConstVal(`${fnName}Solid16`, struct.solid16, '')
  const solid20Const = buildConstVal(`${fnName}Solid20`, struct.solid20, '')
  const solid24Const = buildConstVal(`${fnName}Solid24`, struct.solid24, '')

  const hasSolid16 = !!struct.solid16
  const hasSolid20 = !!struct.solid20

  const svgContentBranches = []
  svgContentBranches.push('        HeroIconVariant.Outline -> FNOutline24')
  if (hasSolid16 && hasSolid20) {
    svgContentBranches.push('        HeroIconVariant.Solid -> when (size) {')
    svgContentBranches.push('            HeroIconSize.Sm -> FNSolid16')
    svgContentBranches.push('            HeroIconSize.Md -> FNSolid20')
    svgContentBranches.push('            HeroIconSize.Lg -> FNSolid24')
    svgContentBranches.push('        }')
  } else if (hasSolid20) {
    svgContentBranches.push('        HeroIconVariant.Solid -> when (size) {')
    svgContentBranches.push('            HeroIconSize.Md -> FNSolid20')
    svgContentBranches.push('            HeroIconSize.Lg -> FNSolid24')
    svgContentBranches.push('            else -> FNSolid24')
    svgContentBranches.push('        }')
  } else {
    svgContentBranches.push('        HeroIconVariant.Solid -> FNSolid24')
  }

  const viewBoxBranches = []
  viewBoxBranches.push('        HeroIconVariant.Outline -> "0 0 24 24"')
  if (hasSolid16 && hasSolid20) {
    viewBoxBranches.push('        HeroIconVariant.Solid -> when (size) {')
    viewBoxBranches.push('            HeroIconSize.Sm -> "0 0 16 16"')
    viewBoxBranches.push('            HeroIconSize.Md -> "0 0 20 20"')
    viewBoxBranches.push('            HeroIconSize.Lg -> "0 0 24 24"')
    viewBoxBranches.push('        }')
  } else if (hasSolid20) {
    viewBoxBranches.push('        HeroIconVariant.Solid -> when (size) {')
    viewBoxBranches.push('            HeroIconSize.Md -> "0 0 20 20"')
    viewBoxBranches.push('            else -> "0 0 24 24"')
    viewBoxBranches.push('        }')
  } else {
    viewBoxBranches.push('        HeroIconVariant.Solid -> "0 0 24 24"')
  }

  const svgContentWhen = `val svgContent = when (variant) {\n${svgContentBranches.join('\n')}\n    }`
    .replaceAll('FN', fnName)

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
}${outline24Const}${solid16Const}${solid20Const}${solid24Const}
`
}
