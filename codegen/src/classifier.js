import { toPascalCase as toPascalCaseBase, getBaseClass, getClassesByCategory, getEntriesByCategory, getDefaultSize } from './parser/frontmatter.ts'

export function toPascalCase(name) {
  return toPascalCaseBase(name)
}

/**
 * @typedef {Object} ClassifiedComponent
 * @property {string} componentName - PascalCase component name (e.g., 'Button')
 * @property {string} desc - Component description from frontmatter
 * @property {string} prefix - CSS prefix (e.g., 'btn')
 * @property {string[]} colors - Color variants
 * @property {string[]} styles - Style variants
 * @property {string[]} sizes - Size variants
 * @property {string[]} modifiers - Modifiers
 * @property {string[]} behaviors - Behavior classes
 * @property {string[]} parts - Sub-components
 * @property {string[]} directions - Direction variants
 * @property {string[]} placements - Placement variants
 * @property {string|null} defaultSize - Default size class
 * @property {Record<string, string>} descs - Maps stripped class name → description
 */

/**
 * Classify a component from frontmatter data
 * @param {Object} frontmatter - Parsed frontmatter
 * @param {string} componentName - Component name (e.g., 'button')
 * @returns {ClassifiedComponent}
 */
export function classifyFromFrontmatter(frontmatter, componentName) {
  const classnames = frontmatter.classnames
  const prefix = getBaseClass(classnames)
  const componentClass = prefix || componentName

  const descs = buildDescMap(classnames, prefix)

  return {
    componentName: toPascalCase(componentName),
    componentClass: toPascalCase(componentClass),
    desc: frontmatter.desc || '',
    prefix,
    colors: getClassesByCategory(classnames, 'color').map(stripPrefix(prefix)),
    styles: getClassesByCategory(classnames, 'style').map(stripPrefix(prefix)),
    sizes: getClassesByCategory(classnames, 'size').map(stripPrefix(prefix)),
    modifiers: getClassesByCategory(classnames, 'modifier').map(stripPrefix(prefix)),
    behaviors: getClassesByCategory(classnames, 'behavior').map(stripPrefix(prefix)),
    parts: getClassesByCategory(classnames, 'part'),
    directions: getClassesByCategory(classnames, 'direction').map(stripPrefix(prefix)),
    placements: getClassesByCategory(classnames, 'placement').map(stripPrefix(prefix)),
    defaultSize: getDefaultSize(classnames),
    descs,
  }
}

function buildDescMap(classnames, prefix) {
  const descs = {}
  const categories = ['component', 'color', 'style', 'size', 'modifier', 'behavior', 'part', 'direction', 'placement']
  for (const cat of categories) {
    const entries = getEntriesByCategory(classnames, cat)
    for (const entry of entries) {
      if (!entry.desc) continue
      const key = entry.class.startsWith(prefix + '-')
        ? entry.class.slice(prefix.length + 1)
        : entry.class
      descs[key] = entry.desc
    }
  }
  return descs
}

function stripPrefix(prefix) {
  return (className) => {
    if (className.startsWith(prefix + '-')) {
      return className.slice(prefix.length + 1)
    }
    return className
  }
}

export function toCamelCase(suffix) {
  const pascal = toPascalCase(suffix)
  return pascal.charAt(0).toLowerCase() + pascal.slice(1)
}

export function hasVariants(classified) {
  return classified.colors.length > 0 || classified.styles.length > 0
}

export function hasSizes(classified) {
  return classified.sizes.length > 0
}

export function hasModifiers(classified) {
  return classified.modifiers.length > 0 || classified.behaviors.length > 0
}

export function hasParts(classified) {
  return classified.parts.length > 0
}

export function getAllBooleanParams(classified) {
  return [...classified.modifiers, ...classified.behaviors].sort()
}
