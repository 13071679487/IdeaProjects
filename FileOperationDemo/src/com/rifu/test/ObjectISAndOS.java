package com.rifu.test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rifu
 * @create 2018-05-16 14:59
 * <p>
 * 对象流：ObjectInputStream    ObjectOutputStream
 * (重点就是其中的对象序列化，将其转换为二进制流保存在磁盘上)
 * <p>
 * 知识点：
 * 1.需要传输的对象需要实现Serializable接口
 * 2.需要显式的提供一个serialVersionUID(序列化版本号)，如果不指定，则是由系统自动分配，当源码修改时，版本号会发生改变，导致无法读取
 * 3.使用static 或者transient(短暂的)修饰的属性，不可实现序列化
 */
public class ObjectISAndOS {
    @Test
    public void testObjectWriteAndRead() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            File file = new File("object.txt");
            FileOutputStream fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Person rifu = new Person("rifu", 22);
            Person lili = new Person("lili", 22);

            oos.writeObject(rifu);
            oos.writeObject(lili);
            oos.flush();
            List<Person> persons = new ArrayList<>();
            try {
                while (true) {
                    persons.add((Person) ois.readObject());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Person p :
                    persons) {
                System.out.println(" : "+p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
