import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import { classifyFromFrontmatter } from '../src/classifier.ts'
import type { ComponentName } from '../src/parser/frontmatter.ts'

describe('classifyFromFrontmatter', () => {
  test('keys each description by the class without the component prefix, the component class by itself', () => {
    const classnames = { component: [{ class: 'btn', desc: 'Button' }], size: [{ class: 'btn-lg', desc: 'Large' }, { class: 'btnx', desc: 'Not prefixed' }] }

    assert.deepEqual(classifyFromFrontmatter({ desc: 'Buttons.', classnames }, 'button' as ComponentName), {
      componentName: 'Button',
      desc: 'Buttons.',
      descs: { btn: 'Button', lg: 'Large', btnx: 'Not prefixed' },
    })
  })

  test('describes a component without a desc as empty', () => {
    assert.equal(classifyFromFrontmatter({}, 'button' as ComponentName).desc, '')
  })
})
