package io.github.ollin.kdaisyui.core

/**
 * Type-safe HTML element identifier.
 *
 * Use [htmlId] to create inline instances from string literals,
 * or subclass [AnnotatedIdBase] / [NamedAnnotatedIdBase] for
 * compile-time-safe hierarchical IDs.
 */
interface HtmlId {

    /** The HTML `id` attribute value (without `#` prefix). */
    val id: String

    /** CSS selector targeting this element: `#id`. */
    val target: String get() = "#$id"

    /** Global-scope CSS selector: `global #id`. Useful inside web components. */
    val targetGlobal: String get() = "global $target"
}

/** Create an [HtmlId] from a plain string literal. */
fun htmlId(id: String): HtmlId = StringHtmlId(id)

@JvmInline
internal value class StringHtmlId(override val id: String) : HtmlId {
    override fun toString() = id
}

/**
 * Base class for hierarchical type-safe HTML IDs.
 *
 * Subclass and pass a [segment] to define an ID segment.
 * When a [parent] is provided, the full ID is built by joining
 * parent segments with `-`.
 *
 * ```
 * class AppIds : AnnotatedIdBase("app") {
 *     class Header(parent: AppIds = AppIds()) : AnnotatedIdBase("header", parent)
 * }
 *
 * val headerId = AppIds.Header()
 * headerId.id  // "app-header"
 * ```
 *
 * @param segment The ID segment for this level (e.g. "header" → "app-header" when parent is "app").
 * @param parent Optional parent ID for hierarchical composition.
 *   When provided, the parent's [id] is prepended with a `-` separator.
 */
abstract class AnnotatedIdBase(
    private val segment: String,
    val parent: HtmlId? = null,
) : HtmlId {

    override val id: String by lazy {
        when {
            parent != null && segment.isNotBlank() -> "${parent.id}-$segment"
            parent != null -> parent.id
            else -> segment
        }
    }

    override fun toString() = id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HtmlId) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}

/**
 * Named variant of [AnnotatedIdBase] for IDs with a dynamic suffix.
 *
 * Use this for collections, lists, rows, or any element that appears
 * multiple times with distinct names:
 *
 * ```
 * class Cards(parent: AppIds) : AnnotatedIdBase("cards", parent) {
 *     class Card(name: String, parent: Cards) : NamedAnnotatedIdBase("card", name, parent)
 * }
 *
 * val card1 = Cards.Card("1", cards)
 * card1.id  // "app-cards-card-1"
 * ```
 *
 * @param segment The hierarchical ID segment (e.g. "card").
 * @param name Dynamic name suffix appended after the hierarchical ID.
 *   When non-empty, `-name` is appended.
 * @param parent Optional parent ID for hierarchical composition.
 */
abstract class NamedAnnotatedIdBase(
    segment: String,
    val name: String = "",
    parent: HtmlId? = null,
) : AnnotatedIdBase(segment, parent) {

    /** Accept an [HtmlId] as the name suffix. */
    constructor(segment: String, name: HtmlId, parent: HtmlId? = null) : this(segment, name.id, parent)

    override val id: String by lazy {
        val baseId = super.id
        if (name.isNotEmpty()) "$baseId-$name" else baseId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HtmlId) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}