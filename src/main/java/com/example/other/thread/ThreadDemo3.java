package com.example.other.thread;

/**
 * @author huangqi
 * @Package com.example.other.thread
 * @Description: interrupt 中断
 * @date 2019-08-18 14:26
 */
public class ThreadDemo3 extends Thread{

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i="+i);
            if(this.isInterrupted()){
                System.out.println("我要推出了");
                break;
            }

           /* try {
                Thread.sleep(1000);

            }catch (InterruptedException e){
                System.out.println("是否中断"+this.isInterrupted());
                System.out.println(e.getMessage());
            }*/
        }
    }


}
