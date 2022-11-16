package pl.misiurek.shop.admin.product.service;

import org.springframework.stereotype.Service;
import pl.misiurek.shop.admin.category.domian.model.Category;
import pl.misiurek.shop.admin.category.domian.repository.CategoryRepository;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.domain.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts(UUID categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public Product getProduct(UUID productId) {
        return productRepository.findById(productId).get();
    }


    public Product createProduct(UUID categoryId, Product productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        Category category = categoryRepository.findById(categoryId).get();
        category.addProduct(product);

        productRepository.save(product);
        categoryRepository.save(category);

        return product;
    }

    public Product updateProduct(UUID productId, Product productRequest) {
        Product product = productRepository.findById(productId).get();
        product.setName(productRequest.getName());
        return productRepository.save(product);
    }

    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> findAllByCategoryId(UUID id) {
        return productRepository.findAllByCategoryId(id);
    }
}
