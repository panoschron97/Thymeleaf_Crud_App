package com.thymeleaf.crud.controller;

import com.thymeleaf.crud.entity.Employee;
import com.thymeleaf.crud.service.Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController
{

private Service employeeservice;

@Autowired
public EmployeeController(Service employeeservice)
{
this.employeeservice = employeeservice;
}

@GetMapping("/list")
    public String listemployees(Model model)
{
    List<Employee> employees = employeeservice.findAll();
    model.addAttribute("employees", employees);
    return "employees/list-employees";
}

@GetMapping("/showformforadd")
    public String showformforadd(Model model)
{
    Employee employee = new Employee();
    model.addAttribute("employees", employee);
    return "employees/employee-form";
}

@PostMapping("/save")
    public String saveemployee(@Valid @ModelAttribute("employees") Employee employee, BindingResult bindingresult)
{
    if (bindingresult.hasErrors())
    {
        return "employees/employee-form";
    }

    employeeservice.save(employee);
    return "redirect:/employees/list";
}
@GetMapping("/showformforupdate")
    public String showformforupdate(@RequestParam("employeeId") int id, Model model)
{
    Employee employee = employeeservice.findById(id);
    model.addAttribute("employees", employee);
    return "employees/employee-form";
}

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id)
    {
        employeeservice.deleteById(id);
        return "redirect:/employees/list";
    }

}
