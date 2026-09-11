import fs from 'fs'
import path from 'path'

/**
 * Two kinds of name live in this file, and both are strings.
 *
 * `arrow-up-circle` addresses a file on disk; `ArrowUpCircle` becomes
 * `heroIconArrowUpCircle` in Kotlin. They are used six times between them and a plain
 * `string` annotation cannot tell them apart — `fileNameToPascalCase(pascalName)` would
 * silently return `ArrowUpCircle` unchanged, and every icon would then be emitted under a
 * name no SVG file matches.
 *
 * Branding them makes that a type error at the call site. The cost is exactly two casts,
 * at the two points where each kind is created, and nothing else in the file changes.
 *
 * This is Object Calisthenics rule 3 — wrap all primitives — applied where it pays rather
 * than everywhere. It was declined in `test-generator.ts`, whose four-string helper has no
 * kinds to separate; the difference is whether the strings mean different things.
 */
export type KebabName = string & { readonly __brand: 'KebabName' }
export type PascalName = string & { readonly __brand: 'PascalName' }

/**
 * One icon's processed SVG path data, per variant and size, `null` where the icon ships no
 * such file. Which fields are null is load-bearing: the generator reads exactly `solid16`
 * and `solid20` to choose a viewBox, so this shape IS the branch table it consults.
 */
export interface IconPaths {
  outline24: string | null
  solid16: string | null
  solid20: string | null
  solid24: string | null
}

/** The four Heroicons source directories, by variant and size. */
export interface IconDirectories {
  outline24Dir: string
  solid16Dir: string
  solid20Dir: string
  solid24Dir: string
}

/** Extracts and rewrites the `<path>` elements of one SVG document. */
type SvgProcessor = (svgContent: string) => string

export function fileNameToPascalCase(kebabName: KebabName): PascalName {
  return kebabName
    .split('-')
    .map((part) => part.charAt(0).toUpperCase() + part.slice(1))
    .join('') as PascalName
}

function processOutlineSvg(svgContent: string): string {
  const innerMatch = svgContent.match(/<svg[^>]*>([\s\S]*)<\/svg>/)
  if (!innerMatch) throw new Error('Failed to parse outline SVG: no <svg> wrapper found')
  const inner = innerMatch[1].trim()

  const pathRegex = /<path\s+([^>]*?)\/>/g
  const paths = []
  for (const match of inner.matchAll(pathRegex)) {
    let attrs = match[1]
    attrs = attrs.replace(/stroke="#0F172A"/g, 'stroke="currentColor"')
    if (!attrs.includes('fill=')) {
      attrs = `fill="none" ${attrs}`
    }
    paths.push(`<path ${attrs} />`)
  }

  return paths.join('')
}

function processSolidSvg(svgContent: string): string {
  const innerMatch = svgContent.match(/<svg[^>]*>([\s\S]*)<\/svg>/)
  if (!innerMatch) throw new Error('Failed to parse solid SVG: no <svg> wrapper found')
  const inner = innerMatch[1].trim()

  const pathRegex = /<path\s+([^>]*?)\/>/g
  const paths = []
  for (const match of inner.matchAll(pathRegex)) {
    let attrs = match[1]
    attrs = attrs.replace(/fill="#0F172A"/g, 'fill="currentColor"')
    paths.push(`<path ${attrs} />`)
  }

  return paths.join('')
}

// One of the two places a KebabName comes into existence: a filename with `.svg` removed.
function readDirNames(dir: string): KebabName[] {
  if (!fs.existsSync(dir)) return []
  return fs
    .readdirSync(dir)
    .filter((f) => f.endsWith('.svg'))
    .map((f) => f.replace('.svg', '') as KebabName)
}

function readSvgIfExists(dir: string, kebabName: KebabName, processor: SvgProcessor): string | null {
  const filePath = path.join(dir, `${kebabName}.svg`)
  if (!fs.existsSync(filePath)) return null
  return processor(fs.readFileSync(filePath, 'utf8'))
}

/**
 * Parse all Heroicons SVG sizes.
 * An icon with neither 24px variant is skipped, because the Kotlin API has no size to
 * fall back to for it.
 */
export function parseIconFiles(directories: IconDirectories): Map<PascalName, IconPaths> {
  const { outline24Dir, solid16Dir, solid20Dir, solid24Dir } = directories

  const allNames = new Set([
    ...readDirNames(outline24Dir),
    ...readDirNames(solid16Dir),
    ...readDirNames(solid20Dir),
    ...readDirNames(solid24Dir),
  ])

  const icons = new Map<PascalName, IconPaths>()

  for (const kebabName of allNames) {
    const pascalName = fileNameToPascalCase(kebabName)

    const outline24 = readSvgIfExists(outline24Dir, kebabName, processOutlineSvg)
    const solid16 = readSvgIfExists(solid16Dir, kebabName, processSolidSvg)
    const solid20 = readSvgIfExists(solid20Dir, kebabName, processSolidSvg)
    const solid24 = readSvgIfExists(solid24Dir, kebabName, processSolidSvg)

    if (!outline24 && !solid24) {
      console.warn(`  ⚠ ${kebabName}: no 24px variant found, skipping`)
      continue
    }

    icons.set(pascalName, { outline24, solid16, solid20, solid24 })
  }

  return icons
}
