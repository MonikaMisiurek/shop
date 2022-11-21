package pl.misiurek.shop.admin.category.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.misiurek.shop.admin.category.domian.model.Category;
import pl.misiurek.shop.admin.category.service.CategoryService;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.service.ProductService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {

    private final CategoryService categoryService;
    private final ProductService productService;


    public CategoryViewController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @GetMapping("{id}")
    public String singleView(@PathVariable UUID id, Model model){
        Category category = categoryService.getCategory(id);
        List<Product> products = productService.findAllByCategoryId(id);

        model.addAttribute("categories", categoryService.getCategories(Pageable.unpaged()));
        model.addAttribute("category", category);
        model.addAttribute("products", products);

        return "shop/singleCategory";
    }


}
