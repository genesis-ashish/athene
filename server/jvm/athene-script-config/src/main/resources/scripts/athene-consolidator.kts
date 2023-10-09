package scripts

import global.genesis.gen.dao.PositionBySymbol
import global.genesis.gen.dao.StatsByMaturity
import global.genesis.gen.dao.StatsBySide
import global.genesis.gen.dao.Trade

consolidators{
    consolidator(TRADE,POSITION_BY_SYMBOL){
        select {
            POSITION_BY_SYMBOL{
                sum {price * quantity } into SAMOUNT
                avg{price} into APRICE
                stdev{price} into SDPRICE
                avg {quantity} into AQTY
                stdev{quantity} into SDQTY
                count() into TRADE_COUNT
            }
        }
        groupBy { PositionBySymbol.BySymbol(symbol)} into {
            indexScan{Trade.BySymbol(groupId.symbol)}
        }
    }

    consolidator(TRADE,STATS_BY_SIDE){
        select {
            STATS_BY_SIDE{
                sum {price * quantity } into SAMOUNT
                avg{price} into APRICE
                stdev{price} into SDPRICE
                avg{quantity} into AQTY
                stdev{quantity} into SDQTY
                count() into TRADE_COUNT
            }
        }
        groupBy { StatsBySide.ByDirection(direction)} into {
            indexScan{ Trade.ByDirection(groupId.direction)}
        }
    }

    consolidator(TRADE,STATS_BY_MATURITY){
        select {
            STATS_BY_MATURITY{
                sum {price * quantity } into SAMOUNT
                avg{price} into APRICE
                stdev{price} into SDPRICE
                avg{quantity} into AQTY
                stdev{quantity} into SDQTY
                count() into TRADE_COUNT
            }
        }
        groupBy { StatsByMaturity.ByMaturity(maturity)} into {
            indexScan{ Trade.ByMaturity(groupId.maturity)}
        }
    }

}
