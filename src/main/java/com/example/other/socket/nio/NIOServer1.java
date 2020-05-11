package com.example.other.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangqi
 * @Package com.example.other.socket.bio
 * @Description: BIO服务端 简单版本 只有非阻塞
 * @date 2020/5/10 10:33 下午
 */
public class NIOServer1 {

    /**
     * 虽然解决阻塞问题但是还有列表维护的socket对象一直存在问题，不合适使用
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //这是服务端socket对象
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(8080));
        //设置成非阻塞接收客户端连接
        serverSocket.configureBlocking(false);
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            for (SocketChannel channel : channels) {
                //DirectByteBuffer是直接内存
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                channel.read(buffer);
                //写模式切换到读模式
                buffer.flip();
                Charset charset = Charset.forName("utf-8");
                String content = charset.decode(buffer).toString();
                if (content != null && !"".equals(content)) {
                    System.out.println(content);
                }
            }
            //通信的socket对象
            SocketChannel socket = serverSocket.accept();
            if (socket != null) {
                System.out.println("client conn");
                //设置非阻塞读取数据
                socket.configureBlocking(false);
                //添加到到队列
                channels.add(socket);
            }

        }

    }


}
