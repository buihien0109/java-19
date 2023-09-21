package vn.techmaster.blogapp.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {
    @GetMapping("/")
    public String getAllBlo() {
        return null;
    }

    @GetMapping("search")
    public String searchBlog() {
        return null;
    }

    @GetMapping("categories")
    public String getAllCategory() {
        return null;
    }

    @GetMapping("categories/{categoryName}")
    public String getBlogsOfCategory() {
        return null;
    }

    @GetMapping("blogs/{blogId}/{blogSlug}")
    public String getBlogDetail() {
        return null;
    }
}
