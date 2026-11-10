package kdaisyui.e2e

import com.microsoft.playwright.Page

/**
 * The computed value of a CSS property on the element with this id.
 *
 * Everything else in the suite — and every generated component test — asserts that a class name
 * reaches the HTML, which cannot tell whether the class carries any style: a component can be
 * generated correctly, pass its whole suite, and render unstyled. The specs that use this compare
 * two elements rather than assert a value, so none hard-codes a pixel size a DaisyUI restyle
 * would invalidate.
 */
internal fun Page.computedStyle(elementId: String, property: String): String =
    locator("#$elementId")
        .evaluate("(el, prop) => getComputedStyle(el).getPropertyValue(prop)", property) as String
