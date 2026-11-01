import fs from 'fs'
import path from 'path'
import { heroiconDirectories, parseIconFiles } from './parser/svg-heroicons.ts'
import { generateKotlinFile } from './generator-heroicons.ts'

// Committed generated root — a sibling of lib/src/, never inside it.
// Gradle passes --output-dir explicitly; this default is for a bare `node` run.
const DEFAULT_OUTPUT_DIR = path.resolve(import.meta.dirname, '../../lib/generated/main/kotlin/io/github/ollin/kdaisyui/icons')

function parseOutputDir(): string {
  for (const arg of process.argv) {
    if (arg.startsWith('--output-dir=')) {
      return arg.slice('--output-dir='.length)
    }
  }
  return DEFAULT_OUTPUT_DIR
}

const OUTPUT_DIR = parseOutputDir()

function main() {
  console.log('Generating Heroicons Kotlin source files...\n')

  // Throws, naming `git submodule update --init heroicons`, when the submodule is absent.
  const icons = parseIconFiles(heroiconDirectories())
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
