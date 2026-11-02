// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/kbd/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.FlowContent
import kotlinx.html.kbd
import kotlinx.html.KBD
import kotlinx.html.Tag

/** Size variants for this component (CSS prefix: `kbd-`) */
enum class KbdSize(internal val className: String) : ClassValues<KbdSize> {
    /** CSS: `kbd-xs` — Extra small size */
    Xs("kbd-xs"),
    /** CSS: `kbd-sm` — Small size */
    Sm("kbd-sm"),
    /** CSS: `kbd-md` — Medium size */
    Md("kbd-md"),
    /** CSS: `kbd-lg` — Large size */
    Lg("kbd-lg"),
    /** CSS: `kbd-xl` — Extra large size */
    Xl("kbd-xl"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Kbd is used to display keyboard shortcuts. Renders `<kbd class="kbd ...">`.
 * @param text — Inline text, appended after anything [content] writes
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param size — Size variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content; [text], if given, is appended after it
 */
fun FlowContent.daisyKbd(
    text: String? = null,
    id: HtmlId? = null,
    size: ClassValues<KbdSize>? = null,
    extraClasses: String? = null,
    attrs: (KBD.() -> Unit)? = null,
    content: (KBD.() -> Unit)? = null,
) {
    kbd {
        if (id != null) attributes["id"] = id.id
        addClassNames("kbd")
        addClassNames(size)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        if (content != null) content()
        if (text != null) +text
    }
}

/** The classes `daisyKbd` writes, on the element this is called on. */
fun Tag.daisyKbdClasses(
    size: ClassValues<KbdSize>? = null,
) {
    addClassNames("kbd")
    addClassNames(size)
}
