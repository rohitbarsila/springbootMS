package com.edu.cart.Services;

import com.edu.cart.DTOobjects.user.UserDTO;
import com.edu.cart.exceptions.EntityAlreadyExists;
import com.edu.cart.exceptions.ProductNotFoundException;
import com.edu.cart.exceptions.SessionExpired;
import com.edu.cart.model.Order;

public interface OrderService {
    Long create(Long cart, UserDTO userDTO) throws SessionExpired, EntityAlreadyExists;

    Order get(Long orderId) throws ProductNotFoundException;
}
