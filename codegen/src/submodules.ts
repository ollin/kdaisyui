/**
 * Where the pinned submodules are, found from the repository root rather than from this file.
 *
 * The generators used to resolve `../../daisyui` relative to their own source file. That points
 * nowhere inside StrykerJS's sandbox, a copy of `codegen/` at `codegen/.stryker-tmp/sandbox-…/`,
 * so a test reading a submodule died before any mutant ran. The repository root is the nearest
 * directory above that holds `.gitmodules`, and the sandbox sits inside it.
 */
import fs from 'fs'
import path from 'path'

/** The nearest directory at or above `directory` that holds `.gitmodules`. */
export function repositoryRootAbove(directory: string): string {
  if (fs.existsSync(path.join(directory, '.gitmodules'))) return directory
  const parent = path.dirname(directory)
  if (parent === directory) throw new Error(`No .gitmodules at or above ${directory}`)
  return repositoryRootAbove(parent)
}

/**
 * The checkout of a pinned submodule, or an error that names the command to fix it. A submodule
 * that was never initialised is an empty directory, which is as good as missing.
 */
export function pinnedSubmodule(name: string, from: string = import.meta.dirname): string {
  const checkout = path.join(repositoryRootAbove(from), name)
  const populated = fs.existsSync(checkout) && fs.readdirSync(checkout).length > 0
  if (!populated) throw new Error(`The ${name} submodule is not checked out: git submodule update --init ${name}`)
  return checkout
}
