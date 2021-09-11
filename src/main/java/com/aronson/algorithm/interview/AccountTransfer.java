package com.aronson.algorithm.interview;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountTransfer {
    // 初始化线程池，核心线程和最大线程都是10
    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());


    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        // 用来存放20个账户
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap(20);
        Random random = new Random();
        // i表示账户id，每个账户初始化余额是10000
        for (int i = 0; i < 20; i++) {
            concurrentHashMap.put(i, 10000);
        }

        CountDownLatch countDownLatch = new CountDownLatch(10);
        // 提交这10个任务
        for (int j = 0; j < 10; j++) {
            poolExecutor.execute(() -> {
                // 每个任务（每个线程）进行100次转账
                for (int k = 1; k <= 100; k++) {
                    int accountA = random.nextInt(20);
                    int accountB = random.nextInt(20);
                    // 两个进行转账的账户不能是同一个
                    while (accountA == accountB) {
                        accountB = random.nextInt(20);
                    }
                    synchronized (object) {
                        // 拿到两个账户中的余额
                        int valueA = concurrentHashMap.get(accountA);
                        int valueB = concurrentHashMap.get(accountB);
                        // 1到100的随机数
                        int money = random.nextInt(101);
                        // 进行转账
                        concurrentHashMap.put(accountA, valueA - money);
                        concurrentHashMap.put(accountB, valueB + money);
                    }

                }

                countDownLatch.countDown();
            });
        }
        // 等待所有任务执行完，设定等待时间为10秒
        countDownLatch.await(10, TimeUnit.SECONDS);
        // 打印10个线程进行100次转账之后的余额
        AtomicInteger total = new AtomicInteger();
        concurrentHashMap.forEach((key, value) -> {
            System.out.println(key + "====>" + value);
            total.set(total.get() + value);
        });
        System.out.println(total);

        // 关闭线程池，避免程序一直运行
        poolExecutor.shutdown();
    }
}
