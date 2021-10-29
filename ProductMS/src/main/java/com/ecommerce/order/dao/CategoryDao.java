package com.ecommerce.order.dao;

import com.ecommerce.order.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
    Optional<Category> findBydescription(String Category);
    Category findByIdcategory(int i);
}
