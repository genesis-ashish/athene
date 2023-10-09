//creating csv data pipeline for testing
package scripts
pipelines {
    csvSource("athene-test") {
        location = "file:testData?fileName=trade.csv"
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
