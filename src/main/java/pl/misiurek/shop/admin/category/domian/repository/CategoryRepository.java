package pl.misiurek.shop.admin.category.domian.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.misiurek.shop.admin.category.domian.model.Category;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository <Category, UUID>{

    Page<Category> findByNameContainingIgnoreCase(String search, Pageable pageable);
}
