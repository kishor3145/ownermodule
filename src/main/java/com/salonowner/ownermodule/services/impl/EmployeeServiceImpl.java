package com.salonowner.ownermodule.services.impl;

import com.salonowner.ownermodule.Entity.Employee;
import com.salonowner.ownermodule.repository.EmployeeRepository;
import com.salonowner.ownermodule.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> emp = repo.findById(id);
        return emp.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);
        existing.setEmployeeName(employee.getEmployeeName());
        existing.setContactNumber(employee.getContactNumber());
        existing.setCustomerRating(employee.getCustomerRating());
        existing.setEmploymentStatus(employee.getEmploymentStatus());
        existing.setExperienceYears(employee.getExperienceYears());
        existing.setJoiningDate(employee.getJoiningDate());
        existing.setSalary(employee.getSalary());
        existing.setSalonId(employee.getSalonId());
        return repo.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        repo.deleteById(id);
    }
}
