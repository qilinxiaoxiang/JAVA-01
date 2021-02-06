package com.wsbo.concurrent;

import java.util.concurrent.*;

/**
 * 第四种方法
 * @author 项峥
 */
public class R04FutureTask {
    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        try (MyThreadPoolExecutor executor = new MyThreadPoolExecutor()) {
            FutureTask<Integer> futureTask = new FutureTask<>(Task::sum);
            executor.submit(futureTask);
            Integer result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
