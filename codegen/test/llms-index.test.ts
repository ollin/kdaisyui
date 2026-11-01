import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import os from 'os'
import path from 'path'

import { coverageFindings, documentationPages, generateLlmsIndex, linkFindings, renderLlmsIndex, shapeFindings } from '../src/llms-index.ts'
import { component, shapeOf } from './model-fixture.ts'

const KBD = shapeOf(component('kbd', 'Kbd', 'kbd'), { desc: 'Kbd is used to display keyboard shortcuts. Second sentence.', classes: {} })

/** A link item into this repository, as the index writes it. */
const raw = (file: string, note?: string) => `- [${file}](https://raw.githubusercontent.com/ollin/kdaisyui/main/${file})${note ? `: ${note}` : ''}`

describe('renderLlmsIndex', () => {
  test('links each component to the raw Markdown of its reference page, with its summary as the note', () => {
    const index = renderLlmsIndex('## Components\n\n{{COMPONENTS}}\n', [KBD], { kbd: { summary: 'Keyboard key display' } })

    assert.match(index, /^- \[Kbd\]\(https:\/\/raw\.githubusercontent\.com\/ollin\/kdaisyui\/main\/docs\/reference\/kbd\.md\): Keyboard key display$/m)
    assert.doesNotMatch(index, /\{\{COMPONENTS\}\}/)
  })

  test('lists components in display order and falls back to the first DaisyUI sentence', () => {
    const fileInput = shapeOf(component('file-input', 'FileInput', 'file-input'))
    const index = renderLlmsIndex('{{COMPONENTS}}', [KBD, fileInput], {})

    assert.match(index, /^- \[FileInput\]\(.*\/docs\/reference\/fileInput\.md\): \n- \[Kbd\]\(.*\): Kbd is used to display keyboard shortcuts$/m)
  })

  test('opens with a header saying the file is generated and from what', () => {
    const index = renderLlmsIndex('{{COMPONENTS}}', [KBD], {})

    assert.match(index, /^<!--\nGENERATED — DO NOT EDIT\nSource: codegen\/llms-template\.md and the generated component API\nRegenerate: just generate\n-->\n/)
  })

  test('refuses a template that has lost its placeholder', () => {
    assert.throws(() => renderLlmsIndex('no placeholder here\n', [KBD], {}), /\{\{COMPONENTS\}\}/)
  })
})

describe('shapeFindings', () => {
  test('accepts H2 headings, blank lines and link items with or without a note after the first H2', () => {
    const index = `# kdaisyui\n\n> Summary.\n\nProse here.\n\n## Docs\n\n${raw('README.md', 'the start')}\n\n${raw('docs/how-to.md')}\n\n## Optional\n\n- [DaisyUI](https://daisyui.com)\n`

    assert.deepEqual(shapeFindings(index), [])
  })

  test('names the line that is neither an H2 nor a link item after the first H2', () => {
    const index = '# kdaisyui\n\n## Docs\n\n### Tutorials\n- [One](https://x/one.md)\nA sentence.\n'

    assert.deepEqual(shapeFindings(index), [
      'line 5: after the first H2 only H2 headings and link items may appear, got "### Tutorials"',
      'line 7: after the first H2 only H2 headings and link items may appear, got "A sentence."',
    ])
  })

  test('before the first H2, names a heading that is not the one H1 and counts the H1s', () => {
    const index = '<!--\ngenerated\n-->\n# kdaisyui\n\n### Rules\n\n# Again\n\n## Docs\n'

    assert.deepEqual(shapeFindings(index), [
      'line 6: before the first H2 the only heading is the H1, got "### Rules"',
      'the index needs exactly one H1, found 2',
    ])
  })
})

describe('linkFindings', () => {
  test('names a repository link whose path does not exist, and leaves other hosts alone', () => {
    const index = `## Docs\n\n${raw('README.md', 'start')}\n${raw('docs/gone.md')}\n- [DaisyUI](https://daisyui.com)\n`
    const exists = (file: string) => file === 'README.md'

    assert.deepEqual(linkFindings(index, exists), ['line 4: links docs/gone.md, which does not exist in the repository'])
  })
})

describe('coverageFindings', () => {
  test('names each documentation page no repository link targets', () => {
    const index = `## Docs\n\n${raw('README.md', 'start')}\n- [DaisyUI](https://daisyui.com)\n`

    assert.deepEqual(coverageFindings(index, ['README.md', 'docs/how-to.md']), ['docs/how-to.md is not linked from llms.txt'])
  })
})

describe('documentationPages', () => {
  test('is README and every Markdown file under docs/ except the generated reference pages', () => {
    const root = fs.mkdtempSync(path.join(os.tmpdir(), 'llms-index-'))
    for (const file of ['README.md', 'docs/how-to.md', 'docs/tutorials/index.md', 'docs/reference/button.md', 'docs/screenshots/page.png']) {
      fs.mkdirSync(path.dirname(path.join(root, file)), { recursive: true })
      fs.writeFileSync(path.join(root, file), '')
    }

    assert.deepEqual(documentationPages(root), ['README.md', 'docs/how-to.md', 'docs/tutorials/index.md'])
    fs.rmSync(root, { recursive: true })
  })
})

describe('generateLlmsIndex', () => {
  const repository = { exists: (file: string) => file === 'README.md' || file === 'docs/reference/kbd.md', pages: ['README.md'] }

  test('returns the rendered index when every gate is quiet', () => {
    const template = `# kdaisyui\n\n> Summary.\n\n## Docs\n\n${raw('README.md', 'start')}\n\n## Components\n\n{{COMPONENTS}}\n`

    assert.match(generateLlmsIndex(template, [KBD], {}, repository), /^- \[Kbd\]\(.*kbd\.md\): Kbd is used to display keyboard shortcuts$/m)
  })

  test('refuses an index with findings, listing every one', () => {
    const template = `# kdaisyui\n\n## Docs\n\n### Start\n${raw('docs/gone.md')}\n{{COMPONENTS}}\n`

    assert.throws(() => generateLlmsIndex(template, [KBD], {}, repository), (error: Error) => {
      assert.match(error.message, /^llms\.txt is not the index llmstxt\.org specifies:\n/)
      assert.match(error.message, /line 10: after the first H2 only H2 headings and link items may appear, got "### Start"/)
      assert.match(error.message, /line 11: links docs\/gone\.md, which does not exist in the repository/)
      assert.match(error.message, /README\.md is not linked from llms\.txt/)
      return true
    })
  })
})
