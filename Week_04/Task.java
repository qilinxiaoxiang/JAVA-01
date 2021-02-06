package com.wsbo.concurrent;

/**
 * task
 * @author KimmKing
 */
public class Task {
    public static int sum() {
        System.out.println("子线程id:" + Thread.currentThread().getId());
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}
