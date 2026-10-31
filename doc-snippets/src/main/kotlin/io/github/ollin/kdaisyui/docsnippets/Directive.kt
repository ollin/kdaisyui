package io.github.ollin.kdaisyui.docsnippets

/** What a `<!-- snippet: ... -->` marker asks of the fence below it. */
sealed interface Directive {
    /** No marker: compile the snippet as a statement inside a `FlowContent` builder. */
    data object Compile : Directive

    /** `in <Receiver>`: compile it inside an extension on [receiver]. */
    data class In(val receiver: String) : Directive

    /** `skip - <reason>`: leave it out, for [reason]. */
    data class Skip(val reason: String) : Directive

    /** A marker the build does not understand, and what is wrong with it. */
    data class Invalid(val problem: String) : Directive
}

fun directiveOf(marker: String?): Directive = when {
    marker == null -> Directive.Compile
    marker.startsWith("in ") -> Directive.In(marker.removePrefix("in "))
    marker.startsWith("skip - ") -> Directive.Skip(marker.removePrefix("skip - "))
    marker.startsWith("skip") -> Directive.Invalid("skip needs a reason: <!-- snippet: skip - <reason> -->")
    else -> Directive.Invalid("unknown snippet directive '$marker'; known: in <Receiver>, skip - <reason>")
}
