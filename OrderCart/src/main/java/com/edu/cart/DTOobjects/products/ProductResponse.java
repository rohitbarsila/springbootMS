package com.edu.cart.DTOobjects.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String orderId;
    private String productId;
    private String status;
}
