package com.edu.cart.model;

import com.edu.cart.DTOobjects.products.ProductDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "cart")
public class Cart {

    private Long idcarts;
    private String customer;
    private List<ProductDTO> products;
    private Float subtotal;


    @Id
    @ApiModelProperty(access = "hidden", hidden = true)
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idcarts", unique = true, nullable = false, length = 100)
    public Long getIdcarts() {
        return idcarts;
    }

    public void setIdcarts(Long idcarts) {
        this.idcarts = idcarts;
    }

    @ApiModelProperty(access = "hidden", hidden = true)
    @Column(name = "customer", nullable = false, length = 100)
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name = "total", nullable = false)
    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    @Column(name = "products")
    @ElementCollection
    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

}
