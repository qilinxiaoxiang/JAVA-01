package com.wsbo.batchinsert.inserter;

import com.wsbo.batchinsert.dal.mapper.TradeMapper;
import com.wsbo.batchinsert.dal.model.Trade;
import com.wsbo.batchinsert.util.ApplicationContextUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSqlBatchInserter {
    public static final int TASK_COUNT = 5000;
    public static CountDownLatch LATCH = new CountDownLatch(TASK_COUNT);
    public static void insert() {
        long startTime = System.currentTimeMillis();
        final TradeMapper tradeMapper = ApplicationContextUtil.getBean(TradeMapper.class);
        final Trade trade = Trade.builder()
                .goodsInfo("[]")
                .payTime(new Date())
                .totalAmount(BigDecimal.ZERO)
                .receivedAmount(BigDecimal.ZERO)
                .tradeId("trade1")
                .uid("user1")
                .build();
        List<Trade> batch = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            batch.add(trade);
        }
        Runnable runnable = () -> {
            tradeMapper.insertBatch(batch);
            LATCH.countDown();;
        };
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < TASK_COUNT; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SimpleInserter costs " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
