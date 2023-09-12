package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);

    List<Student> findByNameContainingIgnoreCase(String name);
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Student> findByAgeGreaterThan(Integer age);
    Page<Student> findByAgeGreaterThan(Integer age, Pageable pageable);

    // Sắp xếp
    List<Student> findByAgeGreaterThanOrderByAgeAsc(Integer age);

    @Query(nativeQuery = true, value = "select * from student where age > ?1 order by age asc")
    List<Student> findByAgeGreaterThanNQ(Integer age);

    List<Student> findByAgeGreaterThan(Integer age, Sort sort);
    // End sắp xếp

    List<Student> findByAgeBetween(Integer minAge, Integer maxAge);

    Optional<Student> findByEmailAndName(String email, String name);

    boolean existsByEmail(String email);

    long countByName(String name);

    Optional<Student> findFirstByAgeLessThan(Integer age);

    // Cập nhật
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update student set name = ?2 where id = ?1")
    void updateName(Integer id, String name);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from student where email = ?1")
    void deleteEmail(String email);

    // Phân trang
    @Query(
            nativeQuery = true,
            value = "select * from student",
            countQuery = "select count(*) from student"
    )
    Page<Student> getAllStudent(Pageable pageable);
}
