// GENERATED — DO NOT EDIT
// Source: codegen/src/config/fieldset.yml + daisyui fieldset.css
// Regenerate: ./gradlew generateComponents

package kdaisyui.components

import kdaisyui.core.addClassNames
import kotlinx.html.fieldSet
import kotlinx.html.FIELDSET
import kotlinx.html.FlowContent
import kotlinx.html.legend
import kotlinx.html.LEGEND
import kotlinx.html.span
import kotlinx.html.SPAN

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

fun FIELDSET.daisyFieldsetLegend(
    text: String? = null,
    extraClasses: String? = null,
    attrs: (LEGEND.() -> Unit)? = null,
    content: (LEGEND.() -> Unit)? = null,
) {
    legend {
        addClassNames("fieldset-legend")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}

fun FIELDSET.daisyFieldsetLabel(
    text: String? = null,
    extraClasses: String? = null,
    attrs: (SPAN.() -> Unit)? = null,
    content: (SPAN.() -> Unit)? = null,
) {
    span {
        addClassNames("fieldset-label")
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        when {
            content != null -> content()
            text != null -> +text
        }
    }
}
