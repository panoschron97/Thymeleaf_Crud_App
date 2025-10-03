package com.thymeleaf.crud.service;

import com.thymeleaf.crud.entity.Employee;

import java.util.List;

public interface Service {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
