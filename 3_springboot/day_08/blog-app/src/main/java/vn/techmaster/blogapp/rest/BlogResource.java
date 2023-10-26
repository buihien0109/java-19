package vn.techmaster.blogapp.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blogapp.model.request.UpsertBlogRequest;
import vn.techmaster.blogapp.service.BlogService;

@RestController
@RequestMapping("/api/v1/admin/blogs")
public class BlogResource {
    private final BlogService blogService;

    public BlogResource(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<?> createBlog(@Valid @RequestBody UpsertBlogRequest request) {
        return new ResponseEntity<>(blogService.createBlog(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@Valid @RequestBody UpsertBlogRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(blogService.updateBlog(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
