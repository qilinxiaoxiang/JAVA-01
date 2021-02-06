package com.wsbo.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 第五种方法
 * @author 项峥
 */
public class R05CompletableFuture {
    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(Task::sum);
        try {
            Integer result = completableFuture.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
