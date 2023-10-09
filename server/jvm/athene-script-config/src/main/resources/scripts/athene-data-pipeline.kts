//creating csv data pipeline for testing
package scripts

import global.genesis.db.rx.RxDb.Companion.getValue

pipelines {
    csvSource("athene-test") {
        location = "file:"+systemDefinition.getValue("UPLOAD_DIR")+"?fileName="+systemDefinition.getValue("UPLOAD_FILE")
        map("e2e-test", TRADE) {
            TRADE {
                TRADE_ID {
                    property = "trade_id"
                }
                QUANTITY {
                    property = "quantity"
                }

                PRICE {
                    property = "price"
                }

                SYMBOL {
                    property = "symbol"
                }

                DIRECTION {
                    property = "direction"
                }
            }
        }
    }
}
