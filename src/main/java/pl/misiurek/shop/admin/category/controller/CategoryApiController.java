package pl.misiurek.shop.admin.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.misiurek.shop.admin.category.domian.model.Category;
import pl.misiurek.shop.admin.category.domian.model.CategoryDto;
import pl.misiurek.shop.admin.category.service.CategoryService;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping
    Page<Category> getCategories(Pageable pageable) {
        return categoryService.getCategories(pageable);
    }

    @GetMapping("{id}")
    Category getCategory(@PathVariable UUID id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    Category createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategoryDto(categoryDto);
    }

    @PutMapping("{id}")
    Category updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("{id}")
    void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }
}
