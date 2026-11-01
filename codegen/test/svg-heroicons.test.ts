import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import os from 'os'
import path from 'path'

import { parseIconFiles } from '../src/parser/svg-heroicons.ts'

/**
 * What the snapshot tests cannot show, because no pinned Heroicons SVG does it: an SVG without its
 * wrapper, and an icon of more than one path.
 */
function iconDirectories(svgs: { outline?: string, solid?: string }) {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'heroicons-'))
  const directories = {
    outline24Dir: path.join(root, '24/outline'),
    solid16Dir: path.join(root, '16/solid'),
    solid20Dir: path.join(root, '20/solid'),
    solid24Dir: path.join(root, '24/solid'),
  }
  for (const dir of Object.values(directories)) fs.mkdirSync(dir, { recursive: true })
  if (svgs.outline) fs.writeFileSync(path.join(directories.outline24Dir, 'probe.svg'), svgs.outline)
  if (svgs.solid) fs.writeFileSync(path.join(directories.solid24Dir, 'probe.svg'), svgs.solid)
  return { root, directories }
}

describe('parseIconFiles', () => {
  test('keeps every path of an icon, in order, each rewritten for its variant', () => {
    const svg = '<svg viewBox="0 0 24 24"><path d="M1" stroke="#0F172A"/><path d="M2" fill="#0F172A"/></svg>'
    const { root, directories } = iconDirectories({ outline: svg, solid: svg })

    const icon = parseIconFiles(directories).get('Probe' as never)
    assert.equal(icon?.outline24, '<path fill="none" d="M1" stroke="currentColor" /><path d="M2" fill="#0F172A" />')
    assert.equal(icon?.solid24, '<path d="M1" stroke="#0F172A" /><path d="M2" fill="currentColor" />')
    fs.rmSync(root, { recursive: true })
  })

  test('names the variant of an SVG that has no <svg> wrapper', () => {
    const broken = '<path d="M1"/>'
    const outline = iconDirectories({ outline: broken })
    const solid = iconDirectories({ outline: '<svg><path d="M1"/></svg>', solid: broken })

    assert.throws(() => parseIconFiles(outline.directories), /^Error: Failed to parse outline SVG: no <svg> wrapper found$/)
    assert.throws(() => parseIconFiles(solid.directories), /^Error: Failed to parse solid SVG: no <svg> wrapper found$/)
    fs.rmSync(outline.root, { recursive: true })
    fs.rmSync(solid.root, { recursive: true })
  })
})
