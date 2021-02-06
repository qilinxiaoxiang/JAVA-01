package com.wsbo.concurrent;

import java.util.concurrent.*;

/**
 * 可以try-with-resource的线程池
 * @author 项峥
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor implements AutoCloseable {
    public MyThreadPoolExecutor() {
        super(1,
                1,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Override
    public void close() {
        this.shutdown();
    }
}
