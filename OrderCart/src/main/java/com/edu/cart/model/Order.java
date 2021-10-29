package com.edu.cart.model;

import com.edu.cart.DTOobjects.user.UserDTO;
import com.edu.cart.DTOobjects.user.UserResponse;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ordersData")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(access = "hidden", hidden = true)
    @Column(name = "orderid", unique = true, nullable = false, length = 100)
    private Long orderId;

    @Column(name = "cartData", nullable = false, unique = true)
    private Long cartid;

    @Column(name = "userData", nullable = false, unique = true)
    private UserResponse userDTLS;

    @Column(name = "payment", nullable = false, length = 100)
    private String payment;

    @OneToOne
    @JoinColumn(name = "cart", referencedColumnName = "idcarts" ,nullable = true)
    private Cart carts;

    public UserResponse getUserDTLS() {
        return userDTLS;
    }

    public void setUserDTLS(UserResponse userDTLS) {
        this.userDTLS = userDTLS;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Long getCartid() {
        return cartid;
    }

    public void setCartid(Long cart) {
        this.cartid = cart;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Cart getCarts() {
        return carts;
    }

    public void setCarts(Cart carts) {
        this.carts = carts;
    }
}
