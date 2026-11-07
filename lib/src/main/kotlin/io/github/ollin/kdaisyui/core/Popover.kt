package io.github.ollin.kdaisyui.core

import kotlinx.html.Tag

/**
 * Makes this tag the trigger of [popover]: `daisyButton("Menu") { popoverTarget(menu, anchored = true) }`.
 *
 * [anchored] also writes the anchor name an anchored popover such as `daisyDropdownPopover`
 * positions itself against, as `data-anchor-name` rather than an inline style, which a strict
 * Content-Security-Policy drops; `kdaisyui.css` turns it into `anchor-name`. Off by default:
 * DaisyUI's megamenu sets its triggers' anchor itself.
 */
fun Tag.popoverTarget(popover: HtmlId, action: PopoverTargetAction? = null, anchored: Boolean = false) {
    attributes["popovertarget"] = popover.id
    if (action != null) attributes["popovertargetaction"] = action.value
    if (anchored) attributes["data-anchor-name"] = popover.anchorName
}

/** What a [popoverTarget] trigger does to its popover; without one, the browser toggles it. */
enum class PopoverTargetAction(internal val value: String) { Show("show"), Hide("hide"), Toggle("toggle") }
