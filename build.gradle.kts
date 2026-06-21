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
