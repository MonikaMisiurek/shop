package pl.misiurek.shop.admin.category.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.misiurek.shop.admin.category.domian.model.Category;
import pl.misiurek.shop.admin.category.service.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/category")
public class CategoryApiController {

    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    Page<Category> getCategories(Pageable pageable){
        return categoryService.getCategories(pageable);
    }
    @GetMapping("{id}")
    Category getCategory(@PathVariable UUID id){
        return categoryService.getCategory(id);
    }
    @PostMapping
    Category createCategory(@RequestBody Category category ){
        return categoryService.createCategory(category);
    }
    @PutMapping("{id}")
    Category updateCategory(@PathVariable UUID id, @RequestBody Category category){
        return categoryService.updateCategory(id,category);
    }
    @DeleteMapping("{id}")
    void deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
    }

}
