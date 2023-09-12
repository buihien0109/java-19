package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // tên sản phẩm

    private Integer price; // giá

    private String brand; // thương hiệu

    private LocalDateTime createdAt; // ngày tạo

    private Integer count; // số lượng
}
