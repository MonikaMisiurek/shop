package pl.misiurek.shop.admin.category.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public Page<Category> getCategories(Pageable pageable) {
        return getCategories(pageable,null);
    }
    @Transactional(readOnly = true)
    public Page<Category> getCategories(Pageable pageable, String search) {
        if(search == null) {
            return categoryRepository.findAll(pageable);
        }else {
            return categoryRepository.findByNameContainingIgnoreCase(search, pageable);
        }
    }

    @Transactional(readOnly = true)
    public Category getCategory(UUID id) {
        return categoryRepository.findById(id).get();
    }

    @Transactional
    public Category createCategory(Category categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(UUID id, Category categoryRequest) {
        Category category=categoryRepository.findById(id).get();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(UUID id) {
         categoryRepository.deleteById(id);
    }
}
