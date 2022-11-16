package pl.misiurek.shop.admin.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories/{category-id}/products")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    List<Product> getProducts(@PathVariable("category-id")UUID categoryId){
        return productService.getProducts(categoryId);
    }
    @GetMapping("{product-id}")
    Product getProduct(@PathVariable("category-id")UUID categoryId,@PathVariable("product-id")UUID productId){
        return productService.getProduct(productId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Product createProduct(@PathVariable ("category-id")UUID categoryId,@RequestBody Product product){
        return productService.createProduct(categoryId, product);
    }

    @PutMapping("{product-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Product updateProduct(@PathVariable("category-id")UUID categoryId,
                          @PathVariable("product-id")UUID productId,
                          @RequestBody Product product){
        return productService.updateProduct(productId,product);
    }

    @DeleteMapping("{product-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void  deleteProduct(@PathVariable("product-id")UUID productId){
         productService.deleteProduct(productId);
    }



}
