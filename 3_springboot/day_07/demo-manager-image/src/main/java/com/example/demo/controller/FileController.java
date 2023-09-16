package com.example.demo.controller;

import com.example.demo.entity.FileServer;
import com.example.demo.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // 2. Xem file
    @GetMapping("/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        FileServer fileServer = fileService.getFileById(id);
        // Code logic
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileServer.getType()))
                .body(fileServer.getData()); // 200
    }

    // 3. Xoa file
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        fileService.deleteFile(id);
        // Code logic
        return ResponseEntity.noContent().build(); // 204
    }
}
