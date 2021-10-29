package com.edu.cart.Services;

import com.edu.cart.DTOobjects.products.ProductClient;
import com.edu.cart.DTOobjects.user.UserClient;
import com.edu.cart.dao.CartDao;
import com.edu.cart.exceptions.ProductNotFoundException;
import com.edu.cart.DTOobjects.products.ProductDTO;
import com.edu.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartServiceImp implements CartService {
    @Autowired
    CartDao cartDao;
    @Autowired
    UserClient userClient;
    @Autowired
    ProductClient productClient;

    @Override
    public Long add(Long idCart, List<ProductDTO> Products) throws ProductNotFoundException {
        Cart cart = cartDao.getByidcarts(idCart);
        if (cart == null) {
            throw new ProductNotFoundException();
        }
        float total = cart.getSubtotal();
        for (ProductDTO product : Products) {
            cart.getProducts().add(product);
            total += product.getPrice();
        }
        cart.setSubtotal(total);
        save(cart);
        return cart.getIdcarts();
    }

    @Override
    public Long save(Cart cart) {
        cartDao.save(cart);
        return cart.getIdcarts();
    }

    @Override
    public Cart cartItem(Long idCart) throws ProductNotFoundException {
        Cart cart = cartDao.getByidcarts(idCart);
        if (cart == null) {
            throw new ProductNotFoundException();
        } else
            return cart;
    }
}
