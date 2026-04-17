// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/fieldset/+page.md
// Regenerate: cd codegen && npm run generate

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.div
import kotlinx.html.DIV
import kotlinx.html.fieldSet
import kotlinx.html.FIELDSET
import kotlinx.html.FlowContent

fun FlowContent.daisyFieldset(
    extraClasses: String? = null,
    attrs: (FIELDSET.() -> Unit)? = null,
    content: (FIELDSET.() -> Unit),
) {
    fieldSet {
        addClassNames("fieldset")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

fun FlowContent.daisyFieldsetLegend(
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
) {
    div {
        addClassNames("fieldset-legend")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}
