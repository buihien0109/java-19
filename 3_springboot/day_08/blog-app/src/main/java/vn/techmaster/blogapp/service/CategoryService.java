package vn.techmaster.blogapp.service;

import org.springframework.stereotype.Service;
import vn.techmaster.blogapp.entity.Category;
import vn.techmaster.blogapp.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
