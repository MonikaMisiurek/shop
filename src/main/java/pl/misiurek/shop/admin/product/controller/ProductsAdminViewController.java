package pl.misiurek.shop.admin.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.domain.model.ProductRequest;
import pl.misiurek.shop.admin.product.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductsAdminViewController {
    private final ProductService productService;

    @GetMapping
    public String indexView(
            Pageable pageable,
            @RequestParam(name = "s", required = false) String search,
            Model model) {
        Page<Product> productPage = productService.getProductsWithoutCategory(pageable, search);
        model.addAttribute("productsPage", productPage);
        model.addAttribute("search", search);
        paging(model, productPage);
        return "admin/products/index";
    }

    @GetMapping("{id}")
    public String editView(Model model, @PathVariable UUID id) {
        model.addAttribute("product", productService.getProduct(id));
        return "admin/products/edit";
    }


    @PostMapping("{id}")
    public String edit(
            @Valid @ModelAttribute("product") ProductRequest productRequest,
            BindingResult bindingResult,
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes,
            Model model

    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", productRequest);
            model.addAttribute("message", "Błąd wpisu");
            return "admin/products/edit";
        }
        try {
            productService.updateProduct(id, productRequest);
            redirectAttributes.addFlashAttribute("message", "Produkt zapisany");

        } catch (Exception e) {
            model.addAttribute("product", productRequest);
            model.addAttribute("message", "Nieznany zapis");

            return "admin/products/edit";
        }
        return "redirect:/admin/products";
    }

    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("add")
    public String addView(Model model) {
        model.addAttribute("product", new Product());

        return "admin/products/add";
    }

    @PostMapping
    public String add(ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return "redirect:/admin/products";
    }

    private void paging(Model model, Page page) {
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

}


