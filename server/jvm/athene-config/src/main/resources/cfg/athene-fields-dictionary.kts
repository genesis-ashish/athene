/**
 * System              : Genesis Business Library
 * Sub-System          : multi-pro-code-test Configuration
 * Version             : 1.0
 * Copyright           : (c) Genesis
 * Date                : 2022-03-18
 * Function : Provide fields config for multi-pro-code-test.
 *
 * Modification History
 */

fields {

    field("TRADE_ID", type = STRING)
    field("QUANTITY", type = INT)
    field("TRADE_COUNT", type = INT)
    field("PRICE", type = DOUBLE)
    field("SYMBOL", type = STRING)
    field("DIRECTION", type = ENUM("BUY", "SELL", default = "BUY"))
    field("SAMOUNT", type=DOUBLE)
    field("AQTY", type = DOUBLE)
    field("SDQTY", type = DOUBLE)
    field("APRICE", type = DOUBLE)
    field("SDPRICE", type = DOUBLE)
    field("MATURITY", type = INT)
    field("POSITION_ID", type=STRING)
    field("ORDER_ID", type=STRING)

}
