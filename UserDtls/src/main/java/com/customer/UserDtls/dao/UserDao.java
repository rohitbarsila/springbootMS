package com.customer.UserDtls.dao;

import com.customer.UserDtls.model.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserData, Long> {
    UserData findByUsername(String username);
}