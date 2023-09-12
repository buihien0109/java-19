package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//    1.Tìm kiếm sản phẩm theo tên và sắp xếp theo tên giảm dần
//    2.Tìm kiếm sản phầm có tên chưa ký tự, không phần biệt hoa thường (có phân trang)
//    3.Sắp xếp các sản phẩm theo giá giảm dần
//    4.Sắp xếp các sản phẩm theo tên (A -> Z), có phân trang
//    5.Sắp xếp sản phẩm theo count, có phân trang
//    6.Tìm kiếm sản phẩm theo brand nào đó và sắp xếp theo giá, có phân trang
//    7.Đếm số lượng các sản phẩm theo brand
//    8.Tính tổng các sản phẩm còn lại trong kho theo brand
}