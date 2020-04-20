package com.example.other.thread;

/**
 * @author huangqi
 * @Package com.example.other.thread
 * @Description:
 * @date 2019-08-08 14:14
 */
public class ThreadException implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName());
        System.out.println(e.getCause());
    }
}
