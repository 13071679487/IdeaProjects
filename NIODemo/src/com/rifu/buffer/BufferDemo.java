package com.rifu.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author Rifu
 * @create 2018-05-17 14:07
 * NIO(New IO)
 *
 * 使用管道(Channel)进行连接，使用缓冲区(Buffer)进行数据传输
 * IO是阻塞的，NIO是非阻塞的
 *
 * 根据数据类型不同，提供了不同类型的缓冲区
 * ByteBuffer....等等
 *
 *
 * 缓冲区存取数据的两个核心方法
 * put()
 * get()
 * 缓冲区的核心属性
 *     // Invariants: mark <= position <= limit <= capacity
 *     private int capacity;            最大缓冲区存储数据的用量，一旦声明不能改变
 *     private int limit;                    界限，表示缓冲区中可以操作数据的大小
 *     private int position = 0;       位置：表示缓冲区中正在操作数据的位置
 *     private int mark = -1;           标记：记录当前position的位置，可以通过reset()恢复到mark的位置
 *
 *     直接缓冲区和非直接缓冲区
 *     1.通过allocate分配的内存是在JVM里面的，是非直接缓冲区,每次读和写都会经过一次从内核地址空间(物理内存)到用户地址空间(JVM)的copy
 *     2.直接缓冲区，通过allocateDirect      ，内核地址空间和用户地址空间都是直接指向到物理内存映射文件(希望文件在内存长时间停留才使用)
 *
 */
public class BufferDemo {

    @Test
    public void testMark(){
        byte [] b="This is a string".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);      //申请字节大小为1024的缓冲区
        System.out.println("position : "+byteBuffer.position());
        System.out.println("limit : "+byteBuffer.limit());
        System.out.println("capacity : "+byteBuffer.capacity());

        byteBuffer.put(b);

        byteBuffer.flip();      //切换到读数据的模式，limit就会变为当前字节的长度，position=0

        byte[] result=new byte[5];
        byteBuffer.get(result);
        System.out.println("result : "+new String(result,0,result.length));
        System.out.println("position : "+byteBuffer.position());
        byteBuffer.mark();

        byteBuffer.get(result);
        System.out.println("position : "+byteBuffer.position());

        byteBuffer.reset();
        System.out.println("position : "+byteBuffer.position());

    }

    @Test
    public void test(){
        byte [] b="This is a string".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);      //申请字节大小为1024的缓冲区
        System.out.println("position : "+byteBuffer.position());
        System.out.println("limit : "+byteBuffer.limit());
        System.out.println("capacity : "+byteBuffer.capacity());

        byteBuffer.put(b);

        byteBuffer.flip();      //切换到读数据的模式，limit就会变为当前字节的长度，position=0

        System.out.println("position : "+byteBuffer.position());
        System.out.println("limit : "+byteBuffer.limit());
        System.out.println("capacity : "+byteBuffer.capacity());

        byte[] result=new byte[byteBuffer.limit()];
        byteBuffer.get(result);
        System.out.println("result : "+new String(result,0,result.length));

        byteBuffer.rewind();        //可重复读数据

        byteBuffer.clear();         //清空缓冲区，缓冲区里面的数据依然存在，只是处于“遗弃”状态，position,limit,capacity被重置
    }
}
