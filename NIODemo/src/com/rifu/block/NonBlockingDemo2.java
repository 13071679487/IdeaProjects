package com.rifu.block;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Rifu
 * @create 2018-05-18 15:29
 * <p>
 * 非阻塞式的实现
 */
public class NonBlockingDemo2 {

    @Test
    public void client() {
        SocketChannel socketChannel = null;
        try {
            //1.获取到通道
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 12345));
            //2.切换为非阻塞模式
            socketChannel.configureBlocking(false);
            //3.分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String content = scanner.next().trim();
                if (content.equals("e") || content.equals("exit")) {
                    break;
                }
                if (content != null && content.length() > 0) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(content);
                    sb.append("\t");
                    sb.append(sdf.format(new Date()));
                    byteBuffer.put(sb.toString().getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                    byteBuffer.clear();
                }
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
        }
    }

    @Test
    public void server() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            //1.获取通道
            serverSocketChannel = ServerSocketChannel.open();
            // 2. 服务器端也要设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //3.绑定端口
            serverSocketChannel.bind(new InetSocketAddress(12345));
            //4.获取选择器
            Selector selector = Selector.open();
            //5.将通道注册到选择器上，并且指定  监听接收事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //6.轮训式地获取选择器上已经“准备就绪”的事件
            while (selector.select() > 0) {
                //7.获取当前选择器中所有注册的选择键（已就绪的监听事件）
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    //8.获取准备就绪的事件
                    SelectionKey selectionKey = iterator.next();
                    //9.判断是什么事件准备就绪（以此来进行不用的操作）
                    if (selectionKey.isAcceptable()) {
                        //10，若“接受就绪”，获取客户端连接
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        //11.切换为非阻塞模式
                        socketChannel.configureBlocking(false);
                        //12.将该通道注册到选择器上
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        //13.获取当前选择器上“读就绪”状态的通道
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int len;
                        while ((len = socketChannel.read(byteBuffer)) > 0) {
                            byteBuffer.flip();
                            System.out.println(socketChannel.getRemoteAddress() + " : " + new String(byteBuffer.array(), 0, len));
                            byteBuffer.clear();
                        }
                    }
                    //14.取消选择键，不然下次依然有效
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
