import fs from 'fs'
import path from 'path'
import { parseIconFiles } from './parser/svg-heroicons.js'

const HEROICONS_SRC_DIR = path.resolve(import.meta.dirname, '../../heroicons/src')
const DEFAULT_OUTPUT_DIR = path.resolve(
  import.meta.dirname,
  '../../lib/src/test/kotlin/io/github/ollin/kdaisyui/icons',
)

function parseOutputDir() {
  for (const arg of process.argv) {
    if (arg.startsWith('--output-dir=')) {
      return arg.slice('--output-dir='.length)
    }
  }
  return DEFAULT_OUTPUT_DIR
}

const OUTPUT_DIR = parseOutputDir()

const directories = {
  outline24Dir: path.join(HEROICONS_SRC_DIR, '24', 'outline'),
  solid16Dir: path.join(HEROICONS_SRC_DIR, '16', 'solid'),
  solid20Dir: path.join(HEROICONS_SRC_DIR, '20', 'solid'),
  solid24Dir: path.join(HEROICONS_SRC_DIR, '24', 'solid'),
}

// The dimension (width == height) emitted for each size is taken from
// HeroIconSize.dimension and is independent of the icon's available variants.
const SIZE_DIMENSION = { Sm: 16, Md: 20, Lg: 24 }

/**
 * Compute the viewBox the function will emit for a Solid render at a given size,
 * mirroring the branch shape produced by generator-heroicons.js:
 *   - icons with solid16 + solid20: Sm -> 16, Md -> 20, Lg -> 24
 *   - icons with solid20 only:      Sm -> else(24), Md -> 20, Lg -> 24
 *   - icons with neither:           any -> 24
 */
function solidViewBox(size, struct) {
  const hasSolid16 = !!struct.solid16
  const hasSolid20 = !!struct.solid20
  if (size === 'Sm') return hasSolid16 ? '0 0 16 16' : '0 0 24 24'
  if (size === 'Md') return hasSolid20 ? '0 0 20 20' : '0 0 24 24'
  return '0 0 24 24'
}

/**
 * The exact opening-<svg>-tag fragment the function renders, up to and including
 * the closing '>' of the opening tag. Asserting this substring pins viewBox, the
 * full class list (size + variant + optional extraClasses), and width/height —
 * which uniquely identifies the selected `when` branch for every icon shape.
 */
function svgOpenFragment(viewBox, classes, dimension) {
  return `viewBox="${viewBox}" class="${classes}" width="${dimension}" height="${dimension}">`
}

function renderAssertion(fnName, callArgs, fragment, label) {
  return `        run {
            val html = createHTML(prettyPrint = false).div { ${fnName}(${callArgs}) }
            assertTrue(
                html.contains(${'"'.repeat(3)}${fragment}${'"'.repeat(3)}),
                "${fnName} ${label}: expected svg open tag not found in: $html",
            )
        }`
}

function generateIconTest(pascalName, struct) {
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
    solidViewBox('Lg', struct),
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

function main() {
  for (const [name, dir] of Object.entries(directories)) {
    if (!fs.existsSync(dir)) {
      console.error(`${name} directory not found: ${dir}`)
      console.error('Ensure the heroicons git submodule is checked out.')
      process.exit(1)
    }
  }

  const icons = parseIconFiles(directories)
  const sorted = [...icons.keys()].sort()

  const methods = sorted
    .map((pascalName) => generateIconTest(pascalName, icons.get(pascalName)))
    .join('\n')

  const kotlin = `// GENERATED — DO NOT EDIT
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

  fs.mkdirSync(OUTPUT_DIR, { recursive: true })
  const outFile = path.join(OUTPUT_DIR, 'HeroIconsGeneratedTest.kt')
  fs.writeFileSync(outFile, kotlin)
  console.log(`Generated render coverage tests for ${sorted.length} heroicons`)
  console.log(`Output: ${outFile}`)
}

main()
