/**
 * @typedef {Object} ElementRule
 * @property {string} component - Component name (e.g., 'button')
 * @property {string[]} elements - Allowed HTML elements (e.g., ['button', 'a', 'input'])
 * @property {string} primaryElement - Primary element to use
 */

import fs from 'fs'
import path from 'path'

// DaisyUI 5.5.20+ replaced the static llms.txt with per-component skill files
// that its own llms.txt route concatenates at build time; we read them directly.
const COMPONENT_SKILLS_DIR = path.resolve(import.meta.dirname, '../../../daisyui/skills/daisyui/components')

/**
 * @returns {Map<string, ElementRule>}
 */
export function parseLlmsTxt() {
  const files = fs.readdirSync(COMPONENT_SKILLS_DIR)
    .filter((name) => name.endsWith('.md'))
    .sort()

  if (files.length === 0) {
    throw new Error(
      `DaisyUI layout changed: no component skill files found in ${COMPONENT_SKILLS_DIR}. ` +
      `Check the pinned daisyui.version and the codegen path.`
    )
  }

  const content = files
    .map((name) => fs.readFileSync(path.join(COMPONENT_SKILLS_DIR, name), 'utf8'))
    .join('\n')

  return parseLlmsTxtContent(content)
}

function parseLlmsTxtContent(content) {
  const rules = new Map()
  const syntaxBlocks = new Map()
  const lines = content.split('\n')
  let currentComponent = null
  let inSyntaxSection = false
  let inRulesSection = false
  
  for (let i = 0; i < lines.length; i++) {
    const line = lines[i]
    
    if (line.startsWith('### ')) {
      currentComponent = line.slice(4).trim()
      inSyntaxSection = false
      inRulesSection = false
      rules.set(currentComponent, {
        component: currentComponent,
        elements: [],
        primaryElement: null
      })
      syntaxBlocks.set(currentComponent, [])
    } else if (line.startsWith('#### Syntax') && currentComponent) {
      inSyntaxSection = true
      inRulesSection = false
    } else if (line.startsWith('#### Rules') && currentComponent) {
      inSyntaxSection = false
      inRulesSection = true
    } else if (line.startsWith('#### ') && currentComponent) {
      inSyntaxSection = false
      inRulesSection = false
    } else if (inSyntaxSection && currentComponent) {
      if (line.startsWith('```')) {
        continue
      }
      if (line.trim()) {
        syntaxBlocks.get(currentComponent).push(line)
      }
    } else if (inRulesSection && currentComponent) {
      if (line.includes('<') && line.includes('>')) {
        const elements = line.match(/<(\w+)>/g)
        if (elements) {
          const rule = rules.get(currentComponent)
          for (const el of elements) {
            const element = el.slice(1, -1)
            if (!rule.elements.includes(element)) {
              rule.elements.push(element)
            }
          }
        }
      }
    }
  }
  
  for (const [name, rule] of rules) {
    const syntaxBlock = syntaxBlocks.get(name) || []
    const component = findComponentInSyntax(syntaxBlock, name)
    if (component) {
      rule.primaryElement = component.element
      if (!rule.elements.includes(component.element)) {
        rule.elements.push(component.element)
      }
    }
  }
  
  for (const [name, rule] of rules) {
    if (!rule.primaryElement && rule.elements.length > 0) {
      rule.primaryElement = rule.elements[0]
    }
  }
  
  return rules
}

function findComponentInSyntax(syntaxBlock, componentName) {
  for (const line of syntaxBlock) {
    const elementMatch = line.match(/<(\w+)[\s>]/)
    if (elementMatch) {
      const element = elementMatch[1]
      const classMatch = line.match(/class="([^"]+)"/)
      if (classMatch) {
        const classes = classMatch[1].split(/\s+/)
        const baseClass = classes.find(c => c === componentName || c.startsWith(componentName + '-'))
        if (baseClass) {
          return { element, baseClass }
        }
      }
    }
  }
  return null
}

export function getElementForComponent(rules, componentName) {
  const rule = rules.get(componentName)
  if (!rule) return 'div'
  return rule.primaryElement || 'div'
}

export function getAllowedElements(rules, componentName) {
  const rule = rules.get(componentName)
  if (!rule) return ['div']
  return rule.elements.length > 0 ? rule.elements : ['div']
}
