package io.github.ollin.kdaisyui.core

import kotlinx.html.Tag

/**
 * Makes this tag the trigger of [popover]: `daisyButton("Menu") { popoverTarget(menu) }`.
 *
 * It writes no anchor. The browser makes the trigger that opens a popover its implicit anchor
 * (Chrome 133, Safari 26, Firefox 147), so `daisyDropdownPopover` opens beside it with no inline
 * style, which a strict Content-Security-Policy would refuse.
 */
fun Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction? = null) {
    attributes["popovertarget"] = popover.id
    if (action != null) attributes["popovertargetaction"] = action.value
}

/** Since 0.10, [anchored] does nothing: the trigger that opens a popover is its anchor already. */
@Deprecated("The browser anchors a popover to the trigger that opens it; anchored does nothing.", ReplaceWith("popoverTarget(popover, action)"))
@Suppress("UNUSED_PARAMETER")
fun Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction? = null, anchored: Boolean = false) = popoverTarget(popover, action)

/** What a [popoverTarget] trigger does to its popover; without one, the browser toggles it. */
enum class PopoverTargetAction(internal val value: String) { Show("show"), Hide("hide"), Toggle("toggle") }
