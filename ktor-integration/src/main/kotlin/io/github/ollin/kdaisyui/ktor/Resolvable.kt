package io.github.ollin.kdaisyui.ktor

import io.github.ollin.kdaisyui.core.HtmlId
import io.ktor.server.application.Application
import io.ktor.server.resources.href

sealed interface Resolvable {
    val href: String
}

@JvmInline
value class StringResolvable(override val href: String) : Resolvable

class ApplicationResolution(val application: Application)

fun HtmlId.asResolvable(): Resolvable = StringResolvable("#${this.id}")

fun String.asResolvable(): Resolvable = StringResolvable(this)

context(appResolution: ApplicationResolution)
inline fun <reified T : Any> T.asResolvable(): Resolvable = when (this) {
    is Resolvable -> this
    is HtmlId -> StringResolvable("#${this.id}")
    is String -> StringResolvable(this)
    else -> StringResolvable(appResolution.application.href(this))
}

val HtmlId.href: String get() = "#${this.id}"

context(appResolution: ApplicationResolution)
inline val <reified T : Any> T.href: String
    get() = when (this) {
        is Resolvable -> this.href
        is HtmlId -> "#${this.id}"
        is String -> this
        else -> appResolution.application.href(this)
    }
