package com.edu.cart.Services;

import com.edu.cart.DTOobjects.products.ProductClient;
import com.edu.cart.DTOobjects.user.UserClient;
import com.edu.cart.DTOobjects.user.UserDTO;
import com.edu.cart.DTOobjects.user.UserResponse;
import com.edu.cart.dao.CartDao;
import com.edu.cart.dao.OrderDao;
import com.edu.cart.exceptions.EntityAlreadyExists;
import com.edu.cart.exceptions.ProductNotFoundException;
import com.edu.cart.exceptions.SessionExpired;
import com.edu.cart.model.Cart;
import com.edu.cart.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository

public class OrderServiceImp implements OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    CartDao cartDao;
    @Autowired
    UserClient userClient;
    @Autowired
    ProductClient productClient;

    @Override
    public Long create(Long cart, UserDTO userDTO) throws SessionExpired, EntityAlreadyExists {
        Order created_order;
        Cart getcart = cartDao.getByidcarts(cart);
        UserResponse user = userClient.user(userDTO);
        Order order = new Order();
        order.setCartid(cart);
        order.setCarts(getcart);
        order.setPayment("COD");
        order.setUserDTLS(user);
        try {
        created_order = orderDao.save(order);
        productClient.changeQuantity(getcart.getProducts());
        } catch (DataIntegrityViolationException exception) {
            throw new EntityAlreadyExists();
        }
        return created_order.getOrderId();
    }

    @Override
    public Order get(Long orderId) throws ProductNotFoundException {
        Order order = orderDao.getOrderByOrderId(orderId);
        if (order == null)
            throw new ProductNotFoundException();
        return order;
    }
}
