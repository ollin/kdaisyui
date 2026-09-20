// GENERATED — DO NOT EDIT
// Source: daisyui/packages/docs/src/routes/(routes)/components/join/+page.md
// Regenerate: cd codegen && npm run generate

package io.github.ollin.kdaisyui.components

import io.github.ollin.kdaisyui.core.addClassNames
import io.github.ollin.kdaisyui.core.ClassValues
import io.github.ollin.kdaisyui.core.HtmlId
import kotlinx.html.BUTTON
import kotlinx.html.ButtonType
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.SELECT
import kotlinx.html.TagConsumer
import kotlinx.html.visit

/** Direction variants for this component (CSS prefix: `join-`) */
enum class JoinDirection(internal val className: String) : ClassValues<JoinDirection> {
    /** CSS: `join-vertical` — Show items vertically */
    Vertical("join-vertical"),
    /** CSS: `join-horizontal` — Show items horizontally */
    Horizontal("join-horizontal"),
    ;

    override val classNames: List<String> get() = listOf(className)
}


/**
 * Join is a container for grouping multiple items, it can be used to group buttons, inputs, etc. Join applies border radius to the first and last item. Join can be used to create a horizontal or vertical list of items. Renders `<div class="join ...">`.
 * @param id — Type-safe HTML id attribute from [HtmlId] hierarchy
 * @param direction — Direction variant
 * @param extraClasses — Additional CSS classes appended after the generated ones
 * @param attrs — Direct access to the underlying kotlinx.html tag attributes
 * @param content — Nested HTML content
 */
fun FlowContent.daisyJoin(
    id: HtmlId? = null,
    direction: ClassValues<JoinDirection>? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (JoinScope.() -> Unit),
) {
    JoinScope(emptyMap(), consumer).visit {
        if (id != null) attributes["id"] = id.id
        addClassNames("join")
        addClassNames(direction)
        addClassNames(extraClasses)
        if (attrs != null) attrs()
        content()
    }
}

/**
 * The content of a join. Extends [DIV], so every kotlinx.html builder still works here.
 *
 * A component call written DIRECTLY in this lambda resolves to the member below and emits
 * `join-item`; the same call nested inside another builder does not, because
 * kotlinx.html's `@HtmlTagMarker` hides this receiver there. Write `this@daisyJoin.` to reach
 * a member from a nested lambda deliberately.
 */
class JoinScope internal constructor(
    initialAttributes: Map<String, String>,
    consumer: TagConsumer<*>,
) : DIV(initialAttributes, consumer) {

    /** This element as plain flow content, so a member below reaches the top-level function. */
    private val flow: FlowContent get() = this

    /** [daisyButton], marked `join-item` because the call sits directly in the join. */
    fun daisyButton(
        text: String? = null,
        id: HtmlId? = null,
        variant: ClassValues<ButtonVariant>? = null,
        size: ClassValues<ButtonSize>? = null,
        active: Boolean = false,
        block: Boolean = false,
        circle: Boolean = false,
        dash: Boolean = false,
        ghost: Boolean = false,
        link: Boolean = false,
        outline: Boolean = false,
        soft: Boolean = false,
        square: Boolean = false,
        wide: Boolean = false,
        disabled: Boolean = false,
        type: ButtonType? = null,
        extraClasses: String? = null,
        attrs: (BUTTON.() -> Unit)? = null,
        content: (BUTTON.() -> Unit)? = null,
    ) {
        flow.daisyButton(
            text = text,
            id = id,
            variant = variant,
            size = size,
            active = active,
            block = block,
            circle = circle,
            dash = dash,
            ghost = ghost,
            link = link,
            outline = outline,
            soft = soft,
            square = square,
            wide = wide,
            disabled = disabled,
            type = type,
            extraClasses = joinItem(extraClasses),
            attrs = attrs,
            content = content,
        )
    }

    /** [daisyCard], marked `join-item` because the call sits directly in the join. */
    fun daisyCard(
        id: HtmlId? = null,
        size: ClassValues<CardSize>? = null,
        style: ClassValues<CardStyle>? = null,
        modifier: ClassValues<CardModifier>? = null,
        extraClasses: String? = null,
        attrs: (DIV.() -> Unit)? = null,
        content: (DIV.() -> Unit),
    ) {
        flow.daisyCard(
            id = id,
            size = size,
            style = style,
            modifier = modifier,
            extraClasses = joinItem(extraClasses),
            attrs = attrs,
            content = content,
        )
    }

    /** [daisyCollapse], marked `join-item` because the call sits directly in the join. */
    fun daisyCollapse(
        id: HtmlId? = null,
        arrow: Boolean = false,
        close: Boolean = false,
        open: Boolean = false,
        plus: Boolean = false,
        extraClasses: String? = null,
        attrs: (DIV.() -> Unit)? = null,
        content: (DIV.() -> Unit),
    ) {
        flow.daisyCollapse(
            id = id,
            arrow = arrow,
            close = close,
            open = open,
            plus = plus,
            extraClasses = joinItem(extraClasses),
            attrs = attrs,
            content = content,
        )
    }

    /** [daisyInput], marked `join-item` because the call sits directly in the join. */
    fun daisyInput(
        id: HtmlId? = null,
        variant: ClassValues<InputVariant>? = null,
        size: ClassValues<InputSize>? = null,
        ghost: Boolean = false,
        type: InputType = InputType.text,
        placeholder: String? = null,
        value: String? = null,
        disabled: Boolean = false,
        extraClasses: String? = null,
        attrs: (INPUT.() -> Unit)? = null,
    ) {
        flow.daisyInput(
            id = id,
            variant = variant,
            size = size,
            ghost = ghost,
            type = type,
            placeholder = placeholder,
            value = value,
            disabled = disabled,
            extraClasses = joinItem(extraClasses),
            attrs = attrs,
        )
    }

    /** [daisySelect], marked `join-item` because the call sits directly in the join. */
    fun daisySelect(
        id: HtmlId? = null,
        variant: ClassValues<SelectVariant>? = null,
        size: ClassValues<SelectSize>? = null,
        ghost: Boolean = false,
        disabled: Boolean = false,
        extraClasses: String? = null,
        attrs: (SELECT.() -> Unit)? = null,
        content: (SELECT.() -> Unit),
    ) {
        flow.daisySelect(
            id = id,
            variant = variant,
            size = size,
            ghost = ghost,
            disabled = disabled,
            extraClasses = joinItem(extraClasses),
            attrs = attrs,
            content = content,
        )
    }

    /** [daisyThemeController], marked `join-item` because the call sits directly in the join. */
    fun daisyThemeController(
        id: HtmlId? = null,
        extraClasses: String? = null,
        attrs: (INPUT.() -> Unit)? = null,
    ) {
        flow.daisyThemeController(
            id = id,
            extraClasses = joinItem(extraClasses),
            attrs = attrs,
        )
    }

    /** [daisyValidator], marked `join-item` because the call sits directly in the join. */
    fun daisyValidator(
        id: HtmlId? = null,
        extraClasses: String? = null,
        attrs: (INPUT.() -> Unit)? = null,
    ) {
        flow.daisyValidator(
            id = id,
            extraClasses = joinItem(extraClasses),
            attrs = attrs,
        )
    }
}

/** `join-item`, ahead of whatever the caller wrote. */
private fun joinItem(extraClasses: String?): String =
    if (extraClasses == null) "join-item" else "join-item $extraClasses"

