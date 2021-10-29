package com.edu.cart.DTOobjects.user;

import com.edu.cart.exceptions.SessionExpired;

public interface UserClient {

    UserResponse user(UserDTO userDTO) throws SessionExpired;

}
