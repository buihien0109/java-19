package vn.techmaster.demo.service;

import vn.techmaster.demo.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPost();

    Post getPostById(Integer id);

    Post createPost(Post request);

    Post updatePost(Integer id, Post request);

    void deletePost(Integer id);

    List<Post> searchPost(String title);
}
