import { toPascalCase as toPascalCaseBase, getBaseClass, getClassesByCategory, getDefaultSize } from './parser/frontmatter.js'

export function toPascalCase(name) {
  return toPascalCaseBase(name)
}

/**
 * @typedef {Object} ClassifiedComponent
 * @property {string} componentName - PascalCase component name (e.g., 'Button')
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
  
  return {
    componentName: toPascalCase(componentName),
    componentClass: toPascalCase(componentClass),
    prefix,
    colors: getClassesByCategory(classnames, 'color').map(stripPrefix(prefix)),
    styles: getClassesByCategory(classnames, 'style').map(stripPrefix(prefix)),
    sizes: getClassesByCategory(classnames, 'size').map(stripPrefix(prefix)),
    modifiers: getClassesByCategory(classnames, 'modifier').map(stripPrefix(prefix)),
    behaviors: getClassesByCategory(classnames, 'behavior').map(stripPrefix(prefix)),
    parts: getClassesByCategory(classnames, 'part'),
    directions: getClassesByCategory(classnames, 'direction').map(stripPrefix(prefix)),
    placements: getClassesByCategory(classnames, 'placement').map(stripPrefix(prefix)),
    defaultSize: getDefaultSize(classnames)
  }
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
