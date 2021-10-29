package com.ecommerce.order.dao;

import com.ecommerce.order.model.Category;
import com.ecommerce.order.model.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Products, Long> {
    List<Products> findByCategory(Optional<Category> category);

    List<Products> findByCategory_Idcategory(Integer category);

    List<Products> findByCategory_description(String category);

    Products findByidProduct(Long idProduct);
}
