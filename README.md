# kdaisyUI

Type-safe [DaisyUI](https://daisyui.com/) components for [kotlinx.html](https://github.com/Kotlin/kotlinx.html).

Write DaisyUI markup in Kotlin with autocompletion, compile-time checks, and zero class-name typos.

![DevTrack — example app built with kdaisyUI](docs/screenshots/overview.png)

```kotlin
createHTML().div {
    daisyCard(extraClasses = "bg-base-100 shadow-xs") {
        daisyCardBody {
            daisyCardTitle("Revenue")
            daisyStat(horizontal = true) {
                daisyStatStat {
                    daisyStatStatValue("21,500 USD")
                    daisyStatStatDesc("21% more than last month")
                }
            }
            daisyButton("View report", variant = ButtonVariant.Primary)
        }
    }
}
```

## Who is this for?

Kotlin developers building **server-rendered HTML** (with Ktor, Spring, or any JVM framework) who want beautiful, consistent UIs without writing raw CSS class strings.

No frontend build tools required. No JavaScript frameworks. Just Kotlin.

## Documentation

This project follows the [Diátaxis](https://diataxis.fr/) documentation framework:

| | Learn | Work |
|---|---|---|
| **Hands-on** | [Tutorials](docs/tutorials/index.md) — step-by-step lessons | [How-to guides](docs/how-to.md) — solve specific tasks |
| **Theory** | [Explanation](docs/explanation.md) — background concepts | [Reference](docs/reference.md) — complete API |

### Quick links

- **New here?** Start with the [Getting started tutorial](docs/tutorials/getting-started.md)
- **Want to build a real app?** Follow [Build a dashboard with Ktor](docs/tutorials/build-a-dashboard.md)
- **Need a specific recipe?** Check the [How-to guides](docs/how-to.md)
- **Looking up an API?** See the [Component reference](docs/reference.md)

## Quick start

### 1. Add the dependency

```kotlin
// build.gradle.kts
repositories {
    mavenCentral()
    maven("https://maven.pkg.github.com/ollin/kdaisyUI")
}

dependencies {
    implementation("com.github.ollin.kdaisyui:kdaisyui:5.5.19-1")
}
```

> **Note:** Published to GitHub Packages. Maven Central coming soon.

### 2. Render your first component

```kotlin
import com.github.ollin.kdaisyui.components.*
import kotlinx.html.div
import kotlinx.html.stream.createHTML

val html = createHTML().div {
    daisyButton("Click me", variant = ButtonVariant.Primary, size = ButtonSize.Lg)
}
// → <div><button class="btn btn-primary btn-lg">Click me</button></div>
```

## Modules

| Module | Description |
|---|---|
| `:lib` | Core library — DSL component wrappers |
| `:example-app` | Ktor + htmx demo dashboard |

## Development environment

### Option A — Dev Container (recommended for new contributors)

Open in any Dev Container-compatible editor (VS Code, IntelliJ, GitHub Codespaces):

```bash
git clone https://github.com/ollin/kdaisyUI
# Open in VS Code → "Reopen in Container"
```

The container provides JDK, Gradle, Node, and Playwright Chromium pre-installed (exact versions from [`.tool-versions`](.tool-versions)). Port 8080 is forwarded automatically.

### Option B — Local with asdf

```bash
git clone https://github.com/ollin/kdaisyUI
cd kdaisyUI
asdf install   # reads .tool-versions — installs JDK, Gradle, Node, just
just test      # run unit tests
just dev       # start dev server → http://localhost:8080
```

### Option C — Any JDK 21+

The Gradle wrapper downloads Gradle automatically (version in [`gradle/wrapper/gradle-wrapper.properties`](gradle/wrapper/gradle-wrapper.properties)). Just bring your own JDK 21+.

## Requirements

Exact versions are the single source of truth in these files:

| What | Where |
|---|---|
| JDK, Gradle, Node | [`.tool-versions`](.tool-versions) |
| Kotlin, kotlinx-html | [`gradle.properties`](gradle.properties) |
| Gradle wrapper | [`gradle/wrapper/gradle-wrapper.properties`](gradle/wrapper/gradle-wrapper.properties) |
| Ktor, webjars | [`example-app/build.gradle.kts`](example-app/build.gradle.kts) |

## Common tasks

This project uses [`just`](https://just.systems/) as a task runner. Run `just` to list all recipes.

| Command | What it does |
|---|---|
| `just dev` | Start the example app → http://localhost:8080 |
| `just test` | Run library unit tests |
| `just e2e` | Run Playwright E2E tests (server managed automatically) |
| `just test-all` | Run unit tests + E2E tests |
| `just build` | Build all Gradle modules |
| `just clean` | Remove all build artifacts |

`just` is included in [`.tool-versions`](.tool-versions) and installed automatically with `asdf install`. The raw Gradle commands still work if you prefer them directly.

## License

MIT. See [LICENSE](LICENSE).

## Acknowledgements

[DaisyUI](https://daisyui.com/) by [Pouya Saadeghi](https://github.com/saadeghi) is the reason this library exists — making Tailwind CSS semantic is genuinely great work. See [CREDITS.md](CREDITS.md) for full acknowledgements of all projects this library builds on.
