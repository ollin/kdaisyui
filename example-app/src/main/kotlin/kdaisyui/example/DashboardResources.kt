package kdaisyui.example

import io.ktor.resources.Resource

@Resource("/")
class DashboardPage

@Resource("/popover-modal")
class PopoverModalDemo

@Resource("/fragments")
class Fragments {
    @Resource("/stats")
    class Stats(val parent: Fragments = Fragments())

    @Resource("/cards-row1")
    class CardsRow1(val parent: Fragments = Fragments())

    @Resource("/cards-row2")
    class CardsRow2(val parent: Fragments = Fragments())

    @Resource("/whats-new")
    class WhatsNew(val parent: Fragments = Fragments())

    @Resource("/forms")
    class Forms(val parent: Fragments = Fragments())

    @Resource("/form-sections")
    class FormSections(val parent: Fragments = Fragments())

    @Resource("/team")
    class Team(val parent: Fragments = Fragments())
}
