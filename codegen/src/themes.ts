/**
 * DaisyUI's built-in themes, which `DaisyTheme` offers one entry each.
 *
 * Read from the file names of `packages/daisyui/src/themes`, one CSS file per theme, rather than
 * from `functions/themeOrder.js`: the same 35 names today, without a regex over JavaScript source.
 */

/** The theme names in a listing of DaisyUI's theme directory: the stems of its CSS files, sorted. */
export function themeNames(fileNames: readonly string[]): string[] {
  return fileNames.filter((name) => name.endsWith('.css')).map(themeName).sort()
}

const HEADER = [
  '// GENERATED — DO NOT EDIT',
  '// Source: daisyui/packages/daisyui/src/themes/*.css',
  '// Regenerate: cd codegen && npm run generate',
  '',
  'package io.github.ollin.kdaisyui.themes',
  '',
  '/**',
  ' * DaisyUI\'s built-in themes; [value] is what `data-theme` takes. A stylesheet carries only the',
  ' * themes its entry names, and an element given another renders in the default theme.',
  ' */',
]

/** The Kotlin source of `DaisyTheme`, one entry per theme, named by its capitalised name. */
export function renderDaisyThemeEnum(names: readonly string[]): string {
  const entries = names.map((name) => `    ${entryName(name)}("${name}"),`)
  return [...HEADER, 'enum class DaisyTheme(val value: String) {', ...entries, '}', ''].join('\n')
}

/** `kdaisyui-themes.txt`: each `DaisyTheme` entry and its `data-theme` value, for the Gradle plugin. */
export function renderThemeMap(names: readonly string[]): string {
  const header = '# Per theme: its DaisyTheme entry and its data-theme value, tab-separated. Read by the kdaisyui\n# Gradle plugin. Generated - do not edit.\n'
  return header + names.map((name) => `${entryName(name)}\t${name}\n`).join('')
}

/** A theme's `DaisyTheme` entry: its name, capitalised. */
function entryName(name: string): string {
  return `${name[0].toUpperCase()}${name.slice(1)}`
}

function themeName(fileName: string): string {
  const name = fileName.slice(0, -'.css'.length)
  if (/^[a-z]+$/.test(name)) return name
  throw new Error(`DaisyTheme cannot name ${fileName}: a theme name must be lowercase letters only`)
}
