package com.example.other.thread;

import java.util.Arrays;

/**
 * @author huangqi
 * @Package com.example.other.thread
 * @Description: StackTraceElement[] 堆栈信息
 * @date 2019-08-18 13:07
 */
public class ThreadDemo2 {
   public void a(){
       b();
   }
   public void b(){
       c();
   }
   public void c(){
       //线程堆栈信息
       StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
       Arrays.stream(stackTrace).forEach(f->{
           System.out.println("className:"+f.getClassName()+" "+"fileName"+f.getFileName()
            +" "+"MethodName"+ f.getMethodName()+" "+"lineNumber"+f.getLineNumber());

       });
   }

    public static void main(String[] args) {
        ThreadDemo2 demo2 = new ThreadDemo2();
        demo2.a();
    }

}
