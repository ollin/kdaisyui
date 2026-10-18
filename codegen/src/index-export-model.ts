/**
 * One-off: write `codegen/model/<directory>.json` for every DaisyUI component from today's
 * inferred shape, its configuration and its editorial summary. A skipped component gets a file
 * that says why.
 *
 * Deleted by task 3.3 of `declare-the-component-model`, once the generators read the model. From
 * then on the model files are edited by hand and nothing writes them.
 */

import fs from 'fs'
import path from 'path'
import { readInferredComponentSet } from './component-set.ts'
import { readComponentConfig } from './component-shape.ts'
import { documentedJoinItemCompanions, isJoinItemComponent } from './join-scope.ts'
import { getAllComponentDirs, type ComponentName } from './parser/frontmatter.ts'
import { modelFromShape } from './model.ts'

const CONFIG_PATH = path.resolve(import.meta.dirname, '../codegen-config.json')
const MODEL_DIR = path.resolve(import.meta.dirname, '../model')

const LINE = 120

/** The value on one line: `["a", "b"]`, `{ "k": 1 }`. */
function inline(value: unknown): string {
  if (Array.isArray(value)) return `[${value.map(inline).join(', ')}]`
  if (value !== null && typeof value === 'object') {
    return `{ ${Object.entries(value).map(([key, item]) => `${JSON.stringify(key)}: ${inline(item)}`).join(', ')} }`
  }
  return JSON.stringify(value)
}

/**
 * JSON for a human to edit: a value that fits on its line stays on one line, so a class list or
 * a parameter reads as one row, and only what does not fit is broken over several.
 */
function format(value: unknown, indent = '', key = ''): string {
  const flat = inline(value)
  if (value === null || typeof value !== 'object' || indent.length + key.length + flat.length <= LINE) return flat
  const inner = `${indent}  `
  if (Array.isArray(value)) return `[\n${value.map(item => inner + format(item, inner)).join(',\n')}\n${indent}]`
  const entries = Object.entries(value).map(([name, item]) => {
    const label = `${JSON.stringify(name)}: `
    return `${inner}${label}${format(item, inner, label)}`
  })
  return `{\n${entries.join(',\n')}\n${indent}}`
}

function write(componentDir: string, content: object): void {
  fs.writeFileSync(path.join(MODEL_DIR, `${componentDir}.json`), `${format(content)}\n`)
}

function main(): void {
  const config = JSON.parse(fs.readFileSync(CONFIG_PATH, 'utf8'))
  const { generated, skipped } = readInferredComponentSet(config)
  const companions = documentedJoinItemCompanions(getAllComponentDirs() as ComponentName[])
  fs.mkdirSync(MODEL_DIR, { recursive: true })

  for (const component of generated) {
    write(component.componentDir, modelFromShape(component.shape, readComponentConfig(config, component.shape.componentName), {
      joinItem: isJoinItemComponent(component.shape, companions),
      summary: config.docSummaries?.[component.componentDir],
    }))
  }
  for (const component of skipped) write(component.componentDir, { component: component.componentDir, skip: component.reason })

  console.log(`Wrote ${generated.length + skipped.length} model files to ${MODEL_DIR}`)
}

main()
