package com.rifu.test;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Rifu
 * @create 2018-05-16 13:03
 *
 *
 * 字符流：用于纯文本的处理(速度比字节流快)
 */
public class FileReaderAndWriter {

    @Test
    public void testCopyFile(){
        Instant start = Instant.now();
        String srcPath="out.txt";
        String destPath="out3.txt";
        copyFile(srcPath,destPath);
        Instant end = Instant.now();
        System.out.println("复制文件完成，耗费时间(ms)为 : "+Duration.between(start,end).toMillis());
    }

    public static void copyFile(String srcPath,String destPath){
        FileReader reader=null;
        FileWriter writer=null;
        try {
            File in=new File(srcPath);
            File out=new File(destPath);
            reader=new FileReader(in);
            writer=new FileWriter(out);
            char[] buf=new char[1024];
            int len;
            while((len=reader.read(buf))!=-1){
                writer.write(buf,0,len);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }finally {

            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void testFileReader() {
        FileReader reader = null;
        try {
            File file = new File("out.txt");
            reader = new FileReader(file);
            char[] buf = new char[1024];
            int len;
            while ((len = reader.read(buf)) != -1) {
                System.out.println(new String(buf,0,len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
