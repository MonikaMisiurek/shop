package pl.misiurek.shop.admin.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.domain.model.ProductRequest;
import pl.misiurek.shop.admin.product.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories/{category-id}/products")
public class ProductApiController {

    private final ProductService productService;

    @GetMapping
    List<Product> getProducts(@PathVariable("category-id") UUID categoryId) {
        return productService.getProductsWithCategoryId(categoryId);
    }

    @GetMapping("{product-id}")
    Product getProduct(@PathVariable("category-id") UUID categoryId, @PathVariable("product-id") UUID productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("{product-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Product updateProduct(@PathVariable("product-id") UUID productId,
                          @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }


    @DeleteMapping("{product-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable("product-id") UUID productId) {
        productService.deleteProduct(productId);
    }


}
