package pl.misiurek.shop.admin.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.service.ProductService;

import java.util.UUID;

@Controller
@RequestMapping("/admin/products")
public class ProductsAdminViewController {
    private final ProductService productService;

    public ProductsAdminViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String indexView(Model model){
        model.addAttribute("products", productService.getProductsWithoutCategory());
        return "admin/products/index";
    }

    @GetMapping("{id}")
    public String editView(Model model, @PathVariable UUID id){
        model.addAttribute("product", productService.getProduct(id));
        return "admin/products/edit";
    }

    @PostMapping("{id}")
    public String edit(@ModelAttribute("product") Product product, @PathVariable UUID id){
        productService.updateProduct(id, product);
        return "redirect:/admin/products";
    }



    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable UUID id){
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("add")
    public String addView(Model model){
        model.addAttribute("product", new Product());

        return "admin/products/add";
    }

    @PostMapping
    public String add(UUID id , Product product){
        productService.createProduct(id, product);
        return "redirect:/admin/products";
    }

}


