/**
 * `just daisyui-delta <old-tag> <new-tag>`: what changed in DaisyUI's component documentation,
 * read from the submodule's tags. Prints and exits 0 whatever it finds; the model is edited by hand.
 */

import path from 'path'
import { DocsAtTag, renderDelta } from './daisyui-delta.ts'

const SUBMODULE = path.resolve(import.meta.dirname, '../../daisyui')

const [from, to] = process.argv.slice(2)
if (from === undefined || to === undefined) {
  console.error('usage: node src/index-daisyui-delta.ts <old-tag> <new-tag>')
  process.exit(2)
}
console.log(renderDelta(new DocsAtTag(SUBMODULE, from), new DocsAtTag(SUBMODULE, to), from, to))
