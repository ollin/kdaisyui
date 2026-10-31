package io.github.ollin.kdaisyui.core

import kotlinx.html.Tag

/**
 * Makes this tag the trigger of [popover]: `daisyButton("Menu", attrs = { popoverTarget(menu, anchored = true) })`.
 *
 * [anchored] also writes the anchor name an anchored popover such as `daisyDropdownPopover`
 * positions itself against. Off by default: DaisyUI's megamenu sets its triggers' anchor itself.
 */
fun Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction? = null, anchored: Boolean = false) {
    attributes["popovertarget"] = popover.id
    if (action != null) attributes["popovertargetaction"] = action.value
    if (anchored) attributes["style"] = listOfNotNull(attributes["style"], "anchor-name:${popover.anchorName}").joinToString(";")
}

/** What a [popoverTarget] trigger does to its popover; without one, the browser toggles it. */
enum class PopoverTargetAction(internal val value: String) { Show("show"), Hide("hide"), Toggle("toggle") }
