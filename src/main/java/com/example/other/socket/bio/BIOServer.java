package com.example.other.socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author huangqi
 * @Package com.example.other.socket.bio
 * @Description: BIO服务端
 * @date 2020/5/10 10:33 下午
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        //这是服务端socket对象
        ServerSocket serverSocket =new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));
        byte[] data = new byte[1024];
        while (true){
            //监听客户端连接 第一个阻塞的地方
            //通信的socket对象
            Socket socket = serverSocket.accept();
            //读取通信的数据 第二个阻塞的地方
            InputStream inputStream = socket.getInputStream();
            inputStream.read(data);
            System.out.println(new String(data));

        }

    }


}
