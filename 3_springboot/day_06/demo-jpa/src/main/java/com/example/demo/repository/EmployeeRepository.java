package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//    1. Tìm kiếm tất cả các employee thuộc 1 phòng ban được chỉ định
    List<Employee> findByDepartment(String departmentName);

    @Query("select e from Employee e where e.department = ?1")
    List<Employee> findByDepartmentJPQL(String departmentName);

    @Query("select e from Employee e where e.department = :departmentName")
    List<Employee> findByDepartmentJPQL1(@Param("departmentName") String departmentName);

    @Query(value = "select * from employee e where e.department = ?1", nativeQuery = true)
    List<Employee> findByDepartmentNQ(String departmentName);
//    2. Tìm kiếm tất cả các employee có salary lớn hơn 1 giá trị được chỉ định
//    3. Tìm kiếm tất cả các employee có tên được chỉ định
//    4. Tìm kiếm tất cả các employee trong tên có chứa 1 keyword được chỉ định (không phân biệt hoa thường)
//    5. Tìm kiếm tất cả các employee có tên được bắt đầu với 1 chuỗi (prefix) được chỉ định
//    6. Tìm kiếm tất cả các employee có salary nằm trong khoảng chỉ định
//    7. Đếm số lượng employee thuộc 1 phòng ban được chỉ định
//    8. Tìm kiếm tất cả các employee trong tên có chứa 1 keyword được chỉ định và thuộc 1 phòng ban được chỉ định
//    9. Tìm kiếm tất cả các employee có tên được chỉ định hoặc thuộc 1 phòng ban được chỉ định
//    10. Tìm kiếm tất cả các employee tham gia công ty sau 1 ngày được chỉ định
}