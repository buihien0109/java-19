package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.model.Post;
import vn.techmaster.demo.service.PostService;

import java.util.List;

//@RestController = @Controller + @ResponseBody
//@Controller
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    // 1. Lấy ds tất cả post
//    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    @GetMapping("")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }

    // 2. Lấy post theo id
    // /posts/1 , /posts/2
//    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    @GetMapping("{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    // 3. Tạo mới
//    @RequestMapping(method = RequestMethod.POST, value = "/posts")
    @PostMapping("")
    public Post createPost(@RequestBody Post request) {
        return postService.createPost(request);
    }

    // 4. Cập nhật
//    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}")
    @PutMapping("{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post request) {
        return postService.updatePost(id, request);
    }

    // 5. Xóa
//    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }

    // 6. Tìm kiếm
    // /posts/search?title=bac
    @GetMapping("search")
    public List<Post> searchPost(@RequestParam String title) {
        return postService.searchPost(title);
    }
}
