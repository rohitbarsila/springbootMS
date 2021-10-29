package com.ecommerce.order.controller;

import com.ecommerce.order.Services.CatalogService;
import com.ecommerce.order.exceptions.EntityAlreadyExists;
import com.ecommerce.order.exceptions.ProductNotFoundException;
import com.ecommerce.order.model.Category;
import com.ecommerce.order.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    CatalogService productService;

    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Products>> getProducts() throws ProductNotFoundException {
        List<Products> products = productService.findAll();
        return new ResponseEntity<List<Products>>(products, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/api/products/{idProduct}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Products> getBy(@PathVariable("idProduct") Long idProduct) throws ProductNotFoundException {
        Products products = productService.findById(idProduct);
        return new ResponseEntity<Products>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/products-by-category", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Products>> getByCategory(@RequestParam("category") Long category) throws ProductNotFoundException {
        List<Products> products = productService.findByCategory(Math.toIntExact(category));
        return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/create-category", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Void> createCategory(@RequestParam("discription") String discription, HttpServletRequest request) throws URISyntaxException, EntityAlreadyExists {
        Long id = productService.createCategory(discription);
        HttpHeaders header = new HttpHeaders();
        header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
        return new ResponseEntity<Void>(header, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/create-product", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Void> createProducts(@RequestBody Products product, HttpServletRequest request) throws URISyntaxException, EntityAlreadyExists {
        Long id = productService.createProducts(product);
        HttpHeaders header = new HttpHeaders();
        header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
        return new ResponseEntity<Void>(header, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/category", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Category>> getCategory() throws ProductNotFoundException {
        List<Category> category = productService.findAllcategories();
        return new ResponseEntity<List<Category>>(category, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/api/updateQnty", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Void> updateQnty(@RequestBody List<Products> product, HttpServletRequest request) throws URISyntaxException, EntityAlreadyExists {
        productService.updateQuantity(product);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
