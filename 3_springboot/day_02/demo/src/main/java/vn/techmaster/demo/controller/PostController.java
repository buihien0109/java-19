package vn.techmaster.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.model.Post;
import vn.techmaster.demo.response.ErrorResponse;
import vn.techmaster.demo.service.PostService;

// status
// message
// time
// path
// ...

//@RestController = @Controller + @ResponseBody
//@Controller
@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    // 1. Lấy ds tất cả post
//    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    @GetMapping("")
    public ResponseEntity<?> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost()); // status = 200, body = List<Post>
    }

    // 2. Lấy post theo id
    // /posts/1 , /posts/2
//    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    @GetMapping("{id}")
    public ResponseEntity<?> getPostById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(postService.getPostById(id));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // 3. Tạo mới
//    @RequestMapping(method = RequestMethod.POST, value = "/posts")
    @PostMapping("")
    public ResponseEntity<?> createPost(@Valid @RequestBody Post request) {
        return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED); // status = 201
    }

    // 4. Cập nhật
//    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}")
    @PutMapping("{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @Valid @RequestBody Post request) {
        return ResponseEntity.ok().body(postService.updatePost(id, request));
    }

    // 5. Xóa
//    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build(); // status = 204
    }

    // 6. Tìm kiếm
    // /posts/search?title=bac
    @GetMapping("search")
    public ResponseEntity<?> searchPost(@RequestParam String title) {
        return ResponseEntity.ok(postService.searchPost(title));
    }
}
