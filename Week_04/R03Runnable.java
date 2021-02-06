package com.wsbo.concurrent;

import java.util.concurrent.*;

/**
 * 第二种方法
 * @author 项峥
 */
public class R03Runnable {
    private static class TaskRunnable implements Runnable {
        ResultContainer resultContainer;
        TaskRunnable(ResultContainer resultContainer) {
            this.resultContainer = resultContainer;
        }

        @Override
        public void run() {
            this.resultContainer.result = Task.sum();
        }
    }

    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        try (MyThreadPoolExecutor executor = new MyThreadPoolExecutor()) {
            ResultContainer resultContainer = new ResultContainer();
            Future<ResultContainer> future = executor.submit(new TaskRunnable(resultContainer) , resultContainer);
            executor.shutdown();
            Integer result = future.get().result;
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}




