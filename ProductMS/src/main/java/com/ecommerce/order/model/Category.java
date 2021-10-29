package com.ecommerce.order.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "categories")
public class Category implements java.io.Serializable {

    private Integer idCategory;
    private String description;

    public Category() {
    }

    public Category(String description) {
        this.description = description;
    }

    public Category(String description, List<Products> products) {
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idcategory", unique = true, nullable = false)
    public Integer getIdcategory() {
        return this.idCategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idCategory = idcategory;
    }
    @ApiModelProperty(hidden = true)
    @Column(name = "description", unique = true, nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
