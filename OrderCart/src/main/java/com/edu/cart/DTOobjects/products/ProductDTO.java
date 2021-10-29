package com.edu.cart.DTOobjects.products;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductDTO {

    private Integer idProduct;
    private String description;
    private Float price;
    private Long stock;

}
