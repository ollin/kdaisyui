---
description: Add a new DaisyUI component to the kotlinx.html DSL library
---

# Goal
Add a new DaisyUI component to this repository in a consistent way (API, tests, example usage). Start with `Button`.

# Preconditions
- The component exists in DaisyUI documentation.
- We keep the public API minimal and stable.

# Steps
1. Decide the **API surface**
   - Choose the entry function name (e.g. `button(...)`, `daisyButton(...)` if conflict).
   - Decide whether the component is:
     - an extension on `FlowContent`/`TagConsumer` (preferred for kotlinx.html DSL)
     - or a dedicated builder object
   - Decide supported variants:
     - required classes (e.g. `btn`)
     - common modifiers (e.g. `btn-primary`, `btn-outline`, `btn-sm`, `btn-lg`, `btn-wide`, `btn-block`, `btn-circle`, `btn-square`, `btn-disabled`)

2. Implement core CSS class composition
   - Provide a small internal helper to merge classes safely (avoid double spaces, handle existing `class` attr).
   - Ensure the base class is always present (for `Button`: `btn`).

3. Add the component implementation
   - Place in the main library module under a dedicated package (e.g. `kdaisyui.components`).
   - Keep overload count low; prefer typed enums + optional parameters.

4. Add tests
   - Add a unit test that renders HTML and asserts the resulting markup contains the expected tag + class list.
   - Cover at least:
     - base rendering
     - one variant modifier
     - disabled state

5. Add example usage
   - Add a small snippet/function in test or a dedicated sample (if we add a samples module later).
   - Ensure the example compiles.

6. Validate
   - Run `./gradlew test`.

# Definition of Done
- Public API is consistent with existing components.
- Tests for HTML output pass.
- Component supports at least the most common DaisyUI variants.
