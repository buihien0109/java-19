package vn.techmaster.demo.controller;

import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@RestController = @Controller + @ResponseBody
//@Controller
@RestController
public class WebController {
    private List<Post> postList;

    public WebController() {
        System.out.println("WebController Created");

        this.postList = new ArrayList<>();
        this.postList.add(new Post(1, "Post title 1", "Author 1"));
        this.postList.add(new Post(2, "Post title 2", "Author 2"));
        this.postList.add(new Post(3, "Post title 3", "Author 3"));
    }

    // 1. Lấy ds tất cả post
    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    public List<Post> getAllPost() {
        return this.postList;
    }

    // 2. Lấy post theo id
    // /posts/1 , /posts/2
    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return this.postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);
    }

    // 3. Tạo mới
    @RequestMapping(method = RequestMethod.POST, value = "/posts")
    public Post createPost(@RequestBody Post request) {
        System.out.println(request);

        Random rd = new Random();
        Post post = new Post();
        post.setId(rd.nextInt(1000));
        post.setTitle(request.getTitle());
        post.setAuthor(request.getAuthor());

        this.postList.add(post);
        return post;
    }

    // 4. Cập nhật
    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post request) {
        Post post = this.postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);

        if(post != null) {
            post.setTitle(request.getTitle());
            post.setAuthor(request.getAuthor());
            return post;
        }
        return null;
    }

    // 5. Xóa
    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    public void deletePost(@PathVariable Integer id) {
        this.postList.removeIf(post -> post.getId().equals(id));
    }
}
