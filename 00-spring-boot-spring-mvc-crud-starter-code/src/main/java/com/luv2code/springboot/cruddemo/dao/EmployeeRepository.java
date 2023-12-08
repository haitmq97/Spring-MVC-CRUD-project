package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    //check a Employee is exist!!!
    List<Employee> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);



}
