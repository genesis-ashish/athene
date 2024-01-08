package global.genesis.athene.perf;

import global.genesis.commons.annotation.Module;
import global.genesis.db.entity.InsertResult;
import global.genesis.db.rx.entity.multi.RxEntityDb;
import global.genesis.gen.dao.Orders;
import global.genesis.gen.dao.enums.Direction;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Module
public class OrderManagerJava {
    private final RxEntityDb rxEntityDb;
    Logger logger = LoggerFactory.getLogger(OrderManagerJava.class);

    @Inject
    public OrderManagerJava(RxEntityDb rxEntityDb) {
        this.rxEntityDb = rxEntityDb;
    }

    @PostConstruct
    public void writeDataToConsole() {
        List<String> symbols = List.of("AAPL", "GOOG", "MSFT", "AMZN", "TSLA", "META", "NVDA", "BRK.A", "JPM", "V");
        List<String> directions = List.of("BUY", "SELL");
        List<Integer> orderIds = new ArrayList<>();
        int total_records = 1000000;
        int splitNumber = 10000;
        for (int i = 1; i <= total_records; i++) {
            orderIds.add(i);
        }

//        todo: insert starting time here
        Random random = new Random();
        List<Orders> ordersList = new ArrayList<>();
        initializedOrderList(total_records, random, symbols, directions, orderIds, ordersList);
        List<List<Orders>> splitedList = new ArrayList<>();
        splitOrderList(ordersList, splitedList, splitNumber);
        long startTime = System.currentTimeMillis();
        insertIntoDatabase(splitedList);
        long totalTime = System.currentTimeMillis() - startTime;
        logger.info("total records inserted: {} and total time taken: {}",total_records, totalTime);
    }

    private void insertIntoDatabase(List<List<Orders>> splitedList) {
        for (List<Orders> orders:splitedList) {
            rxEntityDb.insertAll(orders).subscribe((SingleObserver<? super List<InsertResult<Orders>>>) new SingleObserver<List<InsertResult<Orders>>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }

                @Override
                public void onSuccess(@NonNull List<InsertResult<Orders>> insertResults) {
                    logger.info("total records inserted into database: {}",insertResults.size());
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
        }
    }

    private void splitOrderList(List<Orders> ordersList, List<List<Orders>> splitedList, int splitNumber) {
        for (int i=0;i<ordersList.size(); i+=splitNumber) {
            List<Orders> subList = ordersList.subList(i,Math.min(i+splitNumber, ordersList.size()));
            splitedList.add(subList);
        }
    }

    private static void initializedOrderList(int total_records, Random random, List<String> symbols, List<String> directions, List<Integer> orderIds, List<Orders> ordersList) {
        for (int i = 0; i < total_records; i++) {
            int quantity = random.nextInt(1000) + 1;
            double price = 100.00 + (1000.00 - 100.00) * random.nextDouble();
            int symbolIndex = random.nextInt(symbols.size());
            int directionIndex = random.nextInt(directions.size());
            Orders orders = Orders.builder().setOrderId(String.valueOf(orderIds.get(i))).setSymbol(symbols.get(symbolIndex)).setQuantity(quantity).setPrice(price).setDirection(Direction.valueOf(directions.get(directionIndex))).build();
            ordersList.add(orders);
            }
    }
}
