package scripts
/**
 * System              : Genesis Business Library
 * Sub-System          : multi-pro-code-test Configuration
 * Version             : 1.0
 * Copyright           : (c) Genesis
 * Date                : 2022-03-18
 * Function : Provide dataserver config for multi-pro-code-test.
 *
 * Modification History
 */
dataServer {
    query("ALL_TRADES", TRADE)
    query("ALL_POSITIONS",POSITION_BY_SYMBOL)
    query("ALL_SIDES", STATS_BY_SIDE)
    query("ALL_MATURITY", STATS_BY_MATURITY)
}
