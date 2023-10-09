dependencies {
    implementation("global.genesis:genesis-pal-execution")
    implementation("global.genesis:genesis-eventhandler")
    implementation(project(":athene-messages"))
    api("global.genesis:genesis-db")
    compileOnly(project(":athene-config"))
    compileOnly(project(path = ":athene-dictionary-cache", configuration = "codeGen"))
    testImplementation("global.genesis:genesis-dbtest")
    testImplementation("global.genesis:genesis-testsupport")
    testImplementation(project(path = ":athene-dictionary-cache", configuration = "codeGen"))
}

description = "athene-eventhandler"