package com.rifu.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TCPClient {
    public static void main(String [] args){
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
}
