package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    //    1.Tìm kiếm sản phẩm theo tên và sắp xếp theo count giảm dần
    List<Product> findByNameOrderByCountDesc(String name);

    //    2.Tìm kiếm sản phầm có tên chứa ký tự, không phần biệt hoa thường (có phân trang)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

//    3.Sắp xếp các sản phẩm theo giá giảm dần (findAll(Sort sort))

//    4.Sắp xếp các sản phẩm theo tên (A -> Z), có phân trang (findAll(Pageable pageable))

//    5.Sắp xếp sản phẩm theo count, có phân trang (findAll(Pageable pageable))

    //    6.Tìm kiếm sản phẩm theo brand nào đó và sắp xếp theo giá, có phân trang
    Page<Product> findByBrand(String brand, Pageable pageable);

    //    7.Đếm số lượng các sản phẩm theo brand
    long countByBrand(String brand);

//    8.Tính tổng các sản phẩm còn lại trong kho theo brand
//    long countByBrandGreaterThan(String brand, Integer count);
}