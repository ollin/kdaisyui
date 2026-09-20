import { describe, it } from 'node:test'
import assert from 'node:assert/strict'
import { PropertyTable } from '../src/parser/property-table.ts'

const PAGE = [
  '# Utilities',
  '',
  '| Component | Variable        | Description                            |',
  '| --------- | --------------- | -------------------------------------- |',
  '| Dropdown  | `--anchor-v`    | vertical position of the anchor        |',
  '|           | `--anchor-h`    | horizontal position of the anchor      |',
  '| Tooltip   | `--tt-trans`    | transform offset of the tooltip        |',
  '',
  'Some prose with a | pipe in it.',
  '| `bg-primary` | Sets the background color |',
].join('\n')

describe('PropertyTable', () => {
  const table = PropertyTable.parse(PAGE)

  it('reads a variable row, whether or not its component cell is filled', () => {
    assert.equal(table.describe('--anchor-v'), 'vertical position of the anchor')
    assert.equal(table.describe('--anchor-h'), 'horizontal position of the anchor')
  })

  it('reads a row whose description carries no direction word as it is', () => {
    assert.equal(table.describe('--tt-trans'), 'transform offset of the tooltip')
  })

  it('ignores the header, the rule line, prose, and tables of other shapes', () => {
    assert.equal(table.size(), 3)
    assert.equal(table.describe('--------'), undefined)
    assert.equal(table.describe('bg-primary'), undefined)
  })

  it('says nothing about a variable the table does not list', () => {
    assert.equal(table.describe('--indicator-y'), undefined)
  })
})
