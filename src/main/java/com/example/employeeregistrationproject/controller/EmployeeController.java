package com.example.employeeregistrationproject.controller;


import com.example.employeeregistrationproject.bootstrap.DataGenerator;
import com.example.employeeregistrationproject.model.Employee;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/register")
    public String createEmployee(Model model){

        model.addAttribute("employee", new Employee());
        model.addAttribute("states", DataGenerator.getAllStates);

        System.out.println();

        return "employee/employee-create";
    }

    @PostMapping("/list")
    public String employeeList(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            // Because employee is broken,
            // We need to get states in the dropdown
            model.addAttribute("states", DataGenerator.getAllStates);
            return "employee/employee-create"; // Stay in the same page
        }

        DataGenerator.saveEmployee(employee);

        model.addAttribute("employeeList",DataGenerator.readAllEmployees());

        return "employee/employee-list";
    }
}
