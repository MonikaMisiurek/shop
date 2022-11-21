package pl.misiurek.shop.admin.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.misiurek.shop.admin.category.service.CategoryService;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.service.ProductService;

import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    private final ProductService productService;

    private final CategoryService categoryService;

    public ProductViewController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping
    public String indexView(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("products", productService.getProductsWithoutCategory());
        return "shop/index";
    }

    @GetMapping("{id}")
    public String singleProductView(Model model, @PathVariable UUID id){
        Product product = productService.getProduct(id);
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("product", product);
        return "shop/singleProduct";
    }

//    @GetMapping
//    public String indexView(Model model, @PathVariable ("category-id") UUID categoryId){
//        model.addAttribute("products", productService.getProducts(categoryId));
//        return "shop/index";
//    }


}
