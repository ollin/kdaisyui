import fs from 'fs'
import path from 'path'
import { getAllComponentDirs, readComponentFrontmatter } from './parser/frontmatter.js'
import { parseLlmsTxt, getElementForComponent } from './parser/llms-txt.js'
import { classifyFromFrontmatter } from './classifier.js'
import { generateKotlinFile } from './generator-new.js'

const OUTPUT_DIR = path.resolve(import.meta.dirname, '../../lib/src/main/kotlin/io/github/ollin/kdaisyui/components')
const CONFIG_PATH = path.resolve(import.meta.dirname, '../codegen-config.json')

function loadConfig() {
  if (!fs.existsSync(CONFIG_PATH)) {
    return { extras: {}, textParams: [], roles: {}, inputTypes: {} }
  }
  return JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf8'))
}

function main() {
  console.log('Generating kdaisyUI components from DaisyUI source...\n')
  
  const config = loadConfig()
  const elementRules = parseLlmsTxt()
  const componentDirs = getAllComponentDirs()
  
  console.log(`Found ${componentDirs.length} components in DaisyUI docs\n`)
  
  let generated = 0
  let skipped = 0
  
  for (const componentName of componentDirs) {
    if (config.skip?.includes(componentName)) {
      console.log(`  ⊘ ${componentName}: Skipped (alias)`)
      skipped++
      continue
    }
    
    const frontmatter = readComponentFrontmatter(componentName)
    if (!frontmatter) {
      console.log(`  ⚠ ${componentName}: No frontmatter found`)
      skipped++
      continue
    }
    
    if (!frontmatter.classnames?.component?.length) {
      console.log(`  ⚠ ${componentName}: No component class defined`)
      skipped++
      continue
    }
    
    const classified = classifyFromFrontmatter(frontmatter, componentName)
    const element = getElementForComponent(elementRules, componentName)
    
    const kotlin = generateKotlinFile(classified, { primaryElement: element }, config)
    const outFile = path.join(OUTPUT_DIR, `${classified.componentName}.kt`)
    
    fs.writeFileSync(outFile, kotlin)
    console.log(`  ✓ ${classified.componentName}.kt (${element})`)
    generated++
  }
  
  console.log(`\nGenerated ${generated} components, skipped ${skipped}`)
  console.log(`Output: ${OUTPUT_DIR}`)
}

main()
