# Findings

The change comes from Oliver's question of 2026-11-14: what the Deploy-Radar (fwb-application)
still had to adapt after kdaisyui 0.11.0, and what the library can do about it.

## F0 - The review found the plan's proof and its counts unsound

- **What happened:** the review of 2026-11-14 (`fwb-openspec-reviewer`) found four problems.
  - The site count was inconsistent across the artifacts.
  - The example app's `^ r` steps were to be proven by an E2E suite that asserts the classes of
    only five places.
  - Block 4 and tasks 3.1 and 3.3 were too big for one commit each.
  - The enabler in `ComponentMap` was missing.

  All four were folded in before any code: one count with every site listed, the class-set diff
  of task 1.3, split tasks, and the A5 probe moved first.
- **Cost:** about 20 minutes. Two more rounds went to F5.

## F1 - The source probe counted a comment

- **What happened:** the probe read every quoted string in a `.kt` file, comments included. Its
  32 example-app literals held `href="menu-active"` from a comment at
  `DashboardShell.kt:132`, so there were 31 real literals. The class-file count (27 distinct
  per file) was right, as the live warning of task 3.5 showed.
- **Cause:** a regex over source text stood in for the compiler.
- **Cost:** none. Task 4.4 found one site instead of two.

## F2 - Two steps in one commit

- **What happened:** the TCR run of task 4.5 and the edit of task 4.6 went out as parallel tool
  calls. The commit took both, under 4.5's subject.
- **Cause:** an agent error. A TCR run commits with `git add -A`, so nothing may edit the tree
  while it runs.
- **Cost:** a reworded commit, `19dbf3d`, whose body says so. The class-set diff after the commit
  was empty.

## F3 - The TCR tool's report names only the Bun suite

- **What happened:** every `mcp_Tcr` run at the root reported `bun test … 904 pass` and nothing
  about Gradle. Whether `./gradlew build` ran could only be inferred afterwards: `check` came back
  fully UP-TO-DATE. So each step was also run through `./gradlew -p gradle-plugin check`
  (Kover 100%) or the class-set diff before its TCR run.
- **Cause:** not investigated. The tool's own output.
- **Cost:** about 1 minute per step for the extra run.

## F4 - Inline collection functions left mutants no test can kill

- **What happened:** `:gradle-plugin:pitest` reached 98%, with three survivors in
  `RawClassLists.kt`: the boundary of a redundant `filter { it.isNotEmpty() }`, and the
  `is Collection` short-cut inlined from `all` and `none`. The first rewrite replaced them with
  `trim { }` and string checks and left one survivor, in `trim`'s inlined loop bounds. Two regexes
  over the string reached 100% (131 of 131).
- **Cause:** the same mechanism `ThemeCheck.kt` documents for `any`, met again because nothing
  says it before PIT does.
- **Cost:** two extra runs, about 15 minutes.

## F5 - The reviewer agent could not read the change

- **What happened:** `fwb-openspec-reviewer` was refused (`external_directory`) on the worktree
  and on `/tmp/opencode/…`. It reviewed only after the artifacts were copied into the primary
  checkout's `tmp/`. Oliver then asked for the work to move from the worktree to a branch of the
  checkout.
- **Cause:** the agent's permissions allow only its session's directory.
- **Cost:** two empty review rounds, about 10 minutes.

## Proposals

One row per finding (house rules §9).

| Finding | Proposal | Status |
|---|---|---|
| F0 | - | none - record only: a review round doing its job |
| F1 | - | none - record only: the class files are the measure, and the check reads those |
| F2 | - | none - record only: an agent error with its fix |
| F3 | The TCR tool reports each suite it ran by name, with its result, so a run at a Gradle root shows that Gradle ran | open - for the TCR tool's owner (opencode config, `custom-tools-first`) |
| F4 | A sentence in `kdaisyui-testing`: in the plugin and `lib`, an inline collection predicate (`all`, `none`, `any`, `count`, `trim { }`) inlines a short-cut or a loop bound whose mutant no test can kill; write a regex, a set operation or a non-inline call | open |
| F5 | `fwb-openspec-reviewer` may read the session's worktrees under `~/dev/worktrees/**` | open - for Oliver's opencode config |
