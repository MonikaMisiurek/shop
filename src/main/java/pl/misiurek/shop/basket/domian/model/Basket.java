package pl.misiurek.shop.basket.domian.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Basket {

    @Id
    private UUID id;

    private String name;

}
