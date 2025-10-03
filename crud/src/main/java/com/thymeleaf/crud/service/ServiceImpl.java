package com.thymeleaf.crud.service;

import com.thymeleaf.crud.dao.Repository;
import com.thymeleaf.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service
{

    private Repository repository;

    @Autowired
    public ServiceImpl(Repository theRepository) {
        repository = theRepository;
    }

    @Override
    public List<Employee> findAll()
    {

        return repository.findAllByOrderBySalaryDesc();
    }

    @Override
    public Employee findById(int theId)
    {

        Optional<Employee> result = repository.findById(theId);
        Employee theEmployee = null;

        if (result.isPresent())
        {
            theEmployee = result.get();
        }
        else
        {

            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return repository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        repository.deleteById(theId);
    }
}






