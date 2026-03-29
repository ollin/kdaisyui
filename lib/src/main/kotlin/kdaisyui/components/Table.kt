// GENERATED — DO NOT EDIT
// Source: codegen/src/config/table.yml + daisyui table.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.FlowContent
import kotlinx.html.table
import kotlinx.html.TABLE

enum class TableSize(internal val className: String) {
    Xs("table-xs"),
    Sm("table-sm"),
    Md("table-md"),
    Lg("table-lg"),
    Xl("table-xl"),
}


fun FlowContent.daisyTable(
    size: TableSize? = null,
    pinCols: Boolean = false,
    pinRows: Boolean = false,
    zebra: Boolean = false,
    extraClasses: String? = null,
    attrs: (TABLE.() -> Unit)? = null,
    content: (TABLE.() -> Unit),
) {
    table {
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
