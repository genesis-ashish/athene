
// Add your genesis config dependencies here
dependencies {
    implementation(project(":athene-dictionary-cache:athene-generated-dao"))
    implementation(project(":athene-dictionary-cache:athene-generated-fields"))
    implementation(project(":athene-dictionary-cache:athene-generated-hft"))
    implementation(project(":athene-dictionary-cache:athene-generated-sysdef"))
    implementation(project(":athene-dictionary-cache:athene-generated-view"))

    implementation("global.genesis:auth-config:${properties["authVersion"]}")
}

description = "athene-dictionary-cache"
