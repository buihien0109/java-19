package com.example.demo.controller;

import com.example.demo.entity.FileServer;
import com.example.demo.entity.User;
import com.example.demo.repository.FileServerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private final FileService fileService;
    private final UserRepository userRepository;
    private final FileServerRepository fileServerRepository;

    public UserController(FileService fileService,
                          UserRepository userRepository,
                          FileServerRepository fileServerRepository) {
        this.fileService = fileService;
        this.userRepository = userRepository;
        this.fileServerRepository = fileServerRepository;
    }

    // == API tra về HTML ==
    @GetMapping("/")
    public String getUserPage(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);
        // Code logic
        return "index";
    }

    @GetMapping("/users/{id}/files")
    public String getFilesPage(Model model, @PathVariable Integer id) {
        List<FileServer> fileServerList = fileServerRepository.findByUser_IdOrderByCreatedAtDesc(id);
        model.addAttribute("files", fileServerList);
        // Code logic
        return "file";
    }

    // == API tra về JSON ==
    @PostMapping("/api/users/{id}/files")
    public ResponseEntity<?> uploadFile(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileService.uploadFile(id, file));
    }
}
