package com.example.other.thread;

/**
 * @author huangqi
 * @Package com.example.other.thread
 * @Description:
 * @date 2019-08-18 13:07
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
