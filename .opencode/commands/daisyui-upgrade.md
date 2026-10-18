---
description: "Move kdaisyui to a new DaisyUI version, running every step and guard in order"
---

Upgrade this repository to a new DaisyUI version.

**Load the `kdaisyui-daisyui-upgrade` skill first.** It carries why each step exists and how to
read each guard's failure; this command is only the running order.

**Input**: the target DaisyUI version after `/daisyui-upgrade` (e.g. `/daisyui-upgrade 5.8.0`).
If omitted, ask — do not guess, and do not read "latest" from the network without confirming it.

**Steps**

1. **Check the version is usable.** It must have a published `org.webjars.npm:daisyui`
   artifact. State which version you are moving from and to, then set `daisyui` in
   `gradle/libs.versions.toml`, the single source of every version.

2. **Sync the submodule.**

   ```
   just sync-daisyui
   ```

3. **Read what changed in DaisyUI's documentation.**

   ```
   just daisyui-delta v<old> v<new>
   ```

   Also read `daisyui/CHANGELOG.md` back to the old version, for construction methods the report
   cannot see because they change no class.

4. **Edit `codegen/model/`.** Place every added class, delete every removed one, add or skip new
   components. Whether a new class is an axis member or a toggle is a design decision; say which
   and why in the commit.

5. **Regenerate.**

   ```
   just generate
   ```

   It fails, naming component and class, until step 4 is complete. Read the diff it prints.

6. **Update both API baselines and read the diff.**

   ```
   just update-api
   ```

7. **Write the migration note** in `README.md` if anything broke. A breaking change with no
   entry does not ship.

8. **Run the gates.**

   ```
   ./gradlew check
   ./gradlew :lib:pitest
   ```

9. **Commit** in Arlo notation, model edits and regenerated output together per step, so the
   reviewer sees which decision produced which output.

**Guardrails**

- Never re-dump an artefact to make a guard green without reading its diff first.
- Never hand-edit `lib/generated/**`, `docs/reference/**`, `llms.txt` or either `lib/api/*.api`.
  `codegen/model/` is the one place to edit.
- Stop and report if a prerequisite is missing (Node, submodules, Docker) rather than skipping a
  step.
