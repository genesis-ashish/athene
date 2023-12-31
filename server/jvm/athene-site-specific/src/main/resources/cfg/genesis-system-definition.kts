package genesis.cfg

systemDefinition {
    global {
        item(name = "DEPLOYED_PRODUCT", value = "athene")
        item(name = "MqLayer", value = "ZeroMQ")
        item(name = "DbLayer", value = "SQL")
        item(name = "DbHost", value = "jdbc:postgresql://localhost:5432/?user=genesis&password=genesis")
        item(name = "DbSqlConnectionPoolSize", value = "4")
        item(name = "DictionarySource", value = "DB")
        item(name = "AliasSource", value = "DB")
        item(name = "MetricsEnabled", value = "false")
        item(name = "ZeroMQProxyInboundPort", value = "5001")
        item(name = "ZeroMQProxyOutboundPort", value = "5000")
        item(name = "DbMode", value = "VANILLA")
        item(name = "GenesisNetProtocol", value = "V2")
        item(name = "ResourcePollerTimeout", value = "5")
        item(name = "ReqRepTimeout", value = "60")
        item(name = "MetadataChronicleMapAverageKeySizeBytes", value = "128")
        item(name = "MetadataChronicleMapAverageValueSizeBytes", value = "1024")
        item(name = "MetadataChronicleMapEntriesCount", value = "512")
        item(name = "DaemonServerPort", value = "4568")
        item(
            name = "JVM_OPTIONS",
            value = "-XX:MaxHeapFreeRatio=70 -XX:MinHeapFreeRatio=30 -XX:+UseG1GC -XX:+UseStringDeduplication -XX:OnOutOfMemoryError=\"handleOutOfMemoryError.sh %p\""
        )
        item(name = "DaemonHealthPort", value = "4569")
    }

    systems {

        system(name = "LOCAL") {

            hosts {
                host(LOCAL_HOST)
            }
            item(name = "DbNamespace", value = "athene")
            item(name = "ClusterPort", value = "6000")
            item(name = "location", value = "LO")
            item(name = "LogFramework", value = "LOG4J2")
            item(name = "LogFrameworkConfig", value = "log4j2-default.xml")
            item(name = "UPLOAD_DIR", value = "/home/chat/run/testdata")
            item(name = "UPLOAD_FILE", value = "trade.csv")
            item(name = "DbHost", value = "jdbc:postgresql://localhost:5432/?user=postgres&password=Password11*")
        }
    }

}
