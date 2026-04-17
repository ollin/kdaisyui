/**
 * @typedef {Object} ClassEntry
 * @property {string} class - CSS class name (e.g., 'btn-primary')
 * @property {string} desc - Description
 * @property {boolean} [default] - Whether this is the default (for sizes)
 */

/**
 * @typedef {Object} Classnames
 * @property {ClassEntry[]} [component] - Base class
 * @property {ClassEntry[]} [part] - Sub-components
 * @property {ClassEntry[]} [style] - Style variants
 * @property {ClassEntry[]} [color] - Color variants
 * @property {ClassEntry[]} [size] - Size variants
 * @property {ClassEntry[]} [modifier] - Modifiers
 * @property {ClassEntry[]} [behavior] - Behavior classes
 * @property {ClassEntry[]} [direction] - Direction variants
 * @property {ClassEntry[]} [placement] - Placement variants
 */

/**
 * @typedef {Object} FrontmatterData
 * @property {string} title - Component title
 * @property {string} desc - Component description
 * @property {string} source - CSS source URL
 * @property {Classnames} classnames - Class categories
 */

export function parseFrontmatter(content) {
  const match = content.match(/^---\n([\s\S]*?)\n---/)
  if (!match) return null
  return parseYamlFrontmatter(match[1])
}

function parseYamlFrontmatter(yaml) {
  const result = {}
  const lines = yaml.split('\n')
  let currentKey = null
  let currentCategory = null
  let currentObject = null
  
  for (const line of lines) {
    const trimmed = line.trim()
    if (!trimmed) continue
    
    const lineIndent = line.search(/\S/)
    const isTopLevel = lineIndent === 0
    const isSecondLevel = lineIndent === 2
    const isThirdLevel = lineIndent === 4
    
    if (isTopLevel) {
      const colonIndex = trimmed.indexOf(':')
      if (colonIndex === -1) continue
      
      const key = trimmed.slice(0, colonIndex).trim()
      const value = trimmed.slice(colonIndex + 1).trim()
      
      if (value) {
        result[key] = parseValue(value)
        currentKey = null
        currentCategory = null
      } else {
        currentKey = key
        if (key === 'classnames') {
          result[key] = {}
        } else {
          result[key] = []
        }
      }
    } else if (isSecondLevel && currentKey === 'classnames') {
      if (trimmed.startsWith('- ')) {
        const itemText = trimmed.slice(2).trim()
        if (itemText.includes(':')) {
          const obj = {}
          const colonIndex = itemText.indexOf(':')
          const key = itemText.slice(0, colonIndex).trim()
          const value = itemText.slice(colonIndex + 1).trim()
          obj[key] = parseValue(value)
          if (currentCategory && result.classnames[currentCategory]) {
            result.classnames[currentCategory].push(obj)
            currentObject = obj
          }
        } else {
          if (currentCategory && result.classnames[currentCategory]) {
            result.classnames[currentCategory].push({ class: parseValue(itemText) })
            currentObject = null
          }
        }
      } else {
        const colonIndex = trimmed.indexOf(':')
        if (colonIndex === -1) continue
        const key = trimmed.slice(0, colonIndex).trim()
        result.classnames[key] = []
        currentCategory = key
        currentObject = null
      }
    } else if (isThirdLevel && currentKey === 'classnames') {
      if (trimmed.startsWith('- ')) {
        const itemText = trimmed.slice(2).trim()
        if (itemText.includes(':')) {
          const obj = {}
          const colonIndex = itemText.indexOf(':')
          const key = itemText.slice(0, colonIndex).trim()
          const value = itemText.slice(colonIndex + 1).trim()
          obj[key] = parseValue(value)
          if (currentCategory && result.classnames[currentCategory]) {
            result.classnames[currentCategory].push(obj)
            currentObject = obj
          }
        } else {
          if (currentCategory && result.classnames[currentCategory]) {
            result.classnames[currentCategory].push({ class: parseValue(itemText) })
            currentObject = null
          }
        }
      } else if (trimmed.includes(':') && currentObject) {
        const colonIndex = trimmed.indexOf(':')
        const key = trimmed.slice(0, colonIndex).trim()
        const value = trimmed.slice(colonIndex + 1).trim()
        currentObject[key] = parseValue(value)
      }
    } else if (isThirdLevel && currentObject) {
      const colonIndex = trimmed.indexOf(':')
      if (colonIndex !== -1) {
        const key = trimmed.slice(0, colonIndex).trim()
        const value = trimmed.slice(colonIndex + 1).trim()
        currentObject[key] = parseValue(value)
      }
    } else if (isSecondLevel && Array.isArray(result[currentKey])) {
      if (trimmed.startsWith('- ')) {
        result[currentKey].push(parseValue(trimmed.slice(2).trim()))
      }
    }
  }
  
  return result
}

function parseValue(value) {
  if (!value) return ''
  if ((value.startsWith("'") && value.endsWith("'")) ||
      (value.startsWith('"') && value.endsWith('"'))) {
    return value.slice(1, -1)
  }
  if (value === 'true') return true
  if (value === 'false') return false
  const num = Number(value)
  if (!isNaN(num) && value.trim() !== '') return num
  return value
}

/**
 * Extract component name from directory path
 * @param {string} dirPath - e.g., '/path/to/components/button'
 * @returns {string} - e.g., 'button'
 */
export function getComponentName(dirPath) {
  return dirPath.split('/').pop()
}

/**
 * Convert component name to PascalCase
 * @param {string} name - e.g., 'button'
 * @returns {string} - e.g., 'Button'
 */
export function toPascalCase(name) {
  return name.split('-').map(p => p.charAt(0).toUpperCase() + p.slice(1)).join('')
}

export function getBaseClass(classnames) {
  if (!classnames?.component?.length) return null
  return classnames.component[0].class.replace(/^['"]|['"]$/g, '')
}

/**
 * Get classes by category
 * @param {Classnames} classnames
 * @param {string} category
 * @returns {string[]}
 */
export function getClassesByCategory(classnames, category) {
  if (!classnames?.[category]) return []
  return classnames[category].map(entry => {
    const cls = entry.class
    return cls.replace(/^['"]|['"]$/g, '')
  })
}

export function getDefaultSize(classnames) {
  if (!classnames?.size) return null
  const defaultEntry = classnames.size.find(entry => entry.default === true)
  if (!defaultEntry) return null
  return defaultEntry.class.replace(/^['"]|['"]$/g, '')
}

import fs from 'fs'
import path from 'path'

const DAISYUI_DOCS_PATH = path.resolve(import.meta.dirname, '../../../daisyui/packages/docs')
const COMPONENTS_PATH = path.join(DAISYUI_DOCS_PATH, 'src/routes/(routes)/components')

export function getAllComponentDirs() {
  return fs.readdirSync(COMPONENTS_PATH)
    .filter(f => {
      const stat = fs.statSync(path.join(COMPONENTS_PATH, f))
      return stat.isDirectory() && fs.existsSync(path.join(COMPONENTS_PATH, f, '+page.md'))
    })
    .sort()
}

export function readComponentFrontmatter(componentName) {
  const filePath = path.join(COMPONENTS_PATH, componentName, '+page.md')
  if (!fs.existsSync(filePath)) return null
  return parseFrontmatter(fs.readFileSync(filePath, 'utf8'))
}
