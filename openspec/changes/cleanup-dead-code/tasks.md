# Tasks

Ordered by uncertainty first. Section 1 is the only part that can invalidate the plan: if
something outside `codegen/` reaches the v1 pipeline, it is not dead and section 2 does not
happen.

The safety net throughout is the same one `commit-generated-sources` established —
regenerate and compare against the committed `lib/generated`. Deleting genuinely dead code
cannot change a single byte of it, so an empty diff is the proof.

## 1. Confirm the v1 cluster is unreachable

*Refuted if any caller exists. Then: that caller decides what happens — either it is itself
dead and goes too, or `index.js` is live and this change is wrong about its central claim.*

- [x] 1.1 `. r` Search the whole repository — `.github/`, `justfile`, `docs/`, `README.md`,
      `llms.txt`, `buildSrc/`, every `*.gradle.kts` — for `index.js`, `generator.js`,
      `classify.js`, `src/config`, `npm run generate` and `codegen/src/config`. Record what
      is found, including in documentation, not only in executable code
- [x] 1.2 `. d` **Confirmed unreachable.** Outside the cluster itself, not one live caller:

      | Where | Result |
      |---|---|
      | `lib/build.gradle.kts:159,174,190` | invokes `index-new.js`, `test-generator.js`, `index-heroicons.js` only |
      | `codegen/package.json:6-7` | `generate` → `index-new.js`, `generate:heroicons` → `index-heroicons.js` |
      | `.github/workflows/*` | no codegen entry point named at all |
      | `justfile` | `generate` goes through Gradle since `2e93f84` |
      | `docs/`, `README.md`, `llms.txt`, `renovate.json` | reference only live files |
      | `codegen/src/**` imports | `index.js` → `generator.js` → `classify.js`, a closed cycle nothing enters |

      The only live mention is `.opencode/skills/kdaisyui-codegen/SKILL.md:145-153`, a "Dead
      files — do not edit, do not revive" section that reached this conclusion earlier and
      says to delete itself along with them. It also names the check used in 2.3: `codegen/src`
      is a declared input of all three generate tasks (`lib/build.gradle.kts:160,176,191`), so
      the deletion invalidates them and forces a real regeneration.

      Hits elsewhere were the `daisyui/` and `heroicons/` submodules' own `index.js` files —
      unrelated — and the openspec archive, which is history and stays as written.

## 2. Delete the v1 pipeline

- [x] 2.1 `. r` Delete `codegen/src/index.js`, `codegen/src/generator.js` and
      `codegen/src/classify.js`
- [x] 2.2 `. r` Delete the 22 files in `codegen/src/config/`
- [x] 2.3 `. r` **Proven.** The deletion invalidated `codegen/src`, a declared input of all
      three generate tasks, so they genuinely re-ran (`5 executed`). Aggregate hash over all
      450 files identical before and after (`2019abca…`), `git status` reports nothing under
      `lib/generated`, and `:lib:test` is 566 green
- [x] 2.4 `. r` Drop `js-yaml` from `codegen/package.json` — checked first, and
      `parser/frontmatter.js` does **not** need it: it has its own `parseYamlFrontmatter`.
      `index.js` was the only importer. `package-lock.json` was refreshed by the
      `npm install` the generate task already runs, dropping `js-yaml` and its transitive
      `argparse`. Regenerated output still hashes to `2019abca…`

      **The codegen now has zero npm dependencies.** That makes the `npm install --silent &&`
      prefix in two of the `Exec` commands a network round-trip that can no longer install
      anything — see section 6.

- [x] 2.5 `. d` Delete the `kdaisyui-codegen` skill's "Dead files — do not edit, do not
      revive" section, which asked to be removed together with the files. Also corrected two
      stale lines it sat next to: "any build regenerates" (now the opposite) and `just
      generate` described as a separate npm path (it drives the Gradle tasks since `2e93f84`)

## 3. Stop tracking the three `.idea` files

- [x] 3.1 `. d` Decide with Oliver which way it goes. **Decided 2026-08-15: untrack all
      three** — `.idea/misc.xml`, `.idea/gradle.xml`, `.idea/vcs.xml` (all added in
      `e24148b`) — and leave them on disk. This makes `.gitignore:15` true instead of
      aspirational; IntelliJ regenerates all three on the first Gradle import.

      Why each one goes, rather than just the noisy one:

      - `misc.xml` carries `project-jdk-name`, the JDK's display name in the *local* SDK
        table, so it cannot be tracked without churning per machine. The rest is a
        Kubernetes-plugin component that only exists where that plugin is installed, plus
        `languageLevel=JDK_21`, which `jvmToolchain(21)` in `buildSrc` already owns.
      - `gradle.xml` holds `gradleJvm` and a module list IntelliJ rebuilds from
        `settings.gradle.kts` on import, including the `buildSrc` composite build.
      - `vcs.xml` maps `daisyui/` and `heroicons/` as git roots — actively misleading since
        `commit-generated-sources`, because an ordinary clone no longer has them.

      Rejected: narrowing `.gitignore` to `.idea/*` plus `!` exceptions. It trades one
      inconsistency for more rules and does not fix the churn, which is inherent to
      `project-jdk-name`.

