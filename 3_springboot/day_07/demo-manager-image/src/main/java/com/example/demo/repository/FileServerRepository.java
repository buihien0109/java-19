package com.example.demo.repository;

import com.example.demo.entity.FileServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileServerRepository extends JpaRepository<FileServer, Integer> {
    List<FileServer> findByUser_IdOrderByCreatedAtDesc(Integer userId);
}