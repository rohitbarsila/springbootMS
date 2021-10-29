package com.ecommerce.order.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")//, catalog = "shopping_cart_db")
public class Products implements java.io.Serializable {

    private Long idProduct;
    private Category category;
    private String description;
    private Float price;
    private Long stock;

    public Products() {
    }

    public Products(String description, Float price, Long stock) {
        this.description = description;
        this.price = price;
        this.stock = stock;

    }

    public Products(Category category, String description, Float price, Long stock) {
        this.category = category;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idproduct", unique = true, nullable = false)
    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "idcategory")
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "description", nullable = false, length = 100)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price", nullable = false, precision = 10)
    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Column(name = "stock", nullable = false, length = 100)
    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
