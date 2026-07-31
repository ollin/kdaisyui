package io.github.ollin.kdaisyui.codegen

/**
 * One DaisyUI CSS class, with the documentation DaisyUI ships next to it.
 *
 * [isDefault] comes from DaisyUI's own `default: true` marker — e.g. `tooltip-top` is the
 * placement you get when you set none. The JavaScript generator discards it.
 */
data class ClassOption(
    val cssClass: String,
    val description: String,
    val isDefault: Boolean = false,
)

/**
 * A group of CSS classes that DaisyUI documents under one category name.
 *
 * Whether the group is a *choice* or a set of independent *flags* is the single decision
 * that shapes every generated signature — so it is modelled explicitly rather than being
 * an accident of how the emitter happens to loop.
 */
sealed interface Axis {
    val category: String
    val options: List<ClassOption>

    /** At most one option may apply. Emitted as one nullable enum parameter. */
    data class Choice(
        override val category: String,
        override val options: List<ClassOption>,
    ) : Axis {
        val default: ClassOption? = options.singleOrNull { it.isDefault }
    }

    /** Any combination may apply. Emitted as one `Boolean = false` parameter per option. */
    data class Flags(
        override val category: String,
        override val options: List<ClassOption>,
    ) : Axis
}

/**
 * Which DaisyUI categories describe a mutually exclusive choice.
 *
 * This set IS the API design, in one readable place. In the JavaScript generator the same
 * knowledge is spread across `classifier.js` (which keeps the categories apart) and
 * `generator-new.js:44-82` (which flattens all of them back into one bag of booleans), so
 * `daisyButton(outline = true, ghost = true)` compiles and emits nonsense.
 */
val EXCLUSIVE_CATEGORIES = setOf("color", "size", "style", "placement", "direction")

/** Categories that describe the component itself rather than a variation of it. */
val STRUCTURAL_CATEGORIES = setOf("component", "part")

data class Component(
    val name: String,
    val baseClass: String,
    val description: String,
    /** kotlinx.html element this renders as, e.g. `BUTTON`. */
    val element: String,
    val axes: List<Axis>,
) {
    val choices: List<Axis.Choice> get() = axes.filterIsInstance<Axis.Choice>()
    val flags: List<Axis.Flags> get() = axes.filterIsInstance<Axis.Flags>()
}
