rootProject.name = "genesisproduct-athene"

buildCache {
    local {
        directory = File(rootDir.parentFile.parent, "build-cache")
        removeUnusedEntriesAfterDays = 30
        isEnabled = true
    }
}

pluginManagement {
    pluginManagement {
        val genesisVersion: String by settings
        val deployPluginVersion: String by settings
        plugins {
            id("global.genesis.build") version genesisVersion
            id("global.genesis.deploy") version deployPluginVersion
        }
    }
    repositories {
        mavenLocal {
            // VERY IMPORTANT!!! EXCLUDE AGRONA AS IT IS A POM DEPENDENCY AND DOES NOT PLAY NICELY WITH MAVEN LOCAL!
            content {
                excludeGroup("org.agrona")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            val repoUrl = if(extra.properties["clientSpecific"] == "true") {
                "https://genesisglobal.jfrog.io/genesisglobal/libs-release-client"
            } else {
                "https://genesisglobal.jfrog.io/genesisglobal/dev-repo"
            }
            url = uri(repoUrl)
            credentials {
                username = extra.properties["genesisArtifactoryUser"].toString()
                password = extra.properties["genesisArtifactoryPassword"].toString()
            }
        }
    }
}



include("athene-config")
include("athene-messages")
include("athene-eventhandler")
include("athene-integration")
include("athene-script-config")
include("athene-distribution")
include("athene-dictionary-cache")
include("athene-dictionary-cache:athene-generated-sysdef")
include("athene-dictionary-cache:athene-generated-fields")
include("athene-dictionary-cache:athene-generated-dao")
include("athene-dictionary-cache:athene-generated-hft")
include("athene-dictionary-cache:athene-generated-view")
include("athene-deploy")
include("athene-site-specific")

includeBuild("../../client")
