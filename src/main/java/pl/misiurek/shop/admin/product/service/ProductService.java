package pl.misiurek.shop.admin.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.misiurek.shop.admin.product.domain.model.Product;
import pl.misiurek.shop.admin.product.domain.model.ProductRequest;
import pl.misiurek.shop.admin.product.domain.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Page<Product> getProductsWithoutCategory(Pageable pageable) {
        return getProductsWithoutCategory(pageable, null);
    }

    public Page<Product> getProductsWithoutCategory(Pageable pageable, String search) {
        if (search == null) {
            return productRepository.findAll(pageable);
        } else {
            return productRepository.findByNameContainingIgnoreCase(search, pageable);

        }
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsWithCategoryId(UUID categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }


    @Transactional(readOnly = true)
    public Product getProduct(UUID productId) {
        return productRepository.findById(productId).get();
    }


    @Transactional
    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
        return product;
    }

    @Transactional
    public Product updateProduct(UUID productId, ProductRequest productRequest) {
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
