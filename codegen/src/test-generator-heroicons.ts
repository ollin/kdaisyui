import type { IconPaths, PascalName } from './parser/svg-heroicons.ts'

// The dimension (width == height) emitted for each size is taken from
// HeroIconSize.dimension and is independent of the icon's available variants.
const SIZE_DIMENSION = { Sm: 16, Md: 20, Lg: 24 }

/** The three sizes `HeroIconSize` declares. Derived so the two cannot drift apart. */
type IconSize = keyof typeof SIZE_DIMENSION

// `IconPaths` is imported rather than declared here: it describes what the parser PRODUCES,
// so the parser is where it belongs. It was briefly duplicated in task 1.3, which is how a
// shape drifts — two declarations, one of them eventually stale.

/**
 * Compute the viewBox the function will emit for a Solid render at a smaller size, Lg being 24
 * for every icon,
 * mirroring the branch shape produced by generator-heroicons.js:
 *   - icons with solid16 + solid20: Sm -> 16, Md -> 20, Lg -> 24
 *   - icons with solid20 only:      Sm -> else(24), Md -> 20, Lg -> 24
 *   - icons with neither:           any -> 24
 */
function solidViewBox(size: Exclude<IconSize, 'Lg'>, struct: IconPaths): string {
  if (size === 'Sm') return struct.solid16 ? '0 0 16 16' : '0 0 24 24'
  return struct.solid20 ? '0 0 20 20' : '0 0 24 24'
}

/**
 * The exact opening-<svg>-tag fragment the function renders, up to and including
 * the closing '>' of the opening tag. Asserting this substring pins viewBox, the
 * full class list (size + variant + optional extraClasses), and width/height —
 * which uniquely identifies the selected `when` branch for every icon shape.
 */
function svgOpenFragment(viewBox: string, classes: string, dimension: number): string {
  return `viewBox="${viewBox}" class="${classes}" width="${dimension}" height="${dimension}">`
}

// Four strings in a fixed order, and the types say so and nothing more. Swapping `fragment`
// and `label` type-checks perfectly and produces nonsense — the same shape of defect that
// motivated this change (a kotlinx.html builder name passed where an HTML tag name was
// meant). Plain annotations do NOT catch it; only branded types would, at a cost this
// change has not taken on. Recorded rather than papered over.
function renderAssertion(fnName: string, callArgs: string, fragment: string, label: string): string {
  return `        run {
            val html = createHTML(prettyPrint = false).div { ${fnName}(${callArgs}) }
            assertTrue(
                html.contains(${'"'.repeat(3)}${fragment}${'"'.repeat(3)}),
                "${fnName} ${label}: expected svg open tag not found in: $html",
            )
        }`
}

function generateIconTest(pascalName: string, struct: IconPaths): string {
  const fnName = `heroIcon${pascalName}`
  const outlineFragment = svgOpenFragment(
    '0 0 24 24',
    'heroicon-lg heroicon-outline',
    SIZE_DIMENSION.Lg,
  )
  const smFragment = svgOpenFragment(
    solidViewBox('Sm', struct),
    'heroicon-sm heroicon-solid',
    SIZE_DIMENSION.Sm,
  )
  const mdFragment = svgOpenFragment(
    solidViewBox('Md', struct),
    'heroicon-md heroicon-solid',
    SIZE_DIMENSION.Md,
  )
  // The Lg render also exercises the `if (extraClasses != null)` true branch and
  // asserts the extra class is appended after the size + variant classes.
  const lgExtraFragment = svgOpenFragment(
    // Every icon ships a 24px solid variant or falls back to it.
    '0 0 24 24',
    'heroicon-lg heroicon-solid custom-extra',
    SIZE_DIMENSION.Lg,
  )

  const body = [
    renderAssertion(fnName, '', outlineFragment, 'Outline default'),
    renderAssertion(
      fnName,
      'variant = HeroIconVariant.Solid, size = HeroIconSize.Sm',
      smFragment,
      'Solid Sm',
    ),
    renderAssertion(
      fnName,
      'variant = HeroIconVariant.Solid, size = HeroIconSize.Md',
      mdFragment,
      'Solid Md',
    ),
    renderAssertion(
      fnName,
      'variant = HeroIconVariant.Solid, size = HeroIconSize.Lg, extraClasses = "custom-extra"',
      lgExtraFragment,
      'Solid Lg + extraClasses',
    ),
  ].join('\n')

  return `    @Test
    fun ${fnName}_rendersAllVariantSizePaths() {
${body}
    }
`
}

/** The whole `HeroIconsGeneratedTest.kt` for the parsed icons, in name order. A pure function. */
function heroIconsTestFile(icons: ReadonlyMap<PascalName, IconPaths>): string {
  const methods = [...icons.keys()]
    .sort()
    // `!` because the key came from `icons.keys()` — present by construction.
    .map((pascalName) => generateIconTest(pascalName, icons.get(pascalName)!))
    .join('\n')

  return `// GENERATED — DO NOT EDIT
// Exhaustive render coverage for every generated heroIcon* function.
// Regenerate: cd codegen && npm run generate:heroicon-tests

package io.github.ollin.kdaisyui.icons

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertTrue

class HeroIconsGeneratedTest {
${methods}}
`
}

export { heroIconsTestFile }
