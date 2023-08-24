package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private final List<User> userList;

    public WebController() {
        userList = new ArrayList<>();
        userList.add(new User(1, "Nguyễn Văn A", "a@gmail.com"));
        userList.add(new User(2, "Trần Văn B", "b@gmail.com"));
        userList.add(new User(3, "Ngô Thị C", "c@gmail.com"));
    }

    @GetMapping("/")
    public String getHome(Model model) {
        String name = "Bùi Hiên";

        model.addAttribute("name", name);
        model.addAttribute("user", userList.get(0));
        model.addAttribute("userList", userList);
        return "index";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin/admin";
    }
}
