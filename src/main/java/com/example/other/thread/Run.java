package com.example.other.thread;

/**
 * @author huangqi
 * @Package com.example.other.thread
 * @Description:
 * @date 2019-08-18 14:50
 */
public class Run {

    public static void main(String[] args) {
        try {

            ThreadDemo3 demo3 = new ThreadDemo3();
            demo3.start();
            Thread.sleep(1000);
            demo3.interrupt();
            Thread.currentThread().interrupt();
            System.out.println("ThreadDemo3是否停止" + demo3.isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("main是否停止" + Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
