package vn.techmaster.blogapp;

import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blogapp.entity.*;
import vn.techmaster.blogapp.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class BlogAppApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void save_roles() {
        List<Role> roles = List.of(
                new Role(null, "ADMIN"),
                new Role(null, "USER"),
                new Role(null, "AUTHOR")
        );

        roleRepository.saveAll(roles);
    }

    @Test
    void save_users() {
        Role userRole = roleRepository.findByName("USER").orElse(null);
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        Role authorRole = roleRepository.findByName("AUTHOR").orElse(null);

        List<User> users = List.of(
                new User(null, "Bùi Hiên", "hien@gmail.com", "111", null, List.of(adminRole, userRole)),
                new User(null, "Minh Duy", "duy@gmail.com", "111", null, List.of(userRole)),
                new User(null, "Thu Hằng", "hang@gmail.com","111", null, List.of(authorRole, userRole))
        );

        userRepository.saveAll(users);
    }

    @Test
    void save_categories() {
        List<Category> categories = List.of(
                new Category(null, "Backend"),
                new Category(null, "Frontend"),
                new Category(null, "Devops"),
                new Category(null, "Database"),
                new Category(null, "Mobile"),
                new Category(null, "Javascript"),
                new Category(null, "Java"),
                new Category(null, "Game")
        );
        categoryRepository.saveAll(categories);
    }

    @Test
    void save_blogs() {
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        List<User> userList = userRepository.findByRoles_NameIn(List.of("ADMIN", "AUTHOR"));
        List<Category> categoryList = categoryRepository.findAll();

        for (int i = 0; i < 25; i++) {
            // Random 1 user
            User rdUser = userList.get(rd.nextInt(userList.size()));

            // Random 1 ds category tuong ung
            List<Category> rdList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Category rdCategory = categoryList.get(rd.nextInt(categoryList.size()));
                if(!rdList.contains(rdCategory)) {
                    rdList.add(rdCategory);
                }
            }

            // Tao blog
            Blog blog = Blog.builder()
                    .title("Blog " + (i + 1))
                    .slug(slugify.slugify("Blog " + (i + 1)))
                    .description("description " + (i + 1))
                    .content("content " + (i + 1))
                    .status(rd.nextInt(2) == 0)
                    .user(rdUser)
                    .categories(rdList)
                    .build();

            blogRepository.save(blog);
        }
    }

    @Test
    void save_comments() {
        Random rd = new Random();
        List<User> userList = userRepository.findAll();
        List<Blog> blogList = blogRepository.findAll();

        for (int i = 0; i < 100; i++) {
            // Random 1 user
            User rdUser = userList.get(rd.nextInt(userList.size()));

            // Random 1 blog
            Blog rdBlog = blogList.get(rd.nextInt(blogList.size()));

            // Tao comment
            Comment comment = Comment.builder()
                    .content("comment " + (i + 1))
                    .user(rdUser)
                    .blog(rdBlog)
                    .build();

            commentRepository.save(comment);
        }
    }

}
