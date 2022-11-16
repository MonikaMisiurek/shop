package pl.misiurek.shop.admin.category.domian.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.misiurek.shop.admin.product.domain.model.Product;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name= "categories")
public class Category {
    @Id
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
        this.id=UUID.randomUUID();
    }

    public Category(String name) {
        this.name = name;
        this.id=UUID.randomUUID();
    }

    public Category addProduct(Product product) {
        if(product == null){
            products = new LinkedHashSet<>();
        }
        product.setCategory(this);
        products.add(product);
        return this;

    }
}
