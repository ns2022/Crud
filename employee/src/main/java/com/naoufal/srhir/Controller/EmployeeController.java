package com.naoufal.srhir.Controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naoufal.srhir.Entity.Employee;
import com.naoufal.srhir.Repository.EmployeeRepository;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);}
   
        @PutMapping("/{id}")
        public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
            return employeeRepository.findById(id)
                    .map(existingEmployee -> {
                        existingEmployee.setFirstName(employee.getFirstName());
                        existingEmployee.setLastName(employee.getLastName());
                        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
                        existingEmployee.setAddress(employee.getAddress());
                        existingEmployee.setEmail(employee.getEmail());
                        existingEmployee.setSalary(employee.getSalary());
                        existingEmployee.setPosition(employee.getPosition());
                        return employeeRepository.save(existingEmployee);
                    })
                    .orElseGet(() -> {
                        employee.setId(id);
                        return employeeRepository.save(employee);
                    });
        }

        @DeleteMapping("/{id}")
        public void deleteEmployee(@PathVariable Long id) {
            employeeRepository.deleteById(id);
        }
    }
