plugins {
    id("global.genesis.deploy")
}

description = "athene-deploy"

dependencies {
    genesisServer(
        group = "global.genesis",
        name = "genesis-distribution",
        version = properties["genesisVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )
    genesisServer(
        group = "global.genesis",
        name = "auth-distribution",
        version = properties["authVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )

    genesisServer(project(":athene-distribution", "distribution"))
    genesisServer(project(":athene-site-specific", "distribution"))
    genesisWeb(":client")

    api(project(":athene-eventhandler"))
    api(project(":athene-messages"))
    // Add additional dependencies on other external distributions here
}
tasks {
    copyDependencies {
        from(configurations.getByName("genesisServer"))
    }
}
