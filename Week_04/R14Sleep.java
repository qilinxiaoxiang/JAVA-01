package com.wsbo.concurrent;

/**
 * 第十四种方法
 * @author 项峥
 */
public class R14Sleep {
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
        new TaskThread(resultContainer).start();
        while (resultContainer.result == null) {
            Thread.sleep(1000);
        }
        System.out.println(resultContainer.result);
    }
}
