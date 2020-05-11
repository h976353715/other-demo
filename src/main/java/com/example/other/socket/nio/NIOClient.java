package com.example.other.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author huangqi
 * @Package com.example.other.socket.bio
 * @Description: NIO客户端
 * @date 2020/5/10 10:40 下午
 */
public class NIOClient {


    public static void main(String[] args) throws IOException {
        //客户端连接
        SocketChannel client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8081));
        //输入数据
        while (true){
            Scanner scanner = new Scanner(System.in);
            String text = scanner.next();
            //发送给服务端
            ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
            System.out.println("sending: " + text);
            client.write(buffer);
            buffer.clear();
        }

    }
}
