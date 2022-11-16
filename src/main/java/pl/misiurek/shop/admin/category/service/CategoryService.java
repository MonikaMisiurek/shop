package pl.misiurek.shop.admin.category.service;

import org.springframework.stereotype.Service;
import pl.misiurek.shop.admin.category.domian.model.Category;
import pl.misiurek.shop.admin.category.domian.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(UUID id) {
        return categoryRepository.findById(id).get();
    }

    public Category createCategory(Category categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(UUID id, Category categoryRequest) {
        Category category=categoryRepository.findById(id).get();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }


    public void deleteCategory(UUID id) {
         categoryRepository.deleteById(id);
    }
}
