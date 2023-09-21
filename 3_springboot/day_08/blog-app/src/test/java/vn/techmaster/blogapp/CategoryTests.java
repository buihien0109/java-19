package vn.techmaster.blogapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blogapp.model.dto.CategoryDto;
import vn.techmaster.blogapp.repository.CategoryRepository;

import java.util.List;

@SpringBootTest
public class CategoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void test_find_all_category_dto() {
        List<CategoryDto> categoryDtos = categoryRepository.getAllCategoryDtoNQ();
        categoryDtos.forEach(System.out::println);
    }
}
