package com.thymeleaf.crud.dao;

import com.thymeleaf.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repository extends JpaRepository<Employee, Integer>
{
    public List<Employee> findAllByOrderBySalaryDesc();

}
