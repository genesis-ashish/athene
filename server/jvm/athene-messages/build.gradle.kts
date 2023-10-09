dependencies {
    implementation("global.genesis:genesis-messages")
    compileOnly(project(path = ":athene-dictionary-cache", configuration = "codeGen"))
}

description = "athene-messages"