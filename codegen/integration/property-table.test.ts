import { describe, it } from 'node:test'
import assert from 'node:assert/strict'

import { PropertyTable } from '../src/parser/property-table.ts'

/**
 * Tests that READ THE DAISYUI SUBMODULE, and therefore cannot run in `codegen-tests`.
 *
 * That job checks out no submodules on purpose — it imports the generator modules and asserts
 * on pure functions, so it needs neither a JDK nor the DaisyUI checkout, and that is what
 * makes it the fastest gate in the pipeline. A test reading the submodule there fails with
 * ENOENT rather than an assertion, which is what turned `main` red.
 *
 * Skipping such a test when the submodule is absent would be worse than moving it: it would
 * become a test CI can never fail, which is the one thing this repository has repeatedly paid
 * for. So it runs in `generated-sources-drift` instead, which checks out submodules already.
 */
describe('PropertyTable against the real DaisyUI', () => {
  it('reads the real page from the submodule', () => {
    // The rows the axis names depend on. If DaisyUI drops or rewords one, this is the test
    // that says so before generation fails with a less specific message.
    const real = PropertyTable.fromSubmodule()

    assert.equal(real.describe('--indicator-y'), 'vertical position of the indicator')
    assert.equal(real.describe('--indicator-x'), 'horizontal position of the indicator')
    assert.equal(real.describe('--toast-y'), 'vertical position of the toast')
    assert.equal(real.describe('--toast-x'), 'horizontal position of the toast')
    assert.equal(real.describe('--anchor-v'), 'vertical position of the anchor')
    assert.equal(real.describe('--anchor-h'), 'horizontal position of the anchor')
    assert.ok(real.size() > 100)
  })
})
