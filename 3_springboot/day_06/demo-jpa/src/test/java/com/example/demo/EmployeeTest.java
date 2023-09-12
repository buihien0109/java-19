package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findByDepartmentTest() {
        List<Employee> employeeList = employeeRepository.findByDepartment("A");
        employeeList.forEach(System.out::println);

        List<Employee> employeeList1 = employeeRepository.findByDepartmentJPQL("A");
        employeeList1.forEach(System.out::println);

        List<Employee> employeeList2 = employeeRepository.findByDepartmentNQ("A");
        employeeList2.forEach(System.out::println);
    }
}
