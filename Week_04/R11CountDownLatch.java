package com.wsbo.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 第十一种方法
 * @author 项峥
 */
public class R11CountDownLatch {
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);
    private static class TaskThread extends Thread {
        final ResultContainer resultContainer;

        public TaskThread(ResultContainer resultContainer) {
            this.resultContainer = resultContainer;
        }

        @Override
        public void run() {
            resultContainer.result = Task.sum();
            COUNT_DOWN_LATCH.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        ResultContainer resultContainer = new ResultContainer();
        new TaskThread(resultContainer).start();
        COUNT_DOWN_LATCH.await();
        System.out.println(resultContainer.result);
    }
}
