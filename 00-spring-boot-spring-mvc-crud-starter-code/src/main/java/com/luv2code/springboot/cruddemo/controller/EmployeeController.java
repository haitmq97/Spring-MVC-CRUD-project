package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/list")
    public String list(Model theModel){
        List<Employee> employeeList = employeeService.findAll();

        theModel.addAttribute("employees", employeeList);

        return  "employees/employee-list";
    }

    @GetMapping("/addingform")
    public String form(Model theModel){
        theModel.addAttribute("employee", new Employee());
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee theEmployee, Model theModel) {
        // if employee is existing then throw message
        if(employeeService.isEmployeeExist(theEmployee)){
            theModel.addAttribute("existingMessage", "Employee already exists!");
            return "employees/employee-form";
        }

        employeeService.save(theEmployee);

        return "redirect:/employees/list";
    }

    // add mapping for update an employee
    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int theId,Model theModel) {

        // get the employee by id
        Employee theEmployee = employeeService.findById(theId);

        // set Employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId) {

        // delete employee by id
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }

}
