package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByAgeGreaterThan(Integer age);

    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    Optional<Student> findByEmailAndName(String email, String name);

    boolean existsByEmail(String email);

    long countByName(String name);

    Optional<Student> findFirstByAgeLessThan(Integer age);
}
