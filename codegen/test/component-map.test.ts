import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { classListFile, componentMapFile, componentMapLine } from '../src/component-map.ts'

describe('componentMapLine', () => {
  it('names the file class and every class the file declares, then the CSS classes, each sorted once', () => {
    const kotlin = 'enum class ButtonSize(internal val className: String)\nfun FlowContent.daisyButton() {}\nclass JoinScope\n  enum class Nested\n'

    assert.equal(componentMapLine('button', 'Button', kotlin, ['btn-lg', 'btn', 'btn']), 'button\tButtonKt ButtonSize JoinScope\tbtn btn-lg')
  })
})

describe('the resource files', () => {
  it('sort the component lines whatever order they come in', () => {
    assert.match(componentMapFile(['card\tCardKt\tcard', 'alert\tAlertKt\talert']), /\nalert\tAlertKt\talert\ncard\tCardKt\tcard\n$/)
  })

  it('list each class once, sorted', () => {
    assert.match(classListFile(['btn-lg', 'btn', 'btn-lg']), /\nbtn\nbtn-lg\n$/)
  })
})
