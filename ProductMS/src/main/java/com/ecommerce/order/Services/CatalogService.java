package com.ecommerce.order.Services;

import com.ecommerce.order.exceptions.EntityAlreadyExists;
import com.ecommerce.order.exceptions.ProductNotFoundException;
import com.ecommerce.order.model.Category;
import com.ecommerce.order.model.Products;


import java.util.List;

public interface CatalogService {

    List<Products> findAll() throws ProductNotFoundException;

    Products findById(Long idProduct) throws ProductNotFoundException;

    List<Products> findByCategory(Integer category) throws ProductNotFoundException;

    Long createCategory(String discription) throws EntityAlreadyExists;

    Long createProducts(Products product) throws EntityAlreadyExists;

    List<Category> findAllcategories() throws ProductNotFoundException;

    Boolean updateQuantity(List<Products> product);
}
