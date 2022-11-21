package pl.misiurek.shop.admin.product.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.misiurek.shop.admin.category.domian.model.Category;
import pl.misiurek.shop.admin.product.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategoryId(UUID categoryId);

    Page<Product> findByNameContainingIgnoreCase(String search, Pageable pageable);
}
