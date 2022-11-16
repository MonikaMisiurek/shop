package pl.misiurek.shop.admin.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.misiurek.shop.admin.product.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategoryId(UUID categoryId);
}
