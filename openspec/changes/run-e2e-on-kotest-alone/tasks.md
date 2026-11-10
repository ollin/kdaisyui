# Tasks

Ordered by uncertainty: section 1 settles A1 and A2 before anything is deleted or ported. Every
step runs under `mcp_Tcr` at the repository root, which runs the whole E2E suite.

**Every port carries a control:** break one expectation of the new spec, run
`./gradlew :e2e-tests:test --tests '<Spec>'` alone, see it fail on that assertion, restore it.
Record the broken expectation in the task. The control is never committed. It stands in for the
mutation tool the E2E suite does not have.

**Estimate, frozen 2026-11-10 at proposal, raw and uncorrected:** 300 minutes developer-only;
forecast `2h 40min (5h DevOnly)` (ratio 0.52 over 177 runs).

| Section | Minutes |
|---|---|
| 1. Open assumptions | 40 |
| 2. The duplicates | 30 |
| 3. Ports | 150 |
| 4. Cucumber out | 30 |
| 5. Gates, archive, pull request | 50, plus CI waits logged as `machine` |

## 1. Open assumptions

- [x] 1.1 `. d (internal)` (A1) For each duplicated feature (`cards`, `dashboard_shell`, `forms`,
  `progressive_loading`, `stats`, `team`, `webjars_assets`' first two scenarios), list every
  `Then`/`And` step beside the twin's assertion that covers it. Record each gap here; each gap
  becomes a task in section 2.
  A1 verified 2026-11-10: no gap. `cards`, `dashboard_shell`, `forms`, `stats` and `team` match
  their twins step for step, with the same texts, roles, placeholders, timeouts (5, 8, 10 s) and
  response-body strings. `progressive_loading` matches `HtmxProgressiveLoadingTest`: the same
  3 s wait, at least 2 fragment responses, and the same six endpoints. `webjars_assets`' first two
  scenarios match `WebjarsAssetsTest`. One difference that loses nothing: the Cucumber endpoint
  scenarios also opened `/` first, and so put the dashboard under the console-error teardown
  once more. Every twin spec still does that in its other test.
- [ ] 1.2 `! F (internal)` (A2) `PlaywrightSpec(javaScriptEnabled: Boolean = true)` (D1), with a
  `WithoutJavaScriptTest` that opens the dashboard with JavaScript off and asserts that no
  `/fragments/` response arrives within 3 s. A positive control the other way round: the same
  assertion in a JavaScript-enabled spec would fail, as `HtmxProgressiveLoadingTest` shows.

## 2. The duplicates

- [x] 2.1 `^ F (internal)` One step per gap from 1.1: the missing assertion added to the Kotest
  twin. None if 1.1 finds none. None: 1.1 found no gap.
- [ ] 2.2 `. r (internal)` Delete `cards.feature`, `dashboard_shell.feature`, `forms.feature`,
  `progressive_loading.feature`, `stats.feature`, `team.feature`, and `webjars_assets.feature`'s
  first two scenarios. The step definitions stay until no feature uses them.

## 3. Ports

Each one `. r (internal)`: the new spec and the deleted feature in one run. Step bodies used by
nothing else any more are deleted in the same run.

- [ ] 3.1 `webjars_assets.feature`' last scenario into `WebjarsAssetsTest`, which deletes the file.
- [ ] 3.2 `tailwind_variant.feature` → `TailwindVariantTest` (4 tests). The stylesheet-rule
  lookup becomes a helper shared with 3.1 if both use it (D3).
- [ ] 3.3 `content_security_policy.feature` → `ContentSecurityPolicyTest` (2 tests, plus one per
  example of the outline: 7).
- [ ] 3.4 `disabled_state.feature` → `DisabledStateTest` (2).
- [ ] 3.5 `tabs.feature` → `TabsTest` (2). The computed-style comparison is shared with 3.4 (D3).
- [ ] 3.6 `dropdown_popover.feature` → `DropdownPopoverTest` and
  `DropdownPopoverWithoutJavaScriptTest` (1 + 1). Control: the expected top edge moved by 80px
  (`daisyui-component-coverage`).
- [ ] 3.7 `popover_modal.feature` → `PopoverModalTest` and `PopoverModalWithoutJavaScriptTest`
  (1 + 1).
- [ ] 3.8 `! F (internal)` `saveScreenshot(name)` in `PlaywrightSpec` (D2), used by its first
  caller in 3.9.
- [ ] 3.9 `megamenu_reference.feature` → `MegamenuReferenceTest` and
  `MegamenuReferenceWithoutJavaScriptTest` (3 + 1), four screenshots under
  `build/reports/screenshots/` as before.
- [ ] 3.10 `profile.feature` → `ProfileTest` (11).

## 4. Cucumber out

- [ ] 4.1 `. r (internal)` (A3, D4) Delete `RunCucumberTest.java`, `CucumberHooks`,
  `PlaywrightWorld` and what is left of `steps/`; the five libraries in
  `e2e-tests/build.gradle.kts` and the catalog with the `cucumber` and `junit-platform` versions;
  the naming-strategy property. Count the tests in `build/test-results`: 22 + 1 (1.2) + 35 = 58.
- [ ] 4.2 `. d (internal)` Cucumber out of `AGENTS.md` (117, 246), `docs/how-to.md:34`,
  `openspec/config.yaml:33` and `.opencode/skills/kdaisyui-testing/SKILL.md` (5, 93, 101-107, and
  the Kotest spec list).

## 5. Gates, archive, pull request

- [ ] 5.1 `./gradlew check` green; `code_health_review` on every new spec; the project values on
  CodeScene (`kdaisyui`, id 14) before the push.
- [ ] 5.2 Archive the change on this branch, commit.
- [ ] 5.3 Rebase onto `origin/main`, run the suite, push, open the pull request through
  `github-mcp` (`test: run the E2E suite on Kotest alone`), CI green, merge with a merge commit.
