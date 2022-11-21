package pl.misiurek.shop.admin.product.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.service.ProductService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/products")
public class ProductsAdminViewController {
    private final ProductService productService;

    public ProductsAdminViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String indexView(
            Pageable pageable,
            @RequestParam(name = "s", required = false) String search,
            Model model){
        Page<Product>productPage = productService.getProductsWithoutCategory(pageable,search);
        model.addAttribute("productsPage", productPage);
        model.addAttribute("search", search);
        paging (model, productPage);
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
    public String add(UUID categoryId , Product product){
        productService.createProduct(categoryId, product);
        return "redirect:/admin/products";
    }
    private void paging(Model model, Page page){
        int totalPages = page.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

}


