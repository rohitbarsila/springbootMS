package com.edu.cart.Services;


import com.edu.cart.exceptions.ProductNotFoundException;
import com.edu.cart.DTOobjects.products.ProductDTO;
import com.edu.cart.model.Cart;

import java.util.List;

public interface CartService {
    Long add(Long idCart, List<ProductDTO> Product) throws ProductNotFoundException;

    Long save(Cart cart);

    Cart cartItem(Long idCart) throws ProductNotFoundException;
}
