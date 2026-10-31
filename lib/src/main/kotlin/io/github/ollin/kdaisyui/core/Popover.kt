package io.github.ollin.kdaisyui.core

import kotlinx.html.Tag

/** Makes this tag the trigger of [popover], anchored to it: `daisyButton("Menu", attrs = { popoverTarget(menu) })`. */
fun Tag.popoverTarget(popover: HtmlId) {
    attributes["popovertarget"] = popover.id
    attributes["style"] = listOfNotNull(attributes["style"], "anchor-name:${popover.anchorName}").joinToString(";")
}
