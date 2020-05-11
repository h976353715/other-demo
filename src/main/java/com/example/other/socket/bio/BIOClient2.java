package com.example.other.socket.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author huangqi
 * @Package com.example.other.socket.bio
 * @Description: BIO客户端
 * @date 2020/5/10 10:40 下午
 */
public class BIOClient2 {


    public static void main(String[] args) throws IOException {
        //客户端连接
        Socket client = new Socket("127.0.0.1",8080);
        //输入数据
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        //发送给服务端
        client.getOutputStream().write(text.getBytes());
        client.close();
    }
}
