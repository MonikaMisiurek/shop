package pl.misiurek.shop.admin.category.domian.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.misiurek.shop.admin.product.domain.model.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private UUID id;

    @NotBlank(message = " Name can't be empty")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private Set<Product> products;

    public Category() {
        this.id = UUID.randomUUID();
    }

    public Category(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public Category addProduct(Product product) {
        if (product == null) {
            products = new LinkedHashSet<>();
        }
        product.setCategory(this);
        products.add(product);
        return this;

    }
}
