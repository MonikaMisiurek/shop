package pl.misiurek.shop.admin.category.domian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.misiurek.shop.admin.category.domian.model.Category;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository <Category, UUID>{
}
