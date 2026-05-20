import fs from 'fs'
import path from 'path'
import { parseIconFiles } from './parser/svg-heroicons.js'
import { generateKotlinFile } from './generator-heroicons.js'

const HEROICONS_SRC_DIR = path.resolve(import.meta.dirname, '../../heroicons/src')
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../lib/src/main/kotlin/io/github/ollin/kdaisyui/icons')

function parseOutputDir() {
  for (const arg of process.argv) {
    if (arg.startsWith('--output-dir=')) {
      return arg.slice('--output-dir='.length)
    }
  }
  return DEFAULT_OUTPUT_DIR
}

const OUTPUT_DIR = parseOutputDir()

const directories = {
  outline24Dir: path.join(HEROICONS_SRC_DIR, '24', 'outline'),
  solid16Dir: path.join(HEROICONS_SRC_DIR, '16', 'solid'),
  solid20Dir: path.join(HEROICONS_SRC_DIR, '20', 'solid'),
  solid24Dir: path.join(HEROICONS_SRC_DIR, '24', 'solid'),
}

function main() {
  console.log('Generating Heroicons Kotlin source files...\n')

  for (const [name, dir] of Object.entries(directories)) {
    if (!fs.existsSync(dir)) {
      console.error(`${name} directory not found: ${dir}`)
      console.error('Ensure the heroicons git submodule is checked out.')
      process.exit(1)
    }
  }

  const icons = parseIconFiles(directories)
  const totalVariants = [...icons.values()].reduce((sum, s) => {
    return sum + [s.outline24, s.solid16, s.solid20, s.solid24].filter(Boolean).length
  }, 0)
  console.log(`Found ${icons.size} icons (${totalVariants} SVG variants)\n`)

  fs.mkdirSync(OUTPUT_DIR, { recursive: true })

  let generated = 0
  const sorted = [...icons.keys()].sort()
  for (const pascalName of sorted) {
    const struct = icons.get(pascalName)
    const kotlin = generateKotlinFile(pascalName, struct)
    const outFile = path.join(OUTPUT_DIR, `${pascalName}.kt`)
    fs.writeFileSync(outFile, kotlin)
    console.log(`  ✓ ${pascalName}.kt`)
    generated++
  }

  console.log(`\nGenerated ${generated} files`)
  console.log(`Output: ${OUTPUT_DIR}`)
}

main()
