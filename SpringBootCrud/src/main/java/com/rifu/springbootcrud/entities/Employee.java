package com.rifu.springbootcrud.entities;

import com.rifu.springbootcrud.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author Rifu
 * @Date 2018/9/15  20:20
 */
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private Department dept;

    public Employee() {
    }

//    public Employee(Integer id, String lastName, String email, Integer gender, Date birth){
//        this.id = id;
//        this.lastName = lastName;
//        this.email = email;
//        this.gender = gender;
//        this.birth = birth;
//    }

    public Employee(Integer id, String lastName, String email, Integer gender, Date birth, Department dept) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
        this.dept = dept;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                ", dept=" + dept +
                '}';
    }
}
