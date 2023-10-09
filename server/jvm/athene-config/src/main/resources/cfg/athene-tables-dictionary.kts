import javassist.compiler.ast.Symbol
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType

/**
 * System              : Genesis Business Library
 * Sub-System          : multi-pro-code-test Configuration
 * Version             : 1.0
 * Copyright           : (c) Genesis
 * Date                : 2022-03-18
 * Function : Provide table definition config for multi-pro-code-test.
 *
 * Modification History
 */

tables {

    table (name = "TRADE", id = 2000) {
        sequence(TRADE_ID, "TR")
        QUANTITY not null
        PRICE not null
        SYMBOL not null
        DIRECTION not null
        MATURITY not null

        primaryKey {
            TRADE_ID
        }
        indices {
            nonUnique { QUANTITY }
            nonUnique { PRICE }
            nonUnique { SYMBOL }
            nonUnique { DIRECTION }
            nonUnique { MATURITY }
        }
    }

    table(name="POSITION_BY_SYMBOL", id=2002){
        SYMBOL
        SAMOUNT
        TRADE_COUNT
        AQTY
        SDQTY
        APRICE
        SDPRICE
        primaryKey {
            SYMBOL
        }
    }

    table (name = "STATS_BY_SIDE", id=2004){
        DIRECTION
        SAMOUNT
        TRADE_COUNT
        AQTY
        SDQTY
        APRICE
        SDPRICE
        primaryKey {
            DIRECTION
        }
    }

    table(name = "STATS_BY_MATURITY", id=2006){
        MATURITY
        SAMOUNT
        TRADE_COUNT
        AQTY
        SDQTY
        APRICE
        SDPRICE
        primaryKey {
            MATURITY
        }
    }

}
