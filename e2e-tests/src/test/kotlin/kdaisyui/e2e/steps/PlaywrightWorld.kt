package kdaisyui.e2e.steps

import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page

class PlaywrightWorld {
    lateinit var context: BrowserContext
    lateinit var page: Page
}
