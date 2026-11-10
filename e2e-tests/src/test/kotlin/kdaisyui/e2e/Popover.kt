package kdaisyui.e2e

import com.microsoft.playwright.Page

/**
 * Whether the popover with this id is showing, asserted through the `:popover-open` pseudo-class
 * rather than through visibility. That is the browser's own record of the popover's state, so it
 * holds whatever the stylesheet does, including in a spec that runs with JavaScript disabled.
 */
internal fun Page.isPopoverOpen(elementId: String): Boolean =
    locator("#$elementId:popover-open").count() == 1
