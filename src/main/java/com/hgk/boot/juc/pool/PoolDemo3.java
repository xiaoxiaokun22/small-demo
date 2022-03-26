package com.hgk.boot.juc.pool;

import java.util.concurrent.*;

public class PoolDemo3 {
    public static void main(String[] args){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                10,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

    }
}
