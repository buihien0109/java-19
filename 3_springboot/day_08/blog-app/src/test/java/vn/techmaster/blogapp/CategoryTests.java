package vn.techmaster.blogapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blogapp.model.dto.CategoryDto;
import vn.techmaster.blogapp.repository.CategoryRepository;
import vn.techmaster.blogapp.repository.UserRepository;

import java.util.List;

@SpringBootTest
public class CategoryTests {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void test_find_all_category_dto() {
        List<CategoryDto> categoryDtos = categoryRepository.getAllCategoryDtoNQ();
        categoryDtos.forEach(System.out::println);
    }

    @Test
    void test_find_by_email_return_projection() {
        var user = userRepository.findByEmail("hien@gmail.com");
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getAvatar());
    }
}
