package com.wsbo.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 第十种方法
 * @author 项峥
 */
public class R10Semaphore {
    private static final Semaphore SEMAPHORE = new Semaphore(0);
    private static class TaskThread extends Thread {
        final ResultContainer resultContainer;

        public TaskThread(ResultContainer resultContainer) {
            this.resultContainer = resultContainer;
        }

        @Override
        public void run() {
            resultContainer.result = Task.sum();
            SEMAPHORE.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        ResultContainer resultContainer = new ResultContainer();
        new TaskThread(resultContainer).start();
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(resultContainer.result);
        } finally {
            SEMAPHORE.release();
        }
    }
}
