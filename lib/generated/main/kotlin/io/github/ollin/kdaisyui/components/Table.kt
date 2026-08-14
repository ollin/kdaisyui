// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/table/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.table
import kotlinx.html.TABLE

/** Size variants for this component (CSS prefix: `table-`) */
enum class TableSize(internal val className: String) {
    /** CSS: `table-xs` — Extra small size */
    Xs("table-xs"),
    /** CSS: `table-sm` — Small size */
    Sm("table-sm"),
    /** CSS: `table-md` — Medium size */
    Md("table-md"),
    /** CSS: `table-lg` — Large size */
    Lg("table-lg"),
    /** CSS: `table-xl` — Extra large size */
    Xl("table-xl"),
}


/**
 * Table can be used to show a list of data in a table format. Renders `<table class="table ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param pinCols — For <table> to make all the <th> columns sticky
 * @param pinRows — For <table> to make all the rows inside <thead> and <tfoot> sticky
 * @param zebra — For <table> to show zebra stripe rows
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyTable(
    id: HtmlId? = null,
    size: TableSize? = null,
    pinCols: Boolean = false,
    pinRows: Boolean = false,
    zebra: Boolean = false,
    extraClasses: String? = null,
    attrs: (TABLE.() -> Unit)? = null,
    content: (TABLE.() -> Unit),
) {
    table {
        if (id != null) attributes["id"] = id.id
        addClassNames("table")
        if (size != null) addClassNames(size.className)
        if (pinCols) addClassNames("table-pin-cols")
        if (pinRows) addClassNames("table-pin-rows")
        if (zebra) addClassNames("table-zebra")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
