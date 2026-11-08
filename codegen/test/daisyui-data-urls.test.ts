/**
 * The README names the DaisyUI components that draw with `data:` images, because they are why
 * kdaisyui's Content-Security-Policy allows `img-src 'self' data:` (guarantee-strict-csp, F5).
 * A DaisyUI bump that adds or drops one would leave that list silently wrong; this fails instead.
 */
import { test } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import path from 'path'
import { pinnedSubmodule, repositoryRootAbove } from '../src/submodules.ts'

const MARKER = 'daisyui-data-urls:'

function withoutComments(css: string): string {
  return css.replace(/\/\*[\s\S]*?\*\//g, '')
}

/** Every stylesheet under DaisyUI's `src/`, as `components/mask`, whose rules use a `data:` URL. */
function stylesheetsDrawingWithDataUrls(): string[] {
  const source = path.join(pinnedSubmodule('daisyui'), 'packages/daisyui/src')
  return fs.readdirSync(source, { recursive: true, encoding: 'utf8' })
    .filter(file => file.endsWith('.css'))
    .filter(file => withoutComments(fs.readFileSync(path.join(source, file), 'utf8')).includes('url("data:'))
    .map(file => file.replace(/\.css$/, ''))
    .sort()
}

/** The stylesheets the README's marker names. */
function stylesheetsTheReadmeNames(): string[] {
  const readme = fs.readFileSync(path.join(repositoryRootAbove(import.meta.dirname), 'README.md'), 'utf8')
  const line = readme.split('\n').find(text => text.startsWith(MARKER))
  assert.ok(line, `README.md has no line starting with "${MARKER}"`)
  return line.slice(MARKER.length).replace('-->', '').trim().split(/\s+/).sort()
}

test('the README names exactly the DaisyUI stylesheets that draw with data: images', () => {
  assert.deepEqual(stylesheetsTheReadmeNames(), stylesheetsDrawingWithDataUrls())
})
