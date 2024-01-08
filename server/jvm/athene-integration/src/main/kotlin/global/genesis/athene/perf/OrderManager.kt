package global.genesis.athene.perf

import global.genesis.db.entity.InsertResult
import global.genesis.db.rx.entity.multi.RxEntityDb
import global.genesis.gen.dao.Orders
import global.genesis.gen.dao.enums.Direction
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Inject

//@Module
class OrderManager @Inject constructor(private val rxEntityDb: RxEntityDb) {
    private val logger: Logger = LoggerFactory.getLogger(OrderManager::class.java.name)

    @PostConstruct
    fun writeDataToConsole() {
        val symbols = listOf("AAPL", "GOOG", "MSFT", "AMZN", "TSLA", "META", "NVDA", "BRK.A", "JPM", "V")
        val directions = listOf("BUY", "SELL")
        val orderIds = ArrayList<Int>()
        val totalRecords = 1000000
        val splitNumber = 10000
        for (i in 1..totalRecords) {
            orderIds.add(i)
        }

        val random = Random()
        val ordersList = ArrayList<Orders>()
        initializedOrderList(totalRecords, random, symbols, directions, orderIds, ordersList)
        val splitedList: MutableList<List<Orders>> = ArrayList()
        splitOrderList(ordersList, splitedList, splitNumber)
        val startTime = System.currentTimeMillis()
        insertIntoDatabase(splitedList)
        val totalTime = System.currentTimeMillis() - startTime
        logger.info("total records inserted: {} and total time taken: {}", totalRecords, totalTime)
    }

    private fun insertIntoDatabase(splitedList: List<List<Orders>>) {
        for (orders in splitedList) {
            rxEntityDb.insertAll(orders).subscribe(object : SingleObserver<List<InsertResult<Orders>>> {
                override fun onSubscribe(d: @NonNull Disposable) {}
                override fun onSuccess(insertResults: @NonNull List<InsertResult<Orders>>) {
                    logger.info("total records inserted into database: {}", insertResults.size)
                }

                override fun onError(e: @NonNull Throwable) {}
            })
        }
    }

    private fun splitOrderList(ordersList: List<Orders>, splitedList: MutableList<List<Orders>>, splitNumber: Int) {
        var i = 0
        while (i < ordersList.size) {
            val subList = ordersList.subList(i, (i + splitNumber).coerceAtMost(ordersList.size))
            splitedList.add(subList)
            i += splitNumber
        }
    }

    private fun initializedOrderList(totalRecords: Int, random: Random, symbols: List<String>, directions: List<String>, orderIds: List<Int>, ordersList: MutableList<Orders>) {
        for (i in 0 until totalRecords) {
            val quantity = random.nextInt(1000) + 1
            val price = 100.00 + (1000.00 - 100.00) * random.nextDouble()
            val symbolIndex = random.nextInt(symbols.size)
            val directionIndex = random.nextInt(directions.size)
            val orders = Orders.builder()
                .setOrderId(orderIds[i].toString())
                .setSymbol(symbols[symbolIndex])
                .setQuantity(quantity)
                .setPrice(price)
                .setDirection(Direction.valueOf(directions[directionIndex]))
                .build()
            ordersList.add(orders)
        }
    }
}
