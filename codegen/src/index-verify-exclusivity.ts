/**
 * Entry point: fail when `codegen/exclusivity.json` no longer describes the DaisyUI in the
 * submodule.
 *
 * Runs in `generated-sources-drift`, beside `checkComponentApi`, because it reads the
 * submodule — the codegen unit-test job is deliberately free of one.
 */
import { describeMismatches, findMismatches } from './verify-exclusivity.ts'
import { loadMeasurement } from './measurement.ts'

const mismatches = findMismatches(loadMeasurement())

if (mismatches.length > 0) {
  console.error(describeMismatches(mismatches))
  process.exit(1)
}

console.log('codegen/exclusivity.json covers every class group DaisyUI documents.')
