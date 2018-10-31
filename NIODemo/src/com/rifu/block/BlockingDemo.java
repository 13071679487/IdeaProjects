package com.rifu.block;

import org.junit.Test;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Rifu
 * @create 2018-05-17 22:29
 * <p>
 * NIO是非阻塞的：（应用于网络数据的传输）
 * Selector(选择器)：把每一个传输数据的通道注册到选择器上面，当所有的数据都准备就绪后，才会分配任务到服务器对应的线程上
 * 是SelectableChannel的多路复用器，用于监控SelectableChannel的IO状况
 */
public class BlockingDemo {

    @Test
    public void client() {
        //1.获取通道
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 12345));
            fileChannel = FileChannel.open(Paths.get("test.jpg"), StandardOpenOption.READ);

            //2.分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void server() {
        ServerSocketChannel serverSocketChannel = null;
        FileChannel outChannel = null;
        SocketChannel socketChannel = null;
        try {
            //1.获取通道
            serverSocketChannel = ServerSocketChannel.open();
            //2.绑定连接
            serverSocketChannel.bind(new InetSocketAddress(12345));
            outChannel = FileChannel.open(Paths.get("test5.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            //3.获取客户端连接的通道
            socketChannel = serverSocketChannel.accept();
            //4.分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (serverSocketChannel != null) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
