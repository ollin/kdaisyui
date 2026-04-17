package kdaisyui.example.fragments

import io.github.ollin.kdaisyui.components.*
import io.github.ollin.kdaisyui.core.addClassNames
import kotlinx.html.TagConsumer
import kotlinx.html.section

fun TagConsumer<*>.statsFragment() {
    section {
        addClassNames("stats stats-vertical xl:stats-horizontal bg-base-100 col-span-12 w-full shadow-xs")
        val stats = listOf(
            Triple("Active Repositories", "142", "Across all organizations"),
            Triple("Open Issues", "1,847", "Live triage queue"),
            Triple("PRs Merged This Month", "389", "Healthy review velocity"),
            Triple("Pipeline Success Rate", "94.2%", "Last 30 days"),
        )
        for ((title, value, desc) in stats) {
            daisyStatStat {
                daisyStatStatTitle(title)
                daisyStatStatValue(value)
                daisyStatStatDesc(desc)
            }
        }
    }
}
