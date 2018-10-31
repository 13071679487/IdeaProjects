package com.rifu.test;

import org.junit.Test;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Rifu
 * @create 2018-05-16 13:16
 *
 * 缓冲流：
 * BufferedInputStream  ---     BufferedOutputStream
 * BufferedReader       ---     BufferedWriter
 *
 *
 * 以下是带缓冲的字节流
 */
public class BufferedISAndOS {

    @Test
    public void testCopyFileWithBuffered(){
        Instant start = Instant.now();
        String srcPath="in.txt",destPath="out.txt";
        copyFileWithBuffered(srcPath,destPath);
        Instant end = Instant.now();
        System.out.println("复制文件完成，耗费时间(ms)为 : "+Duration.between(start,end).toMillis());
    }

    /**
     * 使用缓冲流复制文件
     * @param srcPath
     * @param destPath
     */
    public static  void copyFileWithBuffered(String srcPath,String destPath){
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;

        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            File in = new File(srcPath);
            File out = new File(destPath);
            fis=new FileInputStream(in);
            fos=new FileOutputStream(out);
            bis=new BufferedInputStream(fis);
            bos=new BufferedOutputStream(fos);

            byte [] buf=new byte[1024];
            int len;
            while((len=bis.read(buf))!=-1){
                bos.write(buf,0,len);
                bos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
