plugins {
    base
    alias(libs.plugins.jreleaser)
    alias(libs.plugins.kover)
}

group = "io.github.ollin.kdaisyui"

// The root aggregates coverage at build time, so it must resolve Kover's own
// runtime artifacts (the JVM coverage agent + reporter). The measured modules
// declare their own repositories; the root needs its own for the Kover toolchain.
repositories {
    mavenCentral()
}

// Root-level coverage aggregation: merge the published library modules into one
// report. Only :lib and :ktor-integration are measured; :example-app, :e2e-tests,
// and :bom are intentionally out of scope (demo / test code / no code).
dependencies {
    kover(project(":lib"))
    kover(project(":ktor-integration"))
}

// Aggregated coverage reports. The hard verify rule is added LAST (see the
// staged rollout in design.md D5) — until then the build stays green: Kover is
// present and reports are produced, but nothing fails on a shortfall.
kover {
    reports {
        // Exclude the synthetic `$DefaultImpls` interface-default bridge classes
        // (design.md D4: an exclusion is explicit + justified, never silent).
        //
        // Kotlin 2.4 compiles interface default methods (HtmlId.target /
        // targetGlobal) with `-jvm-default=enable` by default. The real method
        // bodies live in the interface and ARE measured at 100% (HtmlId.getTarget
        // / getTargetGlobal). In ENABLE mode the compiler ADDITIONALLY emits a
        // static `HtmlId$DefaultImpls` class holding binary-compatibility bridge
        // stubs. Those bridges are only ever invoked by consumers compiled against
        // the legacy (disable-mode) ABI; every current-ABI Kotlin/Java caller —
        // including a `super<HtmlId>.target` super-call (tested) — routes to the
        // interface default directly, NEVER the bridge. They are therefore
        // provably unreachable from any source-level test (2 lines, 2 methods).
        //
        // We exclude them rather than (a) faking coverage, or (b) switching :lib
        // to `-Xjvm-default=no-compatibility` — which would remove the bridges but
        // is an ABI change to a published Maven Central artifact, contradicting the
        // proposal's "no impact on published artifacts" constraint. The exclusion
        // is ABI-neutral and reversible.
        filters {
            excludes {
                classes("*\$DefaultImpls")
            }
        }
        total {
            html { onCheck = true }
            xml { onCheck = true }
            // The hard gate (added LAST per the staged rollout, design.md D5):
            // 100% LINE and 100% BRANCH for the aggregated in-scope modules, or
            // the build fails. Bound to `check` via onCheck, so `./gradlew check`
            // (locally and in CI) fails on any shortfall — no separately named task.
            verify {
                onCheck = true
                rule("100% line coverage") {
                    bound {
                        minValue = 100
                        coverageUnits = kotlinx.kover.gradle.plugin.dsl.CoverageUnit.LINE
                        aggregationForGroup =
                            kotlinx.kover.gradle.plugin.dsl.AggregationType.COVERED_PERCENTAGE
                    }
                }
                rule("100% branch coverage") {
                    bound {
                        minValue = 100
                        coverageUnits = kotlinx.kover.gradle.plugin.dsl.CoverageUnit.BRANCH
                        aggregationForGroup =
                            kotlinx.kover.gradle.plugin.dsl.AggregationType.COVERED_PERCENTAGE
                    }
                }
            }
        }
    }
}

jreleaser {
    project {
        description.set("Type-safe DaisyUI component DSL for Kotlin server-rendered HTML")
        authors.set(listOf("Oliver Nautsch"))
        license.set("MIT")
        links {
            homepage.set("https://github.com/ollin/kdaisyui")
        }
        inceptionYear.set("2026")
    }

    release {
        github {
            repoOwner.set("ollin")
            name.set("kdaisyui")
            overwrite.set(true)
            changelog {
                formatted.set(org.jreleaser.model.Active.ALWAYS)
                preset.set("conventional-commits")
                contributors {
                    enabled.set(false)
                }
                // Already the default. Pinned because it looks like the cure for the
                // duplicated dependency entries and is the opposite: merge commits are the
                // ONLY Conventional Commits this project produces — the Arlo commits beneath
                // them are uncategorized by design. Setting this true would empty the
                // changelog of everything worth reading. The duplicates are gone via
                // hide.category("tasks") below.
                skipMergeCommits.set(false)
                hide {
                    uncategorized.set(true)
                    // `tasks` is the preset's category for the `chore` label, and
                    // `chore(deps):` is where every Renovate bump lands — the scope is not
                    // part of the labeler regex, so there is no narrower key to name here.
                    category("tasks")
                }
            }
        }
    }

    signing {
        active.set(org.jreleaser.model.Active.ALWAYS)
        armored.set(true)
    }

    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active.set(org.jreleaser.model.Active.ALWAYS)
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    stagingRepository(layout.buildDirectory.dir("staging-deploy").get().asFile.path)
                    applyMavenCentralRules.set(true)
                }
            }
        }
    }
}

// Every publishable module the release must contain. The staging step below
// fails if any of these is missing, so JReleaser can never upload a partial
// bundle to Maven Central (where every version is published exactly once).
val publishedArtifacts = listOf("kdaisyui", "kdaisyui-ktor-integration", "kdaisyui-bom")

// Guarantee EVERY subproject has fully staged into staging-deploy BEFORE
// JReleaser signs/deploys. Without this edge, Gradle is free to interleave
// jreleaserDeploy with the slow :lib publish (daisyui/heroicons codegen) and
// upload a bundle that contains only the fast modules (this happened in 0.1.1,
// where only the BOM reached Central). tasks.matching is a lazy, live
// collection that resolves at graph time, so it survives the subprojects'
// task-creation timing without afterEvaluate.
val stageAll =
    tasks.register("stageAll") {
        group = "publishing"
        description = "Publishes all subproject publications into the staging-deploy repo"
        dependsOn(
            subprojects.map { sub ->
                sub.tasks.matching { it.name == "publishAllPublicationsToStagingRepository" }
            },
        )
    }

// Fail the release if not all expected artifacts are staged for THIS version,
// instead of silently publishing an incomplete set. applyMavenCentralRules
// validates POM shape, not module count, so a valid-but-partial bundle would
// otherwise upload cleanly.
val verifyStagingComplete =
    tasks.register("verifyStagingComplete") {
        group = "publishing"
        description = "Fails the release if not all expected artifacts are staged"
        dependsOn(stageAll)
        val stagingRoot = layout.buildDirectory.dir("staging-deploy/io/github/ollin/kdaisyui")
        val releaseVersion = version.toString()
        doLast {
            val base = stagingRoot.get().asFile
            val missing =
                publishedArtifacts.filter { artifact ->
                    base.resolve(artifact).resolve(releaseVersion).listFiles()
                        ?.any { it.name.endsWith(".pom") } != true
                }
            require(missing.isEmpty()) {
                "Refusing to release $releaseVersion: missing staged artifacts $missing under $base"
            }
        }
    }

tasks.matching { it.name == "jreleaserDeploy" || it.name == "jreleaserFullRelease" }
    .configureEach { dependsOn(verifyStagingComplete) }
