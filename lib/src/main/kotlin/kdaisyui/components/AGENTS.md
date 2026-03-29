# kdaisyUI Components

**Purpose:** DaisyUI component DSL wrappers for kotlinx.html

## OVERVIEW

22 component files, each exposing `FlowContent.daisyXxx()` extension functions.

## STRUCTURE

```
components/
├── Button.kt      # daisyButton, ButtonVariant, ButtonSize
├── Card.kt        # daisyCard, daisyCardBody, daisyCardTitle
├── Input.kt       # daisyInput, InputVariant
├── Alert.kt       # daisyAlert, AlertVariant
├── Badge.kt       # daisyBadge, BadgeVariant
├── ...            # 17 more components
└── core/ClassNames.kt  # addClassNames helper
```

## WHERE TO LOOK

| Task | File |
|------|------|
| Button variants | `Button.kt` → `ButtonVariant` enum |
| Card structure | `Card.kt` → `daisyCard`, `daisyCardBody`, `daisyCardTitle` |
| Form controls | `Input.kt`, `Select.kt`, `Checkbox.kt`, `Radio.kt` |
| Layout | `Drawer.kt`, `Menu.kt`, `Dropdown.kt` |
| Data display | `Table.kt`, `Stats.kt`, `Badge.kt` |

## CONVENTIONS

- **Naming**: `daisy<Component>` prefix for all DSL functions
- **Variants**: Enum with `internal val className` mapping to DaisyUI classes
- **Parameters**: `variant`, `size`, `extraClasses`, `attrs`, `content` — consistent across components
- **Core utility**: Use `addClassNames()` from `kdaisyui.core`

## PATTERN (Button example)

```kotlin
enum class ButtonVariant(internal val className: String) {
    Primary("btn-primary"),
    Secondary("btn-secondary"),
    // ...
}

fun FlowContent.daisyButton(
    text: String? = null,
    variant: ButtonVariant? = null,
    size: ButtonSize? = null,
    extraClasses: String? = null,
    attrs: (BUTTON.() -> Unit)? = null,
    content: (BUTTON.() -> Unit)? = null,
) { /* ... */ }
```

## ADDING NEW COMPONENT

1. Create `<Component>.kt` in this directory
2. Define `<Component>Variant` enum with `internal val className`
3. Define `<Component>Size` enum if applicable
4. Add `FlowContent.daisy<Component>()` extension function
5. Add test in `lib/src/test/kotlin/kdaisyui/components/`
