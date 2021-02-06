package com.wsbo.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 第七种方法
 *
 * @author 项峥
 */
public class R07ForkJoin {
    private static class FibTask extends RecursiveTask<Integer> {
        final int n;

        public FibTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            System.out.println("子线程id:" + Thread.currentThread().getId() + ", n=" + n);
            if (n < 2) {
                return 1;
            }
            FibTask f1 = new FibTask(n - 1);
            f1.fork();
            FibTask f2 = new FibTask(n - 2);
            return f2.compute() + f1.join();
        }
    }

    public static void main(String[] args) {
        System.out.println("父线程id:" + Thread.currentThread().getId());
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        FibTask task = new FibTask(36);
        Integer result = forkJoinPool.invoke(task);
        System.out.println(result);
    }
}