- [x] 3.2 `. d` Applied by Oliver in the shell; committed as `b46cec3`.

- [x] 3.3 `. d` **My list of three was incomplete.** `.idea/kotlinc.xml` (tracked since
      `36248e7`) and `.idea/.gitignore` (tracked since the initial commit `0c9029a`) are also
      tracked, and `kotlinc.xml` surfaced as a working-tree modification right after the other
      three were untracked — the same IDE churn, one file later.

      `.idea/.gitignore` is a deliberate exception worth keeping: JetBrains generates it to
      exclude its own volatile files, and it is the one thing in `.idea/` that does not churn.
      `kotlinc.xml` is not — it records the IDE's Kotlin compiler settings and moves with the
      IDE version.

      Done — `a27f08a`. The working tree is finally free of IDE churn.

      The lesson for 3.1: I derived "three files" from one commit's stat instead of asking git
      what is tracked. A commit shows what that commit touched, not what the index holds.

## 4. Prune branches

**Revised 2026-08-15 — the premise was wrong.** The proposal called these "merged local
branches, all at 0 ahead / 0 behind". That reading was of each branch against *its own
upstream*, not against `main`. Checked properly with `--merged origin/main`: **none of the
three is contained in `origin/main`.** Deleting them would discard commits.

They are not stale-by-a-little, they are ancient. `chore/minimize-codegen-yaml-configs` is
249 files away from `origin/main`, and the diff runs the wrong way — adopting it would delete
`classifier.js`, `generator-new.js`, `ktor-integration/`, the Kotlin e2e suite and `openspec/`
and restore `codegen/src/index.js` with `lib/src/main/kotlin/kdaisyui/components/`. It
predates the v2 codegen entirely. The other two sit on the same old base.

What each carries, as far as the subject lines and today's tree show:

| Branch | Subject | Still relevant? |
|---|---|---|
| `chore/minimize-codegen-yaml-configs` | derive `htmlTag` from `htmlElement`, drop redundant YAML fields | **No.** It edits the v1 `config/*.yml` deleted in task 2.2 |
| `chore/remove-version-consistency-ci` | remove version-consistency CI job | **No.** No such job exists in `ci.yml` today |
| `fix/renovate-action-version` | pin `renovatebot/github-action` to v46.1.6 | **Superseded.** `renovate.yml:22` already pins v46.1.14 |

- [x] 4.1 `. d` Deleted all three, decided by Oliver 2026-08-15.
- [x] 4.2 `. d` Fast-forwarded local `main` from `4bdb074` to `origin/main` (`0034960`),
      168 commits.

      **That fast-forward exposed something that outranks this whole change** — see the
      note below.

## 7. STOP — this branch is far behind `origin/main`

Not a cleanup item. Found while doing 4.2, recorded here because it invalidates assumptions
several pieces of work in this session were built on.

`ollins-stuff-at-home` is missing 168 commits of `origin/main`. Marker files present on
`origin/main` and absent here: root `build.gradle.kts`, `bom/`,
`.github/workflows/release.yml`, `codegen/src/test-generator-heroicons.js`.

Consequences, worst first:

1. **`origin/main` already deleted `codegen/src/{index,generator,classify}.js`.** Commit
   `cec80ae` in this session re-did work that main had done. The YAML configs and the
   `js-yaml` removal may or may not still be needed there — unchecked.
2. **`js-yaml` was bumped to v5.3.0 on main** (`0034960`, PR #230) while this branch removed
   it as unused. Direct collision.
3. **Release automation exists on `main`** (`release.yml`, a root `build.gradle.kts`, a `bom/`
   module). This branch's `AGENTS.md` and the `kdaisyui-release` skill both state there is
   none. That statement is true of this branch and false of the project.
4. `main` also carries a coverage-enforcement spec, mutation-testing and DaisyUI 5.6 change
   proposals, and its own `openspec/config.yaml` — none of which this session saw.
5. `commit-generated-sources` edited `ci.yml`; `main` changed the same file by 18 lines.

- [ ] 7.1 Decide with Oliver how this branch rejoins `main` before any further work lands on
      it. Everything from this session is committed and none of it is pushed

## 6. Decide on the now-empty `npm install`

Surfaced by 2.4, not planned. With zero dependencies, `npm install --silent &&` in
`lib/build.gradle.kts:159,190` costs a network round-trip and installs nothing.

- [ ] 6.1 `. d` With Oliver: drop the prefix, or keep it so that adding a dependency later
      needs no build change. Dropping it also makes regeneration work offline; keeping it
      means one less thing to remember. Not obvious either way, hence not decided here

## 5. Resolve `just generate-heroicons`

- [ ] 5.1 `. d` Keep it as a documented convenience or delete it as a subset of
      `just generate` — with Oliver, since it is a taste question about the task runner's
      surface, not a correctness one
