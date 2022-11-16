package pl.misiurek.shop.admin.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.misiurek.shop.admin.product.service.ProductService;

import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String indexView(Model model, @PathVariable ("category-id") UUID categoryId){
        model.addAttribute("products", productService.getProducts(categoryId));
        return "shop/index";
    }
}
