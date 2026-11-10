/**
 * DaisyUI's built-in themes, which `DaisyTheme` offers one entry each.
 *
 * Read from the file names of `packages/daisyui/src/themes`, one CSS file per theme, rather than
 * from `functions/themeOrder.js`: the same 35 names today, without a regex over JavaScript source.
 */

/** The theme names in a listing of DaisyUI's theme directory: the stems of its CSS files, sorted. */
export function themeNames(fileNames: readonly string[]): string[] {
  return fileNames.filter((name) => name.endsWith('.css')).map((name) => name.slice(0, -'.css'.length)).sort()
}
