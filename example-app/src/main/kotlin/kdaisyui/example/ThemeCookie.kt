package kdaisyui.example

import io.github.ollin.kdaisyui.themes.DaisyTheme
import io.github.ollin.kdaisyui.themes.daisyThemeOf
import io.ktor.http.Cookie
import io.ktor.server.application.ApplicationCall

private const val THEME_COOKIE = "theme"

/**
 * The profile's chosen theme, kept for a year. `HttpOnly`, because only the server reads it, to
 * render `data-theme`; `SameSite=Lax`, so a link from elsewhere still arrives in the user's theme.
 */
private fun themeCookie(theme: DaisyTheme) =
    Cookie(THEME_COOKIE, theme.value, maxAge = 31_536_000, path = "/", httpOnly = true, extensions = mapOf("SameSite" to "Lax"))

/** The theme the cookie names, or `null` for none or for a value that is no theme. */
fun ApplicationCall.storedTheme(): DaisyTheme? = request.cookies[THEME_COOKIE]?.let(::daisyThemeOf)

/**
 * Keeps [value] as the profile's theme, or forgets it for `system`, so the page follows the
 * browser's light or dark preference again; `false`, keeping nothing, when it is neither.
 */
fun ApplicationCall.storeTheme(value: String): Boolean {
    if (value == "system") {
        response.cookies.append(Cookie(THEME_COOKIE, "", maxAge = 0, path = "/"))
        return true
    }
    val theme = daisyThemeOf(value) ?: return false
    response.cookies.append(themeCookie(theme))
    return true
}
