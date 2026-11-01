import { test, describe } from 'node:test'
import assert from 'node:assert/strict'
import fs from 'fs'
import os from 'os'
import path from 'path'

import { pinnedSubmodule, repositoryRootAbove } from '../src/submodules.ts'

/** A throwaway repository: `.gitmodules` at its root, and a nested directory to start from. */
function repository(): { root: string, nested: string } {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'submodules-'))
  fs.writeFileSync(path.join(root, '.gitmodules'), '')
  const nested = path.join(root, 'codegen/.stryker-tmp/sandbox-1/src')
  fs.mkdirSync(nested, { recursive: true })
  return { root, nested }
}

describe('repositoryRootAbove', () => {
  test('finds the directory holding .gitmodules from a directory nested below it', () => {
    const { root, nested } = repository()

    assert.equal(repositoryRootAbove(nested), root)
    assert.equal(repositoryRootAbove(root), root)
    fs.rmSync(root, { recursive: true })
  })

  test('names the directory when nothing above it holds .gitmodules', () => {
    assert.throws(() => repositoryRootAbove(path.parse(os.tmpdir()).root), /^Error: No \.gitmodules at or above \/$/)
  })
})

describe('pinnedSubmodule', () => {
  test('gives the checkout of a populated submodule', () => {
    const { root, nested } = repository()
    fs.mkdirSync(path.join(root, 'heroicons/src'), { recursive: true })

    assert.equal(pinnedSubmodule('heroicons', nested), path.join(root, 'heroicons'))
    fs.rmSync(root, { recursive: true })
  })

  test('refuses a submodule that is missing or was never initialised, naming the command', () => {
    const { root, nested } = repository()
    const message = /^Error: The heroicons submodule is not checked out: git submodule update --init heroicons$/

    assert.throws(() => pinnedSubmodule('heroicons', nested), message)
    fs.mkdirSync(path.join(root, 'heroicons'))
    assert.throws(() => pinnedSubmodule('heroicons', nested), message)
    fs.rmSync(root, { recursive: true })
  })

  test('finds the real checkouts from this repository by default', () => {
    assert.ok(fs.existsSync(path.join(pinnedSubmodule('daisyui'), 'packages/docs')))
  })
})
