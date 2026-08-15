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
- [ ] 2.4 `. d` Drop `js-yaml` from `codegen/package.json` if the deletion made it unused,
      and refresh `package-lock.json` — check first, `parser/frontmatter.js` may still need it

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

- [x] 3.2 `. d` Applied by Oliver in the shell; committed as `b46cec3`. The files remain on
      disk and the working tree is finally clean of IDE churn

## 4. Prune branches

- [ ] 4.1 `. d` Delete the three merged local branches
      (`chore/minimize-codegen-yaml-configs`, `chore/remove-version-consistency-ci`,
      `fix/renovate-action-version`) after confirming each is contained in `origin/main`
- [ ] 4.2 `. d` Fast-forward local `main` to `origin/main`, or delete it if the workflow
      never uses it

## 5. Resolve `just generate-heroicons`

- [ ] 5.1 `. d` Keep it as a documented convenience or delete it as a subset of
      `just generate` — with Oliver, since it is a taste question about the task runner's
      surface, not a correctness one
