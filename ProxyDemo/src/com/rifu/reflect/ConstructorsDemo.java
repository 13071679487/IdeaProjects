package com.rifu.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Rifu
 * @create 2018-05-17 11:31
 * <p>
 * 1.三种方法创建Class实例
 * <p>
 * 2.调用构造方法
 */
public class ConstructorsDemo {

    @Test
    public void testMethod() throws Exception {
        Class animalClass = Animal.class;
        Object animal = animalClass.newInstance();//默认调用的是无参的构造方法
        Animal a = (Animal) animal;

        //调用非public的方法
        Method privateShow = animalClass.getDeclaredMethod("privateShow");
        privateShow.setAccessible(true);
        Object returnVal = privateShow.invoke(a);
        System.out.println("returnVal : " + returnVal);
        //调用public的方法
        Method show = animalClass.getMethod("show");
        Object returnVal1 = show.invoke(a);
        System.out.println("returnVal1 : " + returnVal1);
        //调用static的方法
        Method staticShow = animalClass.getDeclaredMethod("staticShow");
        staticShow.setAccessible(true);
        Object returnVal2 = staticShow.invoke(a);
        System.out.println("returnVal2 : " + returnVal2);
    }

    @Test
    public void testField() throws Exception {
        Class animalClass = Animal.class;
        Object animal = animalClass.newInstance();//默认调用的是无参的构造方法
        Animal a = (Animal) animal;

        Field name = animalClass.getDeclaredField("name");
        name.setAccessible(true);       //当调用的属性为private修饰时
        name.set(a, "dog");

        Field age = animalClass.getDeclaredField("age");
        age.setAccessible(true);
        age.set(a, 15);
        //调用static修饰的属性
        Field desc = animalClass.getDeclaredField("desc");
        System.out.println(desc.get(null));

        System.out.println(a);
    }

    @Test
    public void test() throws Exception {
        Class animalClass = Animal.class;
        Constructor constructor = animalClass.getConstructor(String.class, int.class);
        constructor.setAccessible(true);            //当权限不够时，设置可访问
        Object cat = constructor.newInstance("cat", 2);
        Animal animal = (Animal) cat;
        System.out.println(animal);
    }
}

class Animal {
    private String name;
    private int age;
    static String desc = "I am a animal";

    public Animal() {
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void show() {
        System.out.println("I am a the show");
    }

    private void privateShow() {
        System.out.println("I am a the privateShow");
    }

    public static void staticShow() {
        System.out.println("I am a the staticShow");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
