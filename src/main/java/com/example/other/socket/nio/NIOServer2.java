package com.example.other.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author huangqi
 * @Package com.example.other.socket.bio
 * @Description: BIO服务端
 * @date 2020/5/10 10:33 下午
 */
public class NIOServer2 {

    /**
     * 虽然解决阻塞问题但是还有列表维护的socket对象一直存在问题，不合适使用
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //这是服务端socket对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置成非阻塞接收客户端连接
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8081));
        //注册到选择器
        Selector selector = Selector.open();
        //只对连接事件感兴趣
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //监听
        while (true) {
            selector.select();
            //返回感兴趣事件集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (selectionKeys.size() > 0) {
                System.out.println(1);
            }
            //循环处理事件结合
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //连接事件
                if (key.isAcceptable()) {
                    System.out.println("Client connect!");
                    //接受客户端连接
                    SocketChannel acceptClient = serverSocketChannel.accept();
                    acceptClient.configureBlocking(false);
                    //注册到选择器 只对读事件感兴趣
                    acceptClient.register(selector, SelectionKey.OP_READ);
                }
                //可读事件 客户端有数据过来就会有
                if (key.isReadable()) {
                    //客户端发送数据过来了
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    int read = channel.read(buffer);
                    System.out.println("接收到消息：" + new String(buffer.array()));
                    System.out.println("read:" + read);
                    //不关闭会一直重复读取
                    if (read == -1) {
                        channel.close();
                    }
                }
                //不移除的话会留到下次循环 会信息重复
                //只移除了SelectionKey 信道信息还是在selector中的
                keyIterator.remove();

            }

        }

    }


}
