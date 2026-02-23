# Skill: kotlinx.html DaisyUI DSL design

## Purpose
Provide consistent patterns for building a DaisyUI-flavoured DSL on top of `kotlinx.html`.

## Core principles
- Prefer **thin wrappers** over HTML tags.
- Keep the API **type-safe** where it matters (variants/sizes), but do not explode overloads.
- Make it easy to **opt-out / extend**: allow additional classes and arbitrary attributes.

## Proposed package layout
- `kdaisyui.core`:
  - class merging helpers
  - attribute helpers
  - common enums/marker interfaces
- `kdaisyui.components`:
  - DaisyUI components (Button, Card, Navbar, ...)

## DSL patterns
### Entry points
Prefer extension functions on `FlowContent` so usage looks like native kotlinx.html:

- `FlowContent.daisyButton(...) { ... }` if we need to avoid clashing with `button {}`
- Otherwise `FlowContent.button(...)` can be used only if we intentionally shadow/replace the default.

### Class handling
- Always include required DaisyUI base classes (e.g. `btn`).
- Support:
  - `classes: String?` (raw)
  - or `extraClasses: Iterable<String>`
  - and typed modifiers via enums
- Merge rules:
  - ignore null/blank
  - trim tokens
  - avoid duplicated spaces

### Variants as enums
Use enums to control DaisyUI modifiers:
- `ButtonVariant`: `Primary`, `Secondary`, `Accent`, `Info`, `Success`, `Warning`, `Error`, `Ghost`, `Link`, `Neutral`
- `ButtonSize`: `Xs`, `Sm`, `Md`, `Lg`
Map them to DaisyUI classes.

### Attributes and disabled state
- Prefer explicit parameters for common attrs:
  - `disabled: Boolean = false`
  - `type: ButtonType? = null` (optional)
- Still allow arbitrary attrs:
  - `attrs: (BUTTON.() -> Unit)? = null` (or the appropriate tag type)

### Rendering correctness
- Ensure correct tag semantics:
  - Button component usually renders `<button>` but sometimes should be `<a>`; we can support both later.

## Testing strategy
- Render to string with `kotlinx.html.stream.createHTML()` and assert:
  - tag name
  - presence of required class
  - presence/absence of modifier classes
  - disabled attribute

## Roadmap (high level)
1. Foundation (`core` helpers + first component: Button)
2. Form controls (Input, Select, Checkbox, Radio, Toggle)
3. Layout (Card, Navbar, Drawer, Sidebar, Hero)
4. Feedback (Alert, Toast, Modal)
5. Data display (Table, Badge, Avatar)
6. App-level composition helpers (page shell, navigation patterns)
