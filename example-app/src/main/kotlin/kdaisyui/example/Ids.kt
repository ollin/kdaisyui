package kdaisyui.example

import io.github.ollin.kdaisyui.core.AnnotatedIdBase

/**
 * IDs for the standalone megamenu reference page.
 *
 * Every one of these is used twice — once as an element's `id`, once as the `popovertarget` that
 * opens it — and DaisyUI's rule is that the two must match. Deriving both from one object is what
 * makes a trigger pointing at a panel that does not exist a compile error rather than a page that
 * silently does nothing.
 */
class Megamenu : AnnotatedIdBase("megamenu") {
    class Reference(parent: Megamenu = Megamenu()) : AnnotatedIdBase("reference", parent)
    class PanelOne(parent: Megamenu = Megamenu()) : AnnotatedIdBase("panel-one", parent)
    class PanelTwo(parent: Megamenu = Megamenu()) : AnnotatedIdBase("panel-two", parent)
}

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

    class PopoverModal(parent: Dashboard = Dashboard()) : AnnotatedIdBase("popover-modal", parent)

    class DropdownPopover(parent: Dashboard = Dashboard()) : AnnotatedIdBase("dropdown-popover", parent)

    class Pipelines(parent: Dashboard = Dashboard()) : AnnotatedIdBase("pipelines", parent)

    class Issues(parent: Dashboard = Dashboard()) : AnnotatedIdBase("issues", parent)

    class CardsRow2(parent: Dashboard = Dashboard()) : AnnotatedIdBase("cards-row2", parent)

    class WhatsNew(parent: Dashboard = Dashboard()) : AnnotatedIdBase("whats-new", parent) {
        class Otp(parent: WhatsNew = WhatsNew()) : AnnotatedIdBase("otp", parent)
        class Megamenu(parent: WhatsNew = WhatsNew()) : AnnotatedIdBase("megamenu", parent) {
            class Panel(parent: Megamenu = Megamenu()) : AnnotatedIdBase("panel", parent)
        }
    }

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
