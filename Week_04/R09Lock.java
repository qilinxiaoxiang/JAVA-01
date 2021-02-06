package com.wsbo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 第九种方法
 * @author 项峥
 */
public class R09Lock {
    private static final Lock LOCK = new ReentrantLock();
    private static final Condition RESULT_GET = LOCK.newCondition();
    private static class TaskThread extends Thread {
        final ResultContainer resultContainer;

        public TaskThread(ResultContainer resultContainer) {
            this.resultContainer = resultContainer;
        }

        @Override
        public void run() {
            LOCK.lock();
            try {
                resultContainer.result = Task.sum();
                RESULT_GET.signalAll();
            } finally {
                LOCK.unlock();
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        ResultContainer resultContainer = new ResultContainer();
        new TaskThread(resultContainer).start();
        LOCK.lock();
        try {
            RESULT_GET.await();
            System.out.println(resultContainer.result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
