import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { renderLlmsIndex, shapeFindings } from '../src/llms-index.ts'
import { component, shapeOf } from './model-fixture.ts'

const KBD = shapeOf(component('kbd', 'Kbd', 'kbd'), { desc: 'Kbd is used to display keyboard shortcuts. Second sentence.', classes: {} })

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
    const index = '# kdaisyui\n\n> Summary.\n\nProse here.\n\n## Docs\n\n- [README](https://x/README.md): the start\n\n- [How-to](https://x/docs/how-to.md)\n\n## Optional\n\n- [DaisyUI](https://daisyui.com)\n'

    assert.deepEqual(shapeFindings(index), [])
  })

  test('names the line that is neither an H2 nor a link item after the first H2', () => {
    const index = '# kdaisyui\n\n## Docs\n\n### Tutorials\n- [One](https://x/one.md)\nA sentence.\n'

    assert.deepEqual(shapeFindings(index), [
      'line 5: after the first H2 only H2 headings and link items may appear, got "### Tutorials"',
      'line 7: after the first H2 only H2 headings and link items may appear, got "A sentence."',
    ])
  })
})
