package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void updateNameTest() {
        studentRepository.updateName(1, "Bùi Hiên");

        Student std = studentRepository.findById(1).orElse(null);
        System.out.println(std);

        Assertions.assertNotNull(std);
        Assertions.assertEquals(std.getName(), "Bùi Hiên");
    }

    @Test
    void deleteEmailTest() {
        studentRepository.deleteEmail("an@gmail.com");
    }

    @Test
    void pagingTest() {
        Pageable pageable = PageRequest.of(1, 5);
        Page<Student> page = studentRepository.findAll(pageable);
        page.getContent().forEach(System.out::println);

        System.out.println();
        Page<Student> page1 = studentRepository.getAllStudent(pageable);
        page1.getContent().forEach(System.out::println);
    }

    @Test
    void sortingTest() {
        List<Student> studentList = studentRepository.findByAgeGreaterThanOrderByAgeAsc(44);
        studentList.forEach(System.out::println);

        System.out.println();
        List<Student> studentList1 = studentRepository.findByAgeGreaterThanNQ(44);
        studentList1.forEach(System.out::println);

        System.out.println();
        List<Student> studentList2 = studentRepository.findByAgeGreaterThan(44, Sort.by("age").ascending());
        studentList2.forEach(System.out::println);
    }
}
