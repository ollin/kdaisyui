/**
 * DaisyUI's reference table of CSS custom properties, read from
 * `packages/docs/src/routes/(routes)/docs/utilities/+page.md`.
 *
 * One row per variable, with a description: `--anchor-v` is "vertical position of the anchor",
 * `--indicator-x` is "horizontal position of the indicator". It is the one place DaisyUI says
 * what an axis's variable MEANS, and therefore the one place an axis's name can come from
 * without being invented — see `axis-names.ts`.
 *
 * The frontmatter `desc` lines carry the same words in prose, in three different phrasings
 * across four components. They are not read for this: a reference table has one row per
 * variable, and a row that disappears or changes its word fails generation loudly instead of
 * being reparsed as something else.
 *
 * Parsed by splitting on the pipe, not by a regular expression — a Markdown table row is
 * cells between pipes, and that is all this needs to know.
 */
import { readFileSync } from 'node:fs'
import path from 'node:path'

const UTILITIES_PAGE = path.resolve(
  import.meta.dirname,
  '../../../daisyui/packages/docs/src/routes/(routes)/docs/utilities/+page.md',
)

/** One line of the page, seen as table cells. Empty when the line is not a table row. */
class TableRow {
  private readonly cells: readonly string[]

  constructor(line: string) {
    const trimmed = line.trim()
    const isRow = trimmed.startsWith('|') && trimmed.endsWith('|') && trimmed.length > 1
    this.cells = isRow ? trimmed.slice(1, -1).split('|').map((cell) => cell.trim()) : []
  }

  /** The variable a property-table row names: its second cell, written as `` `--name` ``. */
  variable(): string | undefined {
    const cell = this.cells[1]
    if (cell === undefined || !cell.startsWith('`--') || !cell.endsWith('`')) return undefined
    return cell.slice(1, -1)
  }

  description(): string {
    return this.cells[2] ?? ''
  }
}

/** Variable name → DaisyUI's description of it. */
export class PropertyTable {
  private readonly descriptions: ReadonlyMap<string, string>

  private constructor(descriptions: ReadonlyMap<string, string>) {
    this.descriptions = descriptions
  }

  static parse(markdown: string): PropertyTable {
    const descriptions = new Map<string, string>()
    for (const line of markdown.split('\n')) {
      const row = new TableRow(line)
      const variable = row.variable()
      if (variable !== undefined) descriptions.set(variable, row.description())
    }
    return new PropertyTable(descriptions)
  }

  static fromSubmodule(): PropertyTable {
    return PropertyTable.parse(readFileSync(UTILITIES_PAGE, 'utf8'))
  }

  describe(property: string): string | undefined {
    return this.descriptions.get(property)
  }

  size(): number {
    return this.descriptions.size
  }
}
