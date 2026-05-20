package kdaisyui.example

import io.github.ollin.kdaisyui.core.AnnotatedIdBase

class Dashboard : AnnotatedIdBase("dashboard") {
    class Drawer(parent: Dashboard = Dashboard()) : AnnotatedIdBase("drawer", parent)
    class Header(parent: Dashboard = Dashboard()) : AnnotatedIdBase("header", parent)
    class Search(parent: Dashboard = Dashboard()) : AnnotatedIdBase("search", parent)
    class Stats(parent: Dashboard = Dashboard()) : AnnotatedIdBase("stats", parent)
}
