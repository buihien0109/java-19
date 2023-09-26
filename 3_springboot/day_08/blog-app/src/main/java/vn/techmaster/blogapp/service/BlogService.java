package vn.techmaster.blogapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.techmaster.blogapp.entity.Blog;
import vn.techmaster.blogapp.exception.NotFoundException;
import vn.techmaster.blogapp.model.projection.BlogPublic;
import vn.techmaster.blogapp.model.request.UpsertBlogRequest;
import vn.techmaster.blogapp.repository.BlogRepository;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Page<Blog> findAll(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("publishedAt").descending());
        return blogRepository.findByStatus(true, pageable);
    }

    public List<Blog> searchByTitle(String title) {
        return blogRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Blog> findByCategoryName(String name) {
        return blogRepository.findByCategoryName(name);
    }

    public Blog findByIdAndSlug(Integer id, String slug) {
        return blogRepository.findByIdAndSlugAndStatusTrue(id, slug)
                .orElseThrow(() -> new NotFoundException("Cannot find blog"));
    }

    public Page<Blog> getAllBlogs(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());
        return blogRepository.findAll(pageable);
    }

    public Page<Blog> getAllBlogsOfCurrentUser(Integer page, Integer limit) {
        // TODO : Giả định userId = 1, sau này userId chính là user đang login
        Integer userId = 1;
        return null;
    }

    public Blog getBlogById(Integer id) {
        return null;
    }

    public Blog createBlog(UpsertBlogRequest request) {
        // TODO : Giả định userId = 1, sau này userId chính là user đang login
        Integer userId = 1;
        return null;
    }

    public Blog updateBlog(Integer id, UpsertBlogRequest request) {
        return null;
    }

    public void deleteBlog(Integer id) {
    }
}
