package io.github.ollin.kdaisyui.core

/**
 * One or more applications of DaisyUI classes drawn from a single exclusive group [T].
 *
 * Every generated enum implements this at its own type — `ButtonSize` is a
 * `ClassValues<ButtonSize>` — so a bare entry, a variant-prefixed entry and a combination of
 * them all fit the same parameter. `daisyButton(size = ButtonSize.Lg)` therefore reads exactly
 * as it did before variants existed.
 *
 * [T] is a phantom parameter: it appears in no member and exists only to keep two groups apart.
 * It is deliberately **invariant**. Under `out T` the compiler would infer the common supertype
 * `Any` for `ButtonSize.Lg and ToastVerticalPlacement.Top`, and that combination — a button size
 * answered with a toast placement — would compile. Invariance is what makes it a type error.
 */
interface ClassValues<T : Any> {
    /**
     * The CSS classes to emit, in application order, e.g. `["btn-xs", "lg:btn-lg"]`.
     *
     * Public because [ClassValues] is a public parameter type and an interface member cannot be
     * `internal`. The generated enums keep their `className` internal; this is the one accessor
     * the variant API needs, and the class names it returns are already published per-jar in
     * `kdaisyui-classes.txt`.
     */
    val classNames: List<String>
}

/*
 * EVERYTHING BELOW IS `internal` UNTIL A CONSUMER'S TAILWIND CAN SEE WHAT IT COMPOSES.
 *
 * Measured 2026-09-13: a call site of `at(Breakpoint.Xl, ButtonSize.Lg)` renders
 * `class="btn xl:btn-lg"` and the compiled stylesheet is byte-identical — no rule is generated.
 * Tailwind emits CSS per candidate STRING it finds while scanning; `xl:btn-lg` is composed at
 * runtime from two halves and therefore appears in no file. The string form,
 * `extraClasses = "lg:btn-lg"`, works precisely because the literal IS the candidate.
 *
 * So the composition half is built, tested and parked rather than published. Public here would
 * put `at` in completion and in `lib/api/lib.api`, and a caller who found it would get a class
 * attribute that styles nothing, with no compile error and no log — the exact failure this
 * library exists to prevent.
 *
 * `ClassValues` itself stays public: it is the parameter type of every enum-backed parameter, and
 * `size = ButtonSize.Lg` reads the same either way. Publishing it now means the widening costs no
 * API churn later — turning these back to public is the whole of the remaining change.
 *
 * What has to land first is a build-time extractor that reads the CONSUMER's compiled classes and
 * emits the pairs they actually use. Verified feasible the same day: a consumer's constant pool
 * carries a Fieldref to `ButtonSize.Lg` AND to `Breakpoint.Xl`, so no dataflow analysis is needed.
 * That is its own change, and it is worth having with or without this one — it also replaces
 * today's all-560-classes delivery with what an application really names.
 */

/**
 * A Tailwind variant: the prefix that narrows when a class applies.
 *
 * A closed set, in three enums rather than one, because the three are answerable from different
 * authorities — Tailwind's documented breakpoint scale, a recorded need, and DaisyUI's own
 * documentation. Splitting them keeps each list defensible on its own terms and lets completion
 * from `at(` offer the kind a caller is thinking about.
 */
internal interface Variant {
    /** The prefix without its colon, e.g. `lg` or `max-sm`. */
    val prefix: String
}

/**
 * Tailwind's breakpoints, each in its `min-` and `max-` form.
 *
 * Ten rather than the seven DaisyUI's own examples happen to use. The seven are a frequency
 * measurement over one corpus; the vocabulary is Tailwind's and Tailwind documents five
 * breakpoints, each of which takes `max-`. Shipping only the observed seven would leave
 * `2xl:btn-lg` unreachable in an API that has no variant escape hatch by decision.
 */
internal enum class Breakpoint(override val prefix: String) : Variant {
    Sm("sm"),
    Md("md"),
    Lg("lg"),
    Xl("xl"),
    Xxl("2xl"),
    MaxSm("max-sm"),
    MaxMd("max-md"),
    MaxLg("max-lg"),
    MaxXl("max-xl"),
    MaxXxl("max-2xl"),
}

/**
 * The state variants that reach a DaisyUI class.
 *
 * DaisyUI applies **no** state variant to its own classes — `hover:`, `focus:` and `checked:`
 * occur in its examples only on Tailwind utilities — so the measurement does not select these
 * three. They are here on two other grounds: Oliver named them as in scope, and `AGENTS.md`
 * records `dark:alert-info` as a case that silently produced no CSS before the stylesheet was
 * compiled properly. One recorded use and one instruction, which is thin evidence but is
 * evidence, and it points the same way.
 */
internal enum class State(override val prefix: String) : Variant {
    Dark("dark"),
    Hover("hover"),
    Focus("focus"),
}

/**
 * DaisyUI's own variants, which it documents as variants rather than classes.
 *
 * *"we use the `is-drawer-open` and `is-drawer-close` variants. For example
 * `is-drawer-close:hidden`."* They ship today in `kdaisyui-classes.txt` as if they were classes,
 * trailing colon and all — the defect filed as #345. Giving them a home here makes that
 * deletion a move rather than a loss.
 */
internal enum class DrawerVariant(override val prefix: String) : Variant {
    Open("is-drawer-open"),
    Close("is-drawer-close"),
}

/** Two applications of the same group, in the order they were written. */
private class CombinedClassValues<T : Any>(
    private val first: ClassValues<T>,
    private val second: ClassValues<T>,
) : ClassValues<T> {
    override val classNames: List<String> get() = first.classNames + second.classNames
}

/** Every class of [value], each carrying [variant]'s prefix. */
private class PrefixedClassValues<T : Any>(
    private val variant: Variant,
    private val value: ClassValues<T>,
) : ClassValues<T> {
    override val classNames: List<String>
        get() = value.classNames.map { "${variant.prefix}:$it" }
}

/**
 * Apply [value] only where [variant] holds: `at(Breakpoint.Lg, ButtonSize.Lg)` is `lg:btn-lg`.
 *
 * A function rather than a method on the enum, chosen by Oliver so IntelliJ completes it from
 * the parameter position — and because a method would force every one of the generated enums to
 * grow one.
 *
 * The variant comes first because that is the order the rendered class reads in, and because a
 * caller scanning a signature is choosing *when* before *what*.
 *
 * [value] is a whole [ClassValues] rather than a single entry, so `at(Breakpoint.Lg, a and b)`
 * prefixes both. That also makes `at(Breakpoint.Lg, at(Breakpoint.Md, x))` expressible, yielding
 * `lg:md:x` — a stack DaisyUI never applies to its own classes and which styles nothing.
 * Forbidding it would need a second type for the un-prefixed case, and under this change's
 * asymmetric-cost rule permitting a useless combination is the cheap direction; making a
 * reachable one inexpressible is the expensive one.
 */
internal fun <T : Any> at(variant: Variant, value: ClassValues<T>): ClassValues<T> =
    PrefixedClassValues(variant, value)

/**
 * Both applications, e.g. `ButtonSize.Xs and at(Breakpoint.Lg, ButtonSize.Lg)`.
 *
 * The same group on both sides, which is what makes the responsive pattern DaisyUI documents —
 * `btn-xs sm:btn-sm md:btn-md lg:btn-lg xl:btn-xl` — expressible while answering a button size
 * with a toast placement is not.
 */
internal infix fun <T : Any> ClassValues<T>.and(other: ClassValues<T>): ClassValues<T> =
    CombinedClassValues(this, other)
