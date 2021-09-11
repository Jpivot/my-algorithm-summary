package com.aronson.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Sherlock
 */
public class ThreadSafeDateTimeTest {
    public static void threadSafeDate() {
        // 线程安全的日期处理方式
        // 只针对日期进行处理的类：LocalDate
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowPlusOneDay = dateTimeFormatter.format(localDateTime);
        System.out.println(nowPlusOneDay);
    }
}
