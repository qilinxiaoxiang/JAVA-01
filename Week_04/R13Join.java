package com.wsbo.concurrent;

/**
 * 第十三种方法
 * @author 项峥
 */
public class R13Join {
    private static class TaskThread extends Thread {
        final ResultContainer resultContainer;

        public TaskThread(ResultContainer resultContainer) {
            this.resultContainer = resultContainer;
        }

        @Override
        public void run() {
            resultContainer.result = Task.sum();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        ResultContainer resultContainer = new ResultContainer();
        TaskThread thread = new TaskThread(resultContainer);
        thread.start();
        thread.join();
        System.out.println(resultContainer.result);
    }
}
