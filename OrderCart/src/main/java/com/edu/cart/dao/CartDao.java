package com.edu.cart.dao;

import com.edu.cart.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends CrudRepository<Cart, Long> {
    Cart getByidcarts(Long idCart);
}
