package kdaisyui.example

import io.github.ollin.kdaisyui.themes.DaisyTheme
import io.github.ollin.kdaisyui.themes.daisyThemeOf
import io.ktor.http.Cookie
import io.ktor.server.application.ApplicationCall

/**
 * The profile's chosen theme, kept for a year. `HttpOnly`, because only the server reads it, to
 * render `data-theme`; `SameSite=Lax`, so a link from elsewhere still arrives in the user's theme.
 */
fun themeCookie(theme: DaisyTheme) =
    Cookie("theme", theme.value, maxAge = 31_536_000, path = "/", httpOnly = true, extensions = mapOf("SameSite" to "Lax"))

/** The theme the cookie names, or `null` for none or for a value that is no theme. */
fun ApplicationCall.storedTheme(): DaisyTheme? = request.cookies["theme"]?.let(::daisyThemeOf)
