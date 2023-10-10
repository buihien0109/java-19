package vn.techmaster.blogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping("/admin/categories")
    public String getAllCategories() {
        return "admin/category/index";
    }
}
