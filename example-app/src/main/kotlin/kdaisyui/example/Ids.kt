package kdaisyui.example

import io.github.ollin.kdaisyui.core.AnnotatedIdBase

class Dashboard : AnnotatedIdBase("dashboard") {
    class Drawer(parent: Dashboard = Dashboard()) : AnnotatedIdBase("drawer", parent)
    class Header(parent: Dashboard = Dashboard()) : AnnotatedIdBase("header", parent)
    class Search(parent: Dashboard = Dashboard()) : AnnotatedIdBase("search", parent)
    class Sidebar(parent: Dashboard = Dashboard()) : AnnotatedIdBase("sidebar", parent)
    class Content(parent: Dashboard = Dashboard()) : AnnotatedIdBase("content", parent)

    class Stats(parent: Dashboard = Dashboard()) : AnnotatedIdBase("stats", parent)

    class Pipelines(parent: Dashboard = Dashboard()) : AnnotatedIdBase("pipelines", parent)

    class Issues(parent: Dashboard = Dashboard()) : AnnotatedIdBase("issues", parent)

    class CardsRow2(parent: Dashboard = Dashboard()) : AnnotatedIdBase("cards-row2", parent)

    class Repo(parent: Dashboard = Dashboard()) : AnnotatedIdBase("repo", parent) {
        class Form(parent: Repo = Repo()) : AnnotatedIdBase("form", parent)
    }

    class FormSections(parent: Dashboard = Dashboard()) : AnnotatedIdBase("form-sections", parent)

    class Team(parent: Dashboard = Dashboard()) : AnnotatedIdBase("team", parent) {
        class Invite(parent: Team = Team()) : AnnotatedIdBase("invite", parent) {
            class Form(parent: Invite = Invite()) : AnnotatedIdBase("form", parent)
        }
        class Table(parent: Team = Team()) : AnnotatedIdBase("table", parent)
    }
}
