package vn.techmaster.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.PostDAO;
import vn.techmaster.demo.dao.PostDAOImpl;
import vn.techmaster.demo.exception.BadRequestException;
import vn.techmaster.demo.exception.ResouceNotFoundException;
import vn.techmaster.demo.model.Post;

import java.util.List;
import java.util.Random;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDAO postDAO;

    @Override
    public List<Post> getAllPost() {
        return postDAO.findAll();
    }

    @Override
    public Post getPostById(Integer id) {
        List<Post> postList = postDAO.findAll();
        return postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResouceNotFoundException("Not found post"));
    }

    @Override
    public Post createPost(Post request) {
        Random rd = new Random();
        Post post = new Post();
        post.setId(rd.nextInt(1000));
        post.setTitle(request.getTitle());
        post.setAuthor(request.getAuthor());

        postDAO.save(post);
        return post;
    }

    @Override
    public Post updatePost(Integer id, Post request) {
        Post post = getPostById(id);
        post.setTitle(request.getTitle());
        post.setAuthor(request.getAuthor());
        return post;
    }

    @Override
    public void deletePost(Integer id) {
        Post post = getPostById(id);
        postDAO.delete(id);
    }

    @Override
    public List<Post> searchPost(String title) {
        if(title.trim().length() == 0) {
            throw new BadRequestException("Title không được để trống");
        }
        return postDAO.findByTitleContainsIgnoreCase(title);
    }
}
