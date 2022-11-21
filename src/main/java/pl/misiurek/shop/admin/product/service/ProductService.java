package pl.misiurek.shop.admin.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public Page<Product> getProductsWithoutCategory(Pageable pageable){
        return getProductsWithoutCategory(pageable, null);
    }
    public Page<Product>getProductsWithoutCategory(Pageable pageable, String search){
        if (search == null){
            return productRepository.findAll(pageable);
        }else {
            return productRepository.findByNameContainingIgnoreCase(search, pageable);

        }
    }

    @Transactional(readOnly = true)
    public List<Product> getProducts(UUID categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    public Product getProduct(UUID productId) {
        return productRepository.findById(productId).get();
    }

    @Transactional
    public Product createProduct(UUID categoryId, Product productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        Category category = categoryRepository.findById(categoryId).get();
        category.addProduct(product);

        productRepository.save(product);
        categoryRepository.save(category);

        return product;
    }

    @Transactional
    public Product updateProduct(UUID productId, Product productRequest) {
        Product product = productRepository.findById(productId).get();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        return productRepository.save(product);
    }


    @Transactional
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByCategoryId(UUID id) {
        return productRepository.findAllByCategoryId(id);
    }
}
