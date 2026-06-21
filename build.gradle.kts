plugins {
    base
    alias(libs.plugins.jreleaser)
}

group = "io.github.ollin.kdaisyui"

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
