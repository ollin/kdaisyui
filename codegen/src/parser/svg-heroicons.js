import fs from 'fs'
import path from 'path'

export function fileNameToPascalCase(kebabName) {
  return kebabName
    .split('-')
    .map(part => part.charAt(0).toUpperCase() + part.slice(1))
    .join('')
}

function processOutlineSvg(svgContent) {
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

function processSolidSvg(svgContent) {
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

function readDirNames(dir) {
  if (!fs.existsSync(dir)) return []
  return fs.readdirSync(dir).filter(f => f.endsWith('.svg')).map(f => f.replace('.svg', ''))
}

function readSvgIfExists(dir, kebabName, processor) {
  const filePath = path.join(dir, `${kebabName}.svg`)
  if (!fs.existsSync(filePath)) return null
  return processor(fs.readFileSync(filePath, 'utf8'))
}

/**
 * Parse all Heroicons SVG sizes.
 * Returns Map<pascalName, { outline24, solid16, solid20, solid24 }>
 * Keys may be null if that size/variant doesn't exist for the icon.
 */
export function parseIconFiles(directories) {
  const { outline24Dir, solid16Dir, solid20Dir, solid24Dir } = directories

  const allNames = new Set([
    ...readDirNames(outline24Dir),
    ...readDirNames(solid16Dir),
    ...readDirNames(solid20Dir),
    ...readDirNames(solid24Dir),
  ])

  const icons = new Map()

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
