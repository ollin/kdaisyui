package kdaisyui.example

import io.github.ollin.kdaisyui.core.AnnotatedIdBase

class Dashboard : AnnotatedIdBase("dashboard") {
    class Drawer(parent: Dashboard = Dashboard()) : AnnotatedIdBase("drawer", parent)
    class Search(parent: Dashboard = Dashboard()) : AnnotatedIdBase("search", parent)
    class Content(parent: Dashboard = Dashboard()) : AnnotatedIdBase("content", parent)

    class Header(parent: Dashboard = Dashboard()) : AnnotatedIdBase("header", parent) {
        class Avatar(parent: Header = Header()) : AnnotatedIdBase("avatar", parent)
    }

    class Sidebar(parent: Dashboard = Dashboard()) : AnnotatedIdBase("sidebar", parent) {
        class Menu(parent: Sidebar = Sidebar()) : AnnotatedIdBase("menu", parent)
    }

    class Stats(parent: Dashboard = Dashboard()) : AnnotatedIdBase("stats", parent)

    class Pipelines(parent: Dashboard = Dashboard()) : AnnotatedIdBase("pipelines", parent)

    class Issues(parent: Dashboard = Dashboard()) : AnnotatedIdBase("issues", parent)

    class CardsRow2(parent: Dashboard = Dashboard()) : AnnotatedIdBase("cards-row2", parent)

    class Repo(parent: Dashboard = Dashboard()) : AnnotatedIdBase("repo", parent) {
        class Form(parent: Repo = Repo()) : AnnotatedIdBase("form", parent) {
            class Name(parent: Form = Form()) : AnnotatedIdBase("name", parent)
            class Visibility(parent: Form = Form()) : AnnotatedIdBase("visibility", parent)
            class Description(parent: Form = Form()) : AnnotatedIdBase("description", parent)
            class Branch(parent: Form = Form()) : AnnotatedIdBase("branch", parent)
            class Readme(parent: Form = Form()) : AnnotatedIdBase("readme", parent)
            class Gitignore(parent: Form = Form()) : AnnotatedIdBase("gitignore", parent)
        }
    }

    class RecentEvents(parent: Dashboard = Dashboard()) : AnnotatedIdBase("recent-events", parent)

    class QuickStats(parent: Dashboard = Dashboard()) : AnnotatedIdBase("quick-stats", parent)

    class FormSections(parent: Dashboard = Dashboard()) : AnnotatedIdBase("form-sections", parent)

    class Team(parent: Dashboard = Dashboard()) : AnnotatedIdBase("team", parent) {
        class Invite(parent: Team = Team()) : AnnotatedIdBase("invite", parent) {
            class Form(parent: Invite = Invite()) : AnnotatedIdBase("form", parent) {
                class Email(parent: Form = Form()) : AnnotatedIdBase("email", parent)
                class Role(parent: Form = Form()) : AnnotatedIdBase("role", parent)
                class Repos(parent: Form = Form()) : AnnotatedIdBase("repos", parent)
                class Submit(parent: Form = Form()) : AnnotatedIdBase("submit", parent)
            }
        }
        class Table(parent: Team = Team()) : AnnotatedIdBase("table", parent)
    }
}
