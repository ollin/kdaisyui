package io.github.ollin.kdaisyui.core

import kotlinx.html.Tag

/** Makes this tag the trigger of [popover], anchored to it: `daisyButton("Menu", attrs = { popoverTarget(menu) })`. */
fun Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction? = null) {
    attributes["popovertarget"] = popover.id
    if (action != null) attributes["popovertargetaction"] = action.value
    attributes["style"] = listOfNotNull(attributes["style"], "anchor-name:${popover.anchorName}").joinToString(";")
}

/** What a [popoverTarget] trigger does to its popover; without one, the browser toggles it. */
enum class PopoverTargetAction(internal val value: String) { Show("show"), Hide("hide"), Toggle("toggle") }
