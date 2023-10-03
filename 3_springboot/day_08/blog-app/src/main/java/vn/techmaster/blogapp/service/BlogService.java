package vn.techmaster.blogapp.service;

import com.github.slugify.Slugify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.techmaster.blogapp.entity.Blog;
import vn.techmaster.blogapp.entity.Category;
import vn.techmaster.blogapp.entity.User;
import vn.techmaster.blogapp.exception.NotFoundException;
import vn.techmaster.blogapp.model.request.UpsertBlogRequest;
import vn.techmaster.blogapp.repository.BlogRepository;
import vn.techmaster.blogapp.repository.CategoryRepository;
import vn.techmaster.blogapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public BlogService(BlogRepository blogRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
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

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());
        return blogRepository.findByUser_IdOrderByCreatedAtDesc(
                userId,
                pageable
        );
    }

    public Blog createBlog(UpsertBlogRequest request) {
        // TODO: Validate thông tin (nếu cần thiết) - validation

        // Tìm kiếm category
        List<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());

        // TODO : Hiện tại fix userId. Sau này userId chính là user đang login
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Not found user with id = " + userId));

        // Tao blog
        Slugify slugify = Slugify.builder().build();
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .content(request.getContent())
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .status(request.getStatus())
                .categories(categories)
                .comments(new ArrayList<>())
                .user(user)
                .build();

        return blogRepository.save(blog);
    }

    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found blog with id = " + id));
    }

    public Blog updateBlog(Integer id, UpsertBlogRequest request) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found blog with id = " + id));

        // TODO: Validate thông tin (nếu cần thiết) - validation

        // Tìm kiếm category
        List<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());

        Slugify slugify = Slugify.builder().build();
        blog.setTitle(request.getTitle());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setStatus(request.getStatus());
        blog.setThumbnail(request.getThumbnail());
        blog.setCategories(categories);

        return blogRepository.save(blog);
    }

    public void deleteBlog(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found blog with id = " + id));

        blogRepository.delete(blog);
    }
}
