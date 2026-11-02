## Estimate

Frozen 2026-11-02 before implementation, raw. Ledger run `2026-11-02-kdaisyui-one-block-lambda`.

| Block | Estimate |
|---|---|
| 1. Verify the assumptions | 20m |
| 2. Enablers | 60m |
| 3. The behaviour change | 120m |
| 4. Documentation | 45m |
| 5. Gates and archive | 40m |
| **Total** | **285m** |

An informal "about half a day" (240m) was given in conversation before the scope was written down.

## 1. Verify the assumptions

- [x] 1.1 Count every call in `example-app/`, `e2e-tests/`, `lib/src/test/`, `README.md` and
  `docs/` that passes `text` together with a lambda, and note for each whether its lambda writes
  children that must follow the text. Record the count and the verdict in design.md, Risks. Any
  such call refutes the Assumed requirement "A function's text and block add up": stop and revise
  the order before task 2. `. d (internal)`
- [x] 1.2 Pin the kotlinx.html rule the order rests on in a hand-written test: an attribute set
  inside a builder before its first text is rendered, the same attribute after `+text` throws
  `IllegalStateException`, for `createHTML` and `appendHTML`. `. r (internal)`

## 2. Enablers, no rendered output changes

- [x] 2.1 Rewrite every hand-written call that passes `content = { … }` as a trailing lambda, in
  `example-app/`, `e2e-tests/`, `lib/src/test/`, `README.md` and `docs/`. The compiler proves the
  binding; the suite proves the output. `. r`
- [x] 2.2 Move every hand-written `attrs = { … }` into the call's existing lambda, as its first
  statements, where the function takes no `text` and renders a non-void element. `attrs` ran
  directly before `content`, so the output is unchanged. Leave the void and `text` cases for 3.2.
  `^ r`
- [x] 2.3 ~~Let `test-generator.ts` take the hatch names from the shape.~~ **Dropped 2026-11-02.**
  Task 3.2 rewrites the same lines anyway, mostly by deleting the required-lambda and `ATTRS`
  paths, and a lookup with one value left afterwards is an indirection, not an enabler.
- [x] 2.4 ~~Let `documented-elements.ts` take the sibling lambda's name from the shape.~~
  **Dropped 2026-11-02**, for the same reason: one literal in `callArguments` changes in 3.2.

## 3. The behaviour change

- [x] 3.1 Text and content add up: `contentLines` in `generator-new.ts` emits the content call,
  then `if (text != null) +text`. Rewrite `TextAndContentPrecedenceTest` into the three scenarios
  of "A function's text and block add up", regenerate, run TCR at the repository root. `! F`
- [x] 3.2 One block: `escapeHatchParameters` drops `attrs` and renames `content` to `block`,
  typed `TAG.() -> Unit = {}`, also on void elements; both bodies call `block()` unconditionally;
  `join-scope.ts` follows. Move the remaining hand-written `attrs` calls (void elements, `text`
  functions) into the block, regenerate, run `just update-api`, read both API diffs, run TCR at
  the repository root. `! F`
- [x] 3.3 Add a hand-written test for the scenario "No second lambda": no public function in
  `io.github.ollin.kdaisyui.components` declares a parameter named `attrs` or `content`. `. r (internal)`

## 4. Documentation

- [ ] 4.1 `codegen/llms-template.md`, `codegen/llms-full-template.md`: the frame is `id`,
  `extraClasses` and one `block`; regenerate `llms.txt` and `llms-full.txt`. `. d`
- [ ] 4.2 `docs/explanation.md` (common parameters, escape hatches) and `docs/how-to.md`
  (custom attributes: write attributes before children). `. d`
- [ ] 4.3 `docs/upgrading.md` "How to migrate from 0.8.x", the text change first; version 0.9.0 in
  `gradle.properties`. `. d`

## 5. Gates and archive

- [ ] 5.1 `./gradlew check` including E2E, `:lib:pitest` at 100% test strength, and StrykerJS on
  the changed lines under `codegen/src/` with no survivor.
- [ ] 5.2 `code_health_review` on every changed hand-written and codegen file, then
  `pre_commit_code_health_safeguard`; read the server's project values before the push.
- [ ] 5.3 Archive the change on this branch and commit the archival. `. d (internal)`
