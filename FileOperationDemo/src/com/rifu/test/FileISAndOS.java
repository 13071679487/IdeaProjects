package com.rifu.test;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Rifu
 * @create 2018-05-16 12:06
 * <p>
 *     抽象基类         InputStream     OutputStream        Reader      Writer
 * 字节流 \---                                               字符流\------
 * 节点流(直接作用于文件的)：FileInputStream  FileOutputStream FileReader FileWriter
 * 其余都是处理流：
 */
public class FileISAndOS {

    @Test
    public void testCopyFile() {
        String srcPath = "out.txt";
        String destPath = "out2.txt";
        copyFile(srcPath, destPath);
    }

    /**
     * @param srcPath  源文件路径
     * @param destPath 目标文件路径
     */
    public static void copyFile(String srcPath, String destPath) {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            File in = new File(srcPath);
            File out = new File(destPath);
            fis = new FileInputStream(in);
            fos = new FileOutputStream(out);

            byte[] buf = new byte[1024];
            int len;
            Instant start = Instant.now();
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            Instant end = Instant.now();
            System.out.println("耗费时间为 : " + Duration.between(start, end).toMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    @Test
    public void testFileOutputStream() {
        File file = new File("out.txt");        //输出的物理文件可以不存在，会自动创建，若存在，则覆盖
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] content = new String("This is a test String ,it will be transform into a byte array").getBytes();
            fos.write(content);
        } catch (Exception e) {

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void testFileInputStream() {
        FileInputStream is = null;
        try {
            File file = new File("test.txt");
            is = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int length;
            while ((length = is.read(buf)) != -1) {
                System.out.print(new String(buf, 0, length));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
