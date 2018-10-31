package com.rifu.map;

import java.util.*;

/**
 * @author Rifu
 * @create 2018-06-10 19:55
 *
 * 对hashmap进行排序
 */
public class HashMapDemo {

    public static void main(String [] args){
        Map<Integer ,User> map=new HashMap<>();
        map.put(1, new User(25, "张三"));
        map.put(3, new User(22, "李四"));
        map.put(2, new User(28, "王五"));
        LinkedHashMap<Integer, User> resultMap = sortHashMap(map);

        Iterator<Integer> iterator = resultMap.keySet().iterator();
        while (iterator.hasNext()){
            Integer key = iterator.next();
            System.out.println(resultMap.get(key));
        }
    }

    public static LinkedHashMap<Integer,User> sortHashMap(Map<Integer ,User> map){
        Set<Map.Entry<Integer, User>> entries = map.entrySet();
        ArrayList<Map.Entry<Integer, User>> entryArrayList = new ArrayList<>(entries);
        Collections.sort(entryArrayList, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o1.getValue().getAge()-o2.getValue().getAge();
            }
        });

        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<>();
        for(Map.Entry<Integer, User> entry:entryArrayList){
            linkedHashMap.put(entry.getKey(),entry.getValue());
        }
        return linkedHashMap;
    }

}

class User{
    private int age;
    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
