package com.rifu.springbootcrud.dao;

import com.rifu.springbootcrud.entities.Department;
import com.rifu.springbootcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @Author Rifu
 * @Date 2018/9/15  20:28
 */
@Repository
public class EmployeeDao {
    private static Map<Integer,Employee> emps = null;

    @Autowired
    DepartmentDao departmentDao;

    static{
        emps = new HashMap<Integer, Employee>();

        emps.put(2276,new Employee(2276,"Rifu1","rifu1@163.com",1,new Date(),new Department(101,"D-AA")));
        emps.put(2277,new Employee(2277,"Rifu2","rifu2@163.com",1,new Date(),new Department(102,"D-BB")));
        emps.put(2278,new Employee(2278,"Rifu3","rifu3@163.com",1,new Date(),new Department(103,"D-CC")));
        emps.put(2279,new Employee(2279,"Rifu4","rifu4@163.com",1,new Date(),new Department(104,"D-DD")));
        emps.put(2270,new Employee(2270,"Rifu5","rifu5@163.com",1,new Date(),new Department(105,"D-FF")));

    }

    public void saveEmp(Employee emp){
        if(emp.getId() == null){
            emp.setId(emps.size()+2276);
        }
        Department dept = departmentDao.getDept(emp.getDept().getId());
        emp.setDept(dept);
        emps.put(emp.getId(),emp);
    }

    public Collection<Employee> getEmps(){
        return emps.values();
    }

    public Employee getEmp(Integer id){
        return emps.get(id);
    }

    public void deleteEmp(Integer id){
        emps.remove(id);
    }
}
