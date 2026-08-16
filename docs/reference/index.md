# Component reference

Type-safe [DaisyUI](https://daisyui.com/) components for [kotlinx.html](https://github.com/Kotlin/kotlinx.html).
All components are extension functions on `FlowContent`:

```kotlin
import io.github.ollin.kdaisyui.components.*
```

---

## All components

| Component | Description | Tag |
|---|---|---|
| [Alert](alert.md) | Status messages and notifications | `<div>` |
| [Aura](aura.md) | Border light effect wrapping a component | `<div>` |
| [Avatar](avatar.md) | User and entity representations | `<div>` |
| [Badge](badge.md) | Labels, counts, and status tags | `<span>` |
| [Breadcrumbs](breadcrumbs.md) | Navigation trail showing current location | `<div>` |
| [Button](button.md) | Actions and triggers | `<button>` |
| [Calendar](calendar.md) | Calendar date picker widget | `<div>` |
| [Card](card.md) | Content containers with body and title | `<div>` |
| [Carousel](carousel.md) | Slideshow of images or content | `<div>` |
| [Chat](chat.md) | Chat bubble layout for conversations | `<div>` |
| [Checkbox](checkbox.md) | Boolean toggles for forms | `<input>` |
| [Collapse](collapse.md) | Collapsible content sections | `<div>` |
| [Countdown](countdown.md) | Timer countdown display | `<span>` |
| [Diff](diff.md) | Side-by-side diff viewer | `<figure>` |
| [Divider](divider.md) | Visual separator between sections | `<div>` |
| [Dock](dock.md) | macOS-style dock navigation | `<div>` |
| [Drawer](drawer.md) | Sidebar layout with toggle | `<div>` |
| [Dropdown](dropdown.md) | Context menus and action lists | `<details>` |
| [Fab](fab.md) | Floating action button with expandable actions | `<div>` |
| [Fieldset](fieldset.md) | Groups related form controls | `<fieldset>` |
| [FileInput](fileInput.md) | File upload input field | `<input>` |
| [Filter](filter.md) | Filter reset for search and forms | `<form>` |
| [Footer](footer.md) | Page footer layout | `<footer>` |
| [Hero](hero.md) | Hero banner with overlay | `<div>` |
| [Hover3d](hover3d.md) | 3D hover effect container | `<div>` |
| [HoverGallery](hoverGallery.md) | Gallery with hover effects | `<figure>` |
| [Indicator](indicator.md) | Overlays badges on elements | `<div>` |
| [Input](input.md) | Text entry fields | `<input>` |
| [Join](join.md) | Groups buttons and inputs together | `<div>` |
| [Kbd](kbd.md) | Keyboard key display | `<kbd>` |
| [Label](label.md) | Field labels and helper text | `<span>` |
| [Link](link.md) | Styled anchor elements | `<a>` |
| [List](list.md) | Styled list layout | `<ul>` |
| [Loading](loading.md) | Loading spinners and indicators | `<span>` |
| [Mask](mask.md) | CSS masks for cropping images to shapes | `<img>` |
| [Megamenu](megamenu.md) | Horizontal menu with popover navigation blocks | `<div>` |
| [Menu](menu.md) | Navigation lists with submenus | `<ul>` |
| [MockupBrowser](mockupBrowser.md) | Browser window mockup frame | `<div>` |
| [MockupCode](mockupCode.md) | Code block mockup frame | `<div>` |
| [MockupPhone](mockupPhone.md) | Phone mockup frame | `<div>` |
| [MockupWindow](mockupWindow.md) | Desktop window mockup frame | `<div>` |
| [Modal](modal.md) | Modal dialogs | `<dialog>` |
| [Navbar](navbar.md) | Navigation bar layout | `<div>` |
| [Otp](otp.md) | One-time password / verification code input | `<div>` |
| [Progress](progress.md) | Progress bar indicator | `<progress>` |
| [RadialProgress](radialProgress.md) | Circular progress indicator | `<div>` |
| [Radio](radio.md) | Single-choice option groups | `<input>` |
| [Range](range.md) | Slider for numeric ranges | `<input>` |
| [Rating](rating.md) | Star rating input | `<div>` |
| [Select](select.md) | Dropdown option pickers | `<select>` |
| [Skeleton](skeleton.md) | Content placeholder loading indicator | `<div>` |
| [Stack](stack.md) | Stack overlapping elements | `<div>` |
| [Stat](stat.md) | Key metrics and numbers | `<div>` |
| [Status](status.md) | Small status indicator dot | `<span>` |
| [Steps](steps.md) | Step-by-step progress indicator | `<ul>` |
| [Swap](swap.md) | Toggle between two states | `<label>` |
| [Tab](tab.md) | Tabbed navigation | `<button>` |
| [Table](table.md) | Structured data in rows and columns | `<table>` |
| [Textarea](textarea.md) | Multi-line text input | `<textarea>` |
| [TextRotate](textRotate.md) | Rotated text display | `<span>` |
| [ThemeController](themeController.md) | DaisyUI theme controller toggle | `<input>` |
| [Timeline](timeline.md) | Vertical or horizontal timeline | `<ul>` |
| [Toast](toast.md) | Toast notification container | `<div>` |
| [Toggle](toggle.md) | Switch-style boolean input | `<input>` |
| [Tooltip](tooltip.md) | Hover tooltip on elements | `<div>` |
| [Validator](validator.md) | Form validation wrapper | `<input>` |

---

## Common parameters

Every component accepts these escape-hatch parameters:

| Parameter | Type | Description |
|---|---|---|
| `extraClasses` | `String?` | Additional CSS classes, merged safely (no duplicates) |
| `attrs` | `(TAG.() -> Unit)?` | Direct access to the underlying kotlinx.html tag |
| `content` | `(TAG.() -> Unit)?` | Nested HTML content (not all components) |

---

## Core utility

### `addClassNames` — `io.github.ollin.kdaisyui.core`

```kotlin
fun Tag.addClassNames(vararg classNames: String)
fun Tag.addClassNames(classNames: String?)
```

Merges CSS class tokens into the `class` attribute safely: deduplicates, handles null and whitespace, preserves insertion order. Used internally by all components. Also available for raw kotlinx.html elements:

```kotlin
div {
    addClassNames("flex items-center gap-4")
}
```

## Requirements

- JDK: see [`.tool-versions`](../.tool-versions) → `java` (toolchain configured in [`buildSrc/.../kdaisyui.kotlin-library-conventions.gradle.kts`](../buildSrc/src/main/kotlin/kdaisyui.kotlin-library-conventions.gradle.kts))
- Kotlin: see [`gradle/libs.versions.toml`](../gradle/libs.versions.toml) → `kotlin`
- kotlinx-html: see [`gradle/libs.versions.toml`](../gradle/libs.versions.toml) → `kotlinx-html`