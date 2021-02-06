package com.wsbo.concurrent;

/**
 * 第八种方法
 * @author 项峥
 */
public class R08Synchronized {
    private static final Object LOCK_OBJ = new Object();
    private static class TaskThread extends Thread {
        final ResultContainer resultContainer;

        public TaskThread(ResultContainer resultContainer) {
            this.resultContainer = resultContainer;
        }

        @Override
        public void run() {
            synchronized (LOCK_OBJ) {
                resultContainer.result = Task.sum();
                LOCK_OBJ.notify();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        ResultContainer resultContainer = new ResultContainer();
        new TaskThread(resultContainer).start();
        synchronized (LOCK_OBJ) {
            try {
                LOCK_OBJ.wait();
                System.out.println(resultContainer.result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
