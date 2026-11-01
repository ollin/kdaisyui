/**
 * Prints the StrykerJS `--mutate` value for the lines `HEAD^1..HEAD` adds under `codegen/src/`,
 * comma-separated, or nothing when there are none. Run in `codegen/` by the codegen-tests job,
 * where `HEAD` is a pull request's merge commit and `HEAD^1` its base.
 *
 *   node src/index-mutation-scope.ts [<base>]
 */
import { execFileSync } from 'child_process'
import { mutationScope } from './mutation-scope.ts'

const base = process.argv[2] ?? 'HEAD^1'
const diff = execFileSync('git', ['diff', '-U0', '--no-renames', '--relative', base, 'HEAD', '--', 'src/'], { encoding: 'utf8' })
process.stdout.write(mutationScope(diff).join(','))
