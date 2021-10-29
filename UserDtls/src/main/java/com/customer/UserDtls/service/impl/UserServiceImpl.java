package com.customer.UserDtls.service.impl;

import com.customer.UserDtls.dao.UserDao;
import com.customer.UserDtls.exception.InvalidData;
import com.customer.UserDtls.model.UserData;
import com.customer.UserDtls.model.UserDto;
import com.customer.UserDtls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserData user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    public List<UserData> findAll() {
        List<UserData> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserData findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean findAtt(String username, String email, String phone) {
        System.out.println(userDao.findByUsername(username));
        return userDao.findByUsername(username) != null || userDao.findByUsername(email) != null || userDao.findByUsername(phone) != null;
    }

    @Override
    public UserDto findByUsername(String usernameFromToken) {
        UserData user = userDao.findByUsername(usernameFromToken);
        return new UserDto(user.getUsername(), user.getEmail(), user.getPhone(), user.getName(), user.getFull_address(), user.getZip_code());
    }

    @Override
    public UserData save(UserDto user) throws InvalidData {

        UserData nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        return userDao.save(nUser);
    }
}
