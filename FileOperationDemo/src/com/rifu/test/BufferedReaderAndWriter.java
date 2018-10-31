package com.rifu.test;

import org.junit.Test;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Rifu
 * @create 2018-05-16 13:32
 *
 * 带缓冲的字符流
 */
public class BufferedReaderAndWriter {

    @Test
    public void testCopyFile(){
        Instant start = Instant.now();
        String srcPath="in.txt",destPath="out4.txt";
        copyFile(srcPath,destPath);
        Instant end = Instant.now();
        System.out.println("复制文件完成，耗费时间(ms)为 : "+Duration.between(start,end).toMillis());
    }

    public static void copyFile(String srcPath,String destPath){
        BufferedReader bufferedReader = null;
        BufferedWriter  bufferedWriter=null;
        try {
            File in = new File(srcPath);
            File out = new File(destPath);
            FileReader reader = new FileReader(in);
            FileWriter writer = new FileWriter(out);

            bufferedReader = new BufferedReader(reader);
            bufferedWriter = new BufferedWriter(writer);
            String content;
            while ((content=bufferedReader.readLine())!=null){
                bufferedWriter.write(content,0,content.length());
                bufferedWriter.newLine();       //读取一行后，要进行换行，因为它不懂得换行
                bufferedWriter.flush();             //刷新缓冲区
            }
        }catch (Exception e){

        }finally {
            if(bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void testBufferedReader(){
        BufferedReader bufferedReader = null;
        try {
            File file = new File("in.txt");
            FileReader reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);

            String content;
            while ((content=bufferedReader.readLine())!=null){
                System.out.print(content);
            }

        }catch (Exception e){

        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
