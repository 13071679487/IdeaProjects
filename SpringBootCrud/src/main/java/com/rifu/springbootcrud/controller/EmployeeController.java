package com.rifu.springbootcrud.controller;

import com.rifu.springbootcrud.dao.DepartmentDao;
import com.rifu.springbootcrud.dao.EmployeeDao;
import com.rifu.springbootcrud.entities.Department;
import com.rifu.springbootcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Author Rifu
 * @Date 2018/9/16  11:54
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String emps(Model model){
        Collection<Employee> emps = employeeDao.getEmps();
        model.addAttribute("emps",emps);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String add(Model model){
        Collection<Department> depts = departmentDao.getDepts();
        model.addAttribute("depts",depts);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee emp){
        System.out.println(emp);
        employeeDao.saveEmp(emp);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String update(@PathVariable("id")Integer id,Model model){
        Employee emp = employeeDao.getEmp(id);
        model.addAttribute("emp",emp);
        Collection<Department> depts = departmentDao.getDepts();
        model.addAttribute("depts",depts);
        return "emp/add";
    }

    @PutMapping("/emp")
    public String updateEmp(Employee emp){
        System.out.println(emp);
        employeeDao.saveEmp(emp);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        employeeDao.deleteEmp(id);
        return "redirect:/emps";
    }
}
