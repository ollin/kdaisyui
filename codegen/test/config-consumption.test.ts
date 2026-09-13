import { test, describe } from 'node:test'
import assert from 'node:assert/strict'

import {
  CONFIG_SECTIONS,
  describeUnreadEntries,
  findUnreadEntries,
  type ConsumedKeys,
} from '../src/config-consumption.ts'

/**
 * The guard against a config entry nobody reads.
 *
 * The case that motivated it is the first test: `noContent` held `file-input` while the lookup
 * asked for `fileinput`, so the entry was dead and `daisyFileInput` demanded a content lambda
 * on a void element for as long as it existed.
 */

const consumed = (overrides: Partial<ConsumedKeys> = {}): ConsumedKeys => ({
  componentKeys: new Set(['fileinput', 'card', 'button']),
  directoryKeys: new Set(['file-input', 'card', 'button']),
  partKeys: new Set(['card-title', 'megamenu-active']),
  ...overrides,
})

describe('findUnreadEntries', () => {
  test('catches the directory-spelled key in a component-keyed section', () => {
    // Exactly the shipped defect: a real DaisyUI directory name in a section read by
    // lower-cased PascalCase name. A key-existence check would have passed this.
    const unread = findUnreadEntries({ extras: { 'file-input': [] } }, consumed())

    assert.equal(unread.length, 1)
    assert.equal(unread[0].key, 'file-input')
    assert.match(unread[0].message, /was never read/)
    assert.match(unread[0].message, /keyed by component name/)
  })

  test('accepts the same key in a section that IS keyed by directory', () => {
    const unread = findUnreadEntries({ componentElements: { 'file-input': 'INPUT' } }, consumed())

    assert.deepEqual(unread, [])
  })

  test('says nothing when every entry was read', () => {
    const config = {
      extras: { card: [], button: [] },
      roles: { button: 'button' },
      skip: ['card'],
      docSummaries: { 'file-input': { summary: 'x' } },
      subComponentElements: { 'card-title': 'H2' },
    }

    assert.deepEqual(findUnreadEntries(config, consumed()), [])
  })

  test('checks list sections as well as object sections', () => {
    const unread = findUnreadEntries({ skip: ['card', 'nonexistent'] }, consumed())

    assert.deepEqual(unread.map(e => e.key), ['nonexistent'])
  })

  test('lets textParams name a part class, which legitimately takes inline text', () => {
    // `card-title` is a part, not a component, and it does take a text shortcut.
    const unread = findUnreadEntries({ textParams: ['button', 'card-title'] }, consumed())

    assert.deepEqual(unread, [])
  })

  test('still catches a textParams entry that is neither component nor part', () => {
    const unread = findUnreadEntries({ textParams: ['card-nonsense'] }, consumed())

    assert.deepEqual(unread.map(e => e.key), ['card-nonsense'])
  })

  test('catches a part-keyed entry no part matched', () => {
    const unread = findUnreadEntries({ subComponentElements: { 'card-nope': 'SPAN' } }, consumed())

    assert.deepEqual(unread.map(e => e.key), ['card-nope'])
  })

  test('tolerates a config with none of the sections present', () => {
    assert.deepEqual(findUnreadEntries({}, consumed()), [])
  })

  test('reports every unread entry, not just the first', () => {
    const config = { extras: { 'file-input': [] }, roles: { ghost: 'x' }, skip: ['nope'] }

    assert.deepEqual(findUnreadEntries(config, consumed()).map(e => e.key), ['file-input', 'ghost', 'nope'])
  })
})

describe('CONFIG_SECTIONS', () => {
  test('covers every section the generators read', () => {
    // A section missing here is a section the guard cannot police, which is the failure mode
    // the guard exists to end. Keep this list in step with `codegen-config.json`.
    assert.deepEqual(
      CONFIG_SECTIONS.map(s => s.name).sort(),
      [
        'additionalBooleans', 'componentAttributes', 'componentElements', 'customParts',
        'docSummaries', 'elementCrossCheckExceptions', 'extras', 'inputTypes', 'roles',
        'skip', 'subComponentElements', 'textParams',
      ],
    )
  })
})

describe('describeUnreadEntries', () => {
  test('counts them and gives each its own line', () => {
    const unread = findUnreadEntries({ extras: { ghost: [] }, roles: { phantom: 'x' } }, consumed())

    const message = describeUnreadEntries(unread)

    assert.match(message, /^Unread configuration \(2\):/)
    assert.equal(message.split('\n').length, 3)
  })
})
