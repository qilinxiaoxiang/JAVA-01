package com.wsbo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 第十二种方法
 * @author 项峥
 */
public class R12CyclicBarrier {
    private static class TaskThread extends Thread {
        final CyclicBarrier cyclicBarrier;
        final ResultContainer resultContainer;

        public TaskThread(CyclicBarrier cyclicBarrier, ResultContainer resultContainer) {
            this.cyclicBarrier = cyclicBarrier;
            this.resultContainer = resultContainer;
        }

        @Override
        public void run() {
            resultContainer.result = Task.sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        ResultContainer resultContainer = new ResultContainer();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new TaskThread(cyclicBarrier, resultContainer).start();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(resultContainer.result);
    }
}
