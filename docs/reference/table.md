<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/table/+page.md
Regenerate: just generate
-->

# Table

[DaisyUI documentation →](https://daisyui.com/components/table/)

Structured data in rows and columns. Renders `<table class="table ...">`.

```kotlin
// TableSize: Xs | Sm | Md | Lg | Xl
fun FlowContent.daisyTable(
    id: HtmlId? = null,
    size: TableSize? = null,
    pinCols: Boolean = false,  // For <table> to make all the <th> columns sticky
    pinRows: Boolean = false,  // For <table> to make all the rows inside <thead> and <tfoot> sticky
    zebra: Boolean = false,  // For <table> to show zebra stripe rows
    extraClasses: String? = null,
    attrs: (TABLE.() -> Unit)? = null,
    content: (TABLE.() -> Unit),
)
```
