package com.wsbo.concurrent;

import java.util.concurrent.*;

/**
 * 第二种方法
 * @author 项峥
 */
public class R02Callable {
    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        try (MyThreadPoolExecutor executor = new MyThreadPoolExecutor()) {
            Future<Integer> future = executor.submit(Task::sum);
            Integer result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
