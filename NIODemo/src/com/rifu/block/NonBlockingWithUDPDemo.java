package com.rifu.block;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Rifu
 * @create 2018-05-18 16:28
 *
 * UDP的非阻塞式IO的实现
 */
public class NonBlockingWithUDPDemo {

    @Test
    public void send(){
        DatagramChannel datagramChannel=null;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);

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
                    datagramChannel.send(byteBuffer,new InetSocketAddress("127.0.0.1",12345));
                    byteBuffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(datagramChannel!=null){
                try {
                    datagramChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void recive(){
        DatagramChannel datagramChannel=null;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);

            datagramChannel.bind(new InetSocketAddress(12345));
            Selector selector = Selector.open();
            datagramChannel.register(selector,SelectionKey.OP_READ);
            while (selector.select()>0){
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isReadable()){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        datagramChannel.receive(byteBuffer);
                        byteBuffer.flip();
                       System.out.println(new String(byteBuffer.array(),0,byteBuffer.limit()));
                       byteBuffer.clear();
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(datagramChannel!=null){
                try {
                    datagramChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
