---
description: "Move kdaisyui to a new DaisyUI version, running every regeneration and guard in order"
---

Upgrade this repository to a new DaisyUI version.

**Load the `kdaisyui-daisyui-upgrade` skill first.** It carries why each step exists and how to
read each guard's failure; this command is only the running order.

**Input**: the target DaisyUI version after `/daisyui-upgrade` (e.g. `/daisyui-upgrade 5.8.0`).
If omitted, ask — do not guess, and do not read "latest" from the network without confirming it.

**Steps**

1. **Check the version is usable**

   It must have a published `org.webjars.npm:daisyui` artifact, because `:ktor-integration`
   serves that webjar and the measurement reads it. A version without one makes generated
   components reference CSS the library cannot deliver.

   State which version you are moving from and to, then set `daisyui` in
   `gradle/libs.versions.toml`. That file is the single source — never hardcode the version
   anywhere else.

2. **Sync the submodule**

   ```
   just sync-daisyui
   ```

   Everything after this reads the submodule, so a failure here stops the run.

3. **Regenerate the sources**

   ```
   just generate
   ```

   Read the diff it prints. New or removed components, changed parameters and changed reference
   pages are all API changes. Never edit anything under `lib/generated/**` or `docs/reference/**`
   by hand.

4. **Re-measure exclusivity**

   ```
   just measure-exclusivity
   ```

   Needs a system Chromium. Read the diff: a changed verdict is a changed public API.

   - A group that became all-`exclusive` is now a choice and the next run FAILS until
     `enumNames` names it. Naming it is an editorial decision — propose a name by intention,
     never by implementation, and say what question the enum answers.
   - A group that gained a `compose` or `same` pair must lose its enum. That is a breaking
     change and needs a migration note.
   - If the diff shows many new `same` verdicts, suspect the probe before believing it. That was
     the failure signature of all three defects the probe has had.

   If the run reports nothing to do, say so rather than re-dumping the file.

5. **Update both API baselines**

   ```
   just update-api
   ```

   Read the diff rather than accepting it. This is the step that tells you whether the upgrade
   is breaking.

6. **Write the migration note**

   If anything broke, add a **How to migrate** entry to `README.md` naming every affected
   function. A breaking change with no entry does not ship.

7. **Run the gates**

   ```
   ./gradlew check
   ./gradlew :lib:pitest
   ```

   `check` needs Docker. `pitest` is deliberately outside it; a surviving mutant is not always a
   missing assertion — it can be code that cannot matter, and then the answer is to delete the
   code.

8. **Commit**

   Arlo notation, one behaviour per commit, per the `risk-aware-commits` skill. Keep the
   regenerated artefacts in separate commits from any hand-written reaction to them, so a
   reviewer can see which changes the generator made and which a human chose.

**Guardrails**

- Never re-dump an artefact to make a guard green without reading its diff first. The guard is
  the only place that kind of breakage becomes visible.
- Never hand-edit `codegen/exclusivity.json`, `lib/generated/**`, `docs/reference/**` or either
  `lib/api/*.api`.
- Never reason about whether two classes conflict — measure. Six earlier attempts in this
  repository derived it from CSS or from class names and all six were wrong.
- Never make a codegen unit test read the submodule; that job is deliberately free of one.
- Stop and report if a step's prerequisite is missing (Node, submodules, Chromium, Docker)
  rather than skipping the step and continuing.
