dependencies {
    implementation("global.genesis:genesis-pal-execution")
    compileOnly("global.genesis:genesis-dictionary")
    api("global.genesis:genesis-pal-dataserver")
    api("global.genesis:genesis-pal-requestserver")
    api("global.genesis:genesis-pal-streamer")
    api("global.genesis:genesis-pal-streamerclient")
    api("global.genesis:genesis-pal-eventhandler")
    api("global.genesis:genesis-pal-datapipeline")
    api("global.genesis:genesis-pal-consolidator")
    compileOnly(project(path = ":athene-dictionary-cache", configuration = "codeGen"))
    testCompileOnly(project(":athene-config"))
    testImplementation("global.genesis:genesis-dbtest")
    testImplementation("global.genesis:genesis-testsupport")
    testImplementation(project(path = ":athene-dictionary-cache", configuration = "codeGen"))
}

description = "athene-script-config"
