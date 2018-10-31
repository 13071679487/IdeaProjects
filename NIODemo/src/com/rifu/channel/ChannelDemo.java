package com.rifu.channel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

/**
 * @author Rifu
 * @create 2018-05-17 20:12
 * <p>
 * 1.   通道(Channel):用户源节点和目标节点的链接，在NIO中负责缓冲区数据的传输。它本身不存储数据，配合缓冲区进行传输
 * <p>
 * 2.   主要实现类：
 * java.nio.channels.Channel    (Interface)
 * \---FileChannel
 * \---SocketChannel
 * \---ServerSocketChannel
 * \---DatagramChannel
 * 3.   获取通道
 * JDK1.4        getChannel()
 * JDK1.7    在NIO2中  open()
 * JDK1.7    Files工具类中newByteChannel()
 * 4.   通道之间的数据传输
 * transferFrom()
 * transferTo()
 * <p>
 * 5.   分散(Scatter)与聚集（Gather）
 * 分散：是指从Channel中读取的数据“分散”到多个Buffer中（按照缓冲区的顺序，依次填满）
 * <p>
 * 6.   字符集：charset
 * encode:charArray-->byte array
 * decode:byte array-->charArray
 */
public class ChannelDemo {

    /**
     * 字符集：charset
     */
    @Test
    public void testCharset()  throws Exception{
        Charset cs = Charset.forName("GBK");
        CharsetEncoder encoder = cs.newEncoder();   //获取到编码器
        CharsetDecoder decoder = cs.newDecoder();   //获取到解码器

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        String str="是真的牛逼";
        charBuffer.put(str);
        charBuffer.flip();
        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        CharBuffer charBuffer1 = decoder.decode(byteBuffer);
        System.out.println(charBuffer1.toString());

    }

    /**
     * 获取本机支持的字符集
     */
    @Test
    public void testGetAllCharset() {
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey()+" : " +entry.getValue());
        }
    }

    /**
     * 分散读取和聚集写入
     */
    @Test
    public void testScatterAndGather() {
        RandomAccessFile raf = null;
        FileChannel channel = null;
        RandomAccessFile raf2 = null;
        FileChannel channel2 = null;
        try {
            raf = new RandomAccessFile("test.txt", "rw");
            channel = raf.getChannel();
            raf2 = new RandomAccessFile("test2.txt", "rw");
            channel2 = raf2.getChannel();
            ByteBuffer buf1 = ByteBuffer.allocate(128);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);
            ByteBuffer[] bufs = {buf1, buf2};
            channel.read(bufs);
            for (ByteBuffer bb :
                    bufs) {
                bb.flip();
            }
            channel2.write(bufs);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel2 != null) {
                try {
                    channel2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 测试通道之间的数据传输
     */
    @Test
    public void testTransfer() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("test.jpg"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("test3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
            Instant start = Instant.now();

            inChannel.transferTo(0, inChannel.size(), outChannel);    //使用的是直接缓冲区
            //outChannel.transferFrom(inChannel,0,inChannel.size());            //效果同上

            Instant end = Instant.now();
            System.out.println("完成文件复制，耗费时间(ms)为 : " + Duration.between(start, end).toMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用直接缓冲区进行文件的复制
     */
    @Test
    public void testGetChannelWithAllocateDirect() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("test.jpg"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("test2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            byte[] buffer = new byte[inMappedBuffer.limit()];
            inMappedBuffer.get(buffer);
            outMappedBuffer.put(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 利用通道完成文件的复制（非直接缓冲区）
     */
    @Test
    public void testGetChannel() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fisChannel = null;
        FileChannel fosChannel = null;
        try {
            fis = new FileInputStream("test.jpg");
            fos = new FileOutputStream("test1.jpg");
            //1.获取通道
            fisChannel = fis.getChannel();
            fosChannel = fos.getChannel();
            //2.分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //3.将通道的数据存入到缓冲区
            while (fisChannel.read(buffer) != -1) {
                buffer.flip();      //切换为读的模式，并将读到的数据写出去
                fosChannel.write(buffer);
                buffer.clear();     //清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fosChannel != null) {
                try {
                    fosChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fisChannel != null) {
                try {
                    fisChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
