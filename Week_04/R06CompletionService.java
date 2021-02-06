package com.wsbo.concurrent;

import java.util.concurrent.*;

/**
 * 第六种方法
 * @author 项峥
 */
public class R06CompletionService {
    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        try (MyThreadPoolExecutor executor = new MyThreadPoolExecutor()) {
            CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
            completionService.submit(Task::sum);
            Integer result = completionService.take().get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
