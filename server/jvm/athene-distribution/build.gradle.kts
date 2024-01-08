plugins {
    distribution
}

dependencies {
    implementation(project(":athene-config"))
    implementation(project(":athene-dictionary-cache"))
    implementation(project(":athene-eventhandler"))
    implementation(project(":athene-messages"))
    implementation(project(":athene-script-config"))
    implementation(project(":athene-integration"))
}

description = "athene-distribution"

distributions {
    main {
        contents {
            // Octal conversion for file permissions
            val libPermissions = "600".toInt(8)
            val scriptPermissions = "700".toInt(8)
            into("athene/bin") {
                from(configurations.runtimeClasspath)
                exclude("athene-config*.jar")
                exclude("athene-script-config*.jar")
                exclude("athene-distribution*.jar")
                include("athene-*.jar")
            }
            into("athene/lib") {
                from("${project.rootProject.buildDir}/dependencies")
                duplicatesStrategy = DuplicatesStrategy.EXCLUDE

                exclude("genesis-*.jar")
                exclude("athene-*.jar")
                exclude("*.zip")

                fileMode = libPermissions
            }
            into("athene/cfg") {
                from("${project.rootProject.projectDir}/athene-config/src/main/resources/cfg")
                from(project.layout.buildDirectory.dir("generated/product-details"))
                filter(
                    org.apache.tools.ant.filters.FixCrLfFilter::class,
                    "eol" to org.apache.tools.ant.filters.FixCrLfFilter.CrLf.newInstance("lf")
                )
            }
            into("athene/scripts") {
                from("${project.rootProject.projectDir}/athene-config/src/main/resources/scripts")
                from("${project.rootProject.projectDir}/athene-script-config/src/main/resources/scripts")
                filter(
                    org.apache.tools.ant.filters.FixCrLfFilter::class,
                    "eol" to org.apache.tools.ant.filters.FixCrLfFilter.CrLf.newInstance("lf")
                )
                fileMode = scriptPermissions
            }
            // Removes intermediate folder called with the same name as the zip archive.
            into("/")
        }
    }
}

val distribution by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

// To give custom name to the distribution package
tasks {
    distZip {
        archiveBaseName.set("genesisproduct-athene")
        archiveClassifier.set("bin")
        archiveExtension.set("zip")
    }
    copyDependencies {
        enabled = false
    }
}

artifacts {
    val distzip = tasks.distZip.get()
    add("distribution", distzip.archiveFile) {
        builtBy(distzip)
    }
}

publishing {
    publications {
        create<MavenPublication>("atheneServerDistribution") {
            artifact(tasks.distZip.get())
        }
    }
}

description = "athene-distribution"
