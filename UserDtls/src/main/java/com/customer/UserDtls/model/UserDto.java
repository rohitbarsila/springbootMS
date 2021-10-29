package com.customer.UserDtls.model;

import javax.persistence.Column;

public class UserDto {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String full_address;
    private String zip_code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public UserData getUserFromDto() {
        UserData user = new UserData();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        user.setFull_address(full_address);
        user.setZip_code(zip_code);

        return user;
    }

    public UserDto(String username, String email, String phone, String name, String full_address, String zip_code) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.full_address = full_address;
        this.zip_code = zip_code;
    }
}