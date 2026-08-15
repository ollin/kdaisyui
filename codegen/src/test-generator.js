import fs from 'fs'
import path from 'path'
import { getAllComponentDirs, readComponentFrontmatter, toPascalCase } from './parser/frontmatter.js'
import { toCamelCase } from './classifier.js'

const DOCS_DIR = path.resolve(import.meta.dirname, '../../daisyui/packages/docs/src/routes/(routes)/components')
// Committed generated root — a sibling of lib/src/, never inside it.
// Gradle passes --output-dir explicitly; this default is for a bare `node` run.
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../lib/generated/test/kotlin/io/github/ollin/kdaisyui/components')

function parseOutputDir() {
  for (const arg of process.argv) {
    if (arg.startsWith('--output-dir=')) {
      return arg.slice('--output-dir='.length)
    }
  }
  return DEFAULT_OUTPUT_DIR
}

const OUTPUT_DIR = parseOutputDir()
const CONFIG_PATH = path.resolve(import.meta.dirname, '../codegen-config.json')

function loadConfig() {
  if (!fs.existsSync(CONFIG_PATH)) {
    return { skip: [] }
  }
  return JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf8'))
}

function parseTestCases(content) {
  const testCases = []
  const lines = content.split('\n')
  
  let currentTest = null
  let inCodeBlock = false
  let codeBlock = []
  let codeBlockLang = null
  
  for (let i = 0; i < lines.length; i++) {
    const line = lines[i]
    
    const testMatch = line.match(/^### ~(.+)$/)
    if (testMatch) {
      if (currentTest && codeBlock.length > 0) {
        currentTest.html = codeBlock.join('\n')
        testCases.push(currentTest)
      }
      
      currentTest = {
        name: testMatch[1].trim(),
        html: null
      }
      codeBlock = []
      continue
    }
    
    if (line.startsWith('```')) {
      if (!inCodeBlock) {
        inCodeBlock = true
        codeBlockLang = line.slice(3).trim()
        codeBlock = []
      } else {
        inCodeBlock = false
        if (codeBlockLang === 'html' && currentTest) {
          currentTest.html = codeBlock.join('\n')
          testCases.push(currentTest)
          currentTest = null
        }
        codeBlock = []
      }
      continue
    }
    
    if (inCodeBlock) {
      codeBlock.push(line)
    }
  }
  
  if (currentTest && codeBlock.length > 0 && codeBlockLang === 'html') {
    currentTest.html = codeBlock.join('\n')
    testCases.push(currentTest)
  }
  
  return testCases
}

function extractDaisyClasses(html) {
  const classes = []
  const match = html.match(/\$\$([a-z-]+)/g)
  if (match) {
    for (const m of match) {
      classes.push(m.slice(2))
    }
  }
  return [...new Set(classes)]
}

function toClassName(componentName) {
  return toPascalCase(componentName)
}

function buildClassMappings(frontmatter, componentName) {
  const allowedClasses = new Set()
  const classToParam = {}
  const paramToGeneratedClass = {}
  
  const componentClass = frontmatter.classnames?.component?.[0]?.class
  if (componentClass) {
    allowedClasses.add(componentClass)
  }
  
  const categories = ['placement', 'modifier', 'direction', 'behavior', 'style']
  for (const cat of categories) {
    const items = frontmatter.classnames?.[cat]
    if (items && Array.isArray(items)) {
      for (const item of items) {
        if (item.class) {
          allowedClasses.add(item.class)
          const suffix = item.class.replace(`${componentClass}-`, '')
          const paramName = toCamelCase(suffix)
          classToParam[item.class] = paramName
          paramToGeneratedClass[paramName] = item.class
        }
      }
    }
  }
  
  return { allowedClasses, classToParam, paramToGeneratedClass, componentClass }
}

function filterContainerClasses(classes, allowedClasses) {
  return classes.filter(c => allowedClasses.has(c))
}

function toTestFunctionName(name) {
  let funcName = name
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, '_')
    .replace(/^_|_$/g, '')
    .replace(/_+/g, '_')
  
  if (/^[0-9]/.test(funcName)) {
    funcName = `test_${funcName}`
  }
  
  return funcName
}

function mapClassesToParams(classes, classToParam) {
  const params = {}
  for (const cls of classes) {
    if (classToParam[cls]) {
      params[classToParam[cls]] = true
    }
  }
  return params
}

function generateCustomPartTests(className, customParts) {
  if (!customParts || customParts.length === 0) return ''

  let kotlin = ''
  for (const part of customParts) {
    const funcName = `custom_part_${part.name.toLowerCase()}_renders_${part.element.toLowerCase()}`
    const tag = part.element.toLowerCase()
    const receiver = part.receiver || 'FlowContent'
    
    // Determine wrapping context based on receiver type
    const wrapperTag = receiver === 'FlowContent' ? 'div' : receiver.toLowerCase()
    const wrapperFn = htmlTagFnFor(wrapperTag)
    
    if (part.cssClass) {
      kotlin += `
    @Test
    fun ${funcName}() {
        val html = createHTML(prettyPrint = false).${wrapperFn} {
            daisy${className}${part.name} {
            }
        }
        assertTrue(html.contains("<${tag}"))
        assertTrue(html.contains("class=\\"${part.cssClass}"))
    }
`
    } else {
      kotlin += `
    @Test
    fun ${funcName}() {
        val html = createHTML(prettyPrint = false).${wrapperFn} {
            daisy${className}${part.name} {
            }
        }
        assertTrue(html.contains("<${tag}"))
    }
`
    }
  }
  return kotlin
}

