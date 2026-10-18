<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/stat/+page.md
Regenerate: just generate
-->

# Stat

[DaisyUI documentation →](https://daisyui.com/components/stat/)

Key metrics and numbers. Renders `<div class="stats ...">`.

```kotlin
// StatsDirection: Horizontal | Vertical
fun FlowContent.daisyStats(
    id: HtmlId? = null,
    direction: ClassValues<StatsDirection>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStatsClasses(direction: ClassValues<StatsDirection>? = null)
```

```kotlin
fun FlowContent.daisyStat(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStatClasses()
```

```kotlin
fun FlowContent.daisyStatTitle(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStatTitleClasses()
```

```kotlin
fun FlowContent.daisyStatValue(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStatValueClasses()
```

```kotlin
fun FlowContent.daisyStatDesc(
    text: String? = null,
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit)? = null,
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStatDescClasses()
```

```kotlin
fun FlowContent.daisyStatFigure(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStatFigureClasses()
```

```kotlin
fun FlowContent.daisyStatActions(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyStatActionsClasses()
```
