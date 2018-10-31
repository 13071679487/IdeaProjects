package com.rifu.springbootcrud.dao;

import com.rifu.springbootcrud.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Rifu
 * @Date 2018/9/15  20:24
 */
@Repository
public class DepartmentDao {
    private static Map<Integer ,Department> depts = null;

    static {
        depts= new HashMap<Integer, Department>();

        depts.put(101,new Department(101,"D-AA"));
        depts.put(102,new Department(102,"D-BB"));
        depts.put(103,new Department(103,"D-CC"));
        depts.put(104,new Department(104,"D-DD"));
        depts.put(105,new Department(105,"D-EE"));
    }

    public Collection<Department> getDepts(){
        return depts.values();
    }

    public Department getDept(Integer id){
        return depts.get(id);
    }



}
