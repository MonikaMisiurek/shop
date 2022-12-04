package pl.misiurek.shop.admin.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductRequest {

    private String name;
    private Double price;

}
