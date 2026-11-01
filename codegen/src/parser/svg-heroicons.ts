import fs from 'fs'
import path from 'path'
import { pinnedSubmodule } from '../submodules.ts'

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

/** Every `<path>` inside an SVG, its attributes passed through `rewrite`. */
function svgPaths(svgContent: string, variant: string, rewrite: (attributes: string) => string): string {
  const inner = svgContent.match(/<svg[^>]*>([\s\S]*)<\/svg>/)
  if (!inner) throw new Error(`Failed to parse ${variant} SVG: no <svg> wrapper found`)
  return [...inner[1].trim().matchAll(/<path\s+([^>]*?)\/>/g)].map(match => `<path ${rewrite(match[1])} />`).join('')
}

/** Outline paths draw with the current colour, and fill nothing unless they say otherwise. */
function processOutlineSvg(svgContent: string): string {
  return svgPaths(svgContent, 'outline', attributes => {
    const stroked = attributes.replace(/stroke="#0F172A"/g, 'stroke="currentColor"')
    return stroked.includes('fill=') ? stroked : `fill="none" ${stroked}`
  })
}

/** Solid paths fill with the current colour. */
function processSolidSvg(svgContent: string): string {
  return svgPaths(svgContent, 'solid', attributes => attributes.replace(/fill="#0F172A"/g, 'fill="currentColor"'))
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

/** The four Heroicons directories the generators read, in the pinned submodule. */
export function heroiconDirectories(): IconDirectories {
  const source = path.join(pinnedSubmodule('heroicons'), 'src')
  return {
    outline24Dir: path.join(source, '24', 'outline'),
    solid16Dir: path.join(source, '16', 'solid'),
    solid20Dir: path.join(source, '20', 'solid'),
    solid24Dir: path.join(source, '24', 'solid'),
  }
}
