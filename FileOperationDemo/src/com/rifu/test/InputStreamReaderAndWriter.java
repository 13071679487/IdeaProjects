package com.rifu.test;

import org.junit.Test;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Rifu
 * @create 2018-05-16 13:50
 *
 * 转换流：InputStreamReader        InputStreamWriter
 * 编码：  字符串--》字节流
 * 解码：字节流--》字符流
 */
public class InputStreamReaderAndWriter {

    @Test
    public void testCopyFile(){
        Instant start = Instant.now();
        String srcPath="in.txt",destPath="out5.txt";
        copyFile(srcPath,destPath);
        Instant end = Instant.now();
        System.out.println("复制文件完成，耗费时间(ms)为 : "+Duration.between(start,end).toMillis());
    }

    /**
     * 使用转换流进行文件的复制
     * @param srcPath
     * @param destPath
     */
    public static void copyFile(String srcPath,String destPath){
        BufferedReader br=null;
        BufferedWriter bw=null;
        try{
            File in = new File(srcPath);
            File out = new File(destPath);
            FileInputStream fis = new FileInputStream(in);
            InputStreamReader isr = new InputStreamReader(fis,"GBK");
            br = new BufferedReader(isr);

            FileOutputStream fos = new FileOutputStream(out);
            OutputStreamWriter osr = new OutputStreamWriter(fos,"GBK");
            bw = new BufferedWriter(osr);
            String content;
            while ((content=br.readLine())!=null){
                bw.write(content,0,content.length());
                bw.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
