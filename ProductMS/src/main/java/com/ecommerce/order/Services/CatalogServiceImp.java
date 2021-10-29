package com.ecommerce.order.Services;

import com.ecommerce.order.dao.CategoryDao;
import com.ecommerce.order.dao.ProductDao;
import com.ecommerce.order.exceptions.EntityAlreadyExists;
import com.ecommerce.order.exceptions.ProductNotFoundException;
import com.ecommerce.order.model.Category;
import com.ecommerce.order.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CatalogServiceImp implements CatalogService {

    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Products> findAll() throws ProductNotFoundException {
        List<Products> products = productDao.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        } else
            return products;
    }

    @Override
    public Products findById(Long idProduct) throws ProductNotFoundException {
        Products products = productDao.findByidProduct(idProduct);
        if (products.getIdProduct() == null) {
            throw new ProductNotFoundException();
        } else
            return products;
    }

    @Override
    public List<Products> findByCategory(Integer category) throws ProductNotFoundException {
        List<Products> products = productDao.findByCategory_Idcategory(category);
        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        } else
            return products;
    }

    @Override
    public Long createCategory(String discription) throws EntityAlreadyExists {
        if (categoryDao.findBydescription(discription).isPresent())
            throw new EntityAlreadyExists();
        Category cat = new Category();
        cat.setDescription(discription);
        categoryDao.save(cat);
        return Long.valueOf(cat.getIdcategory());
    }

    @Override
    public Long createProducts(Products product) throws EntityAlreadyExists {
        if (productDao.findByidProduct(product.getIdProduct()) != null)
            throw new EntityAlreadyExists();
        productDao.save(product);
        return Long.valueOf(product.getIdProduct());
    }

    @Override
    public List<Category> findAllcategories() throws ProductNotFoundException {
        List<Category> categories = categoryDao.findAll();
        if (categories.isEmpty()) {
            throw new ProductNotFoundException();
        } else
            return categories;
    }

    @Override
    public Boolean updateQuantity(List<Products> products) {
        for (Products product : products) {
            Products one_product = productDao.findByidProduct(product.getIdProduct());
            one_product.setStock(one_product.getStock() - product.getStock());
            productDao.saveAndFlush(one_product);
        }
        return Boolean.TRUE;
    }
}


