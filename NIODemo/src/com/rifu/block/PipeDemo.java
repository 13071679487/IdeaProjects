package com.rifu.block;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author Rifu
 * @create 2018-05-18 17:15
 *
 * 管道：(单向的)
 *
 */
public class PipeDemo {

    /**
     * 通过单向管道发送和接收数据
     */
    @Test
    public void test(){
        Pipe.SinkChannel sinkChannel =null;
        Pipe.SourceChannel sourceChannel=null;
        try {
            //1.获取管道
            Pipe pipe = Pipe.open();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //2.获取到写数据的管道
            sinkChannel = pipe.sink();

            //3.写数据
            byteBuffer.put("This send data by pipe".getBytes());
            byteBuffer.flip();
            sinkChannel.write(byteBuffer);

            //4.获取到读数据的管道
            sourceChannel = pipe.source();
            byteBuffer.flip();
            int length = sourceChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array(),0,length));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sourceChannel!=null){
                try {
                    sourceChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sinkChannel!=null){
                try {
                    sinkChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
