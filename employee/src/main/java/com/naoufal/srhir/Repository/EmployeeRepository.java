package com.naoufal.srhir.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.naoufal.srhir.Entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}