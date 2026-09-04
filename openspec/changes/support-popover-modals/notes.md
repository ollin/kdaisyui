# Notes

Evidence gathered while working the tasks. Newest section last.

## 1.1 — `customParts` alone cannot express the popover method (2026-09-04)

**Probe.** Added a throwaway entry to `codegen/codegen-config.json`:

```json
"modal": [
  { "name": "Popover", "element": "DIV", "cssClass": "modal" }
]
```

then ran `:lib:generateComponents` and `:lib:generateComponentTests` with `--rerun-tasks`,
read the diff under `lib/generated/`, and reverted both the config entry and the output.

**Observed.** `Modal.kt` gained exactly what the task predicted:

```kotlin
/** Renders `<div class="modal ...">`. */
fun FlowContent.daisyModalPopover(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        if (id != null) attributes["id"] = id.id
        addClassNames("modal")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
```

The element is right and the class is right. **The `popover` attribute is absent**, and there
is no configuration key that would add it: `generateCustomPartFunction`
(`codegen/src/generator-new.js:269`) builds `params` and `body` from fixed literals, reading
only `name`, `element`, `cssClass` and `receiver` off the config entry.

**Assumption 1 of this change is therefore confirmed, not refuted.** `customParts` gets the
shape of the wrapper right and stops one attribute short.

**Two further observations, both bearing on section 2.**

1. The test generators already follow `customParts` without being told to. The same probe
   produced a `custom_part_popover_renders_div` case in `ModalTest.kt` and a
   `modalPopover_defaults` / `modalPopover_all_flags` pair in `ModalCoverageTest.kt`. So
   task 2.2 is an extension of a working path, not new wiring — and the coverage gate will
   cover a new branch automatically once the generator emits one.
2. Every generated assertion is about **classes** (`substringAfter("class=\"")`) or about
   `data-*` markers passed through `attrs`. Nothing asserts a bare valueless attribute today.
   That is the same blind spot the proposal names as the reason this defect survived
   `adapt-daisyui-5-6`: a construction method that adds no CSS class is invisible to both the
   generated tests and `generated-sources-drift`. Task 2.2 has to close it deliberately.
