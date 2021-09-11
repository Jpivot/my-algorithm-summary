package com.aronson.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * ${DESCRIPTION}
 * 关于限流 目前存在两大类，从线程个数（jdk1.5 Semaphore）和RateLimiter速率(guava)
 * Semaphore：从线程个数限流
 * RateLimiter：从速率限流  目前常见的算法是漏桶算法和令牌算法，下面会具体介绍
 *
 * @author mengxp
 * @version 1.0
 * @create 2018-01-15 22:44
 **/
public class RateLimiterExample {

    /**
     * Guava  0.5的意思是 1秒中0.5次的操作，2秒1次的操作  从速度来限流，从每秒中能够执行的次数来
     */
    private final static RateLimiter limiter = RateLimiter.create(1d);

    /**
     * 同时只能有三个线程工作 Java1.5  从同时处理的线程个数来限流
     */
    private final static Semaphore sem = new Semaphore(3);

    private static void testSemaphore() {
        try {
            sem.acquire();
            System.out.println(Thread.currentThread().getName() + " is doing work...");
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
            System.out.println(Thread.currentThread().getName() + " release the semephore..other thread can get and do job");
        }
    }

    public static void runTestSemaphore() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach((i) -> {
            // RateLimiterExample::testLimiter 这种写法是创建一个线程
            service.submit(RateLimiterExample::testSemaphore);
        });
        service.shutdown();
    }

    /**
     * Guava的RateLimiter
     */
    private static void testLimiter(CountDownLatch countDownLatch) {
        System.out.println(Thread.currentThread().getName() + " waiting  " + limiter.acquire());
        countDownLatch.countDown();
    }

    /**
     * Guava的RateLimiter
     */
    public static void runTestLimiter(CountDownLatch countDownLatch) {
        ExecutorService service = Executors.newFixedThreadPool(30);
        IntStream.range(0, 20).forEach((i) -> {
            service.submit(() -> testLimiter(countDownLatch));
        });
        service.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        long start = System.currentTimeMillis();
        // 1秒1次操作
        runTestLimiter(countDownLatch);
//         同时只能有三个线程工作
//        runTestSemaphore();
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);
    }
}
