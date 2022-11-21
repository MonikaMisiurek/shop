package pl.misiurek.shop.admin.product.domain.model;

import lombok.Data;
import pl.misiurek.shop.admin.category.domian.model.Category;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    private UUID id;
    private String name;
    private Double price;

    @ManyToOne
    private Category category;

    public Product() {
        this.id = UUID.randomUUID();
    }

    public Product(UUID id, String name, Double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
