package com.wsbo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 第一种方法
 * @author 项峥
 */
public class R01BlockingQueue {
    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        try (MyThreadPoolExecutor executor = new MyThreadPoolExecutor()) {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
            executor.execute(() -> {
                int sum = Task.sum();
                try {
                    queue.put(sum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Integer result = queue.take();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
