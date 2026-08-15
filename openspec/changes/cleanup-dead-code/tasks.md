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

- [ ] 1.1 `. r` Search the whole repository — `.github/`, `justfile`, `docs/`, `README.md`,
      `llms.txt`, `buildSrc/`, every `*.gradle.kts` — for `index.js`, `generator.js`,
      `classify.js`, `src/config`, `npm run generate` and `codegen/src/config`. Record what
      is found, including in documentation, not only in executable code
- [ ] 1.2 `. d` Record the outcome here: confirmed unreachable, or the change is revised

## 2. Delete the v1 pipeline

- [ ] 2.1 `. r` Delete `codegen/src/index.js`, `codegen/src/generator.js` and
      `codegen/src/classify.js`
- [ ] 2.2 `. r` Delete the 22 files in `codegen/src/config/`
- [ ] 2.3 `. r` Prove nothing moved: regenerate all three outputs and confirm `git status`
      reports `lib/generated` unchanged, then `./gradlew :lib:test`
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

- [ ] 3.2 `. d` Apply it: `git rm --cached .idea/misc.xml .idea/gradle.xml .idea/vcs.xml`,
      keeping the files on disk. Needs a shell — the Git MCP server exposes no `rm`, and
      deleting-then-committing would take the running IDE's configuration with it

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
