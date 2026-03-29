import fs from 'fs'
import path from 'path'
import yaml from 'js-yaml'
import { generateComponent } from './generator.js'

const configDir = path.resolve(import.meta.dirname, 'config')
const outputDir = path.resolve(import.meta.dirname, '../../lib/src/main/kotlin/kdaisyui/components')

function findCssDir() {
  const candidates = [
    path.resolve(import.meta.dirname, '../node_modules/daisyui/components'),
    path.resolve(import.meta.dirname, '../node_modules/daisyui/src/components'),
    '/tmp/daisyui/packages/daisyui/src/components',
  ]
  for (const c of candidates) {
    if (fs.existsSync(c)) return c
  }
  throw new Error('DaisyUI CSS source not found. Run: cd codegen && npm install')
}

const cssDir = findCssDir()
console.log(`Using DaisyUI CSS from: ${cssDir}`)

const configFiles = fs.readdirSync(configDir).filter(f => f.endsWith('.yml')).sort()
let generated = 0

for (const file of configFiles) {
  const config = yaml.load(fs.readFileSync(path.join(configDir, file), 'utf8'))
  const kotlin = generateComponent(config, cssDir)
  const outFile = path.join(outputDir, `${config.component}.kt`)
  fs.writeFileSync(outFile, kotlin)
  console.log(`  ✓ ${config.component}.kt`)
  generated++
}

console.log(`\nGenerated ${generated} component files → ${outputDir}`)
