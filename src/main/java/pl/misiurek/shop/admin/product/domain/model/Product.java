package pl.misiurek.shop.admin.product.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import pl.misiurek.shop.admin.category.domian.model.Category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    private UUID id;
    @NotBlank(message = " Name can't be empty")
    @Column(name = "name")
    private String name;
    @NotNull(message = "Can not be empty")
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JsonManagedReference
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
