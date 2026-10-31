import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { componentMapLine } from '../src/component-map.ts'

describe('componentMapLine', () => {
  it('names the file class and every class the file declares, then the CSS classes, each sorted once', () => {
    const kotlin = 'enum class ButtonSize(internal val className: String)\nfun FlowContent.daisyButton() {}\nclass JoinScope\n  enum class Nested\n'

    assert.equal(componentMapLine('button', 'Button', kotlin, ['btn-lg', 'btn', 'btn']), 'button\tButtonKt ButtonSize JoinScope\tbtn btn-lg')
  })
})
