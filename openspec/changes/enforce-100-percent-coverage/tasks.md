## 1. Wire Kover (build stays green — no gate yet)

- [x] 1.1 Add `kover = "0.9.8"` to `[versions]` and the `kover` plugin to `[plugins]` in `gradle/libs.versions.toml` (refactoring; catalog only, no behavior)
- [x] 1.2 Apply `alias(libs.plugins.kover)` to root `build.gradle.kts` and declare `kover(project(":lib"))` + `kover(project(":ktor-integration"))` in root `dependencies` (refactoring; wiring, run `./gradlew help`)
- [x] 1.3 Apply `alias(libs.plugins.kover)` to `lib/build.gradle.kts` and `ktor-integration/build.gradle.kts` (refactoring; wiring)
- [x] 1.4 Add `kover { reports { total { html { onCheck = true }; xml { onCheck = true } } } }` to root (no verify rule yet) (refactoring; reports only, run `./gradlew koverHtmlReport`)

## 2. Baseline measurement

- [x] 2.1 Run `./gradlew koverHtmlReport koverXmlReport` and record the aggregated baseline line% and branch% for `:lib` + `:ktor-integration` into the change README/notes (documentation; no code)
- [x] 2.2 From the report, list the uncovered classes/branches grouped by component batch below, to confirm the batch ordering matches reality (documentation; no code)

## 3. Cover hand-written :lib core + icons

- [x] 3.1 Add tests for `core/ClassNames.kt` covering all line+branch paths AND assert results (feature-test; small, run `:lib:test`)
- [x] 3.2 Add tests for `core/TagId.kt` (the HtmlId hierarchy) covering all paths AND assert (feature-test; small) — extend existing `HtmlIdTest.kt`
- [x] 3.3 Add tests for `icons/HeroIconSize.kt` + `icons/HeroIconVariant.kt` enums (feature-test; small)
- [x] 3.4 Add tests for the generated heroicons source covering each variant/size path (feature-test; small)

## 4. Cover generated components — batch A (Alert–Carousel)

- [x] 4.1 Add render tests asserting HTML output for Alert, Avatar, Badge, Breadcrumbs, Button (feature-test; one test file/batch, `:lib:test`)
- [x] 4.2 Add render tests for Calendar, Card, Carousel incl. all variant/size/boolean-modifier branches (feature-test)

## 5. Cover generated components — batch B (Chat–Drawer)

- [x] 5.1 Add render tests for Chat, Checkbox, Collapse, Countdown, Diff (feature-test)
- [x] 5.2 Add render tests for Divider, Dock, Drawer incl. all branches (feature-test)

## 6. Cover generated components — batch C (Dropdown–Hero)

- [x] 6.1 Add render tests for Dropdown, Fab, Fieldset, FileInput, Filter (feature-test)
- [x] 6.2 Add render tests for Footer, Hero incl. all branches (feature-test)

## 7. Cover generated components — batch D (Hover3d–List)

- [x] 7.1 Add render tests for Hover3d, HoverGallery, Indicator, Input, Join (feature-test)
- [x] 7.2 Add render tests for Kbd, Label, Link, List incl. all branches (feature-test)

## 8. Cover generated components — batch E (Loading–Modal)

- [x] 8.1 Add render tests for Loading, Mask, Menu (feature-test)
- [x] 8.2 Add render tests for MockupBrowser, MockupCode, MockupPhone, MockupWindow, Modal (feature-test)

## 9. Cover generated components — batch F (Navbar–Rating)

- [x] 9.1 Add render tests for Navbar, Progress, RadialProgress, Radio (feature-test)
- [x] 9.2 Add render tests for Range, Rating incl. all branches (feature-test)

## 10. Cover generated components — batch G (Select–Steps)

- [x] 10.1 Add render tests for Select, Skeleton, Stack, Stat (feature-test)
- [x] 10.2 Add render tests for Status, Steps incl. all branches (feature-test)

## 11. Cover generated components — batch H (Swap–Validator)

- [x] 11.1 Add render tests for Swap, Tab, Table, Textarea (feature-test)
- [x] 11.2 Add render tests for TextRotate, ThemeController, Timeline, Toast, Toggle, Tooltip, Validator (feature-test)

## 12. Cover :ktor-integration

- [x] 12.1 Add tests for `Resolvable.kt` covering all line+branch paths AND assert (feature-test; extend `ResolvableTest.kt`)

## 13. Close residual gaps + document exclusions

- [x] 13.1 Regenerate the aggregated report; for every remaining uncovered branch, either add a targeted test OR add a documented `kover { reports { filters { excludes { ... } } } }` entry with a written justification (feature-test/refactoring; iterate until report shows 100/100)
- [x] 13.2 Verify the aggregated report reads exactly 100% LINE and 100% BRANCH before sharpening the gate (documentation; evidence captured)

## 14. Sharpen the hard gate (LAST — build now fails below 100%)

- [x] 14.1 Add the two verify rules (LINE `minValue=100`, BRANCH `minValue=100`, `AggregationType.COVERED_PERCENTAGE`) under `kover { reports { total { verify { ... } } } }` in root AND set `verify { onCheck = true }` (feature; the gate goes hard, run `./gradlew check` — must pass at 100%, fail if any line reverted)
- [x] 14.2 Confirm `./gradlew check` fails when coverage is artificially dropped (delete one assertion, expect non-zero exit) then restore (feature-test; proves the gate bites)

## 15. CI + docs

- [ ] 15.1 Confirm `.github/workflows/ci.yml` runs a task that triggers `check`/`koverVerify` so CI enforces the gate; adjust if the unit-test job calls a narrower task (refactoring; CI wiring)
- [ ] 15.2 Document the 100% coverage gate + how to run the report locally in `AGENTS.md` (documentation)