function htmlTagFnFor(tag) {
  const exceptions = { fieldset: 'fieldSet', textarea: 'textArea' }
  return exceptions[tag] ?? tag
}

function generateKotlinTest(componentName, testCases, frontmatter, config) {
  const className = toClassName(componentName)
  const { allowedClasses, classToParam, paramToGeneratedClass, componentClass } = buildClassMappings(frontmatter, componentName)
  const customParts = config?.customParts?.[componentName] || []
  const hasCustomParts = customParts.length > 0
  
  const extraImports = new Set()
  if (hasCustomParts) {
    extraImports.add('import kotlin.test.assertTrue')
    for (const part of customParts) {
      const receiver = part.receiver || 'FlowContent'
      if (receiver !== 'FlowContent') {
        extraImports.add(`import kotlinx.html.${htmlTagFnFor(receiver.toLowerCase())}`)
      }
    }
  }
  const extraImportLines = [...extraImports].sort().join('\n')
  
  let kotlin = `package io.github.ollin.kdaisyui.components

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlin.test.Test
import kotlin.test.assertEquals${extraImportLines ? '\n' + extraImportLines : ''}

class ${className}Test {
`
  
  const usedNames = new Set()
  
  for (const tc of testCases) {
    let funcName = toTestFunctionName(tc.name)
    
    let uniqueName = funcName
    let counter = 2
    while (usedNames.has(uniqueName)) {
      uniqueName = `${funcName}_${counter}`
      counter++
    }
    usedNames.add(uniqueName)
    
    const allClasses = extractDaisyClasses(tc.html)
    const containerClasses = filterContainerClasses(allClasses, allowedClasses)
    const params = mapClassesToParams(containerClasses, classToParam)
    
    const paramStr = Object.entries(params)
      .filter(([k, v]) => v === true)
      .map(([k, v]) => `${k} = true`)
      .join(', ')
    
    const generatedClasses = [componentClass]
    for (const c of containerClasses) {
      if (c !== componentClass) {
        if (c.startsWith(`${componentClass}-`)) {
          generatedClasses.push(c)
        } else {
          generatedClasses.push(`${componentClass}-${c}`)
        }
      }
    }
    const expectedClasses = generatedClasses.sort().join(' ')
    
    kotlin += `
    @Test
    fun ${uniqueName}() {
        val html = createHTML(prettyPrint = false).div {
            daisy${className}(${paramStr}) {
            }
        }
        val expectedClasses = "${expectedClasses}"
        val actualClasses = html.substringAfter("class=\\"").substringBefore("\\"").split(" ").sorted().joinToString(" ")
        assertEquals(expectedClasses, actualClasses, "Class mismatch for ${tc.name}")
    }
`
  }
  
  kotlin += generateCustomPartTests(className, customParts)
  
  kotlin += `}
`
  
  return kotlin
}

function generateForComponent(componentName, config) {
  const pageFile = path.join(DOCS_DIR, componentName, '+page.md')
  
  if (!fs.existsSync(pageFile)) {
    return { success: false, error: 'File not found' }
  }
  
  const frontmatter = readComponentFrontmatter(componentName)
  if (!frontmatter) {
    return { success: false, error: 'No frontmatter' }
  }
  
  const content = fs.readFileSync(pageFile, 'utf8')
  const testCases = parseTestCases(content)
  
  if (testCases.length === 0) {
    return { success: false, error: 'No test cases' }
  }
  
  const kotlin = generateKotlinTest(componentName, testCases, frontmatter, config)
  const className = toClassName(componentName)
  const outFile = path.join(OUTPUT_DIR, `${className}Test.kt`)
  
  fs.writeFileSync(outFile, kotlin)
  return { success: true, testCount: testCases.length }
}

function main() {
  const args = process.argv.slice(2)
  const mode = args[0]
  const config = loadConfig()
  
  if (mode === 'all') {
    console.log('Generating tests for all components...\n')
    
    const componentDirs = getAllComponentDirs()
    let generated = 0
    let skipped = 0
    
    for (const componentName of componentDirs) {
      if (config.skip?.includes(componentName)) {
        console.log(`  ⊘ ${componentName}: Skipped (alias)`)
        skipped++
        continue
      }
      
      const result = generateForComponent(componentName, config)
      
      if (result.success) {
        console.log(`  ✓ ${componentName}: ${result.testCount} tests`)
        generated++
      } else {
        console.log(`  ⊘ ${componentName}: ${result.error}`)
        skipped++
      }
    }
    
    console.log(`\nGenerated tests for ${generated} components, skipped ${skipped}`)
  } else if (mode) {
    if (config.skip?.includes(mode)) {
      console.error(`Error: ${mode} is skipped (alias)`)
      process.exit(1)
    }
    
    const result = generateForComponent(mode, config)
    
    if (result.success) {
      console.log(`Generated ${result.testCount} tests for ${mode}`)
    } else {
      console.error(`Error: ${result.error}`)
      process.exit(1)
    }
  } else {
    console.log('Usage: node test-generator.js <component-name|all>')
    console.log('Examples:')
    console.log('  node test-generator.js dropdown')
    console.log('  node test-generator.js all')
    process.exit(1)
  }
}

main()
